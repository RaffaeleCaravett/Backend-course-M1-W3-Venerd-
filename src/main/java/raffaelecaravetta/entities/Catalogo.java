package raffaelecaravetta.entities;
import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "catalogs")
@DiscriminatorColumn(name = "tipo_elemento")

public abstract class Catalogo {
    @Id // Serve per definire chi sarà la chiave primaria
    @GeneratedValue
    private long codiceISBN;
    @Column(name = "titolo", length = 255)
    private String Titolo;
    @Column(name = "anno_pubblicazione")
    private int annoPubblicazione;
    @Column(name = "numero_pagine")
    private int numeroPagine;

    @ManyToMany
    @JoinTable(name = "elementi_prestiti",
        joinColumns = @JoinColumn(name = "elemento_ISBN"),
        inverseJoinColumns = @JoinColumn(name = "prestito_id"))
    private Set<Prestito> prestito;

    public Catalogo(){}

    public Catalogo( String titolo, int annoPubblicazione, int numeroPagine) {

        Titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public long getCodiceISBN() {
        return codiceISBN;
    }



    public String getTitolo() {
        return Titolo;
    }

    public void setTitolo(String titolo) {
        Titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }


    @Override
    public String toString() {
        return "Catalogo{" +
            "codiceISBN=" + codiceISBN +
            ", Titolo='" + Titolo + '\'' +
            ", annoPubblicazione=" + annoPubblicazione +
            ", numeroPagine=" + numeroPagine +
            ", prestito=" + prestito +
            '}';
    }
}
