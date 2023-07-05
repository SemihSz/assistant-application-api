package com.earthquake.api.repository;


import com.earthquake.api.entity.WorldEarthQuakeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 27.03.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface WorldEarthQuakeRepository extends CrudRepository<WorldEarthQuakeEntity, Long> {
}
