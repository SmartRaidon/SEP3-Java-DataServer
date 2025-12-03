package dk.via.dataserver.services;

import dk.via.dataserver.entity.GameResult;
import dk.via.dataserver.gRPC.Sep3;
import dk.via.dataserver.repository.GameResultRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameResultServiceDatabase implements GameResultService{

    private final GameResultRepository gameResultRepository;

    public GameResultServiceDatabase(GameResultRepository gameResultRepository) {
        this.gameResultRepository = gameResultRepository;
    }

    @Transactional
    public Sep3.GameResultProto create(Sep3.GameResultProto payload) {
        GameResult gameResult = new GameResult();
        gameResult.setGameId(payload.getGameId());
        gameResult.setWinnerId(payload.getWinnerId());
        gameResult.setLooserId(payload.getLooserId());
        gameResult.setDraw(payload.getIsDraw());

        GameResult savedGameResult = gameResultRepository.save(gameResult);

        return Sep3.GameResultProto.newBuilder()
                .setId(savedGameResult.getId())
                .setGameId(savedGameResult.getGameId())
                .setWinnerId(savedGameResult.getWinnerId()) //needed?
                .setLooserId(savedGameResult.getLooserId()) //needed?
                .setIsDraw(savedGameResult.getDraw()).build();
    }

    @Transactional
    public Sep3.GameResultProto update(Sep3.GameResultProto payload) {
        GameResult existingGameResult = gameResultRepository.findByGameId(
                payload.getGameId()).orElseThrow( () -> new RuntimeException("Game not found"));

        existingGameResult.setGameId(payload.getGameId());
        existingGameResult.setWinnerId(payload.getWinnerId());
        existingGameResult.setLooserId(payload.getLooserId());
        existingGameResult.setDraw(payload.getIsDraw());

        GameResult updatedGameResult = gameResultRepository.save(existingGameResult);

        return Sep3.GameResultProto.newBuilder()
                .setId(updatedGameResult.getId())
                .setGameId(updatedGameResult.getGameId())
                .setWinnerId(updatedGameResult.getWinnerId())
                .setLooserId(updatedGameResult.getLooserId())
                .setIsDraw(updatedGameResult.getDraw()).build();
    }

    @Override
    public Sep3.GameResultProto getByGameId(int gameId) throws Exception {
        GameResult gameById = gameResultRepository.findByGameId(gameId)
                .orElseThrow( () -> new Exception("Game result with game id " + gameId + " not found"));

        return Sep3.GameResultProto.newBuilder()
                .setGameId(gameById.getGameId())
                .setWinnerId(gameById.getWinnerId())
                .setLooserId(gameById.getLooserId())
                .setIsDraw(gameById.getDraw()).build();
    }

    @Override
    public void delete(int id) {
        gameResultRepository.findByGameId(id);
    }

    @Override
    public Sep3.GameResultListProto getAll() {
        List<GameResult> gameResults = gameResultRepository.findAll();

        Sep3.GameResultListProto.Builder builder = Sep3.GameResultListProto.newBuilder();

        gameResults.forEach(gr -> builder.addGameResults(
                Sep3.GameResultProto.newBuilder()
                        .setId(gr.getId())
                        .setGameId(gr.getGameId())
                        .setWinnerId(gr.getWinnerId())
                        .setLooserId(gr.getLooserId())
                        .setIsDraw(gr.getDraw())
                        .build()
        ));

        return builder.build();
    }

}

