package com.sdi.presentation;
import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
@ManagedBean(name="settings")
@SessionScoped
public class BeanSettings implements Serializable{
  private static final long serialVersionUID = 2L;
  
  private static Set<Locale> locales = new HashSet<>();
  
  private static final Locale ENGLISH = new Locale("en");
  private static final Locale SPANISH = new Locale("es");       
  private Locale locale = new Locale("es"); 
//uso de inyección de dependencia
@ManagedProperty(value="#{alumno}") 
private BeanAlumno alumno;
public BeanAlumno getAlumno() { return alumno; }
public void setAlumno(BeanAlumno alumno) {this.alumno = alumno;}

//Se inicia correctamente el Managed Bean inyectado si JSF lo hubiera creado
//y en caso contrario se crea. 
//(hay que tener en cuenta que es un Bean de sesión)

//Se usa @PostConstruct, ya que en el contructor no se sabe todavía si 
//el MBean ya estaba construido y en @PostConstruct SI.
@PostConstruct
public void init() {
loadLanguages();
System.out.println("BeanSettings - PostConstruct");
//Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
alumno = 
   (BeanAlumno)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("alumno"));

//si no existe lo creamos e inicializamos
if (alumno == null) { 
  System.out.println("BeanSettings - No existia");
  alumno = new BeanAlumno();
  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "alumno", alumno);
} 
}
//Es sólo a modo de traza.
@PreDestroy
public void end()
{
System.out.println("BeanSettings - PreDestroy");
}

  
  public Locale getLocale() { 
  //Aqui habria que cambiar algo de código para coger locale del navegador 
  //la primera vez que se accede a getLocale(), de momento dejamos como idioma de partida “es”
    return(locale); 
  } 
  public void setSpanish(ActionEvent event) { 
	  locale = SPANISH; 
	  try {
	    FacesContext.getCurrentInstance().getViewRoot().setLocale(locale); 
	    if (alumno != null) 
	     alumno.iniciaAlumno(null);
	  } catch (Exception ex){
	    ex.printStackTrace();
	  }
	}

  public void setEnglish(ActionEvent event) { 
	  locale = ENGLISH; 
	  try {
	    FacesContext.getCurrentInstance().getViewRoot().setLocale(locale); 
	    if (alumno != null) 
	     alumno.iniciaAlumno(null);
	  } catch (Exception ex){
	    ex.printStackTrace();
	  }
	}

  private void loadLanguages() {
	  File folder = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes"));
	  
	  for (File file:folder.listFiles())
		  if (file.isFile() && file.getName().contains("_") && file.getName().endsWith(".properties"))
			  locales.add(new Locale(file.getName().split("_")[1].split("\\.")[0]));
  }
  
}
