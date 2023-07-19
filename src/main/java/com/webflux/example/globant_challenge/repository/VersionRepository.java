package com.webflux.example.globant_challenge.repository;

import com.webflux.example.globant_challenge.domain.Version;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionRepository extends CrudRepository<Version, Long> {
}
