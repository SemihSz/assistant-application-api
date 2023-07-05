package com.crypto.chart;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author semih on Haziran, 2022, 19.06.2022, 22:52:31
 */
@Getter
@Setter
@RequiredArgsConstructor
@Service
public class QuickChartRestClient {

	private final static String BASE_URL = "https://quickchart.io/chart";

	private final static String CREATE = BASE_URL.concat("/create");


	public ResponseBody createChartUrl(QuickChartModel model) {

		if (Objects.nonNull(model.getChart().getData())) {
			return executeRestCall(CREATE, model);
		}
		return null;
	}

	@SneakyThrows
	public ResponseBody executeRestCall(String url, QuickChartModel model) {

		final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		final String requestString = mapper.writeValueAsString(model);

		final OkHttpClient client = new OkHttpClient().newBuilder()
				.build();

		final Map<String, String> header = new HashMap<>();
		header.put("content-type", "application/json");

		final Headers headers = Headers.of(header);

		final Request request = new Request.Builder()
				.url(url)
				.headers(headers)
				.post(RequestBody.create(MediaType.parse("application/json"), requestString))
				.build();


		final Call call = client.newCall(request);
		final Response response = call.execute();

		return response.body();

	}


}
