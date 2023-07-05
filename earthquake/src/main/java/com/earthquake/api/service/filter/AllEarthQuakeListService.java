package com.earthquake.api.service.filter;

import com.earthquake.api.constant.Constant;
import com.earthquake.api.exception.CoreKandilliException;
import com.earthquake.api.model.EarthQuakeInfo;
import com.earthquake.api.service.core.CoreKandilliService;
import com.earthquake.api.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author semih on Eylül, 2020, 24.09.2020, 20:43:46
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AllEarthQuakeListService {

	private final CoreKandilliService coreKandilliService;

	private final TokenService tokenService;

	@Retryable(value = Exception.class,  backoff = @Backoff(delay = 100))
	public List<EarthQuakeInfo> getAllEarthQuakes() throws Exception {

		final List<EarthQuakeInfo> earthQuakeInfoList = new ArrayList<EarthQuakeInfo>();

		String strHTTPResponse = coreKandilliService.sendPost();

		try {
			int nIndex = strHTTPResponse.indexOf("<pre>") + 5;
			int nLastIndex = strHTTPResponse.indexOf("</pre>");

			// get <pre> .. </pre>
			strHTTPResponse = strHTTPResponse.substring(nIndex, nLastIndex).trim();

			strHTTPResponse = strHTTPResponse.substring(strHTTPResponse.lastIndexOf("------") + 6).trim();

			String[] strLines = strHTTPResponse.split("\n");

			DateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT_ONSOURCE);

			for (int i = 0; i < strLines.length && earthQuakeInfoList.size() < Integer.MAX_VALUE; i++) {
				String strLine = strLines[i];

				try {

					// read date
					int nTmpIndex = strLine.indexOf("  ");
					final String strDate = strLine.substring(0, nTmpIndex);
					final Date date = formatter.parse(strDate);
					strLine = strLine.substring(nTmpIndex + 1).trim();

					// read latitude
					nTmpIndex = strLine.indexOf("  ");
					final double latitude = Double.parseDouble(strLine.substring(0, nTmpIndex));
					strLine = strLine.substring(nTmpIndex + 1).trim();

					// read longitude
					nTmpIndex = strLine.indexOf("  ");
					final double longitude = Double.parseDouble(strLine.substring(0, nTmpIndex));
					strLine = strLine.substring(nTmpIndex + 1).trim();

					nTmpIndex = strLine.indexOf(" ");
					final double depth = Double.parseDouble(strLine.substring(0, nTmpIndex));
					strLine = strLine.substring(nTmpIndex + 1).trim();

					// clear magnitude 1
					nTmpIndex = strLine.indexOf(" ");
					strLine = strLine.substring(nTmpIndex + 1).trim();

					// read magnitude
					nTmpIndex = strLine.indexOf(" ");
					double magnitude = 0.0;

					try {
						magnitude = Double.parseDouble(strLine.substring(0, nTmpIndex));
					} catch (NumberFormatException ex) {
						throw new CoreKandilliException("Number format exception");
					}
					strLine = strLine.substring(nTmpIndex + 1).trim();

					if (magnitude >= 0.0) {
						// clear magnitude 3
						nTmpIndex = strLine.indexOf(" ");
						strLine = strLine.substring(nTmpIndex + 1).trim();

						// read place
						nTmpIndex = strLine.indexOf("   ");
						final String location = strLine.substring(0, nTmpIndex);
						strLine = strLine.substring(nTmpIndex + 1).trim();

						//final String attribute = strLine.trim();

						final EarthQuakeInfo information = EarthQuakeInfo.builder()
								.date(date)
								.latitude(latitude)
								.longitude(longitude)
								.depth(depth)
								.magnitude(magnitude)
								.locationName(location)
								.build();

						earthQuakeInfoList.add(information);
					}
				} catch (Exception e) {

					exceptionHandleForCoreSystemCont(earthQuakeInfoList, i, strLines);
				}
			}
		} catch (Exception e) {
			throw new CoreKandilliException(e);
		}

		log.info("All Size: {}", earthQuakeInfoList.size());

		/**
		 * Before cache operations each earthquakes generate an ID and add into list.
		 * This id restore in only 3 min. then generate token and each earthquakes has new ID.
		 * In this way, dynamic search and faster response.
		 **/
		earthQuakeInfoList.forEach(t -> {
			t.setId(tokenService.generateId());
		});

		return earthQuakeInfoList;
	}

	/**
	 * if there is an one str line parse error this exception handle will run for one line solution.
	 * @param earthQuakeInfoList
	 * @param i
	 * @param strLines
	 */
	@SneakyThrows
	public void exceptionHandleForCoreSystemCont(List<EarthQuakeInfo> earthQuakeInfoList, int i, String[] strLines) {
		DateFormat formatter = new SimpleDateFormat(Constant.DATE_FORMAT_ONSOURCE);
		i++;
		String strLine = strLines[i];

		// read date
		int nTmpIndex = strLine.indexOf("  ");
		final String strDate = strLine.substring(0, nTmpIndex);
		final Date date = formatter.parse(strDate);
		strLine = strLine.substring(nTmpIndex + 1).trim();

		// read latitude
		nTmpIndex = strLine.indexOf("  ");
		final double latitude = Double.parseDouble(strLine.substring(0, nTmpIndex));
		strLine = strLine.substring(nTmpIndex + 1).trim();

		// read longitude
		nTmpIndex = strLine.indexOf("  ");
		final double longitude = Double.parseDouble(strLine.substring(0, nTmpIndex));
		strLine = strLine.substring(nTmpIndex + 1).trim();

		nTmpIndex = strLine.indexOf(" ");
		final double depth = Double.parseDouble(strLine.substring(0, nTmpIndex));
		strLine = strLine.substring(nTmpIndex + 1).trim();

		// clear magnitude 1
		nTmpIndex = strLine.indexOf(" ");
		strLine = strLine.substring(nTmpIndex + 1).trim();

		// read magnitude
		nTmpIndex = strLine.indexOf(" ");
		double magnitude = 0.0;

		try {
			magnitude = Double.parseDouble(strLine.substring(0, nTmpIndex));
		} catch (NumberFormatException ex) {
			throw new CoreKandilliException("Number format exception");
		}
		strLine = strLine.substring(nTmpIndex + 1).trim();

		if (magnitude >= 0.0) {
			// clear magnitude 3
			nTmpIndex = strLine.indexOf(" ");
			strLine = strLine.substring(nTmpIndex + 1).trim();

			// read place
			nTmpIndex = strLine.indexOf("   ");
			final String location = strLine.substring(0, nTmpIndex);
			strLine = strLine.substring(nTmpIndex + 1).trim();

			//final String attribute = strLine.trim();

			final EarthQuakeInfo information = EarthQuakeInfo.builder()
					.date(date)
					.latitude(latitude)
					.longitude(longitude)
					.depth(depth)
					.magnitude(magnitude)
					.locationName(location)
					.build();

			earthQuakeInfoList.add(information);
		}
	}


}
