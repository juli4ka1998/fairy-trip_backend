package com.fairytrip.restresources.jwtconfiguration;

import com.fairytrip.restresources.jwtconfiguration.JsTokenFilterNeeded;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.HashMap;
import java.util.Map;

public class JWTConfig extends ResourceConfig{
    public JWTConfig() {
        packages("com.fairytrip.restresources.jwtconfiguration");
        register(JsTokenFilterNeeded.class);
//        packages("org.glassfish.jersey.media.multipart");
//        register(MultiPartFeature.class);
    }

}
