package raffaelecaravetta.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utente {
    @Column(name = "nome")

    private String nome;
    @Column(name = "cognome")

    private String cognome;
    @Column(name = "data_di_nascita")
    private Date dataDiNascita;
    @Id
    @GeneratedValue
    @Column(name = "numero_di_tessera")
    private long numeroDiTessera;
    @OneToMany(mappedBy = "utente")
    @OrderBy("dataInizioPrestito ASC")
    private List<Prestito> prestitoList;

    public Utente(){}

    public Utente(String nome, String cognome, Date dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public long getNumeroDiTessera() {
        return numeroDiTessera;
    }

    public List<Prestito> getPrestitoList() {
        return prestitoList;
    }

    public void setPrestitoList(List<Prestito> prestitoList) {
        this.prestitoList = prestitoList;
    }
}
