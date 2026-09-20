package controller;

import dao.BookDAO;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controlador principal de la librería.
 * Acciones soportadas: listar (default), buscar, filtrar, agregar.
 * Autor: [Tu nombre]
 */
@WebServlet(name = "BookServlet", urlPatterns = {"/BookServlet"})
public class BookServlet extends HttpServlet {

    private final BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            accion = "listar";
        }

        List<Book> resultados;

        switch (accion) {
            case "buscar": {
                String criterio = request.getParameter("criterio");
                resultados = bookDAO.buscarLibros(criterio);
                request.setAttribute("criterioAplicado", criterio);
                break;
            }
            case "filtrar": {
                String atributo = request.getParameter("atributo");
                String orden = request.getParameter("orden");
                resultados = bookDAO.filtrarYOrdenar(atributo, orden);
                request.setAttribute("atributoAplicado", atributo);
                request.setAttribute("ordenAplicado", orden);
                break;
            }
            case "listar":
            default:
                resultados = bookDAO.obtenerTodos();
        }

        request.setAttribute("libros", resultados);
        request.setAttribute("totalLibros", bookDAO.totalLibros());
        request.getRequestDispatcher("libreria.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("agregar".equals(accion)) {
            String nombre = request.getParameter("nombre");
            String autor = request.getParameter("autor");
            String precioStr = request.getParameter("precio");

            try {
                double precio = Double.parseDouble(precioStr);
                if (nombre != null && !nombre.trim().isEmpty()
                        && autor != null && !autor.trim().isEmpty()
                        && precio >= 0) {
                    bookDAO.agregarLibro(new Book(nombre.trim(), autor.trim(), precio));
                    request.getSession().setAttribute("mensaje", "Libro agregado correctamente.");
                } else {
                    request.getSession().setAttribute("error", "Datos inválidos. Verifica los campos.");
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("error", "El precio no es válido.");
            }
        }

        response.sendRedirect("BookServlet?accion=listar");
    }
}