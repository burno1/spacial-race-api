package br.com.sra.resource;

import br.com.sra.domain.User;
import br.com.sra.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Controller used to do users stuff
 */
@RestController
@RequestMapping(value = "/users")
public class UserResource {


    @Autowired
    private UserService service;


    /**
     * Insert a new user into the database
     * @param user user to be inserted
     * @return Http status
     */
    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<?> insert (@Valid @RequestBody User user){

        if(user.getUsername() == null || user.getPassword() == null){
            return new ResponseEntity<>("Add a username and password to the user",
                                        HttpStatus.BAD_REQUEST);
        }

        User userToPersist = new User(new ArrayList<>(),
                                    user.getName(),
                                    user.getLastname(),
                                    user.getUsername(),
                                    user.getPassword());

        if(user.getScore() != null) user.getScore().forEach(userToPersist::addToScore);

        userToPersist = service.insert(userToPersist);

        return new ResponseEntity<>(HttpStatus.CREATED.getReasonPhrase(),HttpStatus.CREATED);
    }


}
