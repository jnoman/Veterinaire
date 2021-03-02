package services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.hibernate.Session;

import beans.BeanException;
import beans.Message;
import beans.User;
import util.HibernateUtil;

public class MessageService {
	public List<Message> getUserMessages(User user) throws BeanException, NoSuchAlgorithmException, InvalidKeySpecException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Message> result = (List<Message>) session.createQuery("from Message M WHERE M.user = :user", Message.class).setParameter("user", user).list();
		session.getTransaction().commit();
		return result;

	}
}
