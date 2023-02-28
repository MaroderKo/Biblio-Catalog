package com.smart.catalog.Repository;

import com.smart.catalog.Domain.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbstractRepository<T extends Domain> extends JpaRepository<T,Integer> {
}
