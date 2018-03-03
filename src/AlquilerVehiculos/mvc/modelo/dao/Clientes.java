package AlquilerVehiculos.mvc.modelo.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import AlquilerVehiculos.mvc.modelo.dominio.Cliente;
import AlquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 * Clase que contiene el array donde se almacenarán
 * los objetos de tipo Cliente
 * @author john
 *
 */
public class Clientes {

	private Cliente[] clientes;
	private final int MAX_CLIENTES = 20;
	private final String FICHERO_CLIENTES = "datos/clientes.dat";
			
	/**
	 * Constructor de la clase que crea el array
	 * y lo inicializa a MAX_CLIENTES
	 */
	public Clientes() {
		clientes = new Cliente[MAX_CLIENTES];
	}
	
	/**
	 * Método de tipo array que devuelve una copia del array
	 * @return el array de clientes
	 */
	public Cliente[] getClientes() {
		return clientes.clone();
	}
	
	/**
	 * Método para leer datos de clientes del fichero
	 */
	public void leerClientes() {
		
		File fichero = new File (FICHERO_CLIENTES);
		ObjectInputStream entrada;
		
		try {
			entrada = new ObjectInputStream(new FileInputStream(fichero));
			try {
				clientes = (Cliente[])entrada.readObject();
				entrada.close();
				System.out.println("Fichero de clientes leído correctamente.");
				Cliente.aumentarUltimoIdentificador(calcularUltimoIdentificador());
			}catch (ClassNotFoundException e) {
				System.out.println("ERROR: No se encuentra la clase que hay que leer");
			}catch(IOException e) {
				System.out.println("ERROR: Error inesperado de Entrada/Salida");
			}
		}catch (IOException e) {
			System.out.println("ERROR: no se puede abrir el fichero de clientes");
		}	
	}
	
	/**
	 * Método para escribir datos de clientes en un fichero
	 */
	public void escribirClientes() {
		File fichero = new File(FICHERO_CLIENTES);
		
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			salida.writeObject((Cliente[])clientes);
			salida.close();
			System.out.println("Fichero de clientes escrito correctamente");
		}catch(FileNotFoundException e) {
			System.out.println("ERROR: no se puede crear el fichero clientes");
		}catch (IOException e) {
			System.out.println("ERROR: Error inesperado de Entrada/Salida");
		}
	}
		
	/**
	 * Método que añade un cliente al array de clientes.
	 * Si la posición del array está ocupada o el 
	 * array está completo, lanza la excepción.
	 * @param cliente
	 */
	public void anadirCliente (Cliente cliente) {
		int indice = buscarPrimerIndiceLibreComprobandoExistencia(cliente);
		
		if (indiceNoSuperaTamano(indice)) {
			clientes[indice] = new Cliente (cliente);
		}else {
			throw new ExcepcionAlquilerVehiculos ("El array de clientes está lleno.");
		}
	}
	
	/**
	 * Comprueba que no se supera el tamaño del array.
	 * @param indice posición dentro del array.
	 * @return true si no se ha superado el tamaño y false si lo ha hecho.
	 */
	private boolean indiceNoSuperaTamano(int indice) {
		return indice < clientes.length;
	}
	
	
	/**
	 * Comprueba la primera posición libre del array y lanza
	 * una excepción si el dni del cliente pasado por parámetro 
	 * ya está almacenado en el array.
	 * @param cliente para añadir en caso de que no exista.
	 * @return la primera posición libre del array.
	 */
	private int buscarPrimerIndiceLibreComprobandoExistencia(Cliente cliente) {
		int indice = 0;
		boolean encontrado = false;
		
		while (indiceNoSuperaTamano(indice) && !encontrado) {
			if (clientes[indice] == null) {
				encontrado = true;
			}else if (clientes[indice].getDni().equals(cliente.getDni())) {
				throw new ExcepcionAlquilerVehiculos("Ya existe un cliente con ese DNI.");
			}else {
				indice++;
			}
		}
		return indice;
	}
	
	/**
	 * Método que elimina un cliente del array de clientes.
	 * @param dni para buscar el cliente a borrar.
	 */
	public void borrarCliente (String dni) {
		int indice = buscarIndiceCliente(dni);
		
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaLaIzquierda(indice);
		}else {
			throw new ExcepcionAlquilerVehiculos ("El cliente a borrar no existe.");
		}
	}
	
	/**
	 * Cambia la posición de los elementos del array desde el encontrado
	 * hacia la izquierda para no dejar huecos vacíos entre los distintos
	 * elementos del array.
	 * @param indice es la posición del array que ha quedado vacía.
	 */
	private void desplazarUnaPosicionHaciaLaIzquierda(int indice) {
		for (int i = indice; i < clientes.length-1 && clientes[i] != null; i++) {
			clientes[i] = clientes [i+1];
		}
	}
	
	/**
	 * Localiza la posición del array donde se encuentra el cliente buscado.
	 * @param dni para buscar el cliente.
	 * @return la posición del array donde se encuentra el cliente buscado.
	 */
	private int buscarIndiceCliente(String dni) {
		int indice = 0;
		boolean encontrado = false;
		
		while (indiceNoSuperaTamano(indice) && !encontrado) {
			if (clientes[indice] != null && clientes[indice].getDni().equals(dni)) {
				encontrado = true;
			}else {
				indice++;
			}
		}
		return indice;
	}
	
	/**
	 * Método que obtiene un cliente del array de clientes.
	 * @param dni del cliente buscado.
	 * @return devuelve el cliente si lo encuentra o null en caso contrario.
	 */
	public Cliente buscarCliente (String dni) {
		int indice = buscarIndiceCliente(dni);
		
		if (indiceNoSuperaTamano(indice)) {
			return new Cliente(clientes[indice]);
		}else {
			return null;
		}
	}
	
	private int calcularUltimoIdentificador() {
		int ultimoIdentificador = 0;
		int i = 0;
		
		while(clientes[i] != null) {
			if(clientes[i].getIdentificador() > ultimoIdentificador) {
				ultimoIdentificador = clientes[i].getIdentificador();
			}
			i++;
		}	
		
		return ultimoIdentificador;
	}

}
