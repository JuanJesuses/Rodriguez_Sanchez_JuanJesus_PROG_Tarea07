package AlquilerVehiculos.mvc.vista;

import AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos;
import AlquilerVehiculos.mvc.modelo.dao.Alquileres;
import AlquilerVehiculos.mvc.modelo.dao.Clientes;
import AlquilerVehiculos.mvc.modelo.dao.Vehiculos;
import AlquilerVehiculos.mvc.modelo.dominio.Alquiler;
import AlquilerVehiculos.mvc.modelo.dominio.Cliente;
import AlquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;
import utilidades.Consola;

public class IUTextual implements IVistaAlquilerVehiculos {
	
	IControladorAlquilerVehiculos controlador;
	
	public IUTextual () {
		Opcion.setVista(this);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#setControlador(AlquilerVehiculos.mvc.controlador.IControladorAlquilerVehiculos)
	 */
	@Override
	public void setControlador (IControladorAlquilerVehiculos controlador) {
		this.controlador = controlador;
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#comenzar()
	 */
	@Override
	public void comenzar() {
		int ordinalOpcion;
		
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		}while(ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#salir()
	 */
	@Override
	public void salir() {
		System.out.println("Ha escogido salir del programa.");
		controlador.salir();
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#listarAlquileres()
	 */
	@Override
	public void listarAlquileres() {
		Consola.mostrarCabecera("Listar Alquileres.");
		
		for(Alquiler alquiler : controlador.obtenerAlquileres()) {
			if(alquiler != null) {
				System.out.println(alquiler);
			}
		}
		System.out.println("");
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#cerrarAlquiler()
	 */
	@Override
	public void cerrarAlquiler() {
		Consola.mostrarCabecera("Cerrar alquiler");
		String dni = Consola.leerDni();
		Cliente cliente = controlador.buscarCliente(dni);
		String matricula = Consola.leerMatricula();
		Vehiculo vehiculo = controlador.buscarVehiculo(matricula);
		
		if(cliente == null || vehiculo == null) {
			System.out.println("ERROR: No hay ningún alquiler en curso con los datos seleccionados.");
		}else {
			try {
				controlador.cerrarAlquiler(cliente, vehiculo);
				System.out.println("Alquiler cerrado de forma satisfactoria.");
			}catch(ExcepcionAlquilerVehiculos e) {
				System.out.printf("ERROR: %s%n%n", e.getMessage());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#abrirAlquiler()
	 */
	@Override
	public void abrirAlquiler() {
		Consola.mostrarCabecera("Abrir Alquiler");
		String dni = Consola.leerDni();
		Cliente cliente = controlador.buscarCliente(dni);
		String matricula = Consola.leerMatricula();
		Vehiculo vehiculo = controlador.buscarVehiculo(matricula);
		
		if(cliente == null || vehiculo == null) {
			System.out.println("ERROR: No hay ningún alquiler en curso con los datos seleccionados.");
		}else {
			try {
				controlador.abrirAlquiler(cliente, vehiculo);
				System.out.println("Alquiler generado de forma satisfactoria.");
			}catch(ExcepcionAlquilerVehiculos e) {
				System.out.printf("ERROR: %s%n%n", e.getMessage());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#listaVehiculos()
	 */
	@Override
	public void listaVehiculos() {
		Consola.mostrarCabecera("Listar Vehículos");
		
		for (Vehiculo vehiculo : controlador.obtenerVehiculos()) {
			if(vehiculo != null) {
				System.out.println(vehiculo);
			}
		}
		System.out.println("");
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#buscarVehiculo()
	 */
	@Override
	public void buscarVehiculo() {
		Consola.mostrarCabecera("Buscar Vehículo");
		String matricula = Consola.leerMatricula();
		Vehiculo vehiculoBuscado = controlador.buscarVehiculo(matricula);
		String mensaje = (vehiculoBuscado != null) ? vehiculoBuscado.toString() : "El vehículo no existe.";
		System.out.printf("%s%n%n", mensaje);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#borrarVehiculo()
	 */
	@Override
	public void borrarVehiculo() {
		Consola.mostrarCabecera("Borrar Vehículo");
		String matricula = Consola.leerMatricula();
		try {
			controlador.borrarVehiculo(matricula);
			System.out.println("Vehículo eliminado correctamente\n");
		}catch (ExcepcionAlquilerVehiculos e) {
			System.out.printf("ERROR: %s%n%n", e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#anadirVehiculo()
	 */
	@Override
	public void anadirVehiculo() {
		Consola.mostrarCabecera("Añadir Vehículo");
		int tipoVehiculo = Consola.elegirTipoVehiculo();		
		
		try {
			Vehiculo vehiculo = Consola.leerVehiculo();
			controlador.anadirVehiculo(vehiculo, TipoVehiculo.getTipoVehiculoSegunOrdinal(tipoVehiculo));
			System.out.println("Vehículo añadido de forma satisfactoria.");
		}catch (Exception e){
			System.out.printf("ERROR: %s%n%n", e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#listarClientes()
	 */
	@Override
	public void listarClientes() {
		Consola.mostrarCabecera("Listar Clientes");
		
		for (Cliente cliente : controlador.obtenerClientes()) {
			if(cliente != null) {
				System.out.println(cliente);
			}
		}
		System.out.println("");
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#buscarCliente()
	 */
	@Override
	public void buscarCliente() {
		Consola.mostrarCabecera("Buscar Cliente");
		String dni = Consola.leerDni();
		Cliente clienteBuscado = controlador.buscarCliente(dni);
		String mensaje = (clienteBuscado != null) ? clienteBuscado.toString() : "El cliente no existe.";
		System.out.printf("%s%n%n", mensaje);
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#borrarCliente()
	 */
	@Override
	public void borrarCliente() {
		Consola.mostrarCabecera("Eliminar Cliente");
		String dni =  Consola.leerDni();
		
		try {
			controlador.borrarCliente(dni);
			System.out.println("Cliente eliminado de forma satisfactoria.");
		}catch (Exception e) {
			System.out.printf("ERROR: %s%n%n", e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see AlquilerVehiculos.mvc.vista.IVistaAlquilerVehiculos#anadirCliente()
	 */
	@Override
	public void anadirCliente() {
		Consola.mostrarCabecera("Añadir Cliente");
		//Cliente cliente = Consola.leerCliente();
		
		try {
			Cliente cliente = Consola.leerCliente();
			controlador.anadirCliente(cliente);
			System.out.println("El cliente se ha añadido de forma satisfactoria.");
		}catch(Exception e) {
			System.out.printf("ERROR: %s%n%n", e.getMessage());
		}
	}
}
