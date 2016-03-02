package com.shousper.jsonapi.controller;

import com.yahoo.elide.Elide;
import com.yahoo.elide.ElideResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.core.MultivaluedHashMap;
import java.util.Map;

@Controller
@RequestMapping(
        path = "/api",
        consumes = "application/vnd.api+json",
        produces = "application/vnd.api+json")
@Transactional
public class ApiController {

    @Autowired
    private Elide elide;

    @RequestMapping(method = RequestMethod.POST, path = "/**")
    public ResponseEntity<String> post(@RequestBody final String body,
                                       final HttpServletRequest request) {
        return build(elide.post(getElidePath(request), null, body));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/**")
    public ResponseEntity<String> get(@RequestParam final Map<String,String> params,
                                      final HttpServletRequest request) {
        return build(elide.get(getElidePath(request), new MultivaluedHashMap<>(params), null));
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/**")
    public ResponseEntity<String> patch(@RequestHeader(HttpHeaders.CONTENT_TYPE) final String contentType,
                                        @RequestHeader(HttpHeaders.ACCEPT) final String accept,
                                        @RequestBody final String body,
                                        final HttpServletRequest request) {
        return build(elide.patch(contentType, accept, getElidePath(request), null, body));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/**")
    public ResponseEntity<String> delete(@RequestBody final String body, final HttpServletRequest request) {
        return build(elide.delete(getElidePath(request), null, body));
    }

    private static ResponseEntity<String> build(final ElideResponse response) {
        return new ResponseEntity<>(
                response.getBody(),
                HttpStatus.valueOf(response.getResponseCode()));
    }

    private static String getElidePath(final HttpServletRequest request) {
        final String requestPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        // Chop off base path for Elide.
        return requestPath.substring("/api/".length());
    }
}
