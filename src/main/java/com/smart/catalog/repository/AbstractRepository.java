package com.smart.catalog.repository;

import com.smart.catalog.domain.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository<T extends Domain> extends JpaRepository<T,Integer> {
}
