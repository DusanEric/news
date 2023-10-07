package rs.raf.wpdomaci6.repositories.tagovi;

import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.Tag;

import java.util.List;

public interface TagRepository {
    public Tag add(Tag tag);
    public List<Tag> allTags();

    public Tag find(Integer id);

    public void delete(Integer id);
}
