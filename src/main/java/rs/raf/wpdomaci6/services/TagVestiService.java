package rs.raf.wpdomaci6.services;

import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.TagVesti;
import rs.raf.wpdomaci6.repositories.korisnici.KorisnikRepository;
import rs.raf.wpdomaci6.repositories.tagovi.TagVestiRepository;

import javax.inject.Inject;
import java.util.List;

public class TagVestiService {

    @Inject
    private TagVestiRepository tagVestiRepository;

    public TagVesti add(TagVesti tagVesti) {
        return this.tagVestiRepository.add(tagVesti);
    }

    public List<TagVesti> all() {
        return this.tagVestiRepository.allTagsVesti();
    }

    public TagVesti find(Integer id) {
        return this.tagVestiRepository.find(id);
    }

    public void delete(Integer id) {
        this.tagVestiRepository.delete(id);
    }
}
