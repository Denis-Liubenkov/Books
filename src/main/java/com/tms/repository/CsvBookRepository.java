package com.tms.repository;

import com.tms.config.HibernateConfig;
import com.tms.domain.Book;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CsvBookRepository implements BookRepository {

    private final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public List<Book> findAll() {
        Session session = getSession();
        try (session) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
            Root<Book> root = criteria.from(Book.class);
            criteria.select(root);
            return session.createQuery(criteria).getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Ошибка при получении списка книг:", e);
        }
    }

    public Book findById(Integer id) {
        Session session = getSession();
        try (session) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

            TypedQuery<Book> query = session.createQuery(criteriaQuery);
            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        } catch (HibernateException e) {
            throw new RuntimeException("Ошибка при получении книги по ID:", e);
        }
    }

    public void save(Book book) {
        Session session = getSession();
        Transaction tx = null;
        try (session) {
            tx = session.beginTransaction();
            session.persist(book);

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root);
            TypedQuery<Book> query = session.createQuery(criteriaQuery);
            List<Book> bookList = query.getResultList();

            System.out.println(bookList);

            session.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Book book) {
        Session session = getSession();
        Transaction tx = null;
        try (session) {
            tx = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<Book> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Book.class);
            Root<Book> root = criteriaUpdate.from(Book.class);

            criteriaUpdate.set(root.get("title"), book.getTitle());
            criteriaUpdate.set(root.get("description"), book.getDescription());

            criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), book.getId()));

            Query query = session.createQuery(criteriaUpdate);
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        Session session = getSession();
        Transaction tx = null;
        try (session) {
            tx = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Book> criteriaDelete = criteriaBuilder.createCriteriaDelete(Book.class);
            Root<Book> root = criteriaDelete.from(Book.class);

            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));

            Query query = session.createQuery(criteriaDelete);
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
