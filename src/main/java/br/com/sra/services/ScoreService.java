package br.com.sra.services;


import br.com.sra.domain.Score;
import br.com.sra.dto.ScoreDTO;
import br.com.sra.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository repo;

    /**
     * Insert a new score
     * @param score score to be inserted
     * @return Score inserted with id
     */
    public Score insert(Score score) {
        return repo.save(score);
    }


    /**
     * Find all scores
     * @return List<ScoreDTO>
     */
    public List<ScoreDTO> findAllScores(){
        return  repo.findAllScores();
    }

    /**
     * Find all scores of a user
     * @param username username of a user linked with that scores
     * @return List<ScoreDTO>
     */
    public List<ScoreDTO> findAllByUsername(String username){
        return  repo.findAllByUsername(username);
    }
}
