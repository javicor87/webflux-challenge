package com.webflux.example.globant_challenge.repository;

import com.webflux.example.globant_challenge.domain.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    @Query("select p from Purchase p where p.createdAt >= :creationDate")
    List<Purchase> findAllWithCreationDateTimeBefore(
            @Param("creationDate") LocalDateTime creationDate);

}
