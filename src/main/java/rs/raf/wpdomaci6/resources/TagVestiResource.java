package rs.raf.wpdomaci6.resources;

import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.TagVesti;
import rs.raf.wpdomaci6.services.KorisnikService;
import rs.raf.wpdomaci6.services.TagVestiService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tagsVesti")
public class TagVestiResource {

    @Inject
    private TagVestiService tagVestiService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.tagVestiService.all()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public TagVesti create(@Valid TagVesti tagVesti) {
        return this.tagVestiService.add(tagVesti);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TagVesti find(@PathParam("id") Integer id) {
        return this.tagVestiService.find(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.tagVestiService.delete(id);
    }
}
