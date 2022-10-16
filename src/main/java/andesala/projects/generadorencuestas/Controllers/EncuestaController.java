package andesala.projects.generadorencuestas.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import andesala.projects.generadorencuestas.Models.Encuesta;
import andesala.projects.generadorencuestas.Models.Option;
import andesala.projects.generadorencuestas.Models.Pregunta;
import andesala.projects.generadorencuestas.Models.ResUsuario;
import andesala.projects.generadorencuestas.Services.Encuesta.EncuestaService;
import andesala.projects.generadorencuestas.Services.Option.OptionService;
import andesala.projects.generadorencuestas.Services.Pregunta.PreguntaService;
import andesala.projects.generadorencuestas.Services.ResPregunta.ResPreService;
import andesala.projects.generadorencuestas.Services.ResUsuario.ResUserService;

@Controller
@RequestMapping("/encuesta")
public class EncuestaController {
    @Autowired
    private EncuestaService encuestaManager;

    @Autowired
    private PreguntaService preguntaManager;
    
    @Autowired
    private OptionService optionManager;
    
    @Autowired
    private ResPreService rpManager;

    @Autowired
    private ResUserService ruManager;

    @GetMapping(value = "/index")
    public String list(Model model) {
        List<Encuesta> listaEncuestas = encuestaManager.getListEncuestas();
        model.addAttribute("encuestas", listaEncuestas);

        return "encuestas/listEncuestas";
    }

    /* Crear nueva Encuesta */
    @GetMapping(value = "/form")
    public String form(Encuesta encuesta) {
        return "encuestas/formEncuestas";
    }

    /* Guardar Nueva Encuesta */
    @PostMapping(value = "/save")
    public String saveEncuesta(Encuesta encuesta) {
        encuestaManager.saveEncuesta(encuesta);
        return "redirect:/encuesta/index";
    }

    /* Editar Encuesta */
    @GetMapping(value = "/edit/{id}")
    public String editPreguntas(@PathVariable("id") Integer idEncuesta, Model model) {
        Encuesta encuestaF = encuestaManager.getEncuesta(idEncuesta);
        model.addAttribute("encuestaEdit", encuestaF);
        return "encuestas/editEncuestas";
    }

    /* Añadir Pregunta a una Encuesta */
    @GetMapping(value = "/edit/{ide}/add-pregunta")
    public String formAddPregunta(@PathVariable("ide") Integer idEncuesta, Pregunta pregunta, Model model) {
        model.addAttribute("idEncuesta", idEncuesta);
        System.out.println(pregunta);
        return "preguntas/formPregunta";
    }

    /* Guardar la Información de la Nueva Pregunta */
    @PostMapping(value = "/edit/{ide}/add-pregunta")
    public String addPregunta(
        @PathVariable("ide") Integer idEncuesta,
        @RequestParam("correcta") Integer opCorrecta, 
        Pregunta pregunta
    ) {
        pregunta.getOpciones().get(opCorrecta - 1).setIsResponse(true);

        for (Option op : pregunta.getOpciones()) {
            optionManager.saveOption(op);
        }
        Encuesta encuestaF = encuestaManager.getEncuesta(idEncuesta);
        if (pregunta.getId() == null) {
            encuestaF.addPregunta(pregunta);
        }

        preguntaManager.savePregunta(pregunta);
        encuestaManager.saveEncuesta(encuestaF);

        return "redirect:/encuesta/edit/" + idEncuesta;
    }
    
    /* Editar una Pregunta Existente */
    @GetMapping(value = "/edit/{ide}/pregunta/{idp}")
    public String editPregunta(@PathVariable("ide") Integer idEncuesta, @PathVariable("idp") Integer idPregunta, Model model) {
        Pregunta preguntaF = preguntaManager.getPregunta(idPregunta);
        model.addAttribute("idEncuesta", idEncuesta);
        model.addAttribute("pregunta", preguntaF);

        return "preguntas/formPregunta";
    }

    /* Eliminar una Encuesta */
    @GetMapping(value = "/delete/{id}")
    public String deleteEncuesta(@PathVariable("id") Integer idEncuesta) {
        this.encuestaManager.deleteEncuesta(idEncuesta);
        return "redirect:/encuesta/index";
    }

    /* Eliminar una Pregunta */
    @GetMapping(value = "/delete/{ide}/pregunta/{idp}")
    public String deletePregunta(
        @PathVariable("ide") Integer idEncuesta,
        @PathVariable("idp") Integer idPregunta
    ) {
        Pregunta pregunta = preguntaManager.getPregunta(idPregunta);
        Encuesta encuesta = encuestaManager.getEncuesta(idEncuesta);
        encuesta.removePregunta(pregunta);

        optionManager.deleteOptions(pregunta.getOpciones());
        preguntaManager.deletePregunta(idPregunta);
        encuestaManager.saveEncuesta(encuesta);

        return "redirect:/encuesta/index";
    }


    /* Resolver una Encuesta */
    @GetMapping(value = "/{encuesta}")
    public String resolverEncuesta(ResUsuario usuario, Model model) {
        model.addAttribute("usuario", usuario);
        return "encuestas/responderEncuesta";
    }

    /* Resolver una Encuesta */
    @PostMapping(value = "/{encuesta}")
    public String guardarResEncuesta(ResUsuario usuario, Model model) {
        for (int i = 0; i < usuario.getEncuesta().getPreguntas().size(); i++) {
            Pregunta pre = usuario.getEncuesta().getPreguntas().get(i);
            usuario.getRespuestas().get(i).setPregunta(pre);
        }
        
        rpManager.saveResPreguntas(usuario.getRespuestas());
        ruManager.saveResUser(usuario);

        return "redirect:/encuesta/index";
    }


    /* Ver Lista de Respuestas */
    @GetMapping(value = "/respuestas")
    public String listaRespuestas(Model model) {
        List<ResUsuario> resUsers = ruManager.getRespuestasUser();
        model.addAttribute("respuestas", resUsers);

        return "encuestas/listRespuestas";
    }

    /* Ver Respuestas de una Encuesta en Específico */
    @GetMapping(value = "/respuestas/{idr}")
    public String viewRespuestas(@PathVariable("idr") Integer idRes, Model model) {
        ResUsuario resU = ruManager.getResUserById(idRes);
        model.addAttribute("respuesta", resU);

        return "encuestas/viewRespuesta";
    }
}
