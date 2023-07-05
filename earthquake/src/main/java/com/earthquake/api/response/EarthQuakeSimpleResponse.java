package com.earthquake.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author semih on Eylül, 2020, 26.09.2020, 13:50:48
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@NoArgsConstructor
public class EarthQuakeSimpleResponse<T, C> {

	private T firstInfo;

	private C secondInfo;
}
