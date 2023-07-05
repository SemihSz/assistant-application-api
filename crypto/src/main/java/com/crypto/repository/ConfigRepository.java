package com.crypto.repository;

import com.crypto.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface ConfigRepository extends JpaRepository<ConfigEntity, String> {
}
