package raffaelecaravetta.dao;

import raffaelecaravetta.entities.Catalogo;
import raffaelecaravetta.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UtenteDao {
    private final EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }
    public void save(Utente u) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(u);

        transaction.commit();
        System.out.println("Nuovo utente salvato correttamente");
    }
}
