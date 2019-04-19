package com.crxmarkets.surfaces.infra.validation;

import com.crxmarkets.surfaces.infra.exception.SurfaceException;

/**
 * This interface is a contract that must be implemented by
 * all classes that will do some type of validation in the surface configuration
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@FunctionalInterface
public interface Validation {

    /**
     * This method must be overwritten with the implementation of the rule that wishes to be performed
     *
     * @param surfaceConfiguration
     *         configuration of the surface that will be validated
     *
     * @throws SurfaceException
     *         exception that must be thrown if the validation rule is violated
     */
    void validate(final Integer[] surfaceConfiguration);

}
