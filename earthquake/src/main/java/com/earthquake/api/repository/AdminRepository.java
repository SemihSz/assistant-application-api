package com.earthquake.api.repository;


import com.earthquake.api.entity.AdminEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 24.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface AdminRepository extends CrudRepository<AdminEntity, Long> {

    @Query(value = "SELECT * FROM admin_entity t where t.user_email=:userMail", nativeQuery = true)
    AdminEntity userControl(@Param("userMail") String userMail);
}
