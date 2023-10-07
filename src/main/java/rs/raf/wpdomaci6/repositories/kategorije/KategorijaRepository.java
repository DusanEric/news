package rs.raf.wpdomaci6.repositories.kategorije;

import rs.raf.wpdomaci6.entities.Kategorija;
import rs.raf.wpdomaci6.entities.Korisnik;

import java.util.List;

public interface KategorijaRepository {

    public Kategorija add(Kategorija kategorija);
    public List<Kategorija> allKategorije();

    public Kategorija find(Integer id);

    public void delete(Integer id);

    Kategorija update(Kategorija kategorija);
}
