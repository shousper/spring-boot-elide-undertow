package com.shousper.jsonapi.config;

import com.shousper.jsonapi.controller.ApiController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        register(ApiController.class);
    }
}
