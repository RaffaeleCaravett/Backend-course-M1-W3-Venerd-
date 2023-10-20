package raffaelecaravetta.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "prestiti")
public class Prestito {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "utente_numero_di_tessera", nullable = false)
    private Utente utente;


@ManyToMany
@JoinTable(name = "elementi_prestiti",
    joinColumns = @JoinColumn(name = "prestito_id"),
    inverseJoinColumns = @JoinColumn(name = "elemento_ISBN"))
    private Set<Catalogo> elementoPrestato;

private Date dataInizioPrestito;
private Date dataRestituzionePrevista;
private Date restituzioneEffettiva;

    public Prestito(Utente utente, Set<Catalogo> elementoPrestato, Date dataInizioPrestito,  Date restituzioneEffettiva) {
        this.utente = utente;
        this.dataInizioPrestito = dataInizioPrestito;
        LocalDate localDataInizioPrestito = dataInizioPrestito.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDataRestituzionePrevista = localDataInizioPrestito.plusDays(30);
        this.dataRestituzionePrevista =  Date.from(localDataRestituzionePrevista.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.restituzioneEffettiva = restituzioneEffettiva;
    }

    public long getId() {
        return id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Set<Catalogo> getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Set<Catalogo> elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public Date getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(Date dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public Date getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(Date dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public Date getRestituzioneEffettiva() {
        return restituzioneEffettiva;
    }

    public void setRestituzioneEffettiva(Date restituzioneEffettiva) {
        this.restituzioneEffettiva = restituzioneEffettiva;
    }
}

