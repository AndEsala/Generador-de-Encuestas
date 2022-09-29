package andesala.projects.generadorencuestas.Services.Pregunta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andesala.projects.generadorencuestas.Models.Pregunta;
import andesala.projects.generadorencuestas.Repositorys.PreguntaRepository;

@Service
public class PreguntaServiceJPA implements PreguntaService {
    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    public void savePregunta(Pregunta pregunta) {
        preguntaRepository.save(pregunta);
    }

    @Override
    public Pregunta getPregunta(Integer idPregunta) {
        Optional<Pregunta> preguntaF = preguntaRepository.findById(idPregunta);

        if (preguntaF.isPresent()) {
            return preguntaF.get();
        } else {
            return null;
        }
    }
    
}
