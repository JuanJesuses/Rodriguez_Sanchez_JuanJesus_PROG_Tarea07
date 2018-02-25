package AlquilerVehiculos.mvc.modelo.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import AlquilerVehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import AlquilerVehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 * Clase que contiene el array donde se almacenarán los
 * objetos de tipo vehículo
 * @author john
 *
 */
public class Vehiculos {

	private Vehiculo[] vehiculos;
	private final int MAX_VEHICULOS = 20;
	private final String FICHERO_VEHICULOS =  "datos/vehiculos.dat";
	
	/**
	 * Constructor de la clase que crea el array de Vehiculos
	 * y lo inicializa a MAX_VEHICULOS
	 */
	public Vehiculos () {
		vehiculos = new Vehiculo[MAX_VEHICULOS];
	}
	
	/**
	 * Método que devuelve una copia del array
	 * @return el array de vehículos
	 */
	public Vehiculo[] getVehiculos() {
		return vehiculos.clone();
	}
	
	/**
	 * Método para leer ficheros desde archivo
	 */
	public void leerVehiculos() {
		File fichero = new File(FICHERO_VEHICULOS);
		ObjectInputStream entrada;
		
		try {
			entrada = new ObjectInputStream(new FileInputStream(fichero));
			try {
				vehiculos = (Vehiculo[])entrada.readObject();
				entrada.close();
				System.out.println("Fichero de vehículos leído correctamente.");
			}catch (ClassNotFoundException e) {
				System.out.println("ERROR: No se ha encontrado la clase que hay que leer.");
			}catch(IOException e) {
				System.out.println("ERROR: Error inesperado de Entrada/Salida");
			}
		}catch(IOException e) {
			System.out.println("ERROR: No se puede abrir el fichero de vehículos.");
		}
	}
	
	/**
	 * Método que escribe datos en un fichero
	 */
	public void escribirVehiculos() {
		File fichero = new File(FICHERO_VEHICULOS);
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			salida.writeObject((Vehiculo[])vehiculos);
			salida.close();
			System.out.println("Fichero de vehículos escrito correctamente.");
		}catch(FileNotFoundException e) {
			System.out.println("ERROR: No se puede crear el fichero de vehículos.");
		}catch(IOException e) {
			System.out.println("ERROR: Error inesperado de Entrada/Salida");
		}
	}
	
	/**
	 * Método que añade un turismo al array de turismos.
	 * Si la posición del array está ocupada o el 
	 * array está completo, lanza la excepción.
	 * @param vehiculo
	 */
	public void anadirVehiculo (Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
		int indice = buscarPrimerIndiceLibreComprobandoExistencia(vehiculo);
		
		if (indiceNoSuperaTamano(indice)) {
			vehiculos[indice] = tipoVehiculo.getInstancia(vehiculo.getMatricula(), vehiculo.getMarca(), 
					  vehiculo.getModelo(), vehiculo.getDatosTecnicosVehiculo());
		}else {
			throw new ExcepcionAlquilerVehiculos ("El array de vehículos está lleno.");
		}
	}
	
	/**
	 * Comprueba que no se supera el tamaño del array.
	 * @param indice posición dentro del array.
	 * @return true si no se ha superado el tamaño y false si lo ha hecho.
	 */
	private boolean indiceNoSuperaTamano(int indice) {
		return indice < vehiculos.length;
	}
	
	/**
	 * Comprueba la primera posición libre del array y lanza
	 * una excepción si la matrícula del turismo pasada por parámetro 
	 * ya está almacenada en el array.
	 * @param vehiculo para añadir en caso de que no exista.
	 * @return la primera posición libre del array.
	 */
	private int buscarPrimerIndiceLibreComprobandoExistencia(Vehiculo vehiculo) {
		int indice = 0;
		boolean encontrado = false;
		
		while (indiceNoSuperaTamano(indice) && !encontrado) {
			if (vehiculos[indice] == null) {
				encontrado = true;
			}else if (vehiculos[indice].getMatricula().equals(vehiculo.getMatricula())) {
				throw new ExcepcionAlquilerVehiculos ("Ya existe un turismo con esa matrícula.");
			}else {
				indice++;
			}
		}
		return indice;
	}
	
	/**
	 * Método que elimina un turismo del array de Turismos.
	 * @param matricula del vehículo a borrar.
	 */
	public void borrarVehiculo (String matricula) {
		int indice = buscarIndiceVehiculo(matricula);
		
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaLaIzquierda(indice);
		}else {
			throw new ExcepcionAlquilerVehiculos ("El turismo a eliminar no existe.");
		}	
	}
	
	/**
	 * Cambia la posición de los elementos del array desde el encontrado
	 * hacia la izquierda para no dejar huecos vacíos entre los distintos
	 * elementos del array.
	 * @param indice es la posición del array que ha quedado vacía.
	 */
	private void desplazarUnaPosicionHaciaLaIzquierda(int indice) {
		for (int i = indice; i < vehiculos.length-1; i++) {
			vehiculos[i] = vehiculos[i+1];
		}
	}
	
	/**
	 * Localiza la posición del array donde se encuentra el turismo buscado.
	 * @param matricula para buscar el turismo
	 * @return la posición del array donde se encuentra el turismo buscado.
	 */
	private int buscarIndiceVehiculo(String matricula) {
		int indice = 0;
		boolean encontrado = false;
		
		while (indiceNoSuperaTamano(indice) && !encontrado) {
			if (vehiculos[indice] != null && vehiculos[indice].getMatricula().equals(matricula)) {
				encontrado = true;
			}else {
				indice++;
			}
		}
		return indice;
	}
	
	/**
	 * Método que obtiene un turismo del aray de turismos.
	 * @param matricula del turismo buscado
	 * @return el turismo buscado
	 */
	public Vehiculo buscarVehiculo (String matricula) {
		int indice = buscarIndiceVehiculo(matricula);
		
		Vehiculo vehiculo = null;
		if (indiceNoSuperaTamano(indice)) {
			return (vehiculos[indice]);
		}else {
			return null;
		}
	}
}
