package com.sdi.presentation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "tripInfo")
public class BeanTripInfo {

	private String id;
	
	@PostConstruct
	public void init() {

		System.out.println("Creando Bean info trip");
		id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tripInfo");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
