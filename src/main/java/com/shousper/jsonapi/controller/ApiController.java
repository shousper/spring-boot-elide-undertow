package com.shousper.jsonapi.controller;

import com.yahoo.elide.Elide;
import com.yahoo.elide.resources.JsonApiEndpoint;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Controller
@Path("/api")
@Transactional
public class ApiController extends JsonApiEndpoint {

    @Inject
    public ApiController(@Named("elide") Elide elide,
                         @Named("elideUserExtractionFunction") DefaultOpaqueUserFunction getUser) {
        super(elide, getUser);
    }

    @Override
    public Response post(String path, SecurityContext securityContext, String jsonapiDocument) {
        return super.post(path, securityContext, jsonapiDocument);
    }

    @Override
    public Response get(String path, UriInfo uriInfo, SecurityContext securityContext) {
        return super.get(path, uriInfo, securityContext);
    }

    @Override
    public Response patch(String contentType, String accept, String path, SecurityContext securityContext, String jsonapiDocument) {
        return super.patch(contentType, accept, path, securityContext, jsonapiDocument);
    }

    @Override
    public Response delete(String path, SecurityContext securityContext, String jsonApiDocument) {
        return super.delete(path, securityContext, jsonApiDocument);
    }
}
