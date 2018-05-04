package bookshopapp.service;

import bookshopapp.bean.User;
import bookshopapp.dao.UserDao;

public class UserService {
	
	private UserDao userDao = new UserDao();;
	
	public boolean Register(String user_name ,String user_password) {
		boolean result = userDao.Register(user_name ,user_password);
		return result;	
	}
	
	public User Login(String userName ,String password) {
		User user = new User();
		user = userDao.Login(userName, password);
		return user;
	}
}
