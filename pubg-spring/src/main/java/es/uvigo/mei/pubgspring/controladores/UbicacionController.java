package es.uvigo.mei.pubgspring.controladores;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import es.uvigo.mei.pubgspring.entidades.Parametro;
import es.uvigo.mei.pubgspring.entidades.Ubicacion;
import es.uvigo.mei.pubgspring.servicios.ParametroService;
import es.uvigo.mei.pubgspring.servicios.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ubicaciones")
public class UbicacionController {
    @Autowired
    UbicacionService ubicacionService;
    @Autowired
    ParametroService parametrosService;


    /**
     * Model encapsula el modelo (en este caso sera un Model vacio para ser
     * inicializado)
     */

    @GetMapping
    public String prepararListarUbicaciones(Model modelo) {
        List<Ubicacion> ubicaciones = ubicacionService.buscarTodos();
        modelo.addAttribute("ubicaciones", ubicaciones);
        modelo.addAttribute("zonaUbicacion", "");
        modelo.addAttribute("tiempoUbicacion", "");
        return "ubicaciones/listadoUbicaciones";
    }

    /**
     * @RequestParam captura los parámetros de la peticion (en este caso cuerpo del
     * POST) cuyo nombre coincida con el nombre de los parametros
     */
    @PostMapping
    public String actualizarListarUbicaciones(@RequestParam(required = false) String zonaUbicacion,
                                              @RequestParam(required = false) String tiempoUbicacion,
                                              Model modelo) {
        List<Ubicacion> ubicaciones;
        if ((zonaUbicacion != null) && !zonaUbicacion.isEmpty()) {
            ubicaciones = ubicacionService.buscarPorZona(zonaUbicacion);
        } else if ((tiempoUbicacion != null) && !tiempoUbicacion.isEmpty()) {
            ubicaciones = ubicacionService.buscarPorTiempo(tiempoUbicacion);
        } else {
            ubicaciones = ubicacionService.buscarTodos();
        }
        modelo.addAttribute("ubicaciones", ubicaciones);
        return "ubicaciones/listadoubicaciones";
    }

    /**
     * @PathVariable vincula el parametro a un segmento de la URI
     */
    @GetMapping("{id}/eliminar")
    public String borrarUbicacion(@PathVariable("id") Long id, Model modelo) {
        Ubicacion ubicacion = ubicacionService.buscarPorId(id);
        if (ubicacion != null) {
            ubicacionService.eliminar(ubicacion);
            return "redirect:/ubicaciones";
        } else {
            modelo.addAttribute("mensajeError", "ubicacion no encontrada");
            return "error";
        }
    }

    /**
     * ModelAndView encapsula (equivalente a modificar el Model recibido como
     * parametro y retornar un String con la siguiente vista)
     */
    @GetMapping("nuevo")
    public ModelAndView prepararNuevaUbicacion() {
        Ubicacion ubicacion = new Ubicacion();

        ModelAndView result = new ModelAndView();
        result.addObject("ubicacion", ubicacion);
        result.addObject("esNuevo", true);
        result.setViewName("ubicaciones/editarUbicacion");
        return result;
    }

    @PostMapping("/nuevo")
    public String crearUbicacion( @Valid @ModelAttribute Ubicacion ubicacion, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            ubicacionService.crear(ubicacion);
            return "redirect:/ubicaciones";
        } else {
            return null;
        }
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
    @GetMapping("{id}")
    public String prepararEditarUbicacion(@PathVariable("id") Long id, Model modelo) {
        try {
            Ubicacion ubicacion = ubicacionService.buscarPorId(id);
            modelo.addAttribute("ubicacion", ubicacion);
            modelo.addAttribute("esNuevo", false);
            return "ubicaciones/editarUbicacion";
        } catch (EntityNotFoundException e) {
            modelo.addAttribute("error", "ubicacion no encontrada");
            return "error";
        }
    }

   /* @GetMapping("/parametros/{id}")
    public String prepararEditarParametros(@PathVariable("id") Long id, Model modelo) {
        try {
            Ubicacion ubicacion = ubicacionService.buscarPorId(id);
            modelo.addAttribute("ubicacion", ubicacion);
            modelo.addAttribute("esNuevo", false);
            return "ubicaciones/listadoParametros";
        } catch (EntityNotFoundException e) {
            modelo.addAttribute("error", "parametros de ubicacion no encontrados");
            return "error";
        }
    }*/

    @GetMapping("/parametros/{id}")
    public String prepararListarParametros(@PathVariable("id") Long id, Model modelo) {
        List<Parametro> parametros = parametrosService.buscarPorUbicacionId(id);
        modelo.addAttribute("parametros", parametros);
        modelo.addAttribute("ubicacionId", id);
        return "ubicaciones/listadoParametros";

    }

    @GetMapping("/parametros/nuevo/{id}")
    public ModelAndView prepararNuevoParametro(@PathVariable("id") Long id) {
        Ubicacion ubicacion = ubicacionService.buscarPorId(id);
        Parametro parametro = new Parametro();
        parametro.setUbicacion(ubicacion);
        ModelAndView result = new ModelAndView();
        result.addObject("parametro", parametro);
        result.addObject("ubicacionId", id);
        result.addObject("esNuevo", true);
        result.setViewName("ubicaciones/editarParametro");
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
    @PostMapping("/parametros/nuevo/{id}")
    public String crearParametro(@PathParam("id") Long id, @Valid @ModelAttribute Parametro parametro, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            parametro.setId(null);
            parametrosService.crear(parametro);
            return "redirect:/ubicaciones/parametros/"+parametro.getUbicacion().getId();
        } else {
            return null;
        }
    }

    @PostMapping("/parametros/{idUbi}/{idParam}")
    public String actualizarParametro(@PathParam("idUbi") Long idUbi,@PathParam("idParam") Long id, @Valid @ModelAttribute Parametro parametro, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            /*Ubicacion ubicacion = ubicacionService.buscarPorId(idUbi);
            parametro.setUbicacion(ubicacion);*/
            parametro.setId(id);
            parametrosService.modificar(parametro);
            return "redirect:/ubicaciones/parametros/"+idUbi+"/"+parametro.getId();
        } else {
            return null;
        }
    }

    @GetMapping("/parametros/{idUbi}/{idParam}")
    public String prepararEditarParametro(@PathVariable("idUbi") Long idUbi,@PathVariable("idParam") Long id, Model modelo) {
        try {
            Parametro parametro = parametrosService.buscarPorId(id);
            modelo.addAttribute("parametro", parametro);
            modelo.addAttribute("id", id);
            modelo.addAttribute("esNuevo", false);
            return "ubicaciones/editarParametro";
        } catch (EntityNotFoundException e) {
            modelo.addAttribute("error", "ubicacion no encontrada");
            return "error";
        }
    }

    @PostMapping("{id}")
    public String actualizarubicacion(@Valid @ModelAttribute Ubicacion ubicacion, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            ubicacionService.modificar(ubicacion);
            return "redirect:/ubicaciones/parametros";
        } else {
            return null;
        }
    }
}
