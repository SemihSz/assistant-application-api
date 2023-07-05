package com.crypto.request.cmc;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @author semih on Haziran, 2021, 11.06.2021, 22:34:06
 */
@Getter
@Setter
@Builder
public class CoreCoinmarketcapRequest {

	private String url;

	private MultiValueMap<String, String> body;

	private Map<String, String> queryParams;



}
