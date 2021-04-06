package br.com.sra.resource;

import br.com.sra.domain.Score;
import br.com.sra.dto.ScoreDTO;
import br.com.sra.services.ScoreService;
import br.com.sra.services.UserService;
import br.com.sra.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller used to do scores stuff
 */
@RestController
@RequestMapping(value = "/scores")
public class ScoreResource {

    @Autowired
    private ScoreService service;

    @Autowired
    private UserService userService;


    /**
     * Get all scores, depending on the username passed
     *
     * @param username username of the user that you want to find the scores, not mandatory
     * @return List of ScoreDTO
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> find(@RequestParam(defaultValue = "") String username) {
        try {
            List<ScoreDTO> list = new ArrayList<>();
            if (!Objects.isNull(username) && !username.isEmpty()) {
                list = service.findAllByUsername(username);
            } else {
                list = service.findAllScores();
            }

            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add a new score related to user
     *
     * @param score the Score object to be inserted on database
     * @return HTTPStatus
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@Valid @RequestBody ScoreDTO score) {
        try {
            if (Objects.isNull(score.getUsername())) {
                return new ResponseEntity<>("Provide the username",
                        HttpStatus.BAD_REQUEST);
            }

            service.insert(new Score(score.getPoints(),
                    score.getStars(),
                    userService.findByName(score.getUsername())
            ));

            return new ResponseEntity<>(HttpStatus.CREATED.getReasonPhrase(),
                    HttpStatus.CREATED);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
