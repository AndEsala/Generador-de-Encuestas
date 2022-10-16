package andesala.projects.generadorencuestas.Services.ResPregunta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andesala.projects.generadorencuestas.Models.ResPregunta;
import andesala.projects.generadorencuestas.Repositorys.ResPreguntaRepository;

@Service
public class ResPreServiceJPA implements ResPreService {
    @Autowired
    private ResPreguntaRepository rpManager;

    @Override
    public void saveResPreguntas(List<ResPregunta> resPreguntas) {
        rpManager.saveAll(resPreguntas);
    }
}
