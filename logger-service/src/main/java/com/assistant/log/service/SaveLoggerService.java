package com.assistant.log.service;

import com.assistant.commonapi.task.SimpleTask;
import com.assistant.log.entity.LoggerEntity;
import com.assistant.log.model.request.SaveLogRequest;
import com.assistant.log.repository.LoggerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveLoggerService implements Consumer<SaveLogRequest> {

    private final LoggerRepository loggerRepository;

    @Override
    public void accept(SaveLogRequest saveLogRequest) {

        final LoggerEntity entity = LoggerEntity.builder()
                .IP(saveLogRequest.getIP())
                .application(saveLogRequest.getApplication())
                .requestBody(saveLogRequest.getRequestBody())
                .requestDate(LocalDateTime.now().toString())
                .status(saveLogRequest.getStatus())
                .build();

        loggerRepository.save(entity);

    }
}
