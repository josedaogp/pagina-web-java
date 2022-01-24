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

        <form style="margin-left: 16%; margin-top: 20px; margin-bottom: 50px;" id="filtrarArticulos" name="filtrarArticulos">
            <div class="form-group">
                <label for="CategoriaFiltro">Categoría</label>
                <!--<input type="text" class="form-control" name="inputCategoriaFiltro" id="inputCategoriaFiltro" aria-describedby="emailHelp" placeholder="¿Qué categoría quieres mostrar?">-->
                <input id="inputCategoriaFiltro" type="text" name="inputCategoriaFiltro" />
            </div>
            <input type="button" class="btn btn-primary" value="Filtar" onclick="filtrar();"/>
        </form>
            
        <div class="ultimas-publicaciones">
            <div class="widget-resto">
                <div class="container">
                    <div class="col-sm-12 letrero-widgets">
                        <h4>Artículos</h4>
                    </div>
                    <hr class="hr-widgets">	
                    <div class="col-sm-12">

                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner" role="listbox">
                                <div id="respuesta" class="carousel-item active">
                                        <%@ include file="../fragmentosjsp/filtrados.jsp" %>
                                    </div>
                                </div>

                            </div> 
                        </div>
                    </div>
                </div>
            </div>




            <footer class="bg-light text-center text-lg-start">
            <%@ include file="../fragmentosjsp/footer.jspf" %>
        </footer>

        <script src="javascript/js.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
        <script src="js/popper.min.js" type="text/javascript"></script>
        <script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>

    </body>
</html>
