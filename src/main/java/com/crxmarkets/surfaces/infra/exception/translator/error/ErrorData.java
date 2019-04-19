package com.crxmarkets.surfaces.infra.exception.translator.error;

import com.crxmarkets.surfaces.infra.exception.translator.error.serializer.ErrorDataSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This class is a value object with error information of exception to build {@link org.springframework.http.ResponseEntity}
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@AllArgsConstructor
@JsonSerialize(using = ErrorDataSerializer.class)
public class ErrorData {

    private final HttpStatus httpStatus;

    @Getter
    private final String errorMessage;

    /**
     * This method will return the code from {@link HttpStatus}
     *
     * @return integer code from http status
     */
    public Integer getHttpStatusAsInteger() {
        return httpStatus.value();
    }

}
