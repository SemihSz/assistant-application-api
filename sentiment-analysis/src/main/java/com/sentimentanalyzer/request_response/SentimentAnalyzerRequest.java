package com.sentimentanalyzer.request_response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;

/**
 * Created by Semih, 6.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class SentimentAnalyzerRequest {

    @Max(value = 250)
    private String inputText;

    @Max(value = 500)
    private String biggerTextSize;
}
