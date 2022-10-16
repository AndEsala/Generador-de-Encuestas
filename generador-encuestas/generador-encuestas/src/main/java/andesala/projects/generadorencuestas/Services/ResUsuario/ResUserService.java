package andesala.projects.generadorencuestas.Services.ResUsuario;

import java.util.List;

import andesala.projects.generadorencuestas.Models.ResUsuario;

public interface ResUserService {
    List<ResUsuario> getRespuestasUser();
    ResUsuario getResUserById(Integer idR);
    void saveResUser(ResUsuario respuestaUser);
}
