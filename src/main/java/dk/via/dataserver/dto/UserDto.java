package dk.via.dataserver.dto;

public class UserDto {
    private int id;
    private String username;
    private String email;
    private double score;

    public UserDto() {
    }

    public UserDto(int id, String username, String email, double score) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
