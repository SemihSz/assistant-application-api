package com.earthquake.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author semih on Eylül, 2020, 26.09.2020, 13:53:41
 */
@Getter
@Setter
@NoArgsConstructor
public class EarthQuakeModel implements Serializable {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date date;

	private double latitude;

	private double longitude;

	private double magnitude;

	private double depth;
}
