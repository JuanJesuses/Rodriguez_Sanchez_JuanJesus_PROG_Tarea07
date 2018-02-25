package AlquilerVehiculos.mvc.controlador;

import AlquilerVehiculos.mvc.modelo.dominio.Alquiler;
import AlquilerVehiculos.mvc.modelo.dominio.Cliente;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

public interface IControladorAlquilerVehiculos {

	void comenzar();
	
	void salir();

	void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo);

	void borrarVehiculo(String matricula);

	Vehiculo buscarVehiculo(String matricula);

	Vehiculo[] obtenerVehiculos();

	void anadirCliente(Cliente cliente);

	void borrarCliente(String dni);

	Cliente buscarCliente(String dni);

	Cliente[] obtenerClientes();

	void abrirAlquiler(Cliente cliente, Vehiculo vehiculo);

	void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo);

	Alquiler[] obtenerAlquileres();
	

}