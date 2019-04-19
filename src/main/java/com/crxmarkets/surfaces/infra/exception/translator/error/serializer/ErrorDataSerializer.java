package com.crxmarkets.surfaces.infra.exception.translator.error.serializer;

import com.crxmarkets.surfaces.infra.exception.translator.error.ErrorData;
import com.crxmarkets.surfaces.infra.serializer.AbstractSerializer;
import com.crxmarkets.surfaces.infra.serializer.JsonWriter;
import java.io.IOException;
import lombok.AllArgsConstructor;

/**
 * This class knows the path and rule for serializing the object {@link ErrorData}
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
public class ErrorDataSerializer extends AbstractSerializer<ErrorData> {

    @Override
    public void serialize(final ErrorData errorData, final JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField(Field.CODE, errorData.getHttpStatusAsInteger());
        jsonWriter.writeStringField(Field.MESSAGE, errorData.getErrorMessage());
        jsonWriter.writeEndObject();
    }

    @AllArgsConstructor
    private enum Field {

        CODE("code"),
        MESSAGE("message");

        private final String label;

        @Override
        public String toString() {
            return this.label;
        }
    }

}
