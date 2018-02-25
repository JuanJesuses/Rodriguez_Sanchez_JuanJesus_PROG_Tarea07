package AlquilerVehiculos.mvc.modelo.dominio.vehiculo;

import AlquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 * Clase que instancia los datos técnicos de los vehículos
 * @author john
 *
 */
public class DatosTecnicosVehiculos {
	
	private int cilindrada;
	private int numeroPlazas;
	private int pma;
	
	/**
	 * Constructor con parámetros
	 * @param cilindrada
	 * @param numeroPlazas
	 * @param pma
	 */
	public DatosTecnicosVehiculos (int cilindrada, int numeroPlazas, int pma) {
		
		setCilindrada(cilindrada);
		setNumeroPlazas(numeroPlazas);
		setPma(pma);
		
	}
	
	/**
	 * Constructor copia
	 * @param datosTecnicosVehiculos
	 */
	public DatosTecnicosVehiculos (DatosTecnicosVehiculos datosTecnicosVehiculos) {
		
		cilindrada = datosTecnicosVehiculos.getCilindrada();
		numeroPlazas = datosTecnicosVehiculos.getNumeroPlazas();
		pma = datosTecnicosVehiculos.getPma();
		
	}
	
	private void setCilindrada(int cilindrada) {
		if (cilindrada > 0) {
			this.cilindrada = cilindrada;
		}else {
			throw new ExcepcionAlquilerVehiculos ("La cilindrada no puede estar vacía ni ser inferior a cero.");
		}
	}
	
	public int getCilindrada() {
		return cilindrada;
	}
	
	private void setNumeroPlazas(int numeroPlazas) {
		if (numeroPlazas > 0) {
			this.numeroPlazas = numeroPlazas;
		}else {
			throw new ExcepcionAlquilerVehiculos ("El número de plazas no puede estar vacío ni ser inferior a cero.");
		}
	}
	
	public int getNumeroPlazas() {
		return numeroPlazas;
	}
	
	private void setPma(int pma) {
		if (pma > 0) {
			this.pma = pma;
		}else {
			throw new ExcepcionAlquilerVehiculos ("El PMA no puede estar vacío ni ser inferior a cero.");
		}
	}
	
	public int getPma() {
		return pma;
	}
	
	@Override
	public String toString() {
		return String.format("DATOS TÉCNICOS: \nCilindrada: %d Número de Plazas: %d Peso Máximo Autorizado: %d", cilindrada, numeroPlazas, pma);
	}
}
