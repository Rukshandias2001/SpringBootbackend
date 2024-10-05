package com.example.demo.Repositories;

import com.example.demo.Entities.SelectedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SelectedProductRepository extends JpaRepository<SelectedItems,Integer> {

        @Query("SELECT s FROM SelectedItems s WHERE s.productId = :productId AND s.user.id = :userId")
        SelectedItems findByProductIdAndUserId(@Param("productId") Long productId, @Param("userId") Integer userId);

        @Query("SELECT s FROM SelectedItems s where s.user.id =:user_id  order by s.price ASC")
        List<SelectedItems> findAllByUserId(Integer user_id);

        Optional<SelectedItems> removeByProductIdAndEmail(long id, String email);
}
