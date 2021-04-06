package br.com.sra.resource;

import br.com.sra.domain.User;
import br.com.sra.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller used to get login stuff
 */
@RestController
@RequestMapping(value = "/login")
public class LoginResource {


    @Autowired
    private UserService service;

    /**
     * Returns a fake token for front-end usage (this is not being encrypted)
     * @param user user object with username and password
     * @return String of the token
     */
    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<String> login (@Valid @RequestBody User user){
        try {
            User dataBaseUser = service.findByName(user.getUsername());
            if (dataBaseUser != null
                && dataBaseUser.getUsername()
                    .equals(user.getUsername())
                    && user
                    .getPassword()
                    .equals(dataBaseUser.getPassword())) {

                return new ResponseEntity<>("chega mais meu bom", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
