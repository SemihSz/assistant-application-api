package com.earthquake.api.service.core;

import com.earthquake.api.response.CoreAFADResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Semih, 2.02.2021
 * <p>This core service getter earthquake information with static request body.</p>
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
public class CoreAfadV2Service {

    private final String url;

    private static final String REQUEST_TYPE = "POST";

    private static final String HEADER_TO_CACHE = "header-to-cache";

    private static final String HEADER_TO_CACHE_VALUE = "m_0utc_0lastDay_1";

    private static final String CONTENT_TYPE = "content-type";

    private static final String CONTENT_TYPE_VALUE = "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW";

    private static final String MAGNITUDE = "m";

    private static final String MAGNITUDE_VALUE = "0";

    private static final String UTC = "utc";

    private static final String UTC_VALUE = "0";

    private static final String LAST_DAY = "latsDay";

    private static final String LAST_DAY_VALUE = "1";

    private static final String PAGE = "page";

    private static final String PAGE_VALUE = "0";

    public CoreAfadV2Service(@Value("${afad-url}") String url) {
        this.url = url;
    }

    @SneakyThrows
    @Retryable(value = Exception.class,  backoff = @Backoff(delay = 100))
    public List<CoreAFADResponse> coreAfadService() {

        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        final Map<String, String> header = new HashMap<>();
        header.put(HEADER_TO_CACHE, HEADER_TO_CACHE_VALUE);
        header.put(CONTENT_TYPE, CONTENT_TYPE_VALUE);

        final RequestBody formBody = new FormBody.Builder()
                .add(MAGNITUDE, MAGNITUDE_VALUE)
                .add(UTC, UTC_VALUE)
                .add(LAST_DAY, LAST_DAY_VALUE)
                .add(PAGE, PAGE_VALUE)
                .build();

        final Headers headers = Headers.of(header);

        final Request request = new Request.Builder()
                .url(this.url)
                .headers(headers)
                .method(REQUEST_TYPE, formBody)
                .build();

        final Call call = client.newCall(request);
        final Response response = call.execute();

        final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final CoreAFADResponse[] coreResponse = mapper.readValue(response.body().string(), CoreAFADResponse[].class);

        return Arrays.asList(coreResponse);

    }

}
