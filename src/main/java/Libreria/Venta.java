package Libreria;

import Archivos.GestorArchivo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Venta extends Libro {

	public static GestorArchivo gestor = new GestorArchivo();
	public static ArrayList<Libro> librosDisponibles = new ArrayList<Libro>();

	public int contId;


	public static GestorArchivo getGestor() {
		return gestor;
	}

	public static void setGestor(GestorArchivo gestor) {
		Venta.gestor = gestor;
	}

	public static ArrayList<Libro> getLibrosDisponibles() {
		return librosDisponibles;
	}

	public static void setLibrosDisponibles(ArrayList<Libro> librosDisponibles) {
		Venta.librosDisponibles = librosDisponibles;
	}


	public void agregarLibro() throws IOException {
		Libro nuevoLibro = new Libro();
		Scanner teclado = new Scanner(System.in);

		System.out.println("Ingrese nombre del libro");
		nuevoLibro.setTitulo(teclado.next());

		System.out.println("Ingrese nombre del autor");
		nuevoLibro.setNombreAutor(teclado.next());

		System.out.println("Ingrese precio del libro");
		nuevoLibro.setPrecio(validarNumero());

		librosDisponibles.add(nuevoLibro);

		while (contId<librosDisponibles.size()){
			contId = contId +1;
		}
		nuevoLibro.setId(contId);
		gestor.agregarLibroArchivo(nuevoLibro);
		System.out.println("Libro agregado....");
	}

	public void ventaLibroArchivo() throws IOException{
		Scanner teclado = new Scanner(System.in);
		gestor.leerArchivo("libreria.json");
		System.out.println("Para comprar ingrese el nombre del libro");
		String nombre = teclado.next();
		for(int i=0; i<librosDisponibles.size(); i++){
			if(librosDisponibles.get(i).getTitulo().equals(nombre)){
				librosDisponibles.remove(i);
			}
		}
		gestor.vaciarLibro("libreria.json");
		for(int i=0; i<librosDisponibles.size(); i++){
			gestor.agregarLibroArchivo(librosDisponibles.get(i));
			System.out.println("Venta Realizada...");
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