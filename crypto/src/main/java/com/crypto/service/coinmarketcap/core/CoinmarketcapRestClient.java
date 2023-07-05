package com.crypto.service.coinmarketcap.core;

import com.crypto.request.cmc.CoreCoinmarketcapRequest;
import com.assistant.commonapi.task.Mappers;
import com.crypto.utils.ClientFactoryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Set;

/**
 * Created by Semih, 10.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
public class CoinmarketcapRestClient<T> implements Mappers<CoreCoinmarketcapRequest, Class<T>, T> {

    private final CoinmarketcapRestClientInterceptor coinmarketcapRestClientInterceptor;

    public CoinmarketcapRestClient(CoinmarketcapRestClientInterceptor coinmarketcapRestClientInterceptor) {
        this.coinmarketcapRestClientInterceptor = coinmarketcapRestClientInterceptor;
    }

    @Override
    public T apply(CoreCoinmarketcapRequest request, Class<T> responseModel) {

        final RestTemplate restTemplate = ClientFactoryUtils.customRestTemplate(coinmarketcapRestClientInterceptor);
        final UriComponentsBuilder builder = uriBuilder(request.getUrl(), request.getQueryParams());

        final ResponseEntity<T> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(request), responseModel);
        return response.getBody();
    }

    private UriComponentsBuilder uriBuilder(String url, Map<String, String> queryParams) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        Set<String> sets = queryParams.keySet();
        for (String key : sets) {
            builder.queryParam(key, queryParams.get(key));
        }

        return builder;
    }

}
