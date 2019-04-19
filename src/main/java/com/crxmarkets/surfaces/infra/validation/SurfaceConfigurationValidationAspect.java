package com.crxmarkets.surfaces.infra.validation;

import java.util.LinkedList;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * This class is one aspect of Spring
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@Component
@Aspect
public class SurfaceConfigurationValidationAspect {

    private final List<Validation> validations = new LinkedList<>();

    /**
     * Constructor for {@link SurfaceConfigurationValidationAspect}
     *
     * @param validations
     *         list of validation that will be injected by Spring, so all implementations of {@link Validation} must be annotated with {@link
     *         Component}
     */
    public SurfaceConfigurationValidationAspect(final List<Validation> validations) {
        this.validations.addAll(validations);
    }

    /**
     * This method acts as a chain, interacting among all {@link Validation} implementations, with the purpose of performing validations in the
     * surface configuration and will be invoked by spring before the getVolumeAfterFlood method is executed in the {@link
     * com.crxmarkets.surfaces.application.SurfaceApplicationService} class
     *
     * @param joinPoint
     *         implementation from Spring with resources to handle on method
     * @param surfaceConfigurationValidation
     *         annotation that determines where validation will occur
     */
    @Before("@annotation(surfaceConfigurationValidation)")
    public void performValidation(final JoinPoint joinPoint, final SurfaceConfigurationValidation surfaceConfigurationValidation) {
        final Integer[] surfaceConfiguration = (Integer[]) joinPoint.getArgs()[0];
        validations.stream().forEach(v -> v.validate(surfaceConfiguration));
    }

}
