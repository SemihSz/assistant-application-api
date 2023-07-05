package com.crypto.request.coingecko;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * Created by Semih, 18.06.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class CoreCoingeckoCoreRequest {

    private String url;

    private MultiValueMap<String, String> body;

    private Map<String, Object> queryParams;
}
