package raffaelecaravetta.dao;

import raffaelecaravetta.entities.Catalogo;
import raffaelecaravetta.entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PrestitoDao {
    private final EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }
    public void save(Prestito p) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(p);

        transaction.commit();
        System.out.println("Nuovo prestito salvato correttamente");
    }
}
