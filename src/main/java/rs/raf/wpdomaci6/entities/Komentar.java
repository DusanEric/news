package rs.raf.wpdomaci6.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Komentar {

    private Integer id;

    @NotNull(message = "Autor field is required")
    @NotEmpty(message = "Autor of creation is required")
    String autor;
    @NotNull(message = "Text field is required")
    @NotEmpty(message = "Text field is required")
    String text;
    @NotNull(message = "Date field is required")
    @NotEmpty(message = "Date is required")
    String date;
    @NotNull(message = "Vest_id field is required")
    int vest_id;

    public Komentar() {
    }

    public Komentar(Integer id, String autor, String text, String date, int vest_id) {
        this.id = id;
        this.autor = autor;
        this.text = text;
        this.date = date;
        this.vest_id = vest_id;
    }

    public Komentar(String autor, String text, String date, int vest_id) {
        this.autor = autor;
        this.text = text;
        this.date = date;
        this.vest_id = vest_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVest_id() {
        return vest_id;
    }

    public void setVest_id(int vest_id) {
        this.vest_id = vest_id;
    }
}
