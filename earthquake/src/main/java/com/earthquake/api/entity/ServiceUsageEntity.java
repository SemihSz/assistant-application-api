package com.earthquake.api.entity;

import com.earthquake.api.type.ServiceUsageStatusType;
import lombok.*;

import javax.persistence.*;

/**
 * Created by Semih, 16.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceUsageEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiceUsageStatusType status;

    private String serviceName;

    private Long count;

}
