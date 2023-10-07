package rs.raf.wpdomaci6.repositories.vesti;

import rs.raf.wpdomaci6.entities.Kategorija;
import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.Vest;

import java.util.List;

public interface VestRepository {

    public Vest add(Vest vest);
    public List<Vest> allVesti();

    public Vest find(Integer id);

    public void delete(Integer id);

    Vest update(Vest vest);

    List<Vest> findByKategorija(String kategorija);
}
