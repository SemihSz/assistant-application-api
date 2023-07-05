package com.crypto.service.coinmarketcap.core;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author semih on Haziran, 2022, 20.06.2022, 16:19:20
 */
@Component
public class CoinmarketcapRestClientInterceptor implements HttpRequestInterceptor {

	public static final String CMC_PRO_API_KEY = "X-CMC_PRO_API_KEY";

	public final String COINMARKETCAP_API_TOKEN;

	public CoinmarketcapRestClientInterceptor(@Value("${crypto-api.coinmarketcap}") String apiKey) {
		this.COINMARKETCAP_API_TOKEN = apiKey;
	}

	@Override
	public void process(HttpRequest request, HttpContext httpContext) throws HttpException, IOException {

		request.addHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
		request.addHeader(CMC_PRO_API_KEY, COINMARKETCAP_API_TOKEN);

	}
}
