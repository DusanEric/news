package rs.raf.wpdomaci6.services;

import rs.raf.wpdomaci6.entities.Komentar;
import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.repositories.komentari.KomentarRepository;
import rs.raf.wpdomaci6.repositories.korisnici.KorisnikRepository;

import javax.inject.Inject;
import java.util.List;

public class KomentarService {

    @Inject
    private KomentarRepository komentarRepository;

    public Komentar add(Komentar komentar) {
        return this.komentarRepository.add(komentar);
    }

    public List<Komentar> all() {
        return this.komentarRepository.allKomentari();
    }

    public Komentar find(Integer id) {
        return this.komentarRepository.find(id);
    }

    public void delete(Integer id) {
        this.komentarRepository.delete(id);
    }
}
