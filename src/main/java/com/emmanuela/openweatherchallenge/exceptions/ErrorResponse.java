package com.emmanuela.openweatherchallenge.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    public String debugMessage;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime = LocalDateTime.now();

}

