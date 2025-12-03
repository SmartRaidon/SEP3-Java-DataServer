package dk.via.dataserver.services;

import dk.via.dataserver.entity.Score;
import dk.via.dataserver.gRPC.Sep3;
import dk.via.dataserver.repository.ScoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceDatabase implements ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreServiceDatabase(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    @Transactional
    public Sep3.ScoreProto create(Sep3.ScoreProto payload) {
        Score score = new Score();
        score.setScore(payload.getScore());

        Score saved = scoreRepository.save(score);

        return Sep3.ScoreProto.newBuilder()
                .setId(saved.getId())
                .setScore(saved.getScore())
                .build();
    }

    @Override
    @Transactional
    public Sep3.ScoreProto update(Sep3.ScoreProto payload) {
        Score existing = scoreRepository.findById(payload.getId())
                .orElseThrow(() -> new RuntimeException("Score not found"));

        existing.setScore(payload.getScore());

        Score saved = scoreRepository.save(existing);

        return Sep3.ScoreProto.newBuilder()
                .setId(saved.getId())
                .setScore(saved.getScore())
                .build();
    }

    @Override
    public Sep3.ScoreProto getSingle(int id) throws Exception {
        Score score = scoreRepository.findById(id)
                .orElseThrow(() -> new Exception("Score with given id does not exist " + id));

        return Sep3.ScoreProto.newBuilder()
                .setId(score.getId())
                .setScore(score.getScore())
                .build();
    }

    @Override
    public void delete(int id) {
        scoreRepository.deleteById(id);
    }

    @Override
    public Iterable<Sep3.ScoreProto> getAll() {
        List<Score> scores = scoreRepository.findAll();

        return scores.stream()
                .map(score -> Sep3.ScoreProto.newBuilder()
                        .setId(score.getId())
                        .setScore(score.getScore())
                        .build()
                )
                .toList();
    }
}
