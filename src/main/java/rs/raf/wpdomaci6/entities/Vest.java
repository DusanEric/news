package rs.raf.wpdomaci6.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Vest {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    String title;
    @NotNull(message = "Text field is required")
    @NotEmpty(message = "Text field is required")
    String text;
    @NotNull(message = "Number of visits field is required")
//    @NotEmpty(message = "Number of visits field is required")
    int number_of_visits;
    @NotNull(message = "Date of creation field is required")
    @NotEmpty(message = "Date of creation is required")
    String date_of_creation;
    @NotNull(message = "Autor field is required")
    String autor;
    @NotNull(message = "Kategorija field is required")
    String kategorija;

    @NotNull(message = "Date of creation field is required")
    @NotEmpty(message = "Date of creation is required")
    String tagovi;

    public Vest() {
    }

    public Vest(String title, String text, int number_of_visits, String date_of_creation, String autor, String kategorija, String tagovi) {
        this.title = title;
        this.text = text;
        this.number_of_visits = number_of_visits;
        this.date_of_creation = date_of_creation;
        this.autor = autor;
        this.kategorija = kategorija;
        this.tagovi = tagovi;
    }

    public Vest(Integer id, String title, String text, int number_of_visits, String date_of_creation, String autor, String kategorija, String tagovi) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.number_of_visits = number_of_visits;
        this.date_of_creation = date_of_creation;
        this.autor = autor;
        this.kategorija = kategorija;
        this.tagovi = tagovi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber_of_visits() {
        return number_of_visits;
    }

    public void setNumber_of_visits(int number_of_visits) {
        this.number_of_visits = number_of_visits;
    }

    public String getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(String date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getTagovi() {
        return tagovi;
    }

    public void setTagovi(String tagovi) {
        this.tagovi = tagovi;
    }
}
