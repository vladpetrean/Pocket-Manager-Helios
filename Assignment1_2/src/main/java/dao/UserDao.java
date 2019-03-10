package dao;

import entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;

import java.util.List;

public class UserDao {
    private static final Log LOGGER = LogFactory.getLog(UserDao.class);

    private SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }

    public User addUser(User user) {
        int UserId = -1;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserId = (Integer) session.save(user);
            user.setId(UserId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("", e);
        } finally {
            session.close();
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findUsers() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<User> users = null;
        try {
            tx = session.beginTransaction();
            users = session.createQuery("FROM AppUser").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("", e);
        } finally {
            session.close();
        }
        return users;
    }

    @SuppressWarnings("unchecked")
    public User findUser(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<User> Users = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM AppUser WHERE id = :id");
            query.setParameter("id", id);
            Users = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("", e);
        } finally {
            session.close();
        }
        return Users != null && !Users.isEmpty() ? Users.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    public User findByUsername(String username) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<User> Users = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE username = :username");
            query.setParameter("username", username);
            Users = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("", e);
        } finally {
            session.close();
        }
        return Users != null && !Users.isEmpty() ? Users.get(0) : null;
    }



    public User deleteUser(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        User User = null;
        try {
            User=this.findUser(id);
            if (User!=null){
                tx = session.beginTransaction();
                session.delete(User);
                tx.commit();
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            LOGGER.error("", e);
        } finally {
            session.close();
        }
        return User;
    }

}
