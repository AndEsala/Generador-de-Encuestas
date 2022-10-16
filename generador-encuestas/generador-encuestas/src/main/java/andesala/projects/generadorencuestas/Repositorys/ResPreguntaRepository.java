package andesala.projects.generadorencuestas.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import andesala.projects.generadorencuestas.Models.ResPregunta;

public interface ResPreguntaRepository extends JpaRepository<ResPregunta, Integer> {
    
}
