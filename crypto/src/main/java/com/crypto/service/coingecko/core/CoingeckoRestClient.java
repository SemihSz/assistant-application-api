package com.crypto.service.coingecko.core;

import com.crypto.request.coingecko.CoreCoingeckoCoreRequest;
import com.crypto.service.coingecko.RequestResponseLoggingInterceptor;
import com.assistant.commonapi.task.Mappers;
import com.crypto.utils.ClientFactoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Semih, 18.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CoingeckoRestClient<T> implements Mappers<CoreCoingeckoCoreRequest, Class<T>, T> {

    private final CoingeckoRestClientRequestInterceptor coingeckoRestClientRequestInterceptor;

    @Override
    public T apply(CoreCoingeckoCoreRequest request, Class<T> responseModel) {

        final RestTemplate restTemplate = ClientFactoryUtils.customRestTemplate(coingeckoRestClientRequestInterceptor);
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));

        final UriComponentsBuilder builder = uriBuilder(request.getUrl(), request.getQueryParams());

        final ResponseEntity<T> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(request), responseModel);
        return response.getBody();
    }

    private UriComponentsBuilder uriBuilder(String url, Map<String, Object> queryParams) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (Objects.nonNull(queryParams)) {
            Set<String> sets = queryParams.keySet();
            for (String key : sets) {
                builder.queryParam(key, queryParams.get(key));
            }
        }
        return builder;
    }
}
