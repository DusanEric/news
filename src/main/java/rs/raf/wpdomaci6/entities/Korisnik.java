package rs.raf.wpdomaci6.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Korisnik {

    private Integer id;

    @NotNull(message = "Name field is required")
    @NotEmpty(message = "Name field is required")
    String name;
    @NotNull(message = "Lastname field is required")
    @NotEmpty(message = "Lastname field is required")
    String lastname;
    @NotNull(message = "Email field is required")
    @NotEmpty(message = "Email field is required")
    String email;
    @NotNull(message = "Password field is required")
    @NotEmpty(message = "Password field is required")
    String password;
    @NotNull(message = "Type field is required")
    @NotEmpty(message = "Type field is required")
    String type;
    @NotNull(message = "Status field is required")
    boolean status;

    public Korisnik() {
    }

    public Korisnik(String name, String lastname, String email, String password, String type, boolean status) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.type = type;
        this.status = status;
    }

    public Korisnik(Integer id, String name, String lastname, String email, String password, String type, boolean status) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.type = type;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
