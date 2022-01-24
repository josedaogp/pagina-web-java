<%-- 
    Document   : filtrados
    Created on : 27-ene-2021, 13:25:46
    Author     : josed
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<div class="row">
    <c:set var="i" value="-1" />
    <c:forEach var="art" items="${sessionScope.LArtFav}">
        <div class="col-sm-3">
            <a href="verArticulo?idArt=${art.idArticulo}"><img class="d-block img-fluid" src="imagenes/${art.nombreArticulo}.jpg" width="300" height="300" alt="No hay imagen de ${art.nombreArticulo}"></a>
            <div class="text-center">
                <h6 class="card-title">${art.nombreArticulo}.</h6>
                <a href="delFavorito?idArt=${i=i+1}"><h6 style="color:red;">Borrar de favoritos</h6></a>
            </div>
        </div></c:forEach>
    <a href="invalidar"><h6 style="color:red;">ELIMINAR TODOS</h6></a>

</div>
