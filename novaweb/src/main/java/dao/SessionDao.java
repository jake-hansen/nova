package dao;

import datamodel.Session;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SessionDao extends Dao<Session> {

    public Session getBySessionID(String sessionID) {
        Transaction tx = null;
        org.hibernate.Session session = factory.openSession();
        Session returnSession = null;

        try {
            tx = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Session> query = criteriaBuilder.createQuery(Session.class);
            Root<Session> root = query.from(Session.class);
            query.select(root).where(criteriaBuilder.equal(root.get("lastSessionID"), sessionID));

            Query<Session> q = session.createQuery(query);
            returnSession = q.getSingleResult();

            tx.commit();
        }
        catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
                he.printStackTrace();
            }
        }
        catch (NoResultException e) {
        }
        finally {
            session.close();
        }
        return returnSession;
    }
}
