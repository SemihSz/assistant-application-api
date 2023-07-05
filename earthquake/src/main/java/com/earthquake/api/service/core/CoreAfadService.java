package com.earthquake.api.service.core;

import com.earthquake.api.response.CoreAFADResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Semih, 4.11.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
public class CoreAfadService {

    private String url;

    private final static String REQUEST_TYPE = "GET";

    public CoreAfadService(@Value("${afad-urls}") String url) {
        this.url = url;
    }

    @SneakyThrows
    @Retryable(value = Exception.class,  backoff = @Backoff(delay = 100))
    public List<CoreAFADResponse> coreAfadService() {

        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .method(REQUEST_TYPE, null)
                .build();

        final Call call = client.newCall(request);
        final Response response = call.execute();
        ObjectMapper mapper = new ObjectMapper();
        final CoreAFADResponse[] coreResponse = mapper.readValue(response.body().string(), CoreAFADResponse[].class);

        return Arrays.asList(coreResponse);

    }

}
