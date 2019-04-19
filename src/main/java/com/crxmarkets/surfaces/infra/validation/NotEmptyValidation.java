package com.crxmarkets.surfaces.infra.validation;

import com.crxmarkets.surfaces.domain.surface.SurfaceConfigurationException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * This validation implementation verifies that the array with the surface configuration is not empty
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@Component
public class NotEmptyValidation implements Validation {

    @Override
    public void validate(final Integer[] surfaceConfiguration) {
        final List<Integer> configuration = Arrays.asList(surfaceConfiguration).parallelStream().filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(configuration)) {
            throw new SurfaceConfigurationException("Surface setting can not have element null or empty");
        }
    }

}
