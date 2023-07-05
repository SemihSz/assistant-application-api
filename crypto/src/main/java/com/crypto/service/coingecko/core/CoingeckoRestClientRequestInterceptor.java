package com.crypto.service.coingecko.core;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author semih on Haziran, 2022, 20.06.2022, 18:58:46
 */
@Component
public class CoingeckoRestClientRequestInterceptor implements HttpRequestInterceptor {

	@Override
	public void process(HttpRequest request, HttpContext httpContext) throws HttpException, IOException {

		request.addHeader("content-type", MediaType.APPLICATION_JSON_VALUE);

		

	}
}
