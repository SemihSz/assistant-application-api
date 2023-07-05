package com.earthquake.api.service.logger;

import com.earthquake.api.model.EarthQuakeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 28.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
public class EarthQuakeLoggerService {

    private final ObjectMapper objectMapper;


    public EarthQuakeLoggerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     *  log earthquake info
     * @param earthQuakeInfo
     */
    public void logInfo(EarthQuakeInfo earthQuakeInfo) {

        log.info("earth quake  quake ID: {}, date: {}, magnitude: {}, depth: {}, Location Coordinates; Latitude: {}, Longitude: {} ",
                earthQuakeInfo.getId(),
                earthQuakeInfo.getDate(),
                earthQuakeInfo.getMagnitude(),
                earthQuakeInfo.getDepth(),
                earthQuakeInfo.getLatitude(),
                earthQuakeInfo.getLongitude());

    }

    public <T> void requestLogging(T request, String requestName) {

        try {

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            final String requestBody = objectMapper.writer().writeValueAsString(request).replaceAll("\\\\", "");
            log.info(requestName + " Request Body = {}", requestBody);

        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

    }
}
