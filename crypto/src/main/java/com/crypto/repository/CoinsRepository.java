package com.crypto.repository;

import com.crypto.entity.CoinsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface CoinsRepository extends JpaRepository<CoinsEntity, String> {

    @Query(value = "SELECT * FROM coins_entity t where t.coin=:coin", nativeQuery = true)
    CoinsEntity coinsControl(@Param("coin") String coin);

    @Query(value = "SELECT * FROM coins_entity t where t.coin=:coin and t.is_repository=false", nativeQuery = true)
    CoinsEntity coinsRepoControl(@Param("coin") String coin);

    @Query(value = "SELECT * FROM coins_entity t where t.repository_created_date is null and t.is_repository=false and t.repo_location_type='PROD'", nativeQuery = true)
    List<CoinsEntity> coinsListRepoControl();


}
