package andesala.projects.generadorencuestas.Services.ResUsuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andesala.projects.generadorencuestas.Models.ResUsuario;
import andesala.projects.generadorencuestas.Repositorys.ResUsuarioRepository;

@Service
public class ResUserServiceJPA implements ResUserService {
    @Autowired
    private ResUsuarioRepository ruManager;

    @Override
    public void saveResUser(ResUsuario respuestaUser) {
        ruManager.save(respuestaUser);
    }

    @Override
    public List<ResUsuario> getRespuestasUser() {
        return ruManager.findAll();
    }

    @Override
    public ResUsuario getResUserById(Integer idR) {
        Optional<ResUsuario> resF = ruManager.findById(idR);
        
        if (resF.isPresent()) {
            return resF.get();
        } else {
            return null;
        }
    }
}
