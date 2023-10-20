package raffaelecaravetta.entities;

import raffaelecaravetta.enums.Genere;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Libro")

public class Libro extends Catalogo{
    private String autore;
    private Genere genere;

    public Libro() {
    }

    public Libro(long codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, String autore, Genere genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }
}
