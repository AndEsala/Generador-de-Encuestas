package andesala.projects.generadorencuestas.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import andesala.projects.generadorencuestas.Models.Option;

public interface OptionRepository extends JpaRepository<Option, Integer> {
    
}
