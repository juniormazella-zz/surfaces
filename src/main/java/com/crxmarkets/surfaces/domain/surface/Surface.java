package com.crxmarkets.surfaces.domain.surface;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a surface and has an array of integers representing the configuration
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 06/04/2019
 */
public class Surface {

    private final List<Integer> configuration = new LinkedList<>();

    /**
     * Constructor for {@link Surface}
     *
     * @param configuration
     *         of surface to perform the actions
     */
    public Surface(final Integer[] configuration) {
        this.configuration.addAll(Arrays.asList(configuration));
    }

    /**
     * This method has as responsibility to carry out a series of iterations to
     * calculate the volume of the surface in question. The algorithm will look for holes
     * that are "fillables and will calculate the total surface volume from the beginning of the array to the end
     *
     * @return a value object containing the calculated total volume value
     */
    public Volume getVolumeAfterFlood() {
        Integer leftIndex = 0;
        Integer rightIndex = configuration.size() - 1;
        Integer leftMax = 0;
        Integer rightMax = 0;
        Integer volume = 0;

        do {
            if (configuration.get(leftIndex) <= configuration.get(rightIndex)) {
                leftMax = Math.max(leftMax, configuration.get(leftIndex));
                volume += leftMax - configuration.get(leftIndex);
                leftIndex++;
            } else {
                rightMax = Math.max(rightMax, configuration.get(rightIndex));
                volume += rightMax - configuration.get(rightIndex);
                rightIndex--;
            }
        } while (leftIndex < rightIndex);

        return new Volume(volume);
    }

}
