package rs.raf.wpdomaci6.resources;

import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.Vest;
import rs.raf.wpdomaci6.requests.LoginRequest;
import rs.raf.wpdomaci6.services.KorisnikService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/korisnici")
public class KorisnikResource {
    @Inject
    private KorisnikService korisnikService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.korisnikService.all()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik create(@Valid Korisnik korisnik) {
        return this.korisnikService.add(korisnik);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik find(@PathParam("id") Integer id) {
        return this.korisnikService.find(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.korisnikService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik update(@PathParam("id") Integer id, @Valid Korisnik korisnik) throws Exception {
        // Set the ID of the category
        korisnik.setId(id);
        return this.korisnikService.update(korisnik);
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();

        String jwt = this.korisnikService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }
}
