package UserInteraction;

import Archivos.*;
import Libreria.Venta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu extends GestorArchivo {


	/**
	 * Metodo que llama a un menu clasico
	 */
	public void menu() throws IOException {
		GestorArchivo gestor = new GestorArchivo();
		gestor.crearLibreria();
		boolean ejec = true;
		System.out.println("Bienvenido al menu principal, ¿Que desea hacer?");
		do {
			System.out.println("Selecciona la operacion a realizar");
			System.out.println("1 - Leer Json");
			System.out.println("2 - Agregar Libros");
			System.out.println("3 - Vender Libros");
			System.out.println("4 - Eliminar Libreria.json");
			System.out.println("9 - Terminar");
			int opcion = validarNumero();
			if (opcion >= 1 && opcion <= 4 ) {
				seleccion(opcion, ejec, gestor);
			} else if (opcion == 9) {
				ejec = false;
			} else {
				System.out.println("Opcion no valida");
			}

		} while (ejec);
	}

	/**
	 * 
	 * @param opcion
	 * @param ejecucion
	 */
	public void seleccion(int opcion, boolean ejecucion, GestorArchivo gestor) throws IOException {
		Venta venta = new Venta();
		switch(opcion){
			case 1:
				gestor.leerArchivo("libreria.json");
				break;
			case 2:
				venta.agregarLibro();
				break;
			case 3:
				venta.ventaLibroArchivo();
				break;
			case 4:
				gestor.vaciarLibro("libreria.json");
				break;
			default:
				break;
		}
	}


	/**
	 * Metodo que valida los numeros ingresados de letra y/o caracteres
	 * @return Retorna una Variable de tipo int.
	 */
	public int validarNumero() {
		Scanner teclado = new Scanner(System.in);
		int entrada = 0;
		Boolean valido;
		do {
			try {
				entrada = teclado.nextInt();
				if (entrada >= 0){
					valido = false;
				} else {
					System.out.println("Ingreso de negativo, Invalido");
					valido = true;
				}
			} catch (InputMismatchException ime) {
				System.out.println("No ingrese letras u oraciones");
				teclado.next();
				valido = true;
			}
		} while (valido);
		return entrada;
	}


}