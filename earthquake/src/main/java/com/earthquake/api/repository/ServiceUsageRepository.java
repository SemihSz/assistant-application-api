package com.earthquake.api.repository;

import com.earthquake.api.entity.ServiceUsageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Semih, 16.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Repository
public interface ServiceUsageRepository extends CrudRepository<ServiceUsageEntity, String> {

    @Query(value = "SELECT * FROM service_usage_entity t where (t.service_name=:serviceName AND t.status=:status)", nativeQuery = true)
    ServiceUsageEntity service(@Param("serviceName") String serviceName, @Param("status") String status);

    @Query(value = "SELECT * FROM service_usage_entity t where (t.service_name=:serviceName)", nativeQuery = true)
    List<ServiceUsageEntity> serviceName(@Param("serviceName") String serviceName);
}
