package com.crypto.chart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Haziran, 2022, 19.06.2022, 22:26:13
 */
@Getter
@Setter
@Builder
public  class QuickChartModel {

	public String backgroundColor;

	public int width;

	public int height;

	public double devicePixelRatio;

	public Chart chart;

	private String format;

	private String encoding;


}
