package rs.raf.wpdomaci6.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TagVesti {

    private Integer id;

    @NotNull(message = "Tag field is required")
    String tag;
    @NotNull(message = "Vest field is required")
    int vest;

    public TagVesti() {
    }

    public TagVesti(Integer id, String tag, int vest) {
        this.id = id;
        this.tag = tag;
        this.vest = vest;
    }

    public TagVesti(String tag, int vest) {
        this.tag = tag;
        this.vest = vest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getVest() {
        return vest;
    }

    public void setVest(int vest) {
        this.vest = vest;
    }
}
