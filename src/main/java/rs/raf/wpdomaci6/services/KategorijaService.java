package rs.raf.wpdomaci6.services;

import rs.raf.wpdomaci6.entities.Kategorija;
import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.repositories.kategorije.KategorijaRepository;
import rs.raf.wpdomaci6.repositories.korisnici.KorisnikRepository;

import javax.inject.Inject;
import java.util.List;

public class KategorijaService {

    @Inject
    private KategorijaRepository kategorijaRepository;

    public Kategorija add(Kategorija kategorija) {
        return this.kategorijaRepository.add(kategorija);
    }

    public List<Kategorija> all() {
        return this.kategorijaRepository.allKategorije();
    }

    public Kategorija find(Integer id) {
        return this.kategorijaRepository.find(id);
    }

    public void delete(Integer id) {
        this.kategorijaRepository.delete(id);
    }

    public Kategorija update(Kategorija kategorija) throws Exception {
        // Retrieve the existing category from the repository
        Kategorija existingKategorija = kategorijaRepository.find(kategorija.getId());
        if (existingKategorija == null) {
            throw new Exception("Category not found");
        }

        // Update the fields of the existing category
        existingKategorija.setName(kategorija.getName());
        existingKategorija.setDescription(kategorija.getDescription());

        // Save the updated category in the repository
        return kategorijaRepository.update(existingKategorija);
    }
}
