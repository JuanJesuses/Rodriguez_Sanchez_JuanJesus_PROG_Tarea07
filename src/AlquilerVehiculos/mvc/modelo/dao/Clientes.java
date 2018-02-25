package AlquilerVehiculos.mvc.modelo.dao;

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

}
