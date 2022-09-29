package andesala.projects.generadorencuestas.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Encuestas")
public class Encuesta {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
        name = "PreguntasEncuestas",
        joinColumns = @JoinColumn(name = "idEncuesta"),
        inverseJoinColumns = @JoinColumn(name = "idPregunta")
    )
    private List<Pregunta> preguntas;

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
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the preguntas
     */
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
    /**
     * @param preguntas the preguntas to set
     */
    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
    
    public void addPregunta(Pregunta pregunta) {
        this.preguntas.add(pregunta);
    }
}
