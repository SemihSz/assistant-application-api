package com.earthquake.api.repository;

import com.earthquake.api.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 11.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface ConfigRepository extends JpaRepository<ConfigEntity, String> {
}
