package br.com.sra.repository;

import br.com.sra.domain.Score;
import br.com.sra.dto.ScoreDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Integer> {

    /**
     * Return the list of DTO from Score, for all users
     * @return List<ScoreDTO>
     */
    @Query("SELECT NEW br.com.sra.dto.ScoreDTO" +
            "   (s.id," +
            "   s.user.username, " +
            "   s.points, " +
            "   s.stars) " +
            "From Score s " +
            "JOIN s.user user " +
            "ORDER BY s.points DESC")
    List<ScoreDTO> findAllScores();

    /**
     * Return the list of DTO from Score, given the username
     * @param username username from the user you want to find the scores
     * @return List<ScoreDTO>
     */
    @Query("SELECT NEW br.com.sra.dto.ScoreDTO" +
            "   (s.id," +
            "   s.user.username, " +
            "   s.points, " +
            "   s.stars) " +
            "FROM Score s " +
            "JOIN s.user user " +
            "WHERE s.user.username= ?1 " +
            "ORDER BY s.points DESC ")
    List<ScoreDTO> findAllByUsername(String username);
}
