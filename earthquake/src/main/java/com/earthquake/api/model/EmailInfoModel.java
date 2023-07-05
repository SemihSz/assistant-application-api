package com.earthquake.api.model;

import com.earthquake.api.type.MailType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by Semih, 29.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
@Builder
public class EmailInfoModel {

    private String to;

    private String subject;

    private String body;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private Map<String, Object> mailBody;

    private MailType mailType;
}
