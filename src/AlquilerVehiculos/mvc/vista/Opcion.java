package AlquilerVehiculos.mvc.vista;

import AlquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

public enum Opcion {
	
	SALIR("Salir"){
		public void ejecutar() {
			vista.salir();
		}
	},
	
	ANADIR_CLIENTE("Añadir Cliente"){
		public void ejecutar() {
			vista.anadirCliente();
		}
	},
	
	BORRAR_CLIENTE("Borrar Cliente"){
		public void ejecutar() {
			vista.borrarCliente();
		}
	},
	
	BUSCAR_CLIENTE("Buscar Cliente"){
		public void ejecutar() {
			vista.buscarCliente();
		}
	},
	
	LISTAR_CLIENTE("Listar Clientes"){
		public void ejecutar() {
			vista.listarClientes();
		}
	},
	
	ANADIR_VEHICULO("Añadir Vehículo"){
		public void ejecutar() {
			vista.anadirVehiculo();
		}
	},
	
	BORRAR_VEHICULO("Borrar Vehículo"){
		public void ejecutar() {
			vista.borrarVehiculo();
		}
	},
	
	BUSCAR_VEHICULO("Buscar Vehículo"){
		public void ejecutar() {
			vista.buscarVehiculo();
		}
	},
	
	LISTAR_VEHICULO("Listar Vehículos"){
		public void ejecutar() {
			vista.listaVehiculos();
		}
	},
	
	ABRIR_ALQUILER("Abrir Alquiler"){
		public void ejecutar() {
			vista.abrirAlquiler();
		}
	},
	
	CERRAR_ALQUILER("Cerrar Alquiler"){
		public void ejecutar() {
			vista.cerrarAlquiler();
		}
	},
	
	LISTAR_ALQUILERES("Listar Alquileres"){
		public void ejecutar(){
			vista.listarAlquileres();
		}
	};
	
	private String mensaje;
	private static IVistaAlquilerVehiculos vista;
	
	private Opcion(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public abstract void ejecutar();
	
	public String getMensaje() {
		return mensaje;
	}
	
	public static void setVista(IVistaAlquilerVehiculos vista) {
		Opcion.vista = vista;
	}
	
	public String toString() {
		return String.format("%d.- %s", ordinal(), mensaje);
	}
	
	public static Opcion getOpcionSegunOrdinal(int ordinal) {
		if(esOrdinalValido(ordinal)) {
			return values()[ordinal];
		}else {
			throw new ExcepcionAlquilerVehiculos("Ordinal de la opción no válido");
		}
	}
	
	public static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length - 1) ? true : false; 
	}
	
}
