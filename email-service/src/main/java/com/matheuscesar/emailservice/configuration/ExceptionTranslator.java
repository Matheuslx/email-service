package com.matheuscesar.emailservice.configuration;

import com.matheuscesar.emailservice.dto.ApiResponse;
import com.matheuscesar.emailservice.exception.EnviarEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EnviarEmailException.class)
    public ResponseEntity<Object> validationException(EnviarEmailException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiResponse<Object> retornoComErro = ApiResponse.builder()
                .data(null)
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, retornoComErro, new HttpHeaders(), status, request);
    }
}
