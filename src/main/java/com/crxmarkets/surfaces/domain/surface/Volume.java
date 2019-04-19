package com.crxmarkets.surfaces.domain.surface;

import com.crxmarkets.surfaces.infra.serializer.VolumeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class is just a value object to represent
 * the result of calculating the volume of some surface
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@AllArgsConstructor
@Getter
@JsonSerialize(using = VolumeSerializer.class)
public class Volume {

    private final Integer value;

}
