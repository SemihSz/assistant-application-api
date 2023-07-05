package com.crypto.service.coingecko;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by Semih, 25.08.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Component
public class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);

        //Add optional additional headers
        response.getHeaders().add("headerName", "VALUE");

        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("===========================request begin================================================");
            log.debug("URI         : {}", request.getURI());
            log.debug("Method      : {}", request.getMethod());
            log.debug("Headers     : {}", request.getHeaders());
            log.debug("Request body: {}", new String(body, "UTF-8"));
            log.debug("==========================request end================================================");
        }
    }

    private void logResponse(ClientHttpResponse response) throws IOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("============================response begin==========================================");
            log.debug("Status code  : {}", response.getStatusCode());
            log.debug("Status text  : {}", response.getStatusText());
            log.debug("Headers      : {}", response.getHeaders());
            //log.info("Body          : {}", getBodyString(response));
            log.debug("=======================response end=================================================");
        }
    }


    private String getBodyString(ClientHttpResponse response) {
        try {
            if (response != null && response.getBody() != null) {// &&
                // isReadableResponse(response))
                // {
                StringBuilder inputStringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
                String line = bufferedReader.readLine();
                while (line != null) {
                    inputStringBuilder.append(line);
                    inputStringBuilder.append('\n');
                    line = bufferedReader.readLine();
                }
                return inputStringBuilder.toString();
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
}
