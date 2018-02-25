package AlquilerVehiculos.mvc.modelo.dominio.vehiculo;

/**
 * Clase autobús que hereda de la clase Vehículo
 * @author john
 *
 */
public class Autobus extends Vehiculo {
	
	private double factorPrecioAutobus = getDatosTecnicosVehiculo().getCilindrada();
	
	/**
	 * Constructor con parámetros
	 * @param matricula
	 * @param marca
	 * @param modelo
	 * @param datosTecnicosVehiculo
	 */
	public Autobus(String matricula, String marca, String modelo, DatosTecnicosVehiculos datosTecnicosVehiculo) {
		super(matricula, marca, modelo, datosTecnicosVehiculo);
	}
	
	/**
	 * Constructor copia
	 * @param autobus
	 */
	public Autobus (Autobus autobus) {
		super(autobus);
	}
	
	@Override
	public TipoVehiculo getTipoVehiculo() {
		return TipoVehiculo.AUTOBUS;
	}
	
	@Override
	public double getPrecioEspecifico() {
		return factorPrecioAutobus / getFACTOR_CILINDRADA() + (getFACTOR_NUMERO_PLAZAS() * getDatosTecnicosVehiculo().getNumeroPlazas());
	}

}
