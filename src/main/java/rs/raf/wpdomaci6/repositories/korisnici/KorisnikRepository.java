package rs.raf.wpdomaci6.repositories.korisnici;

import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.Vest;

import java.util.List;

public interface KorisnikRepository {

    public Korisnik add(Korisnik korisnik);
    public List<Korisnik> allKorisnici();

    public Korisnik find(Integer id);

    public void delete(Integer id);

    Korisnik update(Korisnik korisnik);
}
