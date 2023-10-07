package rs.raf.wpdomaci6.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Tag {
    private Integer id;

    @NotNull(message = "Description field is required")
    @NotEmpty(message = "Description of creation is required")
    String description;

    public Tag() {
    }

    public Tag(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Tag(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
