package com.sdi.business;

import com.sdi.model.User;

public interface LoginService {

	public User verify(String user, String password);
}
