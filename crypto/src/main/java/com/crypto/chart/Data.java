package com.crypto.chart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author semih on Haziran, 2022, 20.06.2022, 09:09:50
 */
@Getter
@Setter
@NoArgsConstructor
public class Data<T> {

	public List<T> labels;

	public List<DataSet> datasets;
}
