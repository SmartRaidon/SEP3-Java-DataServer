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
    public Iterable<Sep3.GameResultProto> getAll() {

        List<GameResult> gameResults = gameResultRepository.findAll();

        Iterable<Sep3.GameResultProto> gameResultProto;

        gameResultProto = gameResults.stream().map(
                gameResult -> Sep3.GameResultProto.newBuilder()
                .setId(gameResult.getId())
                .setGameId(gameResult.getGameId())
                .setWinnerId(gameResult.getWinnerId())
                .setLooserId(gameResult.getLooserId())
                .setIsDraw(gameResult.getDraw()).build()).toList();
        return gameResultProto;
    }
}
