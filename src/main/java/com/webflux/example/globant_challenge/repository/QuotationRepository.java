package com.webflux.example.globant_challenge.repository;

import com.webflux.example.globant_challenge.domain.Quotation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends CrudRepository<Quotation, Long> {

    Quotation findFirstByModelOrderByIdDesc(String model);

}
