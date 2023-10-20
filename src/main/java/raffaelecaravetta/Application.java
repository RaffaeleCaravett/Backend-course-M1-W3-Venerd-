package raffaelecaravetta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogoBibliograficoEsteso");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();


    }
}
