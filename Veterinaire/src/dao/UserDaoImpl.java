package dao;

import org.hibernate.Session;

import beans.BeanException;
import beans.User;
import util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void inscription(User user) throws DaoException, BeanException {
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

}
