package rs.raf.wpdomaci6.resources;

import rs.raf.wpdomaci6.entities.Kategorija;
import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.services.KategorijaService;
import rs.raf.wpdomaci6.services.KorisnikService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/kategorije")
public class KategorijaResource {

    @Inject
    private KategorijaService kategorijaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.kategorijaService.all()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija create(@Valid Kategorija kategorija) {
        return this.kategorijaService.add(kategorija);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija find(@PathParam("id") Integer id) {
        return this.kategorijaService.find(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.kategorijaService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija update(@PathParam("id") Integer id, @Valid Kategorija kategorija) throws Exception {
        // Set the ID of the category
        kategorija.setId(id);
        return this.kategorijaService.update(kategorija);
    }
}
