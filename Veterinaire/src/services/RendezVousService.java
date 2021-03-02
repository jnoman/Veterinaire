package services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.hibernate.Session;

import beans.BeanException;
import beans.RendezVous;
import beans.User;
import util.HibernateUtil;

public class RendezVousService {
	public List<RendezVous> getUserRendezVous(User user) throws BeanException, NoSuchAlgorithmException, InvalidKeySpecException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<RendezVous> result = (List<RendezVous>) session.createQuery("from RendezVous R WHERE R.user = :user", RendezVous.class).setParameter("user", user).list();
		session.getTransaction().commit();
		return result;
	}
	public List<RendezVous> getAdminRendezVous() throws BeanException, NoSuchAlgorithmException, InvalidKeySpecException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<RendezVous> result = (List<RendezVous>) session.createQuery("from RendezVous R WHERE R.etat = 0", RendezVous.class).list();
		session.getTransaction().commit();
		return result;
	}
	public RendezVous getRendezVousById(Long id) throws BeanException, NoSuchAlgorithmException, InvalidKeySpecException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		RendezVous result = (RendezVous) session.createQuery("from RendezVous R WHERE R.id = :id", RendezVous.class).setParameter("id", id).uniqueResult();
		session.getTransaction().commit();
		return result;
	}
}
