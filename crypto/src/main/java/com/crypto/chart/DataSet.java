package com.crypto.chart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author semih on Haziran, 2022, 20.06.2022, 09:10:13
 */
@Getter
@Setter
@NoArgsConstructor
public class DataSet {

	public String label;

	public List<Integer> data;
}
