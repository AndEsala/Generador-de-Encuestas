package andesala.projects.generadorencuestas.Services.Encuesta;

import java.util.List;

import andesala.projects.generadorencuestas.Models.Encuesta;

public interface EncuestaService {
    void saveEncuesta(Encuesta encuesta);
    List<Encuesta> getListEncuestas();
    Encuesta getEncuesta(Integer id);
    void deleteEncuesta(Integer id);
}
