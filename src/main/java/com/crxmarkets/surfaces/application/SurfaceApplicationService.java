package com.crxmarkets.surfaces.application;

import com.crxmarkets.surfaces.domain.surface.Surface;
import com.crxmarkets.surfaces.domain.surface.Volume;
import com.crxmarkets.surfaces.infra.validation.SurfaceConfigurationValidation;
import org.springframework.stereotype.Service;

/**
 * This class represents the application layer,
 * through which it is possible to access the objects of the domain model
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 06/04/2019
 */
@Service
public class SurfaceApplicationService {

    /**
     * This method takes as parameter the surface configuration,
     * instantiates a new object, and calls the method that will perform the volume
     * calculation. Before the method is executed there is a validation to be performed,
     * it is configured to be executed through an aspect
     *
     * @param configuration
     *         of surface to perform the actions
     *
     * @return a value object containing the calculated total volume value
     *
     * @see com.crxmarkets.surfaces.infra.validation.SurfaceConfigurationValidationAspect
     */
    @SurfaceConfigurationValidation
    public Volume getVolumeAfterFlood(final Integer[] configuration) {
        final Surface surface = new Surface(configuration);
        return surface.getVolumeAfterFlood();
    }

}
