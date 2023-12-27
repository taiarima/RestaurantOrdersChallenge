package org.example.util;

import org.hibernate.Session;

@FunctionalInterface
public interface HibernateSessionFunction<T> {
    T apply(Session session) throws Exception;
}