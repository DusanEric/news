package rs.raf.wpdomaci6.repositories.tagovi;

import rs.raf.wpdomaci6.entities.TagVesti;

import java.util.List;

public interface TagVestiRepository {

    public TagVesti add(TagVesti tag);
    public List<TagVesti> allTagsVesti();

    public TagVesti find(Integer id);

    public void delete(Integer id);
}
