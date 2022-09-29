package andesala.projects.generadorencuestas.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import andesala.projects.generadorencuestas.Models.Pregunta;

public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {
    
}
