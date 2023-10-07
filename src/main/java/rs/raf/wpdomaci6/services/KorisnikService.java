package rs.raf.wpdomaci6.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.wpdomaci6.entities.Korisnik;
import rs.raf.wpdomaci6.entities.Vest;
import rs.raf.wpdomaci6.repositories.korisnici.KorisnikRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class KorisnikService {

    @Inject
    private KorisnikRepository korisnikRepository;

    public Korisnik add(Korisnik korisnik) {
        return this.korisnikRepository.add(korisnik);
    }

    public List<Korisnik> all() {
        return this.korisnikRepository.allKorisnici();
    }

    public Korisnik find(Integer id) {
        return this.korisnikRepository.find(id);
    }

    public void delete(Integer id) {
        this.korisnikRepository.delete(id);
    }

    public Korisnik update(Korisnik korisnik) throws Exception {
        // Retrieve the existing category from the repository
        Korisnik existingKorisnik = korisnikRepository.find(korisnik.getId());
        if (existingKorisnik == null) {
            throw new Exception("Korisnik not found");
        }

        existingKorisnik.setName(korisnik.getName());
        existingKorisnik.setLastname(korisnik.getLastname());
        existingKorisnik.setEmail(korisnik.getEmail());
        existingKorisnik.setPassword(korisnik.getPassword());
        existingKorisnik.setType(korisnik.getType());
        existingKorisnik.setStatus(korisnik.isStatus());

        return korisnikRepository.update(existingKorisnik);
    }

    public String login(String email, String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);

        Korisnik korisnik = new Korisnik();

        for (Korisnik k : korisnikRepository.allKorisnici()){
            if (k.getEmail().equals(email)){
                korisnik = k;
            }
        }
         String hashPassKorisnik = DigestUtils.sha256Hex(korisnik.getPassword());
//        Korisnik k1 = this.korisnikRepository.findUser(email);
        if (korisnik == null || !hashPassKorisnik.equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("type", korisnik.getType())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
//        jwt.getClaim("role").asString();

        Korisnik korisnik = new Korisnik();

        for (Korisnik k : korisnikRepository.allKorisnici()){
            if (k.getEmail().equals(email)){
                korisnik = k;
            }
        }

//        Korisnik k1 = this.korisnikRepository.findUser(username);

        if (korisnik == null){
            return false;
        }

        return true;
    }

    public String getUserType(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            return jwt.getClaim("type").asString();
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            return null;
        }
    }
    public Integer getUserStatus(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            return jwt.getClaim("status").as(Integer.class);
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            return 0;
        }
    }
}
