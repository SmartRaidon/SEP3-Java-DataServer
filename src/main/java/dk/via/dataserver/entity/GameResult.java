package dk.via.dataserver.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "game_results", schema = "sep3")
public class GameResult {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  @Column(name = "gameID", unique = true, nullable = false)
  Integer gameId;
  @Column(name = "winnerID", unique = true, nullable = false)
  Integer winnerId;
  @Column(name = "looserID", unique = true, nullable = false)
  Integer looserId;
  @Column(name = "isDraw", unique = true, nullable = false)
  Boolean isDraw;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDraw() {
        return isDraw;
    }

    public void setDraw(Boolean draw) {
        isDraw = draw;
    }

    public Integer getLooserId() {
        return looserId;
    }

    public void setLooserId(Integer looserId) {
        this.looserId = looserId;
    }

    public Integer getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Integer winnerId) {
        this.winnerId = winnerId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}
