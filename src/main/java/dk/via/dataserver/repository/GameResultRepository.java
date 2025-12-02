package dk.via.dataserver.repository;

import dk.via.dataserver.entity.GameResult;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameResultRepository extends JpaRepository<GameResult, Integer> {
    Optional<GameResult> findByGameId(Integer gameId);
    Optional<GameResult> findByWinnerId(Integer winnerId);
    Optional<GameResult> findByLooserId(Integer looserId);
}
