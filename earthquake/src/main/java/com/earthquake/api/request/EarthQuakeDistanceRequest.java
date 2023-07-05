package com.earthquake.api.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Eylül, 2020, 26.09.2020, 21:57:41
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class EarthQuakeDistanceRequest {

	private String token;

	private String id;

	private Double latitude;

	private Double longitude;

}
