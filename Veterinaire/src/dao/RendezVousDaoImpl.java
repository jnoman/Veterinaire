package dao;

import org.hibernate.query.Query;
import org.hibernate.Session;

import beans.RendezVous;
import util.HibernateUtil;

public class RendezVousDaoImpl implements RendezVousDao {
	@Override
	public void addRendezVous(RendezVous rendezVous) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(rendezVous);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateRendezVous(RendezVous rendezVous) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(rendezVous);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void deleteRendezVous(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query =  session.createQuery("delete from RendezVous R WHERE R.id = :id").setParameter("id", id);
		query.executeUpdate();
		session.close();
		
	}
}
