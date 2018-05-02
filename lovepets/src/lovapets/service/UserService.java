package lovapets.service;

import lovepets.bean.User;
import lovepets.dao.UserDao;

public class UserService {
	
	private UserDao userDao;
	
	public boolean Register(String user_name ,String user_password,String user_tel,String user_email) {
		boolean result = userDao.Register(user_name ,user_password,user_tel,user_email);
		return result;	
	}
	
	public User Login(String userName ,String password) {
		User user = new User();
		user = userDao.Login(userName, password);
		return user;
	}
}
