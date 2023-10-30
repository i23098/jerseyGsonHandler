package com.github.i23098.jerseyGsonHandler.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

/**
 * {@link MessageBodyReader} and {@link MessageBodyWriter} implementation using {@link Gson}
 */
public class GsonMessageBodyHandler implements MessageBodyReader<Object>, MessageBodyWriter<Object> {
    private final Gson gson;

    public GsonMessageBodyHandler() {
        this.gson = new GsonBuilder().create();
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Type jsonType = Collection.class.isAssignableFrom(type) ? genericType : type;
        Object o;
        try (Reader jsonReader = new InputStreamReader(entityStream, StandardCharsets.UTF_8)) {
            o = gson.fromJson(jsonReader, jsonType);
        }

        System.out.println("GsonMessageBodyHandler.readFrom() -= " + o + " =-");

        return o;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        Type jsonType = Collection.class.isAssignableFrom(type) ? genericType : type;
        try (OutputStreamWriter writer = new OutputStreamWriter(entityStream, StandardCharsets.UTF_8)) {
            gson.toJson(o, jsonType, writer);
        }

        System.out.println("GsonMessageBodyHandler.writeTo() -= " + o + " =-  " + gson.toJson(o, jsonType));
    }
}
