package com.crypto.service.coingecko.coin_generator;

import com.crypto.entity.AbstractBaseEntity;
import com.crypto.repository.AbstractBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Transactional
public abstract class AbstractBaseRepositoryImpl<T extends AbstractBaseEntity, ID extends Serializable>
        implements AbstractBaseService<T, ID>{

    private AbstractBaseRepository<T, ID> abstractBaseRepository;

    @Autowired
    public AbstractBaseRepositoryImpl(AbstractBaseRepository<T, ID> abstractBaseRepository) {
        this.abstractBaseRepository = abstractBaseRepository;
    }

    @Override
    public T save(T entity) {
        return (T) abstractBaseRepository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return abstractBaseRepository.findAll();
    }

    @Override
    public Optional<T> findById(ID entityId) {
        return abstractBaseRepository.findById(entityId);
    }

    @Override
    public T update(T entity) {
        return (T) abstractBaseRepository.save(entity);
    }

    @Override
    public T updateById(T entity, ID entityId) {
        Optional<T> optional = abstractBaseRepository.findById(entityId);
        if(optional.isPresent()){
            return (T) abstractBaseRepository.save(entity);
        }else{
            return null;
        }
    }

    @Override
    public void delete(T entity) {
        abstractBaseRepository.delete(entity);
    }

    @Override
    public void deleteById(ID entityId) {
        abstractBaseRepository.deleteById(entityId);
    }

}
