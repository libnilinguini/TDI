package model;

/**
 * Entidad que representa un libro dentro de la librería.
 * Autor: [Tu nombre]
 */
public class Book {
    private int id;
    private String nombre;
    private String autor;
    private double precio;

    public Book() {
    }

    public Book(int id, String nombre, String autor, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.precio = precio;
    }

    public Book(String nombre, String autor, double precio) {
        this.nombre = nombre;
        this.autor = autor;
        this.precio = precio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "Book{id=" + id + ", nombre='" + nombre + "', autor='" + autor + "', precio=" + precio + '}';
    }
}