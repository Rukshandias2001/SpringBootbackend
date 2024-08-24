package com.example.demo.Repositories;

import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    Optional<User> findByEmail(String email);

    User findByEmailAndUserName(String email, String userName);


    @Query("SELECT SUM(o.price) AS total_price, u.id, u.email " +
            "FROM Orders o " +
            "JOIN o.user u " +  // Correct join condition
            "GROUP BY u.id, u.email " +
            "ORDER BY total_price DESC LIMIT 3")
    List<Object[]> findByOrderListByUser();

}
