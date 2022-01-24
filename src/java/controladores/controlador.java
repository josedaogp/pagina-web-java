/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Articulos;
import entidades.Comentarios;
import entidades.Usuarios;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author josed
 */
@MultipartConfig

@WebServlet(name = "acceso", urlPatterns = {"/acceso", "/home", "/registrarse", "/insertaUsuario", "/iniciosesion", "/validarUsuario",
    "/logout", "/subirArticulo", "/creaArticulo", "/articulos", "/verArticulo", "/filtro", "/enviarComentario", "/addFavorito", "/favoritos", "/delFavorito", "/invalidar"})
public class controlador extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalV1PU");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        //Usuarios usuArt = new Usuarios();
        String accion;
        accion = request.getServletPath();
        String vista;
        HttpSession session = request.getSession();

        switch (accion) {
            case "/home":
                //Mostrar página de inicio
                //Todos los articulos
                TypedQuery<Articulos> queryH = em.createNamedQuery("Articulos.findAll", Articulos.class);
                List<Articulos> lrH = queryH.getResultList();
                request.setAttribute("articulos", lrH);

                //Coches
                TypedQuery<Articulos> queryCoches = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                queryCoches.setParameter("categoriaArticulo", "Coches");
                List<Articulos> lrCoches = queryCoches.getResultList();
                request.setAttribute("articulosCoches", lrCoches);

                //Coches
                TypedQuery<Articulos> queryComponentes = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                queryComponentes.setParameter("categoriaArticulo", "Componentes");
                List<Articulos> lrComponentes = queryComponentes.getResultList();
                request.setAttribute("articulosComponentes", lrComponentes);

                vista = "/vistas/Inicio.jsp";
                break;

            case "/iniciosesion":
                //Mostrar página de Iniciar Sesion
                vista = "vistas/IniciarSesion.jsp";
                break;
            case "/validarUsuario":
                // Validar el usuario
                try {
                String email = request.getParameter("emailLogin");
                String pass = request.getParameter("contraseniaLogin");
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(pass.getBytes());
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                String pass_digest = sb.toString();
                TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.findByNameAndPass", Usuarios.class);
                query.setParameter("email", email);
                query.setParameter("pwd", pass_digest);

                List<Usuarios> lu;
                lu = query.getResultList();
                if (!lu.isEmpty()) {
                    Usuarios u = lu.get(0);
                    session.setAttribute("id", u.getId());
                    session.setAttribute("user", u.getNombre());
                    request.setAttribute("msg", "Login correcto.");
                    session.setAttribute("userObject", u);

                    //Todos los articulos
                    TypedQuery<Articulos> queryH1 = em.createNamedQuery("Articulos.findAll", Articulos.class);
                    List<Articulos> lrH1 = queryH1.getResultList();
                    request.setAttribute("articulos", lrH1);

                    //Coches
                    TypedQuery<Articulos> queryCoches1 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                    queryCoches1.setParameter("categoriaArticulo", "Coches");
                    List<Articulos> lrCoches1 = queryCoches1.getResultList();
                    request.setAttribute("articulosCoches", lrCoches1);

                    //Coches
                    TypedQuery<Articulos> queryComponentes1 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                    queryComponentes1.setParameter("categoriaArticulo", "Componentes");
                    List<Articulos> lrComponentes1 = queryComponentes1.getResultList();
                    request.setAttribute("articulosComponentes", lrComponentes1);

                    vista = "/vistas/Inicio.jsp";
                } else {
                    request.setAttribute("msg", "Usuario o Password incorrecto.");
                    vista = "/vistas/IniciarSesion.jsp";
                }
            } catch (Exception e) {
                System.err.println(e);
                request.setAttribute("msg", "ERROR: imposible validar al usuario");

                //Todos los articulos
                TypedQuery<Articulos> queryH2 = em.createNamedQuery("Articulos.findAll", Articulos.class);
                List<Articulos> lrH2 = queryH2.getResultList();
                request.setAttribute("articulos", lrH2);

                //Coches
                TypedQuery<Articulos> queryCoches2 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                queryCoches2.setParameter("categoriaArticulo", "Coches");
                List<Articulos> lrCoches2 = queryCoches2.getResultList();
                request.setAttribute("articulosCoches", lrCoches2);

                //Coches
                TypedQuery<Articulos> queryComponentes2 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                queryComponentes2.setParameter("categoriaArticulo", "Componentes");
                List<Articulos> lrComponentes2 = queryComponentes2.getResultList();
                request.setAttribute("articulosComponentes", lrComponentes2);

                vista = "/vistas/Inicio.jsp";
            }

            break;
            case "/registrarse":
                //Mostrar página de login
                vista = "/vistas/Registrar.jsp";
                break;
            case "/insertaUsuario":
                try {
                String email = request.getParameter("emailRegistro");
                String nombre = request.getParameter("nombreRegistro");
                String pass = request.getParameter("contraseniaRegistro");

                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(pass.getBytes());
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                String pass_digest = sb.toString();
                System.out.println("La contraseña digest es: " + pass_digest);

                Usuarios u = new Usuarios();
                u.setEmail(email);
                u.setNombre(nombre);
                u.setPassword(pass_digest);
                u.setArticulosList(null);
                persist(u);

                request.setAttribute("msg", "Usuario guardado");
            } catch (Exception e) {
                request.setAttribute("msg", "ERROR: Usuario NO guardado: " + e);
            }

            //Todos los articulos
            TypedQuery<Articulos> queryH3 = em.createNamedQuery("Articulos.findAll", Articulos.class);
            List<Articulos> lrH3 = queryH3.getResultList();
            request.setAttribute("articulos", lrH3);

            //Coches
            TypedQuery<Articulos> queryCoches3 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
            queryCoches3.setParameter("categoriaArticulo", "Coches");
            List<Articulos> lrCoches3 = queryCoches3.getResultList();
            request.setAttribute("articulosCoches", lrCoches3);

            //Coches
            TypedQuery<Articulos> queryComponentes3 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
            queryComponentes3.setParameter("categoriaArticulo", "Componentes");
            List<Articulos> lrComponentes3 = queryComponentes3.getResultList();
            request.setAttribute("articulosComponentes", lrComponentes3);
            vista = "/vistas/Inicio.jsp";
            break;

            case "/logout":
                //
                session.invalidate();
                request.setAttribute("msg", "Usuario desconectado");

                //Todos los articulos
                TypedQuery<Articulos> queryH4 = em.createNamedQuery("Articulos.findAll", Articulos.class);
                List<Articulos> lrH4 = queryH4.getResultList();
                request.setAttribute("articulos", lrH4);

                //Coches
                TypedQuery<Articulos> queryCoches4 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                queryCoches4.setParameter("categoriaArticulo", "Coches");
                List<Articulos> lrCoches4 = queryCoches4.getResultList();
                request.setAttribute("articulosCoches", lrCoches4);

                //Coches
                TypedQuery<Articulos> queryComponentes4 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                queryComponentes4.setParameter("categoriaArticulo", "Componentes");
                List<Articulos> lrComponentes4 = queryComponentes4.getResultList();
                request.setAttribute("articulosComponentes", lrComponentes4);
                vista = "/vistas/Inicio.jsp";
                break;

            case "/subirArticulo":
                //redirecciona a la vista de subir articulo
                vista = "/vistas/SubirArticulo.jsp";
                break;

            case "/creaArticulo":

                String msg;
                String name = request.getParameter("nombreArticulo");
                System.out.println("EL nombre del articulo es: " + name);
                String cuerpo = request.getParameter("cuerpoArticulo");
                System.out.println("EL cuerpo del articulo es: " + cuerpo);
                String categoria = request.getParameter("categoria");

                Articulos a;
                TypedQuery<Articulos> query;
                List<Articulos> lr;

                if (name != null && cuerpo != null && categoria != null) {
                    try {
                        a = new Articulos();
                        a.setNombreArticulo(name);
                        a.setCuerpoArticulo(cuerpo);
                        a.setFotoPortada(null);
                        a.setCategoriaArticulo(categoria);
                        Usuarios usu = (Usuarios) session.getAttribute("userObject");
                        a.setIdUsuario(usu);

                        persist(a);
                        System.out.println("Artículo: " + name + " creado");
                        msg = "<p class='ok'>Artículo: " + name + " creado adecuadamente</p>";

                        final Part filePart = request.getPart("introducirFoto");
                        if (filePart != null) {

                            String nombre = filePart.getName();
                            Long tamano = filePart.getSize();
                            String file = filePart.getSubmittedFileName();

                            String relativePathFolder = "imagenes";
                            String absolutePathFolder = getServletContext().getRealPath(relativePathFolder);

                            File folder = new File(absolutePathFolder);
                            if (folder.exists()) {
                                //System.err.println("Error : " + absolutePathFolder + " existe");
                            } else {
                                folder.mkdir();
                            }

                            System.out.println(absolutePathFolder + File.separator + name + ".jpg");
                            File f = new File(absolutePathFolder + File.separator + name + ".jpg");

                            //String nfp = f.getAbsolutePath();
                            OutputStream p = new FileOutputStream(f);
                            InputStream filecontent;
                            filecontent = filePart.getInputStream();
                            System.out.println("Tam: " + filePart.getSize());

                            int read = 0;
                            final byte[] bytes = new byte[1024];
                            while ((read = filecontent.read(bytes)) != -1) {
                                p.write(bytes, 0, read);
                            }

                            p.close();
                            filecontent.close();

                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                        System.out.println("Error: Imposible persistir  articulo: " + name);
                        msg = "<p class='error'>Error: Artículo " + name + " no creado</p>";
                    }
                } else {
                    System.out.println("Error: datos incorrectos");
                    msg = "<p class=\"error\">Error: Faltan datos</p>";
                }

                request.setAttribute("msg", msg);
                query = em.createNamedQuery("Articulos.findAll", Articulos.class);
                lr = query.getResultList();
                request.setAttribute("articulos", lr);

                //Todos los articulos
                TypedQuery<Articulos> queryH5 = em.createNamedQuery("Articulos.findAll", Articulos.class);
                List<Articulos> lrH5 = queryH5.getResultList();
                request.setAttribute("articulos", lrH5);

                //Coches
                TypedQuery<Articulos> queryCoches5 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                queryCoches5.setParameter("categoriaArticulo", "Coches");
                List<Articulos> lrCoches5 = queryCoches5.getResultList();
                request.setAttribute("articulosCoches", lrCoches5);

                //Coches
                TypedQuery<Articulos> queryComponentes5 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                queryComponentes5.setParameter("categoriaArticulo", "Componentes");
                List<Articulos> lrComponentes5 = queryComponentes5.getResultList();
                request.setAttribute("articulosComponentes", lrComponentes5);

                vista = "/vistas/Inicio.jsp";
                break;
            case "/articulos":
                query = em.createNamedQuery("Articulos.findAll", Articulos.class);
                lr = query.getResultList();
                request.setAttribute("articulos", lr);

                vista = "/vistas/Articulos.jsp";
                break;
            case "/verArticulo":
                String idArt = request.getParameter("idArt");
                Integer idArtint = Integer.parseInt(idArt);
                TypedQuery<Articulos> queryArt = em.createNamedQuery("Articulos.findByIdArticulo", Articulos.class);
                queryArt.setParameter("idArticulo", idArtint);

                List<Articulos> lart;
                lart = queryArt.getResultList();

                Articulos art = lart.get(0);
                String nombreArt = art.getNombreArticulo();
                String cuerpoArt = art.getCuerpoArticulo();
                String categoriaArt = art.getCategoriaArticulo();

                request.setAttribute("NombreArticulo", nombreArt);
                request.setAttribute("CuerpoArticulo", cuerpoArt);
                request.setAttribute("CategoriaArticulo", categoriaArt);
                request.setAttribute("IdArticulo", idArt);

                //Pasarle la info de todos los comentarios de ese articulo
                TypedQuery<Comentarios> queryTodosComentarios1 = em.createNamedQuery("Comentarios.findByIdArticulo", Comentarios.class);
                queryTodosComentarios1.setParameter("idArticulo", art);

                List<Comentarios> TodosComentarios1;
                TodosComentarios1 = queryTodosComentarios1.getResultList();
                request.setAttribute("ListaComentarios", TodosComentarios1);
                //

                vista = "/vistas/verArticulo.jsp";
                break;
            case "/filtro":

                String categoriafiltro = request.getParameter("categoriafiltro");
                if (!categoriafiltro.equals("")) {
                    TypedQuery<Articulos> fitro = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                    fitro.setParameter("categoriaArticulo", categoriafiltro);
                    List<Articulos> lrFiltro = fitro.getResultList();
                    request.setAttribute("articulos", lrFiltro);
                } else {
                    TypedQuery<Articulos> filtroTodos = em.createNamedQuery("Articulos.findAll", Articulos.class);
                    List<Articulos> lrTodos = filtroTodos.getResultList();
                    request.setAttribute("articulos", lrTodos);
                }

                vista = "fragmentosjsp/filtrados.jsp";
                break;

            case "/enviarComentario":
                String msg1;
                String comentario = request.getParameter("inputComentario");
                System.out.println("El comentario que se va a insertar es: " + comentario);
                Comentarios c;

                //Rescato el articulo donde se va a comentar
                String idArticulo = request.getParameter("idArt");
                System.out.println("EL id del articulo al insertar comentario es: " + idArticulo);
                Integer idArticuloInt = Integer.parseInt(idArticulo);
                TypedQuery<Articulos> queryArtCom = em.createNamedQuery("Articulos.findByIdArticulo", Articulos.class);
                queryArtCom.setParameter("idArticulo", idArticuloInt);

                List<Articulos> lartCom;
                lartCom = queryArtCom.getResultList();

                Articulos artCom = lartCom.get(0); //Este es el artículo
                //

                if (comentario != null) {
                    try {
                        c = new Comentarios();
                        c.setContenidoComentario(comentario);
                        c.setIdArticulo(artCom);
                        Usuarios usu = (Usuarios) session.getAttribute("userObject");
                        c.setIdUsuario(usu);

                        persist(c);
                        System.out.println("Comentario: " + comentario + " creado");
                        msg1 = "<p class='ok'>Comentario: " + comentario + " creado adecuadamente</p>";

                    } catch (Exception ex) {
                        System.out.println(ex);
                        System.out.println("Error: Imposible persistir  comentario: " + comentario);
                        msg1 = "<p class='error'>Error: Comentario " + comentario + " no creado</p>";
                    }
                } else {
                    System.out.println("Error: datos incorrectos en el comentario");
                    msg1 = "<p class=\"error\">Error: Faltan datos en el comentario</p>";
                }

                request.setAttribute("msg", msg1);

                //Pasarle la info del articulo que estaba activo
                String nombreArt1 = artCom.getNombreArticulo();
                String cuerpoArt1 = artCom.getCuerpoArticulo();
                String categoriaArt1 = artCom.getCategoriaArticulo();

                request.setAttribute("NombreArticulo", nombreArt1);
                request.setAttribute("CuerpoArticulo", cuerpoArt1);
                request.setAttribute("CategoriaArticulo", categoriaArt1);

                //Pasarle la info de todos los comentarios de ese articulo
                TypedQuery<Comentarios> queryTodosComentarios = em.createNamedQuery("Comentarios.findByIdArticulo", Comentarios.class);
                queryTodosComentarios.setParameter("idArticulo", artCom);

                List<Comentarios> TodosComentarios;
                TodosComentarios = queryTodosComentarios.getResultList();
                request.setAttribute("ListaComentarios", TodosComentarios);
                //

                vista = "/vistas/verArticulo.jsp";
                break;

            case "/addFavorito":
                String idf = request.getParameter("idArtFav");
                TypedQuery<Articulos> queryArtFav;
                List<Articulos> LArtFav;
                List<Articulos> lid;
                Articulos aFav = null;
                if (idf != null) {
                    queryArtFav = em.createNamedQuery("Articulos.findByIdArticulo", Articulos.class);
                    queryArtFav.setParameter("idArticulo", Integer.parseInt(idf));
                    LArtFav = queryArtFav.getResultList();

                    if (LArtFav != null) { // Esta el artículo
                        lid = (List<Articulos>) session.getAttribute("LArtFav");
                        if (lid == null) {
                            lid = new ArrayList<>();
                        }
                        aFav = LArtFav.get(0);
                        lid.add(aFav);
                        msg = "<p class=\"ok\">Artículo registrado en favoritos.<p>";
                        session.setAttribute("LArtFav", lid);
                    } else { //No está el art
                        msg = "<p class=\"ok\">ERROR: Artículo registrado en favoritos. No existía.<p>";
                    }
                } else { // Id no enviado
                    msg = "<p class=\"error\">Artículo No registrado en favoritos. Falta Id.<p>";
                }

                request.setAttribute("msg", msg);

                Integer idArtint1 = Integer.parseInt(idf);
                TypedQuery<Articulos> queryArt1 = em.createNamedQuery("Articulos.findByIdArticulo", Articulos.class);
                queryArt1.setParameter("idArticulo", idArtint1);

                List<Articulos> lart1;
                lart1 = queryArt1.getResultList();

                Articulos art2 = lart1.get(0);
                String nombreArt2 = art2.getNombreArticulo();
                String cuerpoArt2 = art2.getCuerpoArticulo();
                String categoriaArt2 = art2.getCategoriaArticulo();

                request.setAttribute("NombreArticulo", nombreArt2);
                request.setAttribute("CuerpoArticulo", cuerpoArt2);
                request.setAttribute("CategoriaArticulo", categoriaArt2);
                request.setAttribute("IdArticulo", idArtint1);

                //Pasarle la info de todos los comentarios de ese articulo
                TypedQuery<Comentarios> queryTodosComentarios2 = em.createNamedQuery("Comentarios.findByIdArticulo", Comentarios.class);
                queryTodosComentarios2.setParameter("idArticulo", art2);

                List<Comentarios> TodosComentarios2;
                TodosComentarios2 = queryTodosComentarios2.getResultList();
                request.setAttribute("ListaComentarios", TodosComentarios2);
                //

                vista = "/vistas/verArticulo.jsp";
                break;

            case "/favoritos":

                vista = "/vistas/ArticulosFav.jsp";
                break;

            case "/delFavorito":
                String idf1 = request.getParameter("idArt");
                int i = Integer.parseInt(idf1);
                System.out.println("La i es: " + i);
                List<Articulos> lid1 = (List<Articulos>) session.getAttribute("LArtFav");
                for (int j = 0; j < lid1.size(); j++) {
                    System.out.println("ARTICULO : " + lid1.get(j) + "con j = " + j);
                }
                lid1.remove(i);
                session.setAttribute("LArtFav", lid1);
                vista = "/vistas/ArticulosFav.jsp";
                break;
            case "/invalidar":
               
                List<Articulos> lid2 = (List<Articulos>) session.getAttribute("LArtFav");
                lid2.clear();
                session.setAttribute("LArtFav", lid2);
                vista = "/vistas/ArticulosFav.jsp";
                break;
            default:
                //Todos los articulos
                TypedQuery<Articulos> queryH6 = em.createNamedQuery("Articulos.findAll", Articulos.class);
                List<Articulos> lrH6 = queryH6.getResultList();
                request.setAttribute("articulos", lrH6);

                //Coches
                TypedQuery<Articulos> queryCoches6 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                queryCoches6.setParameter("categoriaArticulo", "Coches");
                List<Articulos> lrCoches6 = queryCoches6.getResultList();
                request.setAttribute("articulosCoches", lrCoches6);

                //Coches
                TypedQuery<Articulos> queryComponentes6 = em.createNamedQuery("Articulos.findByCategoriaArticulo", Articulos.class);
                queryComponentes6.setParameter("categoriaArticulo", "Componentes");
                List<Articulos> lrComponentes6 = queryComponentes6.getResultList();
                request.setAttribute("articulosComponentes", lrComponentes6);
                vista = "/vistas/Inicio.jsp";

        }
        System.out.println("Entra en el servlet");
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
