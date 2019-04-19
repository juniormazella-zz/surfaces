package com.crxmarkets.surfaces.infra.controlleradvice;

import com.crxmarkets.surfaces.infra.exception.translator.DefaultExceptionTranslator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is a {@link RestControllerAdvice}, which will handle exceptions thrown at runtime
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@RestControllerAdvice
@AllArgsConstructor
public class DefaultRestControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DefaultExceptionTranslator exceptionTranslator;

    /**
     * This method will only handle SurfaceException, so you should pay attention
     * in the implementation so you do not go through exceptions that do not extend SurfaceException
     *
     * @param exception
     *         exception being thrown by the system
     *
     * @return instance of ResponseEntity with the exception information
     *
     * @see DefaultExceptionTranslator
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(final Exception exception) {
        logger.error(exception.getMessage(), exception);
        return exceptionTranslator.translateException(exception);
    }

}
