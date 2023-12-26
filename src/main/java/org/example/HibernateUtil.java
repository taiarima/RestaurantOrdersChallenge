package org.example;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create a Hibernate configuration instance
            Configuration configuration = new Configuration();

            // Apply database properties from ConfigLoader
            Properties props = ConfigLoader.getProperties();
            configuration.setProperties(props);

            return configuration.buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
