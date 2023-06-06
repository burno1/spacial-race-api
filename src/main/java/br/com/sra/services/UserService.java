package br.com.sra.services;


import br.com.sra.domain.User;
import br.com.sra.repository.UserRepository;
import br.com.sra.exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.Assert.assertEquals;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    /**
     * Insert new User
      * @param user user to be persisted
     * @return the persisted user with ID
     */
    public User insert(User user) {
        return repo.save(user);
    }

    /**
     * Find User by username
     * @param username username of the user you want to find
     * @return User if found
     */
    public User findByName(String username) {
        return repo.findByUsername(username).orElseThrow(() ->
                new ObjectNotFoundException("User "+ username +" does not exists"));
    }



}
