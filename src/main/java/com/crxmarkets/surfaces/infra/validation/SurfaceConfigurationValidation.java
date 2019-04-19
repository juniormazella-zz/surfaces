package com.crxmarkets.surfaces.infra.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation was created only to mark where the validation will take place by class SurfaceConfigurationValidationAspect
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 * @see SurfaceConfigurationValidationAspect
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SurfaceConfigurationValidation {

}
