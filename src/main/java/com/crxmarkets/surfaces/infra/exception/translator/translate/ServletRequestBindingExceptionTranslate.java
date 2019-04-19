package com.crxmarkets.surfaces.infra.exception.translator.translate;

import com.crxmarkets.surfaces.infra.exception.translator.error.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 15/04/2019
 */
@Component
public class ServletRequestBindingExceptionTranslate implements ExceptionTranslate<ServletRequestBindingException> {

    private final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @Override
    public ResponseEntity translateException(final ServletRequestBindingException e) {
        final ErrorData errorData = new ErrorData(BAD_REQUEST, e.getMessage());
        return new ResponseEntity(errorData, BAD_REQUEST);
    }

}
