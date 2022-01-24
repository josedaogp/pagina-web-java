/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var xhr;

function init_ajax() {
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }

}

function filtrar() {

    init_ajax();

    var url = "filtro";
    xhr.open("POST", url, true);
    xhr.onreadystatechange = articulosFiltrados;

    var nombre = document.getElementById("inputCategoriaFiltro");

    var datos = "categoriafiltro=" + encodeURIComponent(nombre.value);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(datos);

}

function articulosFiltrados() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            document.getElementById("respuesta").innerHTML = xhr.responseText;
        }
    }

}

function validar() {
    var ok = true;
    var msg = "ERROR: ";
    var user = document.getElementById("emailRegistro");
    var pass1 = document.getElementById("contraseniaRegistro");
    var pass2 = document.getElementById("contraseniaRegistro2");
    if (user.value == "" || pass1.value == "" || pass2.value == "") {
        msg = msg + "Todos los campos son obligatorios. ";
        ok = false;
    }
    if (pass1.value !== pass2.value) {
        msg = msg + "Passwords no coinciden. ";
        ok = false;
    }
    if (!ok)
        alert(msg);

    return ok;
}

function comprobarContraseñas() {
    var ok = true;
    var msg = "";
    var pass1 = document.getElementById("contraseniaRegistro");
    var pass2 = document.getElementById("contraseniaRegistro2");
    var pRegistrar = document.getElementById("pRegistrar");

    if (pass1.value !== pass2.value) {
        msg = msg + "Las contraseñas no coinciden ";
        ok = false;
    }
    if (!ok)
    {
        pRegistrar.innerHTML = "<h5 style=\"color: red; \">" + msg + "</h5>";
    } else {
        pRegistrar.innerHTML = "";
    }

    return ok;
}
