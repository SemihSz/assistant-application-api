package com.crypto.service.coingecko.coin_generator;

import com.crypto.entity.AbstractBaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
public interface AbstractBaseService <T extends AbstractBaseEntity, ID extends Serializable> {

    public abstract T save(T entity);

    public abstract List<T> findAll();

    public abstract Optional<T> findById(ID entityId);

    public abstract T update(T entity);

    public abstract T updateById(T entity, ID entityId);

    public abstract void delete(T entity);

    public abstract void deleteById(ID entityId);

}

