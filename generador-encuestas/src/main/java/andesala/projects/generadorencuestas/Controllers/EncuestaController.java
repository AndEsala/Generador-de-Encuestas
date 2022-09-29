package andesala.projects.generadorencuestas.Controllers;

import java.util.LinkedList;
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
import andesala.projects.generadorencuestas.Models.Respuesta;
import andesala.projects.generadorencuestas.Services.Encuesta.EncuestaService;
import andesala.projects.generadorencuestas.Services.Option.OptionService;
import andesala.projects.generadorencuestas.Services.Pregunta.PreguntaService;

@Controller
@RequestMapping("/encuesta")
public class EncuestaController {
    @Autowired
    private EncuestaService encuestaManager;

    @Autowired
    private PreguntaService preguntaManager;

    @Autowired
    private OptionService optionManager;

    @GetMapping(value = "/index")
    public String list(Model model) {
        List<Encuesta> listaEncuestas = encuestaManager.getListEncuestas();
        model.addAttribute("encuestas", listaEncuestas);

        return "encuestas/listEncuestas";
    }

    @GetMapping(value = "/form")
    public String form(Encuesta encuesta) {
        return "encuestas/formEncuestas";
    }

    @PostMapping(value = "/save")
    public String saveEncuesta(Encuesta encuesta) {
        encuestaManager.saveEncuesta(encuesta);
        return "redirect:/encuesta/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String editPreguntas(@PathVariable("id") Integer idEncuesta, Model model) {
        Encuesta encuestaF = encuestaManager.getEncuesta(idEncuesta);
        model.addAttribute("encuestaEdit", encuestaF);
        return "encuestas/editEncuestas";
    }

    @GetMapping(value = "/edit/{ide}/add-pregunta")
    public String formAddPregunta(@PathVariable("ide") Integer idEncuesta, Pregunta pregunta, Model model) {
        model.addAttribute("idEncuesta", idEncuesta);
        return "preguntas/formPregunta";
    }

    /**
     * @param idEncuesta
     * @param op1
     * @param op2
     * @param op3
     * @param op4
     * @param pregunta
     * @return
     */
    @PostMapping(value = "/edit/{ide}/add-pregunta")
    public String addPregunta(
        @PathVariable("ide") Integer idEncuesta,
        @RequestParam("op1") String op1,
        @RequestParam("op2") String op2,
        @RequestParam("op3") String op3,
        @RequestParam("op4") String op4,
        @RequestParam("correcta") Integer opCorrecta,
        Pregunta pregunta) {
        if (pregunta.getTipo() == 1) {
            Option opt1 = new Option();
            opt1.setRespuesta(op1);

            Option opt2 = new Option();
            opt2.setRespuesta(op2);

            Option opt3 = new Option();
            opt3.setRespuesta(op3);

            Option opt4 = new Option();
            opt4.setRespuesta(op4);

            switch (opCorrecta) {
                case 1 -> opt1.setIsResponse(true);
                case 2 -> opt2.setIsResponse(true);
                case 3 -> opt3.setIsResponse(true);
                case 4 -> opt4.setIsResponse(true);
            }

            optionManager.saveOption(opt1);
            optionManager.saveOption(opt2);
            optionManager.saveOption(opt3);
            optionManager.saveOption(opt4);

            pregunta.setOpciones(new LinkedList<Option>());

            pregunta.addOption(opt1);
            pregunta.addOption(opt2);
            pregunta.addOption(opt3);
            pregunta.addOption(opt4);
        }

        Encuesta encuestaF = encuestaManager.getEncuesta(idEncuesta);
        if (pregunta.getId() == null) {
            encuestaF.addPregunta(pregunta);
        }

        preguntaManager.savePregunta(pregunta);
        

        encuestaManager.saveEncuesta(encuestaF);
        return "redirect:/encuesta/edit/" + idEncuesta;
    }
    
    @GetMapping(value = "/edit/{ide}/pregunta/{idp}")
    public String editPregunta(@PathVariable("ide") Integer idEncuesta, @PathVariable("idp") Integer idPregunta, Model model) {
        Pregunta preguntaF = preguntaManager.getPregunta(idPregunta);
        System.out.println(preguntaF);
        model.addAttribute("idEncuesta", idEncuesta);
        model.addAttribute("pregunta", preguntaF);

        return "preguntas/formPregunta";
    }

    @GetMapping(value = "/{id}")
    public String doEncuesta(@PathVariable("id") Integer idEncuesta, Respuesta respuesta, Model model) {
        Encuesta encuesta = encuestaManager.getEncuesta(idEncuesta);
        respuesta.setEncuesta(encuesta);

        return "encuestas/responderEncuesta";
    }

    /* @PostMapping(value = "/{}") */
}
