package com.crypto.repository;

import com.crypto.entity.CoinsPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 7.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface CoinsPriceEntityRepository extends JpaRepository<CoinsPriceEntity, CoinsPriceEntity.PK> {
}
