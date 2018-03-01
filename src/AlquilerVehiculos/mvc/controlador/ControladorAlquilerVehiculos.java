package AlquilerVehiculos.mvc.controlador;

import AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos;
import AlquilerVehiculos.mvc.modelo.dao.Alquileres;
import AlquilerVehiculos.mvc.modelo.dao.Clientes;
import AlquilerVehiculos.mvc.modelo.dao.Vehiculos;
import AlquilerVehiculos.mvc.modelo.dominio.Alquiler;
import AlquilerVehiculos.mvc.modelo.dominio.Cliente;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;
import AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos;

public class ControladorAlquilerVehiculos implements IControladorAlquilerVehiculos{
	
	IModeloAlquilerVehiculos modelo;
	IVistaAlquilerVehiculos vista;
	
	/**
	 * Constructor que crea un controlador y recibe dos parámetros
	 * @param modelo
	 * @param vista
	 */
	public ControladorAlquilerVehiculos(IModeloAlquilerVehiculos modelo, IVistaAlquilerVehiculos vista) {
		this.vista = vista;
		this.modelo = modelo;
		vista.setControlador(this);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#comenzar()
	 */
	@Override
	public void comenzar() {
		//modelo.anadirDatosPrueba();
		
		modelo.leerVehiculos();
		modelo.leerClientes();
		modelo.leerAlquileres();
		vista.comenzar();
	}
	
	@Override
	public void salir() {
		modelo.escribirVehiculos();
		modelo.escribirClientes();
		modelo.escribirAlquileres();
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#anadirVehiculo(AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo, AlquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo)
	 */
	@Override
	public void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
		modelo.anadirVehiculo(vehiculo, tipoVehiculo);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#borrarVehiculo(java.lang.String)
	 */
	@Override
	public void borrarVehiculo(String matricula) {
		modelo.borrarVehiculo(matricula);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#buscarVehiculo(java.lang.String)
	 */
	@Override
	public Vehiculo buscarVehiculo(String matricula) {
		return modelo.buscarVehiculo(matricula);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#obtenerVehiculos()
	 */
	@Override
	public Vehiculo[] obtenerVehiculos() {
		return modelo.obtenerVehiculos();
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#anadirCliente(AlquilerVehiculos.mvc.modelo.dominio.Cliente)
	 */
	@Override
	public void anadirCliente(Cliente cliente) {
		modelo.anadirCliente(cliente);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#borrarCliente(java.lang.String)
	 */
	@Override
	public void borrarCliente (String dni) {
		modelo.borrarCliente(dni);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#buscarCliente(java.lang.String)
	 */
	@Override
	public Cliente buscarCliente(String dni) {
		return modelo.buscarCliente(dni);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#obtenerClientes()
	 */
	@Override
	public Cliente[] obtenerClientes() {
		return modelo.obtenerClientes();
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#abrirAlquiler(AlquilerVehiculos.mvc.modelo.dominio.Cliente, AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo)
	 */
	@Override
	public void abrirAlquiler(Cliente cliente, Vehiculo vehiculo) {
		modelo.abrirAlquiler(cliente, vehiculo);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#cerrarAlquiler(AlquilerVehiculos.mvc.modelo.dominio.Cliente, AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo)
	 */
	@Override
	public void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo) {
		modelo.cerrarAlquiler(cliente, vehiculo);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos#obtenerAlquileres()
	 */
	@Override
	public Alquiler[] obtenerAlquileres() {
		return modelo.obtenerAlquileres();
	}
	
	/**
	 * Método que llama al método que añade los datos de prueba
	 * para comprobar el funcionamiento de la aplización
	 */
	/**public void anadirDatosPrueba() {
		modelo.anadirDatosPrueba();
	}*/
	
}
