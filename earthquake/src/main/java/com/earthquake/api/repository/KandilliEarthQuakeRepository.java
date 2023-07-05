package com.earthquake.api.repository;

import com.earthquake.api.entity.KandilliEarthQuakeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 14.10.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface KandilliEarthQuakeRepository extends CrudRepository<KandilliEarthQuakeEntity, Long> {

    @Query(value = "SELECT * FROM kandilli_earth_quake_entity t where t.earthQuakeId=:earthQuakeId", nativeQuery = true)
    KandilliEarthQuakeEntity earthQuakeId(@Param("earthQuakeId") String earthQuakeId);

}
