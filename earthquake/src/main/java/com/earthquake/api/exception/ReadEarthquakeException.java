package com.earthquake.api.exception;

/**
 * @author semih on Eylül, 2020, 24.09.2020, 20:54:03
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@SuppressWarnings("serial")
public class ReadEarthquakeException extends Exception {

	public ReadEarthquakeException(Exception pCause) {
		super(pCause);
	}
}
