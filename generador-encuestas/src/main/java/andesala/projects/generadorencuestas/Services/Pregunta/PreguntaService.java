package andesala.projects.generadorencuestas.Services.Pregunta;

import andesala.projects.generadorencuestas.Models.Pregunta;

public interface PreguntaService {
    void savePregunta(Pregunta pregunta);
    Pregunta getPregunta(Integer idPregunta);
}
