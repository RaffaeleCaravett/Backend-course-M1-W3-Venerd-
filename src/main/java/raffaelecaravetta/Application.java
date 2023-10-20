package raffaelecaravetta;

import raffaelecaravetta.dao.CatalogoDao;
import raffaelecaravetta.dao.PrestitoDao;
import raffaelecaravetta.dao.UtenteDao;
import raffaelecaravetta.entities.*;
import raffaelecaravetta.enums.Genere;
import raffaelecaravetta.enums.Periodicità;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;

import java.time.LocalDate;
public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogoBibliograficoEsteso");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        CatalogoDao catalogoDao = new CatalogoDao(em);
        PrestitoDao prestitoDao = new PrestitoDao(em);
        UtenteDao utenteDao = new UtenteDao(em);

        List<Genere> genereList = new ArrayList<>();
        genereList.add(Genere.AVVENTURA);
        genereList.add(Genere.FANTASY);
        genereList.add(Genere.THRILLER);
        List<Periodicità> periodicitàList = new ArrayList<>();
        periodicitàList.add(Periodicità.SETTIMANALE);
        periodicitàList.add(Periodicità.SETTIMANALE);
        periodicitàList.add(Periodicità.MENSILE);
/*
        for (int i = 0; i <= 10; i++) {
            int currentYear = Year.now().getValue();
            Random random = new Random();
            int maxYear = 1990;
            int minYear = 1930;
            int minPage = 50;
            int randomYear = random.nextInt(currentYear - minYear + 1) + minYear;
            int randomPage = random.nextInt(100) + minPage;
            int randomListIndex = random.nextInt(3);
            int randomDataNascita = random.nextInt(maxYear - minYear + 1) + minYear;
            int randomDayOfYear = random.nextInt(365);
            LocalDate randomDate = LocalDate.ofYearDay(randomDataNascita, randomDayOfYear);
            LocalDate randomDatePrestito = LocalDate.ofYearDay(2022, randomDayOfYear);
            Date randomPrestitoDate = Date.from(randomDatePrestito.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date randomBirthDate = Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(randomPrestitoDate);
            calendar.add(Calendar.DAY_OF_MONTH, 15);
            Date newDate = calendar.getTime();
            Utente utente = new Utente("Nome" + i, "Cognome" + i, randomBirthDate);
            utenteDao.save(utente);
            Set<Catalogo> catalogoSet = new HashSet<>();
            Prestito prestito = new Prestito(utente, randomPrestitoDate, newDate);

            if (i % 2 == 0) {
                Libro libro = new Libro("Libro" + i, randomYear, randomPage, "Autore" + i, genereList.get(randomListIndex));
                catalogoDao.save(libro);

                if (i % 4 == 0 && i != 0) {
                    prestito.setRestituzioneEffettiva(null);
                    prestitoDao.save(prestito);
                    catalogoSet.add(libro);
                  //  if (i == 8) {
                    //    Rivista rivista = new Rivista("Rivista" + i, randomYear, randomPage + 10, periodicitàList.get(randomListIndex));
                      //  catalogoSet.add(rivista);
                   // }
                    prestito.setElementoPrestato(catalogoSet);
                }

            } else {
                Rivista rivista = new Rivista("Rivista" + i, randomYear, randomPage, periodicitàList.get(randomListIndex));

                catalogoDao.save(rivista);

                if (i % 3 == 0 && i != 0) {
                    catalogoSet.add(rivista);
                    prestito.setRestituzioneEffettiva(null);
                    prestitoDao.save(prestito);
                  // if (i == 6) {
                    //    Libro libro = new Libro("Libro" + i, randomYear, randomPage + 10, "Autore" + i, genereList.get(randomListIndex));
                      //  catalogoSet.add(libro);
                    //}
                    prestito.setElementoPrestato(catalogoSet);
                }




            }
        }
*/
        catalogoDao.findByIsbnAndDelete(14);
        System.out.println(catalogoDao.findById(16));
        catalogoDao.findByAnnoPubblicazione(1975).forEach(System.out::println);
        catalogoDao.findByAutore("Autore0").forEach(System.out::println);
        catalogoDao.findByTitle("1").forEach(System.out::println);
        catalogoDao.findByTesseraUtente(100).forEach(System.out::println);
        prestitoDao.findPrestitiScaduti().forEach(System.out::println);
        em.close();
        emf.close();
    }
}
