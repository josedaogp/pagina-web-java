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

            <!--enctype="multipart/form-data"-->

        <h1 class="H1LOGIN">SUBIR ARTÍCULO</h1>
        <form id="formLogin" class="login" method="POST" action="creaArticulo" enctype="multipart/form-data">
            <div class="form-group">
                <label style="font-size: 25px;" for="exampleInputEmail1">Nombre de la entrada</label>
                <input type="text" class="form-control" name="nombreArticulo" id="nombreArticulo" aria-describedby="emailHelp" placeholder="Nombre del Artículo">
            </div>
            <div class="form-group">
                <label style="font-size: 25px;" for="exampleInputPassword1">Cuerpo de la entrada</label>
                <input type="text" class="form-control" name="cuerpoArticulo" id="cuerpoArticulo" placeholder="Cuerpo de la entrada">
            </div>
            <div class="form-group">
                <label style="font-size: 25px;" for="exampleInputPassword1">Categoría</label>
                <input type="text" class="form-control" name="categoria" id="categoria" placeholder="Categoría">
            </div>
            <div class="form-group">
                <label style="font-size: 25px;" for="exampleInputPassword1">Imagen de portada</label>
                <input id="introducirFoto" name="introducirFoto" type="file" value="" />
            </div>
            

            <div class="submit-login">
                <button type="submit" class="btn btn-primary">Subir Artículo</button>
            </div>
        </form>


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
