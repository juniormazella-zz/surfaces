package com.crxmarkets.surfaces.infra.exception.translator;

import com.crxmarkets.surfaces.infra.exception.translator.translate.ExceptionTranslate;
import java.util.LinkedList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@Component
public class DefaultExceptionTranslator {

    private final List<ExceptionTranslate> exceptionTranslates = new LinkedList<>();

    /**
     * Constructor for {@link DefaultExceptionTranslator}
     *
     * @param exceptionTranslates
     *         list of {@link ExceptionTranslate} injected by Spring
     */
    public DefaultExceptionTranslator(final List<ExceptionTranslate> exceptionTranslates) {
        this.exceptionTranslates.addAll(exceptionTranslates);
    }

    /**
     * This method will find the ExceptionTranslate for the exception passed as
     * parameter and will construct the ResponseEntity object with the exception information
     *
     * @param exception
     *         which was thrown on some layer of the system
     *
     * @return a populated instance with the exception information
     */
    public ResponseEntity translateException(final Exception exception) {
        final ExceptionTranslate exceptionTranslate = exceptionTranslates.stream().filter(e -> e.resolve(exception)).findFirst().get();
        return exceptionTranslate.translateException(exception);
    }

}
