package com.earthquake.api.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Semih, 4.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AFADEarthquakeEntity {

    @Id
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    private String location;

    private Double latitude;

    private Double longitude;

    private Double depth;

    private Double magnitude;

    private String earthQuakeId;
}
