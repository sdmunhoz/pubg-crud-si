package es.uvigo.mei.pubgspring.controladores;

import es.uvigo.mei.pubgspring.entidades.Arma;
import es.uvigo.mei.pubgspring.entidades.Kills;
import es.uvigo.mei.pubgspring.servicios.ArmaService;
import es.uvigo.mei.pubgspring.servicios.KillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/armas")
public class ArmaController {
    @Autowired
    ArmaService armaService;

    @Autowired
    KillService killService;
    /**
     * Model encapsula el modelo (en este caso sera un Model vacio para ser
     * inicializado)
     */

    @GetMapping
    public String prepararListarArmas(Model modelo) {
        List<Arma> armas = armaService.buscarTodos();
        modelo.addAttribute("armas", armas);
        modelo.addAttribute("nombreArma", "");
        modelo.addAttribute("tipoArma", "");
        modelo.addAttribute("tipoMunicionArma", "");
        modelo.addAttribute("danhoArma", "");
        modelo.addAttribute("capacidadBalasArma", "");
        modelo.addAttribute("rangoArma", "");
        modelo.addAttribute("tasaDisparoArma", "");
        modelo.addAttribute("nDisparosMatarArma", "");
        modelo.addAttribute("nDisparosMatarCabeza", "");
        return "armas/listadoArmas";
    }

    /**
     * @RequestParam captura los parámetros de la peticion (en este caso cuerpo del
     *               POST) cuyo nombre coincida con el nombre de los parametros
     */
    @PostMapping
    public String actualizarListarArmas(@RequestParam(required = false) String nombreArma,
                                            @RequestParam(required = false) String tipoArma,
                                            @RequestParam(required = false) String tipoMunicionArma,
                                            Model modelo) {
        List<Arma> armas;
        if ((nombreArma != null) && !nombreArma.isEmpty()) {
            armas = armaService.buscarPorNombre(nombreArma);
        } else if ((tipoArma != null) && !tipoArma.isEmpty()) {
            armas = armaService.buscarPorTipo(tipoArma);
        } else if ((tipoMunicionArma != null) && !tipoMunicionArma.isEmpty()) {
            armas = armaService.buscarPorTipoMunicion(tipoMunicionArma);
        } else {
            armas = armaService.buscarTodos();
        }
        modelo.addAttribute("armas", armas);
        return "armas/listadoArmas";
    }

    /**
     * @PathVariable vincula el parametro a un segmento de la URI
     */
    @GetMapping("{id}/eliminar")
    public String borrarArma(@PathVariable("id") Long id, Model modelo) {
        Arma arma = armaService.buscarPorId(id);
        if (arma != null) {
            List<Kills> kills = (List<Kills>) killService.buscarPorArma(id);
            if(kills!=null) {
                for (Kills k : kills) {
                    killService.eliminar(k);
                }
            }
            armaService.eliminar(arma);
            return "redirect:/armas";
        } else {
            modelo.addAttribute("mensajeError", "Arma no encontrada");
            return "error";
        }
    }

    /**
     * ModelAndView encapsula (equivalente a modificar el Model recibido como
     * parametro y retornar un String con la siguiente vista)
     */
    @GetMapping("nuevo")
    public ModelAndView prepararNuevoArma() {
        Arma arma = new Arma();

        ModelAndView result = new ModelAndView();
        result.addObject("arma", arma);
        result.addObject("esNuevo", true);
        result.setViewName("armas/editarArma");
        return result;
    }

    /**
     * @Valid indica que se apliquen las validaciones BeanValidation declaradas en
     *        el correspondiente tipo
     * @ModelAttribute vincula con un atributo del Model con el mismo nombre de la
     *                 variable (es opcional, el comportamiento por defecto busca en
     *                 el Model atributos con los nombres de las variables)
     *                 BindingRequest encapsula el resultado del binding de
     *                 parametros de la peticion o Model con atributos de los
     *                 objetos reales
     */
    @PostMapping("nuevo")
    public String crearArma(@Valid @ModelAttribute Arma arma, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            armaService.crear(arma);
            return "redirect:/armas";
        } else {
            return null;
        }
    }

    @GetMapping("{id}")
    public String prepararEditarArma(@PathVariable("id") Long id, Model modelo) {
        try {
            Arma arma = armaService.buscarPorId(id);
            modelo.addAttribute("arma", arma);
            modelo.addAttribute("esNuevo", false);
            return "armas/editarArma";
        } catch (EntityNotFoundException e) {
            modelo.addAttribute("error", "Arma no encontrada");
            return "error";
        }
    }

    @PostMapping("{id}")
    public String actualizarArma(@Valid @ModelAttribute Arma arma, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            armaService.modificar(arma);
            return "redirect:/armas";
        } else {
            return null;
        }
    }


}
