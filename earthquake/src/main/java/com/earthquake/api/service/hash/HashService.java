package com.earthquake.api.service.hash;

import com.earthquake.api.shared.util.HashUtil;
import com.assistant.commonapi.task.SimpleTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Semih, 22.02.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
public class HashService<T> implements SimpleTask<T, String> {

    private final ObjectMapper objectMapper;

    public HashService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String apply(T t) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            final String requestBody = objectMapper.writer().writeValueAsString(t).replaceAll("\\\\", "");
            final byte [] bytes = HashUtil.getSHA(requestBody);
            return HashUtil.toHexString(bytes);

        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
