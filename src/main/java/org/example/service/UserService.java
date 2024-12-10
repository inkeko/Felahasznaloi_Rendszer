package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.User;
import org.example.utils.Databaseutil;

import java.util.List;

public class UserService {

    private final EntityManagerFactory entityManagerFactory = Databaseutil.getEntityManagerFactory();

    // Felhasználó hozzáadása
    public void addUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    // Felhasználók lekérdezése
    public List<User> getUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.close();
        return users;
    }
    /**
     * Felhasználó frissítése.
     */
    public void updateUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);  // Adatok frissítése
        em.getTransaction().commit();
        em.close();
    }

    // Felhasználó törlése
    public void deleteUser(int userId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();
        em.close();
    }
}

