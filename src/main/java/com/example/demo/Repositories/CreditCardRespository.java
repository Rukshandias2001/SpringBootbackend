package com.example.demo.Repositories;

import com.example.demo.Entities.CreditCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRespository extends JpaRepository<CreditCards,Long> {

        CreditCards findByCardNumber(String cardNumber);

}
