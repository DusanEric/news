package rs.raf.wpdomaci6.filters;

import rs.raf.wpdomaci6.resources.KategorijaResource;
import rs.raf.wpdomaci6.resources.KorisnikResource;
import rs.raf.wpdomaci6.services.KorisnikService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    KorisnikService korisnikService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

//        if (true) return;

        if (!this.isAuthRequired(requestContext)) {
            return;
        }

        try {
            String token = requestContext.getHeaderString("Authorization");
            if(token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }

            if (!this.korisnikService.isAuthorized(token)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (Exception exception) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext req) {
        if (req.getUriInfo().getPath().contains("login")) {
            return false;
        }

        String token = req.getHeaderString("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");

            String userType = korisnikService.getUserType(token);

            //SVE STO JE VEZANO ZA SUBJECT TREBA AWT
            List<Object> matchedResources = req.getUriInfo().getMatchedResources();
            for (Object matchedResource : matchedResources) {
                if (matchedResource instanceof KorisnikResource && "creator".equals(userType)) {
                    req.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            }
        }

        return false;
    }
}
