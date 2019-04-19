package com.crxmarkets.surfaces.infra.exception.translator.translate;

import com.crxmarkets.surfaces.infra.exception.translator.error.ErrorData;
import lombok.AllArgsConstructor;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 15/04/2019
 */
@Component
@AllArgsConstructor
public class TypeMismatchExceptionTranslate implements ExceptionTranslate<TypeMismatchException> {

    private final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @Override
    public ResponseEntity translateException(final TypeMismatchException e) {
        final ErrorData errorData = new ErrorData(BAD_REQUEST, e.getMessage());
        return new ResponseEntity(errorData, BAD_REQUEST);
    }

}
