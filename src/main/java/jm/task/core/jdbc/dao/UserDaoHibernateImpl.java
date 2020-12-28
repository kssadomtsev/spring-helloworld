package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            // start the transaction
            tx = session.beginTransaction();
            // do something with session
            SQLQuery updateQuery = session.createSQLQuery(SQLUser.CREATE_TABLE.QUERY);
            updateQuery.executeUpdate();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            // start the transaction
            tx = session.beginTransaction();
            // do something with session
            SQLQuery updateQuery = session.createSQLQuery(SQLUser.DROP_TABLE.QUERY);
            updateQuery.executeUpdate();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void saveUser(final String name, final String lastName, final byte age) {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            // start the transaction
            tx = session.beginTransaction();
            // do something with session
            User user = new User(name, lastName, age);
            session.save(user);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void removeUserById(final long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            // start the transaction
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            // start the transaction
            tx = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            // start the transaction
            tx = session.beginTransaction();
            SQLQuery updateQuery = session.createSQLQuery(SQLUser.CLEAN.QUERY);
            updateQuery.executeUpdate();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
