package dao;

import model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DAO encargado de gestionar los libros en memoria.
 * Incluye operaciones de agregar, listar, buscar y filtrar/ordenar.
 * Autor: [Tu nombre]
 */
public class BookDAO {

    private static final List<Book> almacen = new ArrayList<>();
    private static int secuenciaId = 1;

    /** Agrega un libro asignándole un ID único. */
    public synchronized void agregarLibro(Book book) {
        book.setId(secuenciaId++);
        almacen.add(book);
    }

    /** Devuelve todos los libros registrados. */
    public List<Book> obtenerTodos() {
        return new ArrayList<>(almacen);
    }

    /** Busca libros por nombre o autor (coincidencia parcial, sin distinguir mayúsculas). */
    public List<Book> buscarLibros(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return obtenerTodos();
        }
        String c = criterio.toLowerCase().trim();
        return almacen.stream()
                .filter(b -> b.getNombre().toLowerCase().contains(c)
                          || b.getAutor().toLowerCase().contains(c))
                .collect(Collectors.toList());
    }

    /**
     * Filtra y ordena los libros según el atributo y el orden indicados.
     * @param atributo "nombre" | "autor" | "precio"
     * @param orden    "asc" | "desc"
     */
    public List<Book> filtrarYOrdenar(String atributo, String orden) {
        List<Book> resultado = new ArrayList<>(almacen);

        if (atributo == null) atributo = "nombre";

        Comparator<Book> comparador;
        switch (atributo.toLowerCase()) {
            case "autor":
                comparador = Comparator.comparing(b -> b.getAutor().toLowerCase());
                break;
            case "precio":
                comparador = Comparator.comparingDouble(Book::getPrecio);
                break;
            case "nombre":
            default:
                comparador = Comparator.comparing(b -> b.getNombre().toLowerCase());
        }

        resultado.sort(comparador);

        if ("desc".equalsIgnoreCase(orden)) {
            Collections.reverse(resultado);
        }
        return resultado;
    }

    /** Devuelve la cantidad total de libros almacenados. */
    public int totalLibros() {
        return almacen.size();
    }
}