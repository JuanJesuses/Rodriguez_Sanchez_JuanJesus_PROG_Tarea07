package AlquilerVehiculos.mvc.vista;

import AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos;

public interface IVistaAlquilerVehiculos {

	void setControlador(IControladorAlquilerVehiculos controlador);

	void comenzar();

	void salir();

	void listarAlquileres();

	void cerrarAlquiler();

	void abrirAlquiler();

	void listaVehiculos();

	void buscarVehiculo();

	void borrarVehiculo();

	void anadirVehiculo();

	void listarClientes();

	void buscarCliente();

	void borrarCliente();

	void anadirCliente();

}