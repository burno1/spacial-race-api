package br.com.sra.dto;


/**
 * DTO of Score domain
 */
public class ScoreDTO {
    private Integer id;
    private String username;
    private Integer points;
    private Integer stars;

    public ScoreDTO(Integer id, String username, Integer points, Integer stars) {
        this.id = id;
        this.username = username;
        this.points = points;
        this.stars = stars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
