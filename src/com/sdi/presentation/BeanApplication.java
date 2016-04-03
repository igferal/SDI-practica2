package com.sdi.presentation;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.sdi.business.impl.classes.TripsUpdateStatusTask;

@ManagedBean (name = "applicationBean", eager = true)
@ApplicationScoped
public class BeanApplication {

	public BeanApplication() {
		new TripsUpdateStatusTask();
	}
}
