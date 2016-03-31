package com.sdi.presentation;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import com.sdi.model.Trip;

@ManagedBean(name="trip")
@SessionScoped
public class BeanTrip extends Trip implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6283148030775995714L;

	public BeanTrip(){
		
	}

}
