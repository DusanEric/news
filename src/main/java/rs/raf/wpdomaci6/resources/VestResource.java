package rs.raf.wpdomaci6.resources;

import rs.raf.wpdomaci6.entities.Kategorija;
import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.Vest;
import rs.raf.wpdomaci6.services.KorisnikService;
import rs.raf.wpdomaci6.services.VestService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/vesti")
public class VestResource {

    @Inject
    private VestService vestService;

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response all() {
//        return Response.ok(this.vestService.all()).build();
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(@QueryParam("kategorija") String kategorija) {
        if (kategorija != null && !kategorija.isEmpty()) {
            List<Vest> filteredVesti = this.vestService.findByKategorija(kategorija);
            return Response.ok(filteredVesti).build();
        } else {
            return Response.ok(this.vestService.all()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Vest create(@Valid Vest vest) {
        return this.vestService.add(vest);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vest find(@PathParam("id") Integer id) {
        return this.vestService.find(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.vestService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vest update(@PathParam("id") Integer id, @Valid Vest vest) throws Exception {
        // Set the ID of the category
        vest.setId(id);
        return this.vestService.update(vest);
    }
}
