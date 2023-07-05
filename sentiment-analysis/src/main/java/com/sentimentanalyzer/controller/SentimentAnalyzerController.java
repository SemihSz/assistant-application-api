package com.sentimentanalyzer.controller;

import com.sentimentanalyzer.model.RestResponse;
import com.sentimentanalyzer.request_response.SentimentAnalyzerRequest;
import com.sentimentanalyzer.service.SentimentAnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Semih, 6.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class SentimentAnalyzerController {

    private final SentimentAnalyzerService sentimentAnalyzerService;

    @PostMapping(value = "/analyzer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<String>> analyzer(SentimentAnalyzerRequest request) {

       return ResponseEntity.ok(new RestResponse<>(200, sentimentAnalyzerService.apply(request)));
    }
}
