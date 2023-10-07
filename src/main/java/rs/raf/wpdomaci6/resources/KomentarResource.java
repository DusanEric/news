package rs.raf.wpdomaci6.resources;

import rs.raf.wpdomaci6.entities.Komentar;
import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.services.KomentarService;
import rs.raf.wpdomaci6.services.KorisnikService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/komentari")
public class KomentarResource {
    @Inject
    private KomentarService komentarService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.komentarService.all()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar create(@Valid Komentar komentar) {
        return this.komentarService.add(komentar);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar find(@PathParam("id") Integer id) {
        return this.komentarService.find(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.komentarService.delete(id);
    }
}
