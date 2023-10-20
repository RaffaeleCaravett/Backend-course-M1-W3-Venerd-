package raffaelecaravetta.entities;

import raffaelecaravetta.enums.Periodicità;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Rivista")

public class Rivista extends Catalogo {
    private Periodicità periodicità;

    public Rivista(Periodicità periodicità) {
        this.periodicità = periodicità;
    }

    public Rivista(long codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, Periodicità periodicità) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicità = periodicità;
    }

    public Periodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(Periodicità periodicità) {
        this.periodicità = periodicità;
    }
}
