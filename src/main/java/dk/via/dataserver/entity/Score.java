package dk.via.dataserver.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="score", schema ="sep3" )
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name ="score")
    Double score;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }

}