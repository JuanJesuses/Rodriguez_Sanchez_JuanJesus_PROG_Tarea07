package AlquilerVehiculos.aplicacion;

import utilidades.Entrada;
import AlquilerVehiculos.mvc.controlador.ControladorAlquilerVehiculos;
import AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos;
import AlquilerVehiculos.mvc.modelo.AlquilerVehiculos;
import AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos;
import AlquilerVehiculos.mvc.modelo.dao.Alquileres;
import AlquilerVehiculos.mvc.modelo.dao.Clientes;
import AlquilerVehiculos.mvc.modelo.dao.Vehiculos;
import AlquilerVehiculos.mvc.modelo.dominio.Alquiler;
import AlquilerVehiculos.mvc.modelo.dominio.Cliente;
import AlquilerVehiculos.mvc.modelo.dominio.DireccionPostal;
import AlquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;
import AlquilerVehiculos.mvc.vista.IUTextual;
import AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos;

public class PrincipalAlquilerVehiculos {

	public static void main(String[] args) {
		
		IModeloAlquilerVehiculos modelo = new AlquilerVehiculos();
		IVistaAlquilerVehiculos vista = new IUTextual();
		IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(modelo, vista);
		
		controlador.comenzar();
		//controlador.salir();
		
	}
}