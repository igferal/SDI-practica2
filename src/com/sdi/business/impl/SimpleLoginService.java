package com.sdi.business.impl;

import com.sdi.business.LoginService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.UserDao;

public class SimpleLoginService implements LoginService {

	@Override
	public User verify(String login, String password) {
		User user = Factories.persistence.newUserDao().findByLogin(login);
		if (user != null && user.getPassword().equals(password)) {
			user.setPassword(null);
			return user;
		}
		
		return null;
	}

	@Override
	public boolean saveUser(User user) {
		UserDao dao = Factories.persistence.newUserDao();

		if (dao.findByLogin(user.getLogin()) != null)
			return false;
		
		dao.save(user);
		
		return true;
	}

}
