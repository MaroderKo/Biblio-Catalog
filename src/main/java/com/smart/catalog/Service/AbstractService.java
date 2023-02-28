package com.smart.catalog.Service;

import com.smart.catalog.Domain.Book;
import com.smart.catalog.Domain.Domain;
import com.smart.catalog.Repository.AbstractRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

public class AbstractService<T extends Domain, R extends AbstractRepository<T>> {
    protected final R repository;

    public List<T> getAll()
    {
        return repository.findAll();
    }

    public AbstractService(R repository) {
        this.repository = repository;
    }

    public T save(T t)
    {
        return repository.save(t);
    }

    public T getById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    public boolean delete(T t)
    {
        repository.delete(t);
        return getById(t.getId()) == null;
    }

    @Transactional
    public T update (T t)
    {
        if (getById(t.getId()) != null)
        {
            save(t);
            return getById(t.getId());
        }
        else
        {
            throw new IllegalStateException("Trying to update element that not exist in database!");
        }
    }
}
