package rs.raf.wpdomaci6;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import rs.raf.wpdomaci6.repositories.kategorije.KategorijaRepository;
import rs.raf.wpdomaci6.repositories.kategorije.MySqlKategorijaRepository;
import rs.raf.wpdomaci6.repositories.komentari.KomentarRepository;
import rs.raf.wpdomaci6.repositories.komentari.MySqlKomentarRepository;
import rs.raf.wpdomaci6.repositories.korisnici.KorisnikRepository;
import rs.raf.wpdomaci6.repositories.korisnici.MySqlKorisnikRepository;
import rs.raf.wpdomaci6.repositories.tagovi.MySqlTagRepository;
import rs.raf.wpdomaci6.repositories.tagovi.MySqlTagVestiRepository;
import rs.raf.wpdomaci6.repositories.tagovi.TagRepository;
import rs.raf.wpdomaci6.repositories.tagovi.TagVestiRepository;
import rs.raf.wpdomaci6.repositories.vesti.MySqlVestRepository;
import rs.raf.wpdomaci6.repositories.vesti.VestRepository;
import rs.raf.wpdomaci6.services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {
    public HelloApplication() {
        // Ukljucujemo validaciju
//        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlKorisnikRepository.class).to(KorisnikRepository.class).in(Singleton.class);
                this.bind(MySqlKategorijaRepository.class).to(KategorijaRepository.class).in(Singleton.class);
                this.bind(MySqlTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(MySqlVestRepository.class).to(VestRepository.class).in(Singleton.class);
                this.bind(MySqlKomentarRepository.class).to(KomentarRepository.class).in(Singleton.class);
                this.bind(MySqlTagVestiRepository.class).to(TagVestiRepository.class).in(Singleton.class);

                this.bindAsContract(KorisnikService.class);
                this.bindAsContract(KategorijaService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(VestService.class);
                this.bindAsContract(KomentarService.class);
                this.bindAsContract(TagVestiService.class);

            }
        };
        register(binder);


        // Ucitavamo resurse
        packages("rs.raf.wpdomaci6");
    }
}



