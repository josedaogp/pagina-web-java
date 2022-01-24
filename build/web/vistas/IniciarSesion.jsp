<%-- 
    Document   : Inicio
    Created on : 22-ene-2021, 16:45:57
    Author     : josed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                <%@ include file="../fragmentosjsp/menuNoLogin.jspf" %>
            </div>
        </section>

        <h1 class="H1LOGIN">INICIAR SESION</h1>
        <form id="formLogin" class="login" method="POST" action="validarUsuario">
            <div class="form-group">
                <label for="exampleInputEmail1">Direccion de Correo</label>
                <input type="email" class="form-control" name="emailLogin" id="emailLogin" aria-describedby="emailHelp" placeholder="Introduce email">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Contraseña</label>
                <input type="password" class="form-control" name="contraseniaLogin" id="contraseniaLogin" placeholder="Contraseña">
            </div>

            <div class="submit-login">
                <button type="submit" class="btn btn-primary">Iniciar Sesion</button>
            </div>
        </form>
        <div><c:if test="${requestScope.msg!=null}"><p>${requestScope.msg}</p></c:if></div>

        <footer class="bg-light text-center text-lg-start">
            <%@ include file="../fragmentosjsp/footer.jspf" %>
        </footer>

        


        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
        <script src="js/popper.min.js" type="text/javascript"></script>
        <script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>

    </body>
</html>
