package com.crypto.service.coinmarketcap.core;

import com.crypto.Constant;
import com.crypto.model.cmc.core.CoreCmcResponse;
import com.crypto.request.cmc.CoreCoinmarketcapRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author semih on Haziran, 2021, 13.06.2021, 20:49:59
 */
@Slf4j
@Service
@AllArgsConstructor
public class CmcCoreService implements CoreCmcService {

	private final CoinmarketcapRestClient cmcRestClient;

	@Override
	public CoreCmcResponse getTop100() {

		final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add(Constant.CMC.Body.START_PARAMS, Constant.CMC.Body.START_VALUE);
		body.add(Constant.CMC.Body.LIMIT_PARAMS, Constant.CMC.Body.LIMIT_VALUE);
		body.add(Constant.CMC.Body.CONVERT_PARAMS, Constant.CMC.Body.CONVERT_VALUE);

		final CoreCoinmarketcapRequest coreRequest = CoreCoinmarketcapRequest.builder()
				.url(Constant.CMC.Request.LATEST_LIST)
				.body(body)
				.build();

		final CoreCmcResponse response = (CoreCmcResponse) cmcRestClient.apply(coreRequest, CoreCmcResponse.class);

		return response;
	}

	@Override
	public CoreCmcResponse getNewAdded() {

		final Map<String, String> queryParams = new HashMap<>();
		queryParams.put(Constant.CMC.Body.START_PARAMS, "1");
		queryParams.put(Constant.CMC.Body.LIMIT_PARAMS, "3000");

		final CoreCoinmarketcapRequest coreRequest = CoreCoinmarketcapRequest.builder()
				.url(Constant.CMC.Request.LATEST_LIST)
				.queryParams(queryParams)
				.build();

		final CoreCmcResponse response = (CoreCmcResponse) cmcRestClient.apply(coreRequest, CoreCmcResponse.class);

		return response;
	}
}
