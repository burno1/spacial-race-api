package br.com.sra.domain;


import javax.persistence.*;
import java.util.List;


/**
 * @author Bruno Fernandes
 */
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Score> score;

    private String name;

    private String lastname;

    @Column(unique = true)
    private String username;

    private String password;

    public User(List<Score> score, String name, String lastname, String username, String password) {
        this.score = score;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public void addToScore(Score score){
        score.setUser(this);
        this.score.add(score);
    }

    public List<Score> getScore() {
        return score;
    }

    public void setScore(List<Score> score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String sobrenome) {
        this.lastname = sobrenome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
