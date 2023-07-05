package com.earthquake.api.exception.handler;

import com.earthquake.api.exception.CoreKandilliException;
import com.earthquake.api.exception.TokenException;
import com.earthquake.api.model.RestResponse;
import com.earthquake.api.response.ExceptionResponse;
import com.earthquake.api.service.mail.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static com.earthquake.api.constant.Constant.FAIL_CODE;
import static com.earthquake.api.constant.Constant.TOKEN_CODE;

/**
 * Created by Semih, 28.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MailServiceImpl mailService;

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<RestResponse<ExceptionResponse>> tokenError(TokenException ex) {

        final ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(TOKEN_CODE);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.ok(new RestResponse<ExceptionResponse>(FAIL_CODE, response));
    }

    @ExceptionHandler(CoreKandilliException.class)
    public ResponseEntity<RestResponse<ExceptionResponse>> coreConnectionError(CoreKandilliException ex) {

        final ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(FAIL_CODE);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.ok(new RestResponse<ExceptionResponse>(FAIL_CODE, response));
    }

    // TODO BusinessException
}
