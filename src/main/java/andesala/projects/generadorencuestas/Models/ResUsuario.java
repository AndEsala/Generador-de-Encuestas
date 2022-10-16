package andesala.projects.generadorencuestas.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class ResUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;

    @OneToOne
    @JoinColumn(name = "idEncuesta")
    private Encuesta encuesta;

    @ManyToMany
    @JoinTable(
        name = "RespuestasUsuario",
        joinColumns = @JoinColumn(name = "idUsuario"),
        inverseJoinColumns = @JoinColumn(name = "idRespuesta")
    )
    List<ResPregunta> respuestas;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the encuesta
     */
    public Encuesta getEncuesta() {
        return encuesta;
    }
    
    /**
     * @param encuesta the encuesta to set
     */
    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }
    
    /**
     * @return the respuestas
     */
    public List<ResPregunta> getRespuestas() {
        return respuestas;
    }
    
    /**
     * @param respuestas the respuestas to set
     */
    public void setRespuestas(List<ResPregunta> respuestas) {
        this.respuestas = respuestas;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "ResUsuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", encuesta=" + encuesta
                + ", respuestas=" + respuestas + "]";
    }
    
}
