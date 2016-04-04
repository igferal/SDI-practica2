package com.sdi.tests.Tests;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sdi.tests.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SDI2_Tests {

	WebDriver driver;
	List<WebElement> elementos = null;

	public SDI2_Tests() {
	}

	@Before
	public void run() {
		// Creamos el driver para Firefox. Recomendable usar Firefox.
		driver = new FirefoxDriver();
		// Vamos a la página principal de mi aplicación
		driver.get("http://localhost:8280/sdi2-7");
	}

	@After
	public void end() {
		// Cerramos el navegador
		// driver.quit();
	}

	// PRUEBAS

	// 1. [RegVal] Registro de Usuario con datos válidos.
	@Test
	public void t01_RegVal() {
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-cabecera:join", 2);
		elementos.get(0).click();
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");

		fillFormElementText("form:username", "danirov");
		fillFormElementText("form:nombre", "Daniel");
		fillFormElementText("form:apellidos", "Rovira Rovira");
		fillFormElementText("form:email", "daniRovira@hothotmail.com");
		fillFormElementText("form:password", "12345");
		fillFormElementText("form:confirmaPassword", "12345");

		WebElement boton = driver.findElement(ById.id("form:join"));
		boton.click();

		SeleniumUtils.textoPresentePagina(driver, "Iniciar sesión");

		// Tu cuenta ha sido creada con éxito con el nombre de usuario usuario6.
		// Inicia sesión para empezar a crear y compartir tus viajes

	}

	private void fillFormElementText(String id, String info) {

		WebElement elemento;

		elemento = driver.findElement(By.id(id));
		elemento.clear();
		elemento.click();
		elemento.sendKeys(info);

	}

	// 2. [RegInval] Registro de Usuario con datos inválidos (contraseñas
	// diferentes).
	@Test
	public void t02_RegInval() {

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-cabecera:join", 2);
		elementos.get(0).click();
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");

		fillFormElementText("form:username", "resines");
		fillFormElementText("form:nombre", "Antonio");
		fillFormElementText("form:apellidos", "Resines Resines");
		fillFormElementText("form:email", "daniRovira@hothotmail.com");
		fillFormElementText("form:password", "12345");
		fillFormElementText("form:confirmaPassword", "1234");

		WebElement boton = driver.findElement(ById.id("form:join"));
		boton.click();

		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");

	}

	// 3. [IdVal] Identificación de Usuario registrado con datos válidos.
	@Test
	public void t03_IdVal() {

		log("usuario1", "usuario1");

	}

	private void log(String user, String password) {
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-cabecera:login", 2);
		elementos.get(0).click();
		SeleniumUtils
				.textoPresentePagina(driver, "¿Eres nuevo en ShareMyTrip?");

		fillFormElementText("j_idt12:username", user);
		fillFormElementText("j_idt12:password", password);

		WebElement boton = driver.findElement(ById.id("j_idt12:login"));
		boton.click();
		SeleniumUtils.textoPresentePagina(driver, "Viajes activos");
	}

	// 4. [IdInval] Identificación de usuario registrado con datos inválidos.
	@Test
	public void t04_IdInval() {

		log("usuario1", "noexistojeje");

	}

	// 5. [AccInval] Intento de acceso con URL desde un usuario no público (no
	// identificado). Intento de acceso a vistas de acceso privado.
	@Test
	public void t05_AccInval() {

		driver.get("http://localhost:8280/sdi2-7/restricted/myTrips.xhtml");
		SeleniumUtils
				.textoPresentePagina(driver, "¿Eres nuevo en ShareMyTrip?");

	}

	// 6. [RegViajeVal] Registro de un viaje nuevo con datos válidos.
	@Test
	public void t06_RegViajeVal() {

		t03_IdVal();

		/*
		 * Ciudad SALIDA
		 */
		elementos = SeleniumUtils.EsperaCargaPaginaxpath(driver,
				"/html/body/form[1]/div[2]/ul/li[5]/a", 2);
		elementos.get(0).click();

		// 04/13/2016 00:24:25 formato fecha
		wait(2);
		fillFormElementText("formulario:calleOrigen", "Rua ruadoiro");

		// Comprobaciones especiales para campos que se autocompletan
		WebElement elemento = driver.findElement(By
				.id("formulario:ciudadOrigen_input"));
		elemento.click();
		elemento.sendKeys("Braga");

		elemento = driver
				.findElement(By.id("formulario:provinciaOrigen_input"));
		elemento.click();
		elemento.sendKeys("Braga");
		// fin de campos autocompletados origen

		fillFormElementText("formulario:paisDestino", "Portugal");
		fillFormElementText("formulario:codigoPostalDestino", "09099");

		/*
		 * Ciudad DESTINO
		 */

		fillFormElementText("formulario:calleDestino", "Torre de Opor");

		// Comprobaciones especiales para campos que se autocompletan
		elemento = driver.findElement(By.id("formulario:ciudadDestino_input"));
		elemento.click();
		elemento.sendKeys("Porto");

		elemento = driver.findElement(By
				.id("formulario:provinciaDestino_input"));
		elemento.click();
		elemento.sendKeys("Porto");
		// fin de campos autocompletados origen

		fillFormElementText("formulario:paisOrigen", "Portugal");
		fillFormElementText("formulario:codigoPostalOrigen", "33111");

		fillFormElementText("formulario:fechaSalida_input",
				"05/13/2016 00:24:25");
		fillFormElementText("formulario:fechaLlegada_input",
				"05/14/2016 00:24:25");
		fillFormElementText("formulario:fechaInscripcion_input",
				"05/12/2016 00:24:25");
		fillFormElementText("formulario:costeEstimado", "34");
		fillFormElementText("formulario:plazasDisponibles", "2");
		fillFormElementText("formulario:plazasMaximas", "2");
		fillFormElementText("formulario:comentarios", "Muito obrigado");
		elemento = driver.findElement(By.id("formulario:ciudadDestino_input"));
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "enviar", 2);
		elementos.get(0).click();

		SeleniumUtils.textoPresentePagina(driver, "Viajes activos");

	}

	// 7. [RegViajeInVal] Registro de un viaje nuevo con datos inválidos.
	@Test
	public void t07_RegViajeInVal() {

		t03_IdVal();

		/*
		 * Ciudad SALIDA
		 */
		elementos = SeleniumUtils.EsperaCargaPaginaxpath(driver,
				"/html/body/form[1]/div[2]/ul/li[5]/a", 4);
		elementos.get(0).click();

		// 04/13/2016 00:24:25 formato fecha

		fillFormElementText("formulario:calleOrigen", "Rua ruadoiro");

		// Comprobaciones especiales para campos que se autocompletan
		WebElement elemento = driver.findElement(By
				.id("formulario:ciudadOrigen_input"));
		elemento.click();
		elemento.sendKeys("Braga");

		elemento = driver
				.findElement(By.id("formulario:provinciaOrigen_input"));
		elemento.click();
		elemento.sendKeys("Braga"); // fin de campos
		// autocompletados origen

		fillFormElementText("formulario:paisDestino", "Portugal");
		fillFormElementText("formulario:codigoPostalDestino", "09099");

		/*
		 * Ciudad DESTINO
		 */

		fillFormElementText("formulario:calleDestino", "Torre de Opor");

		// Comprobaciones especiales para campos que se autocompletan
		elemento = driver.findElement(By.id("formulario:ciudadDestino_input"));
		elemento.click();
		elemento.sendKeys("Porto");

		elemento = driver.findElement(By
				.id("formulario:provinciaDestino_input"));
		elemento.click();
		elemento.sendKeys("Porto"); // fin de campos autocompletados origen

		fillFormElementText("formulario:paisOrigen", "Portugal");
		fillFormElementText("formulario:codigoPostalOrigen", "33111");

		fillFormElementText("formulario:fechaSalida_input",
				"05/13/2016 00:24:25");
		fillFormElementText("formulario:fechaLlegada_input",
				"05/14/2016 00:24:25");
		fillFormElementText("formulario:fechaInscripcion_input",
				"05/15/2016 00:24:25");
		fillFormElementText("formulario:costeEstimado", "34");
		fillFormElementText("formulario:plazasDisponibles", "2");
		fillFormElementText("formulario:plazasMaximas", "2");
		fillFormElementText("formulario:comentarios", "Muito obrigado");
		elemento = driver.findElement(By.id("formulario:ciudadDestino_input"));
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "enviar", 2);
		elementos.get(0).click();
		SeleniumUtils.textoPresentePagina(driver, "Crea tu viaje");

		//
	}

	// 8. [EditViajeVal] Edición de viaje existente con datos válidos.
	@Test
	public void t08_EditViajeVal() {

		t03_IdVal();
		elementos = SeleniumUtils.EsperaCargaPaginaxpath(driver,
				"/html/body/form[1]/div[2]/ul/li[3]/a", 4);
		elementos.get(0).click();

		// buscamos elemento a borrar con nuestro XPATH PREFIJADO
		elementos = SeleniumUtils
				.EsperaCargaPaginaxpath(
						driver,
						"/html/body/form[2]/div/div[2]/table/tbody/tr[3]/td[10]/button",
						4);
		elementos.get(0).click();

		// cambiamos la ciudad

		elementos = SeleniumUtils
				.EsperaCargaPaginaxpath(
						driver,
						"/html/body/form[2]/div[2]/div/div/div[2]/div/div/div[6]/div/span/input[1]",
						4);
		elementos.get(0).click();
		elementos.get(0).clear();

		elementos.get(0).sendKeys("CiudadCambiada");

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "enviar", 2);
		elementos.get(0).click();
		wait(2);
		// Viaje editado
		SeleniumUtils.textoPresentePagina(driver, "Viaje editado");
		SeleniumUtils.textoPresentePagina(driver, "Mis viajes Ofertados");

	}

	// 9. [EditViajeInVal] Edición de viaje existente con datos inválidos.
	@Test
	public void t09_EditViajeInVal() {

		t03_IdVal();
		elementos = SeleniumUtils.EsperaCargaPaginaxpath(driver,
				"/html/body/form[1]/div[2]/ul/li[3]/a", 4);
		elementos.get(0).click();

		// buscamos elemento a modificar con nuestro XPATH PREFIJADO
		elementos = SeleniumUtils
				.EsperaCargaPaginaxpath(
						driver,
						"/html/body/form[2]/div/div[2]/table/tbody/tr[3]/td[10]/button",
						4);
		elementos.get(0).click();
		wait(3);
		fillFormElementText("formulario:plazasMaximas", "aaaa");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "enviar", 2);
		elementos.get(0).click();
		wait(2);
		SeleniumUtils
				.textoPresentePagina(driver,
						"Por favor introduce datos numericos en los campos correspondientes");

		// Por favor introduce datos numericos en los campos correspondientes

	}

	// 10. [CancelViajeVal] Cancelación de un viaje existente por un promotor.
	@Test
	public void t10_CancelViajeVal() {

		t03_IdVal();
		elementos = SeleniumUtils.EsperaCargaPaginaxpath(driver,
				"/html/body/form[1]/div[2]/ul/li[3]/a", 4);
		elementos.get(0).click();

		wait(2);

		elementos = SeleniumUtils
				.EsperaCargaPaginaxpath(
						driver,
						"/html/body/form[2]/div/div[2]/table/tbody/tr[3]/td[11]/div",
						2);

		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "enviar", 2);
		elementos.get(0).click();
		wait(2);
		SeleniumUtils.textoPresentePagina(driver, "Viaje/s cancelados");
		// enviar

		//

	}

	// 11. [CancelMulViajeVal] Cancelación de múltiples viajes existentes por un
	// promotor.
	@Test
	public void t11_CancelMulViajeVal() {

		t03_IdVal();
		elementos = SeleniumUtils.EsperaCargaPaginaxpath(driver,
				"/html/body/form[1]/div[2]/ul/li[3]/a", 4);
		elementos.get(0).click();

		wait(2);
		elementos = SeleniumUtils
				.EsperaCargaPaginaxpath(
						driver,
						"/html/body/form[2]/div/div[2]/table/tbody/tr[5]/td[11]/div/div[2]",
						2);

		elementos.get(0).click();

		elementos = SeleniumUtils
				.EsperaCargaPaginaxpath(
						driver,
						"/html/body/form[2]/div/div[2]/table/tbody/tr[6]/td[11]/div/div[2]",
						2);

		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "enviar", 2);
		elementos.get(0).click();
		wait(2);
		SeleniumUtils.textoPresentePagina(driver, "Viaje/s cancelados");

	}

	// 12. [Ins1ViajeAceptVal] Inscribir en un viaje un solo usuario y ser
	// admitido por el promotor.
	@Test
	public void t12_Ins1ViajeAceptVal() {

		log("usuario2", "usuario2");
		elementos = SeleniumUtils.EsperaCargaPaginaxpath(driver,
				"/html/body/form[2]/div/div[2]/table/tbody/tr[5]/td[2]", 2); // XPATH
																				// SUSCEPTIBLE
																				// DE
																				// CAMBIOOOOOOOO

		elementos.get(0).click();
		// elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
		// "solicitarPlaza", 2);
		// elementos.get(0).click();
		// wait(2);

		// SeleniumUtils.textoPresentePagina(driver,

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-cabecera:submenu", 2);
		wait(3);

		SeleniumUtils.ClickSubopcionMenuHover(driver, "form-cabecera:submenu",
				"form-cabecera:logout");
		wait(3);

		// LOG
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-cabecera:login", 10);
		elementos.get(0).click();
		fillFormElementText("j_idt12:username", "usuario1");
		fillFormElementText("j_idt12:password", "usuario1");
		WebElement boton = driver.findElement(ById.id("j_idt12:login"));
		boton.click();

		elementos = SeleniumUtils.EsperaCargaPaginaxpath(driver,
				"/html/body/form[1]/div[2]/ul/li[3]/a", 2);

		elementos.get(0).click();
		wait(8);

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
				"formulario:j_idt10:4:solicitudes", 2);
		elementos.get(0).click();

		//

	}

	// 13. [Ins2ViajeAceptVal] Inscribir en un viaje dos usuarios y ser
	// admitidos los dos por el promotor.

	// @Test
	// public void t13_Ins2ViajeAceptVal() {
	// log("usuario3", "usuario3");
	// elementos = SeleniumUtils.EsperaCargaPaginaxpath(driver,
	// "/html/body/form[2]/div/div[2]/table/tbody/tr[5]/td[2]", 2); // XPATH
	// // SUSCEPTIBLE
	// // DE
	// // CAMBIOOOOOOOO
	//
	// elementos.get(0).click();
	// elementos = SeleniumUtils.EsperaCargaPagina(driver, "id",
	// "solicitarPlaza", 2);
	// elementos.get(0).click();
	// wait(2);
	// SeleniumUtils.textoPresentePagina(driver,
	// "Solicitud de plaza registrada correctamente");
	//
	// }
	//
	// // 14. [Ins3ViajeAceptInval] Inscribir en un viaje (2 plazas máximo) dos
	// // usuarios y ser admitidos los dos y que un tercero intente inscribirse
	// en
	// // // ese mismo viaje pero ya no pueda por falta de plazas.
	//
	// @Test
	// public void t14_Ins3ViajeAceptInval() {
	//
	// log("usuario4", "usuario4");
	// elementos = SeleniumUtils.EsperaCargaPaginaxpath(driver,
	// "/html/body/form[2]/div/div[2]/table/tbody/tr[5]/td[2]", 2); // XPATH
	// // SUSCEPTIBLE
	// // DE
	// // CAMBIOOOOOOOO
	//
	// elementos.get(0).click();
	//
	// }
	//
	// // 15. [CancelNoPromotorVal] Un usuario no promotor Cancela plaza.
	//
	// @Test
	// public void t15_CancelNoPromotorVal() {
	//
	// }
	//
	// // 16. [Rech1ViajeVal] Inscribir en un viaje un usuario que será admitido
	// y
	// // // después rechazarlo por el promotor.
	//
	// @Test
	// public void t16_Rech1ViajeVal() {
	//
	// }
	//
	// // 17. [i18N1] Cambio del idioma por defecto a un segundo idioma. (Probar
	// // algunas vistas)
	//
	// @Test
	// public void t17_i18N1() {
	//
	// }
	//
	// // 18. [i18N2] Cambio del idioma por defecto a un segundo idioma y vuelta
	// al
	// // // idioma por defecto. (Probar algunas vistas)
	//
	// @Test
	// public void t18_i18N2() {
	//
	// }

	/*
	 * // 19. [OpFiltrado] Prueba para el filtrado opcional.
	 * 
	 * @Test public void t19_OpFiltrado() {
	 * 
	 * }
	 * 
	 * // 20. [OpOrden] Prueba para la ordenación opcional.
	 * 
	 * @Test public void t20_OpOrden() {
	 * 
	 * }
	 * 
	 * // 21. [OpPag] Prueba para la paginación opcional.
	 * 
	 * @Test public void t21_OpPag() {
	 * 
	 * }
	 * 
	 * // 22. [OpMante] Prueba del mantenimiento programado opcional.
	 * 
	 * @Test public void t22_OpMante() {
	 * 
	 * }
	 */
	private void wait(int millis) {

		try {
			Thread.sleep(millis * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
