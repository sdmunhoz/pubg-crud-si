package templates.ubicaciones;

import es.uvigo.mei.pubgspring.entidades.Parametro;
import es.uvigo.mei.pubgspring.servicios.ParametroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/parametros")
public class ParametroController {
    @Autowired
    ParametroService parametroService;


    /**
     * Model encapsula el modelo (en este caso sera un Model vacio para ser
     * inicializado)
     */

    @GetMapping
    public String prepararListarParametros(Model modelo) {
        List<Parametro> parametros = parametroService.buscarTodos();
        modelo.addAttribute("parametros", parametros);
        modelo.addAttribute("zonaparametro", "");
        modelo.addAttribute("tiempoparametro", "");
        return "parametros/listadoParametros";
    }

    /**
     * @RequestParam captura los parámetros de la peticion (en este caso cuerpo del
     * POST) cuyo nombre coincida con el nombre de los parametros
     */
  /*  @PostMapping
    public String actualizarListarParametros(@RequestParam(required = false) String zonaparametro,
                                              @RequestParam(required = false) String tiempoparametro,
                                              Model modelo) {
        List<Parametro> parametros;
        if ((zonaparametro != null) && !zonaparametro.isEmpty()) {
            parametros = parametroService.buscarPorZona(zonaparametro);
        } else if ((tiempoparametro != null) && !tiempoparametro.isEmpty()) {
            parametros = parametroService.buscarPorTiempo(tiempoparametro);
        } else {
            parametros = parametroService.buscarTodos();
        }
        modelo.addAttribute("parametros", parametros);
        return "parametros/listadoparametros";
    }*/

    /**
     * @PathVariable vincula el parametro a un segmento de la URI
     */
    @GetMapping("{id}/eliminar")
    public String borrarParametro(@PathVariable("id") Long id, Model modelo) {
        Parametro parametro = parametroService.buscarPorId(id);
        if (parametro != null) {
            parametroService.eliminar(parametro);
            return "redirect:/parametros";
        } else {
            modelo.addAttribute("mensajeError", "parametro no encontrada");
            return "error";
        }
    }

    /**
     * ModelAndView encapsula (equivalente a modificar el Model recibido como
     * parametro y retornar un String con la siguiente vista)
     */
    @GetMapping("nuevo")
    public ModelAndView prepararNuevaparametro() {
        Parametro parametro = new Parametro();

        ModelAndView result = new ModelAndView();
        result.addObject("parametro", parametro);
        result.addObject("esNuevo", true);
        result.setViewName("parametros/editarparametro");
        return result;
    }

    /**
     * @Valid indica que se apliquen las validaciones BeanValidation declaradas en
     * el correspondiente tipo
     * @ModelAttribute vincula con un atributo del Model con el mismo nombre de la
     * variable (es opcional, el comportamiento por defecto busca en
     * el Model atributos con los nombres de las variables)
     * BindingRequest encapsula el resultado del binding de
     * parametros de la peticion o Model con atributos de los
     * objetos reales
     */
    @PostMapping("nuevo")
    public String crearparametro(@Valid @ModelAttribute Parametro parametro, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            parametroService.crear(parametro);
            return "redirect:/parametros";
        } else {
            return null;
        }
    }

}
