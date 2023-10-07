package rs.raf.wpdomaci6.services;

import rs.raf.wpdomaci6.entities.Kategorija;
import rs.raf.wpdomaci6.entities.Tag;
import rs.raf.wpdomaci6.repositories.kategorije.KategorijaRepository;
import rs.raf.wpdomaci6.repositories.tagovi.TagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {

    @Inject
    private TagRepository tagRepository;

    public Tag add(Tag tag) {
        return this.tagRepository.add(tag);
    }

    public List<Tag> all() {
        return this.tagRepository.allTags();
    }

    public Tag find(Integer id) {
        return this.tagRepository.find(id);
    }

    public void delete(Integer id) {
        this.tagRepository.delete(id);
    }
}
