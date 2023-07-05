package com.crypto.repository;

import com.crypto.entity.CryptoTokenAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 1.10.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface CryptoTokenAuth extends JpaRepository<CryptoTokenAuthEntity, String> {

    @Query(value = "SELECT * FROM crypto_token_auth_entity t where t.user_id=:userId", nativeQuery = true)
    CryptoTokenAuthEntity botUserControl(@Param("userId") String userId);
}
