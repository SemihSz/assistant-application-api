package com.assistant.log.controller;


import com.assistant.log.model.request.SaveLogRequest;
import com.assistant.log.service.SaveLoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logger")
@RequiredArgsConstructor
public class LoggerController {

    private final SaveLoggerService saveLoggerService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity saveLogger(SaveLogRequest request) {

        saveLoggerService.accept(request);
        return new ResponseEntity(HttpStatus.OK);
    }

}
