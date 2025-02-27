package com.andreluiscardoso.digitalwallet.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record HttpErrorResponse(
        String type,
        int status,
        String detail,
        String path,
        LocalDateTime timestamp,
        List<Error> errors
) {

    record Error(String field, String message) {
    }
}
