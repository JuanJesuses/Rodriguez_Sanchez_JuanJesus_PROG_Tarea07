package AlquilerVehiculos.mvc.modelo;

import AlquilerVehiculos.mvc.modelo.dao.Alquileres;
import AlquilerVehiculos.mvc.modelo.dao.Clientes;
import AlquilerVehiculos.mvc.modelo.dao.Vehiculos;
import AlquilerVehiculos.mvc.modelo.dominio.Alquiler;
import AlquilerVehiculos.mvc.modelo.dominio.Cliente;
import AlquilerVehiculos.mvc.modelo.dominio.DireccionPostal;
import AlquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.DatosTecnicosVehiculos;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

public class AlquilerVehiculos implements IModeloAlquilerVehiculos {
	
	private Clientes clientes;
	private Vehiculos vehiculos;
	private Alquileres alquileres;
	
	/**
	 * Constructor de la clase Alquiler Vehiculos que instancia
	 * los objetos Clientes, Vehiculos y Alquileres
	 */
	public AlquilerVehiculos() {
		
		clientes = new Clientes();
		vehiculos = new Vehiculos();
		alquileres = new Alquileres();
		
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#anadirCliente(AlquilerVehiculos.mvc.modelo.dominio.Cliente)
	 */
	@Override
	public void anadirCliente (Cliente cliente) {
		clientes.anadirCliente(cliente);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#borrarCliente(java.lang.String)
	 */
	@Override
	public void borrarCliente (String dni) {
		clientes.borrarCliente(dni);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#buscarCliente(java.lang.String)
	 */
	@Override
	public Cliente buscarCliente (String dni) {
		return clientes.buscarCliente(dni);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#obtenerClientes()
	 */
	@Override
	public Cliente[] obtenerClientes() {
		return clientes.getClientes();
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#anadirVehiculo(AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo, AlquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo)
	 */
	@Override
	public void anadirVehiculo (Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
		vehiculos.anadirVehiculo(vehiculo, tipoVehiculo);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#borrarVehiculo(java.lang.String)
	 */
	@Override
	public void borrarVehiculo(String matricula) {
		vehiculos.borrarVehiculo(matricula);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#buscarVehiculo(java.lang.String)
	 */
	@Override
	public Vehiculo buscarVehiculo (String matricula) {
		return vehiculos.buscarVehiculo(matricula);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#obtenerVehiculos()
	 */
	@Override
	public Vehiculo[] obtenerVehiculos() {
		return vehiculos.getVehiculos();
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#abrirAlquiler(AlquilerVehiculos.mvc.modelo.dominio.Cliente, AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo)
	 */
	@Override
	public void abrirAlquiler (Cliente cliente, Vehiculo vehiculo) {
		alquileres.abrirAlquiler(cliente, vehiculo);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#cerrarAlquiler(AlquilerVehiculos.mvc.modelo.dominio.Cliente, AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo)
	 */
	@Override
	public void cerrarAlquiler (Cliente cliente, Vehiculo vehiculo) {
		alquileres.cerrarAlquiler(cliente, vehiculo);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#obtenerAlquileres()
	 */
	@Override
	public Alquiler[] obtenerAlquileres() {
		return alquileres.getAlquileres();
	}
	
	@Override
	public void leerVehiculos() {
		vehiculos.leerVehiculos();
	}
	
	@Override
	public void escribirVehiculos() {
		vehiculos.escribirVehiculos();
	}
	
	@Override
	public void leerClientes() {
		clientes.leerClientes();
	}
	
	@Override
	public void escribirClientes() {
		clientes.escribirClientes();
	}
	
	@Override
	public void leerAlquileres() {
		alquileres.leerAlquileres();
	}
	
	@Override
	public void escribirAlquileres() {
		alquileres.escribirAlquileres();
	}

		
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.modelo.IModeloAlquilerVehiculos#anadirDatosPrueba()
	 */
	/**@Override
	public void anadirDatosPrueba() {
		
		Cliente cliente1 = new Cliente ("Bilbo Bolsón", "12345678A", new DireccionPostal("C/La Runa, 32", "Bolsón Cerrado", "01005"));
		Cliente cliente2 = new Cliente ("Meriadoc Brandigamo", "23456789B", new DireccionPostal("C/La Runa, 72", "Hobbitón", "63541"));
		Cliente cliente3 = new Cliente ("Peregrin Tuc", "34567890C", new DireccionPostal("C/La Runa, 29", "Bolsón Cerrado", "22456"));
		
		anadirCliente(cliente1);
		anadirCliente(cliente2);
		anadirCliente(cliente3);
		
		Vehiculo vehiculo1 = TipoVehiculo.TURISMO.getInstancia("0236NMJ", "Audi", "Q7", new DatosTecnicosVehiculos (3000, 7, 3500));
		Vehiculo vehiculo2 = TipoVehiculo.AUTOBUS.getInstancia("7628HKG", "PEGASO", "NONAINO", new DatosTecnicosVehiculos (5000, 54, 12500));
		Vehiculo vehiculo3 = TipoVehiculo.DE_CARGA.getInstancia("2356CDH", "IVECO", "DAILY", new DatosTecnicosVehiculos (5000, 6, 35000));
		
		anadirVehiculo(vehiculo1, TipoVehiculo.TURISMO);
		anadirVehiculo(vehiculo2, TipoVehiculo.AUTOBUS);
		anadirVehiculo(vehiculo3, TipoVehiculo.DE_CARGA);
		
		abrirAlquiler(cliente1, vehiculo1);
		abrirAlquiler(cliente2, vehiculo2);
		abrirAlquiler(cliente3, vehiculo3);
		
	}*/

}
