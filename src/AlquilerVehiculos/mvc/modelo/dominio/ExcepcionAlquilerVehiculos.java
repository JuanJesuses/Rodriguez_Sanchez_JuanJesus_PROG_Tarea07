package AlquilerVehiculos.mvc.modelo.dominio;

public class ExcepcionAlquilerVehiculos  extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ExcepcionAlquilerVehiculos (String mensaje) {
		super(mensaje);
	}
	
}
