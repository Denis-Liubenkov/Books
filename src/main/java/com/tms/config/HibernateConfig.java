package com.tms.config;

import com.tms.domain.Book;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        Configuration configuration;
        if (sessionFactory == null) {
            configuration = new Configuration();

            Properties properties = new Properties();
            properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/library");
            properties.put("hibernate.connection.username", "postgres");
            properties.put("hibernate.connection.password", "root");

            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");
            properties.put("hibernate.hbm2ddl.auto", "update");

            properties.put("hibernate.cache.use_second_level_cache", "false");
            properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");
            properties.put("hibernate.cache.provider_class", "org.ehcache.jcache.JCacheProvider");

            properties.put("hibernate.cache.use_query_cache", "false");

            configuration.addAnnotatedClass(Book.class);

            configuration.setProperties(properties);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}

