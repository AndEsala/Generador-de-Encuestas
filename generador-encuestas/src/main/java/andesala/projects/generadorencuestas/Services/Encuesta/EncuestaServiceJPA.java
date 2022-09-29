package andesala.projects.generadorencuestas.Services.Encuesta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andesala.projects.generadorencuestas.Models.Encuesta;
import andesala.projects.generadorencuestas.Repositorys.EncuestaRepository;

@Service
public class EncuestaServiceJPA implements EncuestaService {
    @Autowired
    private EncuestaRepository encuestaRepository;

    @Override
    public void saveEncuesta(Encuesta encuesta) {
        encuestaRepository.save(encuesta);        
    }

    @Override
    public List<Encuesta> getListEncuestas() {
        List<Encuesta> encuestas = encuestaRepository.findAll();
        return encuestas;
    }

    @Override
    public Encuesta getEncuesta(Integer id) {
        Optional<Encuesta> encuestaFound = encuestaRepository.findById(id);

        if (encuestaFound.isPresent()) {
            return encuestaFound.get();
        } else {
            return null;
        }
    }
}
