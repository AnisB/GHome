package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaUtil {

    // TODO Changer le nom du persistence unit
    private static final String NAME_PERSISTENCE_UNIT = "GHomePU";
    private static EntityManagerFactory emf;
    private static ThreadLocal<EntityManager> tl = new ThreadLocal<EntityManager>();


    public static void openEntityManager() {
        // System.out.println("Création de l'entity manager");
        emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        tl.set(em);
    }

    public static EntityManager getEntityManager() {
        // System.out.println("obtention de l'entity manager");
        EntityManager em = tl.get();
        return em;
    }

    private static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(NAME_PERSISTENCE_UNIT);
        }
        return emf;
    }

    public static void closeEntityManager() {
        // System.out.println("Fermeture de l'entity manager");
        EntityManager em = tl.get();
        if (em != null) {
            em.close();
            tl.set(null);
        }
    }

    public static EntityTransaction getEntityManagerTransaction() throws Exception {
        // System.out.println("Creation d'une transaction");
        return getEntityManager().getTransaction();
    }
}

