package com.crxmarkets.surfaces.infra.exception.translator.translate;

import com.crxmarkets.surfaces.infra.exception.SurfaceException;
import com.crxmarkets.surfaces.infra.exception.translator.error.ErrorData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This {@link ExceptionTranslate} implementation has the objective of translating
 * the SurfaceException exception to a ResponseEntity with status code and error message
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@Component
public class SurfaceExceptionTranslate implements ExceptionTranslate<SurfaceException> {

    @Override
    public ResponseEntity translateException(final SurfaceException exception) {
        final ResponseStatus annotation = exception.getClass().getAnnotation(ResponseStatus.class);
        final ErrorData errorData = new ErrorData(annotation.code(), exception.getMessage());
        return new ResponseEntity(errorData, annotation.code());
    }

}
