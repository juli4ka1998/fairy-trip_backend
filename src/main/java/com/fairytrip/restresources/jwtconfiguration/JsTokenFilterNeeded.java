package com.fairytrip.restresources.jwtconfiguration;


import com.fairytrip.restresources.BaseResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@JsonTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JsTokenFilterNeeded implements ContainerRequestFilter {

    private static final String AUTHORIZATION_SERVICE_PATH = "check_admin";
    private static final String PRIVATE_KEY = "privateKey";
    private JwTokenHelper jwTokenHelper = JwTokenHelper.getInstance();

    @Override
    public void filter(ContainerRequestContext request)  throws IOException{
        String path = request.getUriInfo().getPath();
        if (path.contains(AUTHORIZATION_SERVICE_PATH))
            return;

        String privateKeyHeaderValue = request.getHeaderString(PRIVATE_KEY);
        String token = privateKeyHeaderValue.substring("Bearer".length()).trim();
        if (token == null || token.isEmpty())
            throw new WebApplicationException(getUnAuthorizeResponse(PRIVATE_KEY + " is missing inside the header"));
        try {
            jwTokenHelper.claimKey(token);
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException)
                throw new WebApplicationException(getUnAuthorizeResponse(PRIVATE_KEY + " is expired."));
            else if (e instanceof MalformedJwtException)
                throw new WebApplicationException(getUnAuthorizeResponse(PRIVATE_KEY + " is not correct."));
        }
    }

    private Response getUnAuthorizeResponse(String message) {
        return Response.ok(new BaseResponse(message, BaseResponse.FAILURE))
                .status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}