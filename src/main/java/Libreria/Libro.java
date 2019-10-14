package Libreria;

public class Libro {

	public int id;
	public String titulo;
	public String nombreAutor;
	public double precio;

	public Libro() {
		this.id = id;
		this.titulo = titulo;
		this.nombreAutor = nombreAutor;
		this.precio = precio;
	}

	public Libro(int id, String titulo, String nombreAutor, double precio) {
		this.id = id;
		this.titulo = titulo;
		this.nombreAutor = nombreAutor;
		this.precio = precio;
	}

	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	/**
	 * 
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNombreAutor() {
		return this.nombreAutor;
	}

	/**
	 * 
	 * @param nombreAutor
	 */
	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public double getPrecio() {
		return this.precio;
	}

	/**
	 * 
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Libro{" + "id=" + id + ", titulo='" + titulo + '\'' + ", nombreAutor='" + nombreAutor + '\'' + ", precio=" + precio + '}';
	}
}