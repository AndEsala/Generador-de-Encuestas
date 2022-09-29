package andesala.projects.generadorencuestas.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import andesala.projects.generadorencuestas.Models.Encuesta;

public interface EncuestaRepository extends JpaRepository<Encuesta, Integer> {
    
}