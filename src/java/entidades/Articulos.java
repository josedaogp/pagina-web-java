/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author josed
 */
@Entity
@Table(name = "articulos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulos.findAll", query = "SELECT a FROM Articulos a"),
    @NamedQuery(name = "Articulos.findByIdArticulo", query = "SELECT a FROM Articulos a WHERE a.idArticulo = :idArticulo"),
    @NamedQuery(name = "Articulos.findByCuerpoArticulo", query = "SELECT a FROM Articulos a WHERE a.cuerpoArticulo = :cuerpoArticulo"),
    @NamedQuery(name = "Articulos.findByFotoPortada", query = "SELECT a FROM Articulos a WHERE a.fotoPortada = :fotoPortada"),
    @NamedQuery(name = "Articulos.findByNombreArticulo", query = "SELECT a FROM Articulos a WHERE a.nombreArticulo = :nombreArticulo"),
    @NamedQuery(name = "Articulos.findByCategoriaArticulo", query = "SELECT a FROM Articulos a WHERE a.categoriaArticulo = :categoriaArticulo")})
public class Articulos implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cuerpoArticulo")
    private String cuerpoArticulo;
    @Size(max = 255)
    @Column(name = "fotoPortada")
    private String fotoPortada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombreArticulo")
    private String nombreArticulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "categoria_articulo")
    private String categoriaArticulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_comentario")
    private int idComentario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
    private List<Comentarios> comentariosList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArticulo")
    private Integer idArticulo;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public Articulos() {
    }

    public Articulos(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Articulos(Integer idArticulo, String cuerpoArticulo, String nombreArticulo, String categoriaArticulo) {
        this.idArticulo = idArticulo;
        this.cuerpoArticulo = cuerpoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.categoriaArticulo = categoriaArticulo;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }


    public String getCategoriaArticulo() {
        return categoriaArticulo;
    }

    public void setCategoriaArticulo(String categoriaArticulo) {
        this.categoriaArticulo = categoriaArticulo;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticulo != null ? idArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulos)) {
            return false;
        }
        Articulos other = (Articulos) object;
        if ((this.idArticulo == null && other.idArticulo != null) || (this.idArticulo != null && !this.idArticulo.equals(other.idArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Articulos[ idArticulo=" + idArticulo + " ]";
    }


    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    @XmlTransient
    public List<Comentarios> getComentariosList() {
        return comentariosList;
    }

    public void setComentariosList(List<Comentarios> comentariosList) {
        this.comentariosList = comentariosList;
    }

    public String getCuerpoArticulo() {
        return cuerpoArticulo;
    }

    public void setCuerpoArticulo(String cuerpoArticulo) {
        this.cuerpoArticulo = cuerpoArticulo;
    }

    public String getFotoPortada() {
        return fotoPortada;
    }

    public void setFotoPortada(String fotoPortada) {
        this.fotoPortada = fotoPortada;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    
}
