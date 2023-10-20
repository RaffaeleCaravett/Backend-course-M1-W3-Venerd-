package raffaelecaravetta.dao;

import raffaelecaravetta.entities.Catalogo;
import raffaelecaravetta.entities.Prestito;
import raffaelecaravetta.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public List<Prestito> findPrestitiScaduti() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date todayLessThirty = calendar.getTime();

        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :todayLessThirty AND p.restituzioneEffettiva IS NULL", Prestito.class);
        query.setParameter("todayLessThirty", todayLessThirty);
        List <Prestito> prestiti = query.getResultList();

        transaction.commit();

        return prestiti;
    }
}
