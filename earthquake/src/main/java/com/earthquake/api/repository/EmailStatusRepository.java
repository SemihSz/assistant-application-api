package com.earthquake.api.repository;

import com.earthquake.api.entity.EmailStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 12.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface EmailStatusRepository extends CrudRepository<EmailStatusEntity, Long> {
}
