package com.crypto.utils;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author semih on Haziran, 2022, 20.06.2022, 19:03:28
 */
@Component
public class ClientFactoryUtils {

	public ClientFactoryUtils() {
	}

	public static RestTemplate customRestTemplate(HttpRequestInterceptor interceptor) {

		return new RestTemplate(createRequestFactory(interceptor));
	}

	private static ClientHttpRequestFactory createRequestFactory(HttpRequestInterceptor interceptor) {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(15);
		connectionManager.setDefaultMaxPerRoute(5);
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000).setSocketTimeout(5000).build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager)
				.addInterceptorFirst(interceptor)
				.setDefaultRequestConfig(config).build();

		return new HttpComponentsClientHttpRequestFactory(httpClient);

	}
}
