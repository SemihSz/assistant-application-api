package com.crypto.repository;

import com.crypto.entity.CoinWhiteListEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 7.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface CoinWhiteListEntityRepository extends CrudRepository<CoinWhiteListEntity, String> {
}
