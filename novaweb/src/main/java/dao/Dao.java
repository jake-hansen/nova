package dao;

import databasetools.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class Dao<T> {

    // Session factory to use
    public static SessionFactory factory = null;

    // Transaction to use
    private Transaction tx = null;

    public Dao() {
        factory = HibernateUtil.getFactory();
    }

    /**
     * Creates a new object of type T in the storage mechanism.
     *
     * @param t - Object to store.
     * @return True if object is successfully created, false otherwise.
     */
    public boolean create(T t) {
        // Open session in session factory
        Session session = factory.openSession();

        // Boolean to keep track of success
        boolean createSuccessful = false;

        try {
            tx = session.beginTransaction();
            session.save(t);
            tx.commit();
            createSuccessful = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return createSuccessful;
    }

    /**
     * Updates the given object of type T in the storage mechanism. Determines object to update by
     * getting ID from t. The ID is used to lookup the primary key in the storage mechanism.
     *
     * @param t - Object to update.
     * @return True if object is successfully updated, false otherwise.
     */
    public boolean update(Class<T> c, T t) {
        // Open session in session factory
        Session session = factory.openSession();

        // Boolean to keep track of success
        boolean updateSuccessful = false;

        try {
            tx = session.beginTransaction();
            session.update(t);
            tx.commit();
            updateSuccessful = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return updateSuccessful;
    }

    /**
     * Deletes the object specified by id in the storage mechanism. Determines object to delete by
     * getting ID from t. The ID is used to lookup the primary key in the storage mechanism.
     *
     * @param t - Object to delete.
     * @return True if object is successfully deleted, false otherwise.
     */
    public boolean delete(Class<T> c, T t) {
        // Open session in session factory
        Session session = factory.openSession();

        // Boolean to keep track of success
        boolean deleteSuccessful = false;

        try {
            tx = session.beginTransaction();
            session.delete(t);
            tx.commit();
            deleteSuccessful = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return deleteSuccessful;
    }

    /**
     * Retrieves the object specified by id in the storage mechanism.
     *
     * @param id - ID of the object to find.
     * @return Found object of type T, null otherwise.
     */
    public T get(Class<T> c, int id) {
        // Open session in session factory
        Session session = factory.openSession();

        // Boolean to keep track of success
        boolean deleteSuccessful = false;

        // Object to return
        T objectToReturn = null;

        try {
            tx = session.beginTransaction();
            objectToReturn = (T) session.get(c, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return objectToReturn;
    }

    /**
     * Gets all objects T from the storage mechanism.
     *
     * @param c - Class of T
     * @return List of retrieved objects. Null otherwise.
     */
    public List<T> getAll(Class<T> c) {
        Session session = factory.openSession();
        List<T> results = null;

        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(c);
            query.from(c);
            results = session.createQuery(query).getResultList();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
                he.printStackTrace();
            }
        } catch (NoResultException e) {
        } finally {
            session.close();
        }
        return results;
    }


    /**
     * Retrieves all records from database that match the searchValue on fieldNmae.
     *
     * @param c           Class of T
     * @param fieldName   Field in database to search.
     * @param searchValue Value to search for.
     * @return List of found records.
     */
    public List<T> getByField(Class<T> c, String fieldName, String searchValue) {
        List<T> returnT;
        try {
            Session session = factory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(c);
            Root<T> root = query.from(c);
            query.select(root).where(criteriaBuilder.equal(root.get(fieldName), searchValue));
            Query<T> q = session.createQuery(query);
            returnT = q.getResultList();
        } catch (Exception e) {
            returnT = null;
        }
        return returnT;
    }
}
