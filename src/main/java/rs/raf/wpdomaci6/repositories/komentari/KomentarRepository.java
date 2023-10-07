package rs.raf.wpdomaci6.repositories.komentari;

import rs.raf.wpdomaci6.entities.Komentar;
import rs.raf.wpdomaci6.entities.Korisnik;

import java.util.List;

public interface KomentarRepository {

    public Komentar add(Komentar komentar);
    public List<Komentar> allKomentari();

    public Komentar find(Integer id);

    public void delete(Integer id);
}
