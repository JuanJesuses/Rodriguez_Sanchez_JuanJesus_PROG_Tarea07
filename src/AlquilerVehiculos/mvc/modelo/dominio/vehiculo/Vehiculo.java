package AlquilerVehiculos.mvc.modelo.dominio.vehiculo;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AlquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import utilidades.Consola;

/**
 * Clase padre de la que heredan las clases Autobús, DeCarga y Turismo
 * @author john
 *
 */
public abstract class Vehiculo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String marca;
	private String modelo;
	private boolean disponible;
	private DatosTecnicosVehiculos datosTecnicosVehiculo;
	private final double FACTOR_CILINDRADA = 50.0;
	private final double FACTOR_NUMERO_PLAZAS = 1.0;
	private final double FACTOR_PMA = 20.0;
	
	/**
	 * Constructor con parámetros
	 * @param matricula
	 * @param marca
	 * @param modelo
	 * @param datosTecnicosVehiculo
	 */
	
	public Vehiculo (String matricula, String marca, String modelo, DatosTecnicosVehiculos datosTecnicosVehiculo) {
		
		setMatricula(matricula);
		setMarca(marca);
		setModelo(modelo);
		setDatosTecnicosVehiculo(datosTecnicosVehiculo);
		setDisponible(true);
				
	}
	
	/**
	 * Constructor copia
	 * @param vehiculo
	 */
	public Vehiculo (Vehiculo vehiculo) {
		
		matricula = vehiculo.getMatricula();
		marca = vehiculo.getMarca();
		modelo = vehiculo.getModelo();
		datosTecnicosVehiculo = vehiculo.getDatosTecnicosVehiculo();
		this.disponible = vehiculo.disponible;
		
	}
	
	public abstract TipoVehiculo getTipoVehiculo();
	
	public abstract double getPrecioEspecifico();
	
	/**
	 * Método que comprueba que el patŕon alfanumérico 
	 * introducido para la matrícula, es válido 
	 * @param matricula
	 * @return
	 */
	private boolean compruebaMatricula(String matricula) {
		Pattern patron = Pattern.compile("[0-9]{4}[B-DF-HJ-NP-TV-Z]{3}");
		Matcher emparejador = patron.matcher(matricula);
		
		return emparejador.matches();
	}
	
	private void setMatricula(String matricula) {
		if(compruebaMatricula(matricula)) {
			this.matricula = matricula;
		}else {
			throw new ExcepcionAlquilerVehiculos("El campo matrícula está vacío o la matrícula introducida no es correcta.");
		}
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	private void setMarca(String marca) {
		if(marca != null && !marca.equals("")) {
			this.marca = marca;
		}else {
			throw new ExcepcionAlquilerVehiculos("El campo marca no puede estar vacío.");
		}
	}
	
	public String getMarca() {
		return marca;
	}
	
	private void setModelo(String modelo) {
		if(modelo != null && !modelo.equals("")) {
			this.modelo = modelo;
		}else {
			throw new ExcepcionAlquilerVehiculos("El campo modelo no puede estar vacío.");
		}
	}
	
	public String getModelo() {
		return modelo;
	}
	
	private void setDatosTecnicosVehiculo (DatosTecnicosVehiculos datosTecnicosVehiculo) {
		this.datosTecnicosVehiculo = new DatosTecnicosVehiculos(datosTecnicosVehiculo);
	}
	
	public DatosTecnicosVehiculos getDatosTecnicosVehiculo() {
		return new DatosTecnicosVehiculos(datosTecnicosVehiculo);
	}
	
	public boolean getDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
		
	}
	
	public double getFACTOR_CILINDRADA() {
		return FACTOR_CILINDRADA;
	}
	
	public double getFACTOR_NUMERO_PLAZAS() {
		return FACTOR_NUMERO_PLAZAS;
	}
	
	public double getFACTOR_PMA() {
		return FACTOR_PMA;
	}
		
	@Override
	public String toString() {
		return String.format("-::VEHÍCULO::- \nMatrícula: %s Marca: %s Modelo: %s Disponible: %b\nDatos Técnicos Vehículo: %s Tipo Vehículo: %s",
				matricula, marca, modelo, disponible, datosTecnicosVehiculo, getTipoVehiculo());
	}
	
}
