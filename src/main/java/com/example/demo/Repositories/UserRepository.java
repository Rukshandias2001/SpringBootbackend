package com.example.demo.Repositories;

import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    Optional<User> findByEmail(String email);

    User findByEmailAndUserName(String email, String userName);

}
