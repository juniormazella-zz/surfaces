package com.crxmarkets.surfaces.domain.surface;

import com.crxmarkets.surfaces.infra.exception.SurfaceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception represents that some surface configuration rule has been violated
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SurfaceConfigurationException extends SurfaceException {

    public SurfaceConfigurationException(final String message) {
        super(message);
    }

}
