package com.webflux.example.globant_challenge.repository;

import com.webflux.example.globant_challenge.domain.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    @Query("select p from Purchase p where a.createdAt >= :creationDateTime")
    List<Purchase> findAllWithCreationDateTimeBefore(
            @Param("creationDateTime") Date creationDateTime);

}
