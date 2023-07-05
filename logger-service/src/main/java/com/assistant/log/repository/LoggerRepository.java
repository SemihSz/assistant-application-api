package com.assistant.log.repository;

import com.assistant.log.entity.LoggerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LoggerRepository extends CrudRepository<LoggerEntity, Long> {

}
