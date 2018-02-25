package AlquilerVehiculos.mvc.modelo.dominio.vehiculo;

/**
 * Clase para vehículos de carga que hereda de la clase Vehículo
 * @author john
 *
 */
public class DeCarga extends Vehiculo {
	
	private double factorPrecioDeCarga = getDatosTecnicosVehiculo().getPma();
	
	/**
	 * Constructor con parámetros
	 * @param matricula
	 * @param marca
	 * @param modelo
	 * @param datosTecnicosVehiculo
	 */
	public DeCarga(String matricula, String marca, String modelo, DatosTecnicosVehiculos datosTecnicosVehiculo) {
		super(matricula, marca, modelo, datosTecnicosVehiculo);
	}
	
	/**
	 * Constructor copia
	 * @param deCarga
	 */
	public DeCarga (DeCarga deCarga) {
		super (deCarga);
	}
	
	@Override
	public TipoVehiculo getTipoVehiculo() {
		return TipoVehiculo.DE_CARGA;
	}
	
	@Override
	public double getPrecioEspecifico() {
		return factorPrecioDeCarga / getFACTOR_PMA() + (getFACTOR_NUMERO_PLAZAS() / getDatosTecnicosVehiculo().getNumeroPlazas());
	}
}
