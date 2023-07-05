package com.crypto.repository;

import com.crypto.entity.AllCoinsInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllCoinsInfoEntityRepository extends JpaRepository<AllCoinsInfoEntity, String> {
}