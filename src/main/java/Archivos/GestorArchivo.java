package Archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Libreria.*;

public class GestorArchivo extends Libro {

	/**
	 * muestra los libros de librerias.json
	 */
	public void leerArchivo(String ruta) {
		Path archivo = Paths.get(ruta);
		String texto = "";
		try {
			texto = new String(Files.readAllBytes(archivo));
			System.out.println("El contenido del archivo es: \n"+texto);
		} catch (IOException e) {
			System.out.println("El archivo no pudo ser leido");
		}
	}


	/**
	 * Verifica si libreria.json existe, de no existir lo crea.
	 */

	public void crearLibreria(){

		if(new File("libreria.json").exists()){

		}
		else{
			try{
				Files.write(Paths.get("libreria.json"), new String().getBytes());
			}
			catch(IOException e) {

			}
		}
	}

	/**
	 * Añade ingredientes o instrucciones a un JSONArray.
	 *
	 * @param array Contiene ingredientes o instrucciones.
	 * @param obj Ingrediente o instruccion.
	 */

	public void llenarJSONArray(JSONArray array, Object obj) {

		array.add(obj);

	}

	/**
	 * Serializa una receta a formato JSONObject.
	 *
	 * @param id Numero id del libro.
	 * @param titulo Nombre del libro.
	 * @param nombreAutor Nombre del Autor.
	 * @param precio Valor del libro.
	 * @return JSONObject Receta en formato JSON.
	 */

	public static JSONObject serializar(int id, String titulo,String nombreAutor, double precio) {

		JSONObject obj = new JSONObject();
		obj.put("precio", precio);
		obj.put("nombreAutor", nombreAutor);
		obj.put("titulo", titulo);
		obj.put("id", id);


		return obj;
	}

	/**
	 * Agrega el texto almacenado en el JSONObject en un archivo .json y lo guarda.
	 *
	 * @param obj Receta en formato JSON.
	 */

	public void saveFile(JSONObject obj) throws IOException {


		String saltoLinea = System.getProperty("line.separator");

		ArrayList<String> lineas = vectorLineas();
		String textoViejo = "";

		for (int x = 0; x < lineas.size(); x++) {
			textoViejo += lineas.get(x) + saltoLinea;
		}

		try (FileWriter file = new FileWriter("libreria.json")) {

			file.write(textoViejo + obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Deserializa las lineas obtenidas de un .json y genera una Receta.
	 *
	 * @param lineas Recetas en formato String.
	 * @param n Cantidad de lineas del .json.
	 * @return Receta Receta como objeto de la clase del mismo nombre.
	 */

	public static Libro deserealizar(ArrayList<String> lineas, int n) {

		Libro libroL = new Libro();

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(lineas.get(n));

			JSONObject jsonObject = (JSONObject) obj;
			int id = (int) jsonObject.get("id");
			String titulo = (String) jsonObject.get("titulo");
			String nombreAutor = ((String) jsonObject.get("nombreAutor"));
			double precio = ((double) jsonObject.get("precio"));

			libroL.setId(id);
			libroL.setTitulo(titulo);
			libroL.setNombreAutor(nombreAutor);
			libroL.setPrecio(precio);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return libroL;
	}

	/**
	 * Cuenta la cantidad de lineas dentro del archivo .json.
	 *
	 * @return int Cantidad de lineas del archivo .json.
	 */

	public static int contarLineas() throws FileNotFoundException, IOException {
		int numLineas = 0;
		String fichero = new File("").getAbsolutePath() + File.separator + "libreria.json";
		BufferedReader reader = new BufferedReader(new FileReader(fichero));
		String linea = reader.readLine();

		while (linea != null) {
			numLineas = numLineas + 1;
			linea = reader.readLine();
		}
		return numLineas;
	}

	/**
	 * Convierte el .json en texto plano.
	 *
	 * @return  ArrayList Contiene el contenido del .json, de manera que cada linea es un String en el ArrayList.
	 *
	 */

	public static ArrayList<String> vectorLineas() throws FileNotFoundException, IOException {

		int numLineas = 0;
		int contador = 0;
		String fichero = new File("").getAbsolutePath() + File.separator + "libreria.json";

		BufferedReader reader = new BufferedReader(new FileReader(fichero));
		String linea = reader.readLine();

		numLineas = contarLineas();

		ArrayList<String> datos = new ArrayList<String>();

		while (linea != null && contador < numLineas) {
			datos.add(linea);
			linea = reader.readLine();
			contador++;
		}
		return datos;
	}


	/**
	 * Añande una receta al archivo .json.
	 *
	 * @param libro Libro para ser agregado al .json.
	 *
	 */

	public void agregarLibroArchivo(Libro libro) throws IOException {

		int id = libro.getId();
		String titulo = libro.getTitulo();
		String nombreAutor = libro.getNombreAutor();
		double precio = libro.getPrecio();


		saveFile(serializar(id, titulo,nombreAutor, precio));
	}


	/**
	 * Elimina completamente el contenido del .json.
	 *
	 * @param dir Ruta del archivo .json.
	 *
	 */

	public void vaciarLibro(String dir){

		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(dir));
			bw.write("");
			bw.close();
		}catch(IOException e){}

	}

}