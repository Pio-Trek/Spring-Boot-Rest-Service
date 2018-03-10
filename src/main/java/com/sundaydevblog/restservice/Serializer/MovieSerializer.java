package com.sundaydevblog.restservice.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sundaydevblog.restservice.model.Movie;

import java.io.IOException;

public class MovieSerializer extends StdSerializer<Movie> {

    public MovieSerializer() {
        this(null);
    }

    private MovieSerializer(Class<Movie> t) {
        super(t);
    }

    @Override
    public void serialize(Movie movie, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {

        generator.writeStartObject();
        generator.writeNumberField("id", movie.getId());
        generator.writeStringField("title", movie.getTitle());
        generator.writeNumberField("year", movie.getYear());
        generator.writeEndObject();
    }
}
