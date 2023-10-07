package rs.raf.wpdomaci6.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Kategorija {
    private Integer id;

    @NotNull(message = "Name field is required")
    @NotEmpty(message = "Name of creation is required")
    String name;
    @NotNull(message = "Descritpion field is required")
    @NotEmpty(message = "Descritpion field is required")
    String description;

    public Kategorija() {
    }

    public Kategorija(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Kategorija(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
