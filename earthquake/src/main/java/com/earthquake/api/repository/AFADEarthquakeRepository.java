package com.earthquake.api.repository;

import com.earthquake.api.entity.AFADEarthquakeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 4.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface AFADEarthquakeRepository extends CrudRepository<AFADEarthquakeEntity, Long> {
}
