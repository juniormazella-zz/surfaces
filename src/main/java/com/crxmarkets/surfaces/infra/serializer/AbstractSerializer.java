package com.crxmarkets.surfaces.infra.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/**
 * This class is a base serializer for the other implementations
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 09/04/2019
 */
public abstract class AbstractSerializer<T> extends JsonSerializer<T> {

    @Override
    public void serialize(final T t, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        serialize(t, new JsonWriter(jsonGenerator));
    }

    /**
     * This method is a way to serialize objects of value.
     * Each implementation must know the rule of writing the data that should be returned from the API
     *
     * @param t
     *         object type that will be serialized
     * @param jsonWriter
     *         helper class to help in writing the information in the context of serialization
     *
     * @throws IOException
     *         exception that can be thrown during the json writing process
     */
    public abstract void serialize(final T t, final JsonWriter jsonWriter) throws IOException;

}
