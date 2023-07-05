package com.earthquake.api.service.core;

import com.earthquake.api.model.world.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Objects;

/**
 * Created by Semih, 17.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
public class CoreWorldEarthQuakeService {

    private String generalUrl;

    private String significantUrl;


    public CoreWorldEarthQuakeService(@Value("${world-earthquake.general-url}") String generalUrl,
                                      @Value("${world-earthquake.significant-url}") String significantUrl) {
        this.generalUrl = generalUrl;
        this.significantUrl = significantUrl;
    }

    public Root generalService(Map<String, String> params) {

        return coreEarthQuakeService(generalUrl, params);
    }

    public Root significantService() {

        return coreEarthQuakeService(significantUrl, null);
    }


    public Root coreEarthQuakeService(String url, Map<String, String> params) {

        RestTemplate restTemplate = new RestTemplate();

        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        if (Objects.nonNull(params)) {
            params.forEach((key, value) -> uriBuilder.queryParam(key, value));
        }

        return restTemplate.getForObject(uriBuilder.toUriString(), Root.class);
    }



}
