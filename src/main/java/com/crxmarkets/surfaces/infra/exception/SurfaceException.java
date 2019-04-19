package com.crxmarkets.surfaces.infra.exception;

/**
 * This exception is a business exception, and it extends RuntimeException,
 * every business rule that is violated must throw a SurfaceException.
 * The exceptions that extend SurfaceException will be handled in a
 * {@link org.springframework.web.bind.annotation.RestControllerAdvice} and
 * will be treated to return a friendly message
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 * @see com.crxmarkets.surfaces.infra.controlleradvice.DefaultRestControllerAdvice
 */
public abstract class SurfaceException extends RuntimeException {

    /**
     * This constructor receives the exception error message
     * so that it is possible to instantiate the object and do the correct treatment.
     *
     * @param message
     *         error message that will be passed to parent class and will be logged in stout
     */
    public SurfaceException(final String message) {
        super(message);
    }

}
