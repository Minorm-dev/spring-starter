package com.minorm.spring.database.repository;

import com.minorm.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // Optional, Entity, Future <- can use, but Optional better
    Optional<Company> findByName(String name);

    // Collection, Stream (batch, close)
    List<Company> findAllByNameContainingIgnoreCase(String fragment);
}
