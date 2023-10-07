package rs.raf.wpdomaci6.services;

import rs.raf.wpdomaci6.entities.Kategorija;
import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.Vest;
import rs.raf.wpdomaci6.repositories.korisnici.KorisnikRepository;
import rs.raf.wpdomaci6.repositories.vesti.VestRepository;

import javax.inject.Inject;
import java.util.List;

public class VestService {

    @Inject
    private VestRepository vestRepository;

    public Vest add(Vest vest) {
        return this.vestRepository.add(vest);
    }

    public List<Vest> all() {
        return this.vestRepository.allVesti();
    }

    public Vest find(Integer id) {
        return this.vestRepository.find(id);
    }

    public void delete(Integer id) {
        this.vestRepository.delete(id);
    }

    public Vest update(Vest vest) throws Exception {
        // Retrieve the existing category from the repository
        Vest existingVest = vestRepository.find(vest.getId());
        if (existingVest == null) {
            throw new Exception("Vest not found");
        }

        // Update the fields of the existing category
        existingVest.setTitle(vest.getTitle());
        existingVest.setText(vest.getText());
        existingVest.setNumber_of_visits(vest.getNumber_of_visits());
        existingVest.setDate_of_creation(vest.getDate_of_creation());
        existingVest.setAutor(vest.getAutor());
        existingVest.setKategorija(vest.getKategorija());
        existingVest.setTagovi(vest.getTagovi());

        // Save the updated category in the repository
        return vestRepository.update(existingVest);
    }

    public List<Vest> findByKategorija(String kategorija) {
        return vestRepository.findByKategorija(kategorija);
    }
}
