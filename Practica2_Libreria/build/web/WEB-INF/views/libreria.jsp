<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Librería en Línea MVC</title>
    <!-- Enlace al nuevo archivo CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <div class="container">
        <h1>📚 Catálogo de Librería</h1>

        <!-- Formulario de Búsqueda -->
        <form action="${pageContext.request.contextPath}/BookServlet" method="GET" class="form-row">
            <input type="hidden" name="accion" value="buscar">
            <input type="text" name="criterio" placeholder="Filtrar por nombre o autor..." value="${param.criterio}">
            <button type="submit">Buscar Libro</button>
            <a href="${pageContext.request.contextPath}/BookServlet" class="limpiar-btn">Limpiar Filtro</a>
        </form>

        <hr style="margin: 30px 0; border: 0; border-top: 2px solid #000;">

        <!-- Formulario para Añadir -->
        <h3>Añadir Nuevo Libro</h3>
        <form action="${pageContext.request.contextPath}/BookServlet" method="POST" class="form-row">
            <input type="text" name="nombre" placeholder="Título del libro" required>
            <input type="text" name="autor" placeholder="Autor" required>
            <input type="number" step="0.01" name="precio" placeholder="Precio ($)" required>
            <button type="submit">+ Añadir al Catálogo</button>
        </form>

        <hr style="margin: 30px 0; border: 0; border-top: 2px solid #000;">

        <!-- Listado Dinámico en Grid (Reemplaza la tabla) -->
        <h2>Disponibles en Tienda</h2>
        <div class="grid-libros">
            <c:choose>
                <c:when test="${not empty libros}">
                    <c:forEach var="libro" items="${libros}">
                        <div class="libro-card">
                            <div class="libro-titulo">${libro.nombre}</div>
                            <div class="libro-autor">${libro.autor}</div>
                            <div class="libro-precio">$${libro.precio}</div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="empty-msg">No se encontraron libros. Añade el primer ejemplar arriba.</div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>