package andesala.projects.generadorencuestas.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Opciones")
public class Option {
    @Id
    @Column(name = "idOpcion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String respuesta;

    @Column(name = "isResponse")
    private Boolean isResponse;
    
    
    public Option() {
        this.isResponse = false;
    }
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
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }
    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    /**
     * @return the isResponse
     */
    public Boolean getIsResponse() {
        return isResponse;
    }
    /**
     * @param isResponse the isResponse to set
     */
    public void setIsResponse(Boolean isResponse) {
        this.isResponse = isResponse;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "Option [id=" + id + ", isResponse=" + isResponse + ", respuesta=" + respuesta + "]";
    }
}
