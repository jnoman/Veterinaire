package services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.hibernate.Session;

import beans.BeanException;
import beans.User;
import methodes.PasswordHash;
import util.HibernateUtil;

public class UserService {
	public User serviceConnexion(ModelUser modelUser) throws BeanException, NoSuchAlgorithmException, InvalidKeySpecException{
		User user = null;
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		user = (User) session.createQuery("from User U WHERE U.email = :email").setParameter("email", modelUser.getEmail()).uniqueResult();
		session.getTransaction().commit();
		if(user != null && PasswordHash.validatePassword(modelUser.getPassword(), user.getPassword())) {
			return user;
		} else {
			return null;
		}
	}
	public List<User> getUsersClient() throws BeanException, NoSuchAlgorithmException, InvalidKeySpecException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<User> result  = session.createQuery("from User U where U.Role='client'", User.class).list();
		session.getTransaction().commit();
		return result;

	}
	public User getUserById(Long id) throws BeanException, NoSuchAlgorithmException, InvalidKeySpecException{
		User user = null;
		Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		user = (User) session.createQuery("from User U WHERE U.id = :id").setParameter("id", id).uniqueResult();
		session.getTransaction().commit();
		return user;
	}
}
