package dk.via.dataserver.dto;

public class ScoreDto {
    private int id;
    private Double score;

    public ScoreDto() {
    }

    public ScoreDto(int id, double score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
