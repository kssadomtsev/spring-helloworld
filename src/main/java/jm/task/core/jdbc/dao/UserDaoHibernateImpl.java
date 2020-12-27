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
        new TransactionalQuery() {
            @Override
            protected void transaction(Session session) {
                //useful part:
                SQLQuery updateQuery = session.createSQLQuery(SQLUser.CREATE_TABLE.QUERY);
                updateQuery.executeUpdate();
            }
        }.execute();
    }

    @Override
    public void dropUsersTable() {
        new TransactionalQuery() {
            @Override
            protected void transaction(Session session) {
                //useful part:
                SQLQuery updateQuery = session.createSQLQuery(SQLUser.DROP_TABLE.QUERY);
                updateQuery.executeUpdate();
            }
        }.execute();
    }


    @Override
    public void saveUser(final String name, final String lastName, final byte age) {
        new TransactionalQuery() {
            @Override
            protected void transaction(Session session) {
                //useful part:
                User user = new User(name, lastName, age);
                session.save(user);
            }
        }.execute();
    }


    @Override
    public void removeUserById(final long id) {
        new TransactionalQuery() {
            @Override
            protected void transaction(Session session) {
                //useful part:
                User user = (User) session.get(User.class, id);
                session.delete(user);
            }
        }.execute();
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
        new TransactionalQuery() {
            @Override
            protected void transaction(Session session) {
                //useful part:
                SQLQuery updateQuery = session.createSQLQuery(SQLUser.CLEAN.QUERY);
                updateQuery.executeUpdate();
            }
        }.execute();
    }

    abstract static class TransactionalQuery {

        void execute() {
            Session session = null;
            Transaction tx = null;
            try {
                session = Util.getSessionFactory().openSession();
                // start the transaction
                tx = session.beginTransaction();
                // do something with session
                transaction(session);
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

        protected abstract void transaction(Session session);
    }
}
