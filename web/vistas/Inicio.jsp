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


        <section class="widget-principal">

            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div  class="carousel-inner carousel-principal">
                    <c:forEach var="art" items="${requestScope.articulos}">
                        <div class="carousel-item active">
                            <a href="verArticulo?idArt=${art.idArticulo}"><img class="d-block  w-100" src="imagenes/${art.nombreArticulo}.jpg" alt="First slide"></a>
                        <div class="titulo-carousel-principal">
                            <h2>${art.nombreArticulo}</h2>
                            <h5>diciembre 1, 2020</h5>
                        </div>

                    </div></c:forEach>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="./imagenes/Mercedes clásico.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="./imagenes/Porsche clasico.jpg" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </section>

        <section class="contenido-pagprin">

            <!--ÚLTIMAS PUBLICACIONES-->
            <div class="ultimas-publicaciones">
                <div class="widget-resto">
                    <div class="container">
                        <div class="col-sm-12 letrero-widgets">
                            <h4>Últimas publicaciones...</h4>
                        </div>
                        <hr class="hr-widgets">	
                        <div class="col-sm-12">
                            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner" role="listbox">
                                <div class="carousel-item active">
                                    <div class="row">
                                        <c:forEach var="art" items="${requestScope.articulos}">
                                            <div class="col-sm-3">
                                                <a href="verArticulo?idArt=${art.idArticulo}"><img class="d-block img-fluid" src="imagenes/${art.nombreArticulo}.jpg" width="300" height="300" alt="No hay imagen de ${art.nombreArticulo}"></a>
                                                <div class="text-center">
                                                    <h6 class="card-title">${art.nombreArticulo}.</h6>
                                                </div>
                                            </div></c:forEach>

                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--CLÁSICOS DEL MOTOR -->
            <div class="widget-resto">
                <div class="container">
                    <div class="col-sm-12 letrero-widgets">
                        <h4>Coches</h4>
                    </div>
                    <hr class="hr-widgets">	
                    <div class="col-sm-12">
                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner" role="listbox">
                                <div class="carousel-item active">
                                    <div class="row">
                                        <c:forEach var="art" items="${requestScope.articulosCoches}">
                                            <div class="col-sm-3">
                                                <a href="verArticulo?idArt=${art.idArticulo}"><img class="d-block img-fluid" src="imagenes/${art.nombreArticulo}.jpg" width="300" height="300" alt="No hay imagen de ${art.nombreArticulo}"></a>
                                                <div class="text-center">
                                                    <h6 class="card-title">${art.nombreArticulo}.</h6>
                                                </div>
                                            </div></c:forEach>

                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <!--Coches modernos -->
                <div class="widget-resto">
                    <div class="container">
                        <div class="col-sm-12 letrero-widgets">
                            <h4>Componentes</h4>
                        </div>
                        <hr class="hr-widgets">	
                        <div class="col-sm-12">
                            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner" role="listbox">
                                <div class="carousel-item active">
                                    <div class="row">
                                        <c:forEach var="art" items="${requestScope.articulosComponentes}">
                                            <div class="col-sm-3">
                                                <a href="verArticulo?idArt=${art.idArticulo}"><img class="d-block img-fluid" src="imagenes/${art.nombreArticulo}.jpg" width="300" height="300" alt="No hay imagen de ${art.nombreArticulo}"></a>
                                                <div class="text-center">
                                                    <h6 class="card-title">${art.nombreArticulo}.</h6>
                                                </div>
                                            </div></c:forEach>

                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <br>
            </section> 

            <footer class="bg-light text-center text-lg-start">
            <%@ include file="../fragmentosjsp/footer.jspf" %>
        </footer>



        <script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/popper.min.js" type="text/javascript"></script>
        <script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>

    </body>
</html>
