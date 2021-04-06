package br.com.sra.repository;

import br.com.sra.domain.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find user by username
     * @param username username from the user you want to find
     * @return Optional<User>
     */
    @Query("SELECT u FROM User u where u.username = ?1")
    Optional<User> findByUsername(String username);
}
