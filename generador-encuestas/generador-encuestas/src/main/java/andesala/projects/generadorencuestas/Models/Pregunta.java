package andesala.projects.generadorencuestas.Models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name = "Preguntas")
public class Pregunta {
    @Id
    @Column(name = "idPregunta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Integer tipo;

    @ManyToMany
    @JoinTable(
        name = "OpcionesPreguntas",
        joinColumns = @JoinColumn(name = "idPregunta"),
        inverseJoinColumns = @JoinColumn(name = "idOpcion")
    )
    private List<Option> opciones;

    /* Constructor Personalizado */
    public Pregunta() {
        this.opciones = new LinkedList<>();
        this.opciones.add(new Option());
        this.opciones.add(new Option());
        this.opciones.add(new Option());
        this.opciones.add(new Option());
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return the tipo
     */
    public Integer getTipo() {
        return tipo;
    }
    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    /**
     * @return the opciones
     */
    public List<Option> getOpciones() {
        return opciones;
    }
    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(List<Option> opciones) {
        this.opciones = opciones;
    }

    /**
     * @param option
     */
    public void addOption(Option option) {
        this.opciones.add(option);
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "Pregunta [description=" + description + ", id=" + id + ", opciones=" + opciones + ", tipo=" + tipo
                + "]";
    }
}
