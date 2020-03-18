package databasetools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    // SessionFactory object to return
    private static SessionFactory factory;

    /**
     * Helper class to create a new sessionFactory
     */
    private static void createSessionFactory() {
        try {
            factory = new Configuration().configure("/config/hibernate.cfg.xml.private").buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object. " + ex);
            throw new ExceptionInInitializerError();
        }
    }

    /**
     * Public method for getting session factory. If the session factory has not yet been generated,
     * calls createSessionFactory() to generate a new session factory.
     * @return - Session factory object.
     */
    public static SessionFactory getFactory() {
        if (factory == null) {
            createSessionFactory();
        }
        return factory;
    }
}
