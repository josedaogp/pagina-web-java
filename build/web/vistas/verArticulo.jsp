<%-- 
    Document   : Inicio
    Created on : 22-ene-2021, 16:45:57
    Author     : josed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" href="imagenes/fav-icon.png"/>

        <title>Catching The Speed</title>
    </head>
    <body>
        
        <section class="cabecera">
            <div class="cabecera-rrss">
                <%@ include file="../fragmentosjsp/cabecerarrss.jspf" %>
            </div>

            <div class="cabecera-principal">
                <%@ include file="../fragmentosjsp/espacioLogo.jspf" %>
            </div>

            <div class="cabecera-menu">
                <c:choose>
                    <c:when test="${sessionScope.user!=null}">
                        <%@ include file="../fragmentosjsp/menuLogin.jspf" %>
                    </c:when>
                    <c:otherwise> 
                        <%@ include file="../fragmentosjsp/menuNoLogin.jspf" %>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>

        <div class="carousel-inner carousel-principal">
            <div class="carousel-item active">
                <img class="d-block  w-100" src="imagenes/${requestScope.NombreArticulo}.jpg" alt="First slide">
            </div>
        </div>

        <div class="tit-articulo"><h1 >${requestScope.NombreArticulo}</h1></div>
        <div class="div-cuerpoart"><p>${requestScope.CuerpoArticulo}</p></div>
        <div class="div-cuerpoart">
            <c:choose>
                <c:when test="${sessionScope.user!=null}">
                    <form action="addFavorito?idArtFav=${requestScope.IdArticulo}" method="POST">
                        <input type="submit" value="Añadir el artículo a favoritos" />
                    </form>
                </c:when>
            </c:choose>
        </div>

        <!-- SECCION DE COMENTARIOS-->
        <div class="div-comentarios">
            <h3 class="col-sm-12 letrero-widgets ">Sección de Comentarios</h3>    
            <c:choose>
                <c:when test="${sessionScope.user!=null}">
                    <h4>${sessionScope.user}, ¿quieres hacer un comentario?</h4>
                    <form action="enviarComentario?idArt=${requestScope.IdArticulo}" method="POST">
                        <div class="form-group">
                            <input type="text" class="form-control" name="inputComentario" id="inputComentario" aria-describedby="emailHelp" placeholder="Introduce aquí tu comentario">
                            <small id="emailHelp" class="form-text text-muted">Los comentarios solo son visibles para usuarios registrados, y serán anónimos.</small>
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar comentario</button>
                    </form>

                    <c:forEach var="com" items="${requestScope.ListaComentarios}">
                        <div style="border-width: 5px; margin-top: 25px;">
                            <div>
                                <h5 class="card-title">${com.contenidoComentario}</h5>
                            </div>
                        </div></c:forEach>

                </c:when>
                <c:otherwise> 
                    <h4>Para ver los comentarios debes estar registrado :(</h4>
                </c:otherwise>
            </c:choose>
        </div>





        <footer class="bg-light text-center text-lg-start">
            <%@ include file="../fragmentosjsp/footer.jspf" %>
        </footer>



        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
        <script src="js/popper.min.js" type="text/javascript"></script>
        <script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>

    </body>
</html>
