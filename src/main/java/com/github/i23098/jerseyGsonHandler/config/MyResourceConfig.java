package com.github.i23098.jerseyGsonHandler.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/ws")
public class MyResourceConfig extends ResourceConfig {
    public MyResourceConfig() {
        packages("com.github.i23098.jerseyGsonHandler.resource");
        register(GsonMessageBodyHandler.class);
    }
}
