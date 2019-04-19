package com.crxmarkets.surfaces.infra.serializer;

import com.crxmarkets.surfaces.domain.surface.Volume;
import java.io.IOException;
import lombok.AllArgsConstructor;

/**
 * This class knows the path and rule for serializing the object {@link Volume}
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
public class VolumeSerializer extends AbstractSerializer<Volume> {

    @Override
    public void serialize(final Volume volume, final JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField(Field.VOLUME, volume.getValue());
        jsonWriter.writeEndObject();
    }

    @AllArgsConstructor
    private enum Field {

        VOLUME("volume");

        private final String label;

        @Override
        public String toString() {
            return this.label;
        }

    }

}
