package AlquilerVehiculos.mvc.modelo.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import AlquilerVehiculos.mvc.modelo.dominio.Alquiler;
import AlquilerVehiculos.mvc.modelo.dominio.Cliente;
import AlquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 * Clase que contiene el array donde se almacenarán los 
 * objetos de tipo Alquiler
 * @author john
 *
 */
public class Alquileres {

	private Alquiler[] alquileres;
	private final int MAX_ALQUILERES = 20;
	private final String FICHERO_ALQUILERES = "datos/alquileres.dat";
	
	/**
	 * Constructor de la clase que crea el array
	 * Alquileres y o inicializa a MAX_ALQUILERES
	 */
	public Alquileres() {
		alquileres = new Alquiler[MAX_ALQUILERES];
	}
	
	/**
	 * Método de tipo array que devuelve una copia del array
	 * @return el array de alquileres
	 */
	public Alquiler[] getAlquileres() {
		return alquileres.clone();
	}
	
	/**
	 * Método para leer datos de alquileres desde un fichero
	 */
	public void leerAlquileres() {
		File fichero = new File(FICHERO_ALQUILERES);
		ObjectInputStream entrada;
		
		try {
			entrada = new ObjectInputStream(new FileInputStream(fichero));
			try {
				alquileres = (Alquiler[])entrada.readObject();
				entrada.close();
				System.out.println("Fichero de alquileres leído correctamente");
			}catch(ClassNotFoundException e) {
				System.out.println("ERROR: no se encuentra la clase que hay que leer.");
			}catch(IOException e) {
				System.out.println("ERROR: Error inesperado de Entrada/Salida");
			}
		}catch(IOException e) {
			System.out.println("ERROR: No se puede abrir el fichero de alquileres");
		}
	}
	
	/**
	 * Método para escribir datos en un fichero
	 */
	public void escribirAlquileres() {
		File fichero = new File(FICHERO_ALQUILERES);
		
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			salida.writeObject((Alquiler[])alquileres);
			salida.close();
			System.out.println("Fichero de alquileres escrito correctamente");
		}catch(FileNotFoundException e) {
			System.out.println("ERROR: no puedo crear el fichero de clientes");
		}catch(IOException e) {
			System.out.println("ERROR: Error inesperado de Entrada/Salida");
		}
	}
	
	/**
	 * Método que inicia un alquiler y pone el vehículo seleccionado
	 * por el cliente como no disponible.
	 * @param cliente El cliente que alquila.
	 * @param vehiculo El turismo elegido por el cliente.
	 */
	public void abrirAlquiler (Cliente cliente, Vehiculo vehiculo) {
		int indice = buscarPrimerIndiceLibreComprobandoExistenciaOtroAbierto(cliente, vehiculo);
		
		if (indiceNoSuperaTamano(indice)) {
			alquileres[indice] = new Alquiler (cliente, vehiculo);
		}else {
			throw new ExcepcionAlquilerVehiculos ("El array de alquileres está lleno.");
		}
			
	}
	
	/**
	 * Comprueba que no se supera el tamaño del array.
	 * @param indice posición dentro del array.
	 * @return true si no se ha superado el tamaño y false si lo ha hecho.
	 */
	private boolean indiceNoSuperaTamano(int indice) {
		return indice < alquileres.length;
	}
	
	/**
	 * Método que busca la primera posición libre del array de Alquileres que
	 * no contiene ningún alquiler y lanza una excepción si el turismo ya está
	 * siendo alquilado en otro alquiler abierto.
	 * @param clientes el cliente que queremos vincular al nuevo alquiler.
	 * @param vehiculos el turismo elegido por el cliente.
	 * @return la posición del array de Alquileres donde poder almacenar un nuevo alquiler.
	 */
	private int buscarPrimerIndiceLibreComprobandoExistenciaOtroAbierto(Cliente clientes, Vehiculo vehiculos) {
		int indice = 0;
		boolean encontrado = false;
		
		
		while(indiceNoSuperaTamano(indice) && !encontrado) {
			if (alquileres[indice] == null) {
				encontrado = true;
			}else if (alquileres[indice].getVehiculo().getMatricula().equals(vehiculos.getMatricula())
					&&alquileres[indice].getVehiculo().getDisponible() == false){
				throw new ExcepcionAlquilerVehiculos("Ya existe un alquiler abierto para ese turismo.");
			}else {
				indice++;
			}
		}
		return indice;
	}
	
	/**
	 * Método que cierra un alquiler abierto y pone el vehículo disponible
	 * para un nuevo alquiler.
	 * @param cliente que tiene un alquiler abierto para cerrar dicho alquiler.
	 * @param vehiculo elegido por el cliente.
	 */
	public void cerrarAlquiler (Cliente cliente, Vehiculo vehiculo) {
		int indice = buscarAlquilerAbierto(cliente, vehiculo);
		
		if (indiceNoSuperaTamano(indice)) {
			alquileres[indice].close();
		}else {
			throw new ExcepcionAlquilerVehiculos ("No hay ningún alquiler abierto para ese vehículo.");
		}
	}
	
	/**
	 * Método que busca un alquiler abierto
	 * @param cliente el cliente que tiene un vehículo alquilado.
	 * @param vehiculo el turismo que ha elegido el cliente.
	 * @return posición del array donde se encuentra el alquiler abierto.
	 */
	private int buscarAlquilerAbierto(Cliente cliente, Vehiculo vehiculo) {
		int indice = 0;
		boolean encontrado = false;
		
		while (indiceNoSuperaTamano(indice) && !encontrado) {
			if(alquileres[indice] != null && alquileres[indice].getVehiculo().getMatricula().equals(vehiculo.getMatricula())
			   && alquileres[indice].getCliente().getDni().equals(cliente.getDni())
			   && alquileres[indice].getVehiculo().getDisponible()==false && alquileres[indice].getDias() == 0) {//Mirar esto
				encontrado = true;
			}else {
				indice++;
			}
		}
		return indice;
	}

}
