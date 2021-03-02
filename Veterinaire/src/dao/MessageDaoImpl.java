package dao;

import org.hibernate.Session;

import beans.Message;
import util.HibernateUtil;

public class MessageDaoImpl implements MessageDao {

	@Override
	public void addMessage(Message message) {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(message);
		session.getTransaction().commit();
		session.close();
	}
}
