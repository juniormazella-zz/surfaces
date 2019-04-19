package com.crxmarkets.surfaces.restapi;

import com.crxmarkets.surfaces.application.SurfaceApplicationService;
import com.crxmarkets.surfaces.domain.surface.Volume;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a {@link RestController} to manipulate
 * information about {@link com.crxmarkets.surfaces.domain.surface.Surface}
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 06/04/2019
 */
@RestController
@AllArgsConstructor
public class SurfaceController {

    private final SurfaceApplicationService surfaceApplicationService;

    /**
     * This endpoint exposes the service that will receive the surface configuration
     * and calculates to determine the volume so that it can fill the surface
     *
     * @param configuration
     *         of the surface to be calculated
     *
     * @return a value object containing the calculated total volume value
     */
    @GetMapping("/surfaces/volumes")
    public Volume calculteVolumeFromSurface(@RequestParam("configuration") final Integer[] configuration) {
        return surfaceApplicationService.getVolumeAfterFlood(configuration);
    }

}
