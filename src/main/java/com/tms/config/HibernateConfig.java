package com.tms.config;

import com.tms.domain.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory== null) {
            Configuration configuration = new Configuration();

            Properties properties = new Properties();
            properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/library");
            properties.put("hibernate.connection.username", "postgres");
            properties.put("hibernate.connection.password", "root");

            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");
            properties.put("hibernate.hbm2ddl.auto", "update");

            configuration.addAnnotatedClass(Book.class);

            configuration.setProperties(properties);

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
        }
    }

