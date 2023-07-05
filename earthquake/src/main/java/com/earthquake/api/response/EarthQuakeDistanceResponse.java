package com.earthquake.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author semih on Eylül, 2020, 26.09.2020, 22:06:43
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Setter
@Getter
@Builder
public class EarthQuakeDistanceResponse {

	private String id;

	private Double distance;

	private Double quakeLatitude;

	private Double quakeLongitude;

	private String location;

	private Double magnitude;

	private LocalDateTime time;

}
