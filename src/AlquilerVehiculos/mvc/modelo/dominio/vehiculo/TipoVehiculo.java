package AlquilerVehiculos.mvc.modelo.dominio.vehiculo;

import AlquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

public enum TipoVehiculo {
	
	TURISMO("TURISMO"){
		public Turismo getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculos datosTecnicosVehiculo) {
			return new Turismo(matricula, marca, modelo, datosTecnicosVehiculo);
		}
	},
	
	DE_CARGA("DE CARGA"){
		public DeCarga getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculos datosTecnicosVehiculo) {
			return new DeCarga(matricula, marca, modelo, datosTecnicosVehiculo);
		}
	},
	
	AUTOBUS("AUTOBÚS"){
		public Autobus getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculos datosTecnicosVehiculo) {
			return new Autobus(matricula, marca, modelo, datosTecnicosVehiculo);
		}
	};
	
	public abstract Vehiculo getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculos datosTecnicosVehiculo);
	
	private String tipoVehiculo;
	
	private TipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	public String toString() {
		return tipoVehiculo;
	}
	
	/**
	 * Método que devuelve el tipo de vehiculo dependiendo
	 * del número seleccionado
	 * @param ordinal números de 0 a 2
	 * @return el ordinal seleccionado
	 */
	public static TipoVehiculo getTipoVehiculoSegunOrdinal(int ordinal) {
		if(esOrdinalValido(ordinal)) {
			return values()[ordinal];
		}else {
			throw new ExcepcionAlquilerVehiculos ("Ordinal del tipo de vehículo no válido.");
		}
	}
	
	/**
	 * Comprueba que el número de orden introducido está 
	 * dentro del rango
	 * @param ordinal
	 * @return
	 */
	public static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length -1) ? true : false;
	}
	
	
}
