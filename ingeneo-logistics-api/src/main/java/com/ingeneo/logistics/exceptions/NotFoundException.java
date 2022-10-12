package com.ingeneo.logistics.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    private HttpStatus status = HttpStatus.NOT_FOUND;
    private String message;

    public NotFoundException(String message) {
        this.message = message;
    }
}
