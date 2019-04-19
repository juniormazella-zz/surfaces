package com.crxmarkets.surfaces.infra.exception.translator.translate;

import com.crxmarkets.surfaces.infra.exception.translator.DefaultExceptionTranslator;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.ResponseEntity;

/**
 * This abstract class is the basis for exception translator implementations, in this
 * class we have an abstract method where each implementation must place its rule
 * and a method that will be common to all and used in to {@link DefaultExceptionTranslator} solve the exception
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
public interface ExceptionTranslate<T> {

    /**
     * Any class that extends ExceptionTranslate should do the implementation
     * of how it should be done to translate the statement into ResponseEntity
     *
     * @param t
     *         type parameter I define in the implementation that will be the parameter that the method will receive as argument
     *
     * @return an implementation of ResponseEntity with the information returned by the implementation
     */
    ResponseEntity translateException(final T t);

    /**
     * This method will read the type parameter and make the comparison with the exception passed as parameter
     *
     * @param exception
     *         to verify that the current implementation translates the thrown exception
     *
     * @return whether or not the implementation translates the exception
     */
    default boolean resolve(final Exception exception) {
        return GenericTypeResolver.resolveTypeArgument(getClass(), ExceptionTranslate.class).equals(exception.getClass().getSuperclass());
    }

}
