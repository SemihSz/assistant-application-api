package com.crypto.repository;

import com.crypto.entity.AbstractBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@NoRepositoryBean
public interface AbstractBaseRepository<T extends AbstractBaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {

}