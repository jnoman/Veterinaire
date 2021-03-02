package dao;

import beans.BeanException;
import beans.User;

public interface UserDao {
	void inscription(User user)  throws DaoException, BeanException;
}
