package com.earthquake.api.repository;

import com.earthquake.api.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Semih, 25.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

    @Query(value = "SELECT * FROM user_entity where is_send_email_active=true", nativeQuery = true)
    List<UserEntity> findByIsSendEmailActive();
}
