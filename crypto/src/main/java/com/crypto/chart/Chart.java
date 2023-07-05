package com.crypto.chart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author semih on Haziran, 2022, 20.06.2022, 09:09:23
 */
@Getter
@Setter
@NoArgsConstructor
public class Chart<T> {

	public String type;

	public Data<T> data;
}
