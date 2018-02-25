package AlquilerVehiculos.mvc.modelo.dominio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que gestiona la dirección postal de los clientes
 * @author john
 *
 */
public class DireccionPostal {
	
	private String calle;
	private String localidad;
	private String codigoPostal;
	
	/**
	 * Constructor con parámetros que cambia el estado
	 * del objeto a través de los métodos set
	 * @param calle
	 * @param localidad
	 * @param codigoPostal
	 */
	public DireccionPostal(String calle, String localidad, String codigoPostal) {
		setCalle(calle);
		setLocalidad(localidad);
		setCodigoPostal(codigoPostal);
	}
	
	/**
	 * Constructor copia
	 * @param direccionPostal
	 */
	public DireccionPostal (DireccionPostal direccionPostal) {
		calle = direccionPostal.getCalle();
		localidad = direccionPostal.getLocalidad();
		codigoPostal = direccionPostal.getCodigoPostal();
	}
	
	private void setCalle(String calle) {
		if(calle != null && !calle.equals("")) {
			this.calle = calle;
		}else {
			throw new ExcepcionAlquilerVehiculos("El campo calle no puede estar vacío.");
		}
	}
	
	public String getCalle() {
		return calle;
	}
	
	private void setLocalidad(String localidad) {
		if (localidad != null && !localidad.equals("")) {
			this.localidad = localidad;
		}else {
			throw new ExcepcionAlquilerVehiculos("El campo localidad no puede estar vacío.");
		}
	}
	
	public String getLocalidad() {
		return localidad;
	}
	
	private void setCodigoPostal (String codigoPostal) {
		if (compruebaCodigoPostal(codigoPostal)) {
			this.codigoPostal = codigoPostal;
		}else {
			throw new ExcepcionAlquilerVehiculos("Error al introducir el código Postal.");
		}
	}
	
	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	/**
	 * Método que comprueba que el patrón para el código postal
	 * introducido por teclado, es correcto
	 * @param codigoPostal
	 * @return
	 */
	private boolean compruebaCodigoPostal(String codigoPostal) {
		Pattern patron = Pattern.compile("[0-9]{5}");
		Matcher emparejador = patron.matcher(codigoPostal);
		
		return emparejador.matches();
	}
	
	@Override
	public String toString() {
		return String.format("Direccion: %s Localidad: %s Código Postal: %s", calle, localidad, codigoPostal);
	}
}