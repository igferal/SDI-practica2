package com.sdi.presentation;

import alb.util.log.Log;
import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.LoginService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

@ManagedBean(name = "login")
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 6L;
	private String name = "";
	private String password = "";
	private String result = "login_form_result_valid";

	public BeanLogin() {

	}

	public String verify() {
		LoginService login = Factories.services.createLoginService();
		User user = login.verify(name, password);
		if (user != null) {
			putUserInSession(user);
			Log.info("El usuario [%s] ha iniciado sesion", name);
			return "exito";
		}
		setResult("login_form_result_error");
		Log.info("El usuario [%s] no esta registrado", name);
		return "fallo";
	}

	private void putUserInSession(User user) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
