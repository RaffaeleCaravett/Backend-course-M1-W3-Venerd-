package raffaelecaravetta.dao;

import raffaelecaravetta.entities.Catalogo;
import raffaelecaravetta.entities.Prestito;
import raffaelecaravetta.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CatalogoDao {
    private final EntityManager em;

    public CatalogoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Catalogo c) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(c);

        transaction.commit();
        System.out.println("Nuovo elemento salvato correttamente");
    }

    public String findById(long isbn) {
        // SELECT * FROM students WHERE students.id=1
        return em.find(Catalogo.class, isbn).toString();
    }
    public List<Catalogo> findByAnnoPubblicazione(int anno) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        TypedQuery<Catalogo> query = em.createQuery("SELECT c FROM Catalogo c WHERE c.annoPubblicazione = :anno", Catalogo.class);
        query.setParameter("anno", anno);

        List<Catalogo> results = query.getResultList();

        transaction.commit();

        return results;
    }
    public List<Catalogo> findByAutore(String autore) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        TypedQuery<Catalogo> query = em.createQuery("SELECT c FROM Catalogo c WHERE c.autore = :autore", Catalogo.class);
        query.setParameter("autore", autore);

        List<Catalogo> results = query.getResultList();

        transaction.commit();

        return results;
    }
    public List<Catalogo> findByTitle(String titolo) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        TypedQuery<Catalogo> query = em.createQuery("SELECT c FROM Catalogo c WHERE c.Titolo LIKE CONCAT('%', :titolo, '%')", Catalogo.class);
        query.setParameter("titolo", titolo);

        List<Catalogo> results = query.getResultList();

        transaction.commit();

        return results;
    }
    public List<Catalogo> findByTesseraUtente(long tessera) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        TypedQuery<Utente> query = em.createQuery("SELECT u FROM Utente u WHERE u.numeroDiTessera = :tessera", Utente.class);
        query.setParameter("tessera", tessera);

        Utente utente = query.getSingleResult();

        List<Catalogo> catalogoSet = new ArrayList<>();
        for (Prestito prestito : utente.getPrestitoList()) {
            catalogoSet.addAll(prestito.getElementoPrestato());
        }

        transaction.commit();

        return catalogoSet;
    }

    public void findByIsbnAndDelete(long isbn) {

        // 1. Faccio una find per cercare lo studente
        Catalogo found = em.find(Catalogo.class, isbn);

        if (found != null) {

            EntityTransaction transaction = em.getTransaction();


            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("L'elemento è stato cancellato correttamente");
        } else {
            // 3. Altrimenti --> "Student not found"
            System.err.println("L'elemento con l'isbn " + isbn + " non è stato trovato");
        }

    }

    public List<Catalogo> filterBlogsByTitle(String title) {
        TypedQuery<Catalogo> getBlogs = em.createNamedQuery("findByPartialName", Catalogo.class);
        getBlogs.setParameter("title", title);
        return getBlogs.getResultList();
    }
}
