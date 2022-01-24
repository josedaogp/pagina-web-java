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


        

        <footer class="bg-light text-center text-lg-start">
            <%@ include file="../fragmentosjsp/footer.jspf" %>
        </footer>



        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
        <script src="js/popper.min.js" type="text/javascript"></script>
        <script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>

    </body>
</html>
