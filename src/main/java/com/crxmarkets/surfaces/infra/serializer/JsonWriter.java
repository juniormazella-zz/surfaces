package com.crxmarkets.surfaces.infra.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import lombok.AllArgsConstructor;

/**
 * This class has sole responsibility in the context of serialization,
 * making it easier to write the information in the API output file
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
@AllArgsConstructor
public class JsonWriter {

    private final JsonGenerator jsonGenerator;

    /**
     * This method delegates to the {@link JsonGenerator} the serialization initialization command
     *
     * @throws IOException
     *         exception that can be thrown during the json writing process
     */
    public void writeStartObject() throws IOException {
        jsonGenerator.writeStartObject();
    }

    /**
     * This method delegates to {@link JsonGenerator} the writing of a field of type {@link String}
     *
     * @param aEnum
     *         an enum implementation with the field name to be serialized
     * @param value
     *         to be written to the file
     *
     * @throws IOException
     *         exception that can be thrown during the json writing process
     */
    public void writeStringField(final Enum<?> aEnum, final String value) throws IOException {
        jsonGenerator.writeStringField(aEnum.toString(), value);
    }

    /**
     * This method delegates to {@link JsonGenerator} the writing of a field of type {@link Integer}
     *
     * @param aEnum
     *         an enum implementation with the field name to be serialized
     * @param value
     *         to be written to the file
     *
     * @throws IOException
     *         exception that can be thrown during the json writing process
     */
    public void writeNumberField(final Enum<?> aEnum, final Integer value) throws IOException {
        jsonGenerator.writeNumberField(aEnum.toString(), value);
    }

    /**
     * This method delegates to the {@link JsonGenerator} the command to end the serialization
     *
     * @throws IOException
     *         exception that can be thrown during the json writing process
     */
    public void writeEndObject() throws IOException {
        jsonGenerator.writeEndObject();
    }

}
