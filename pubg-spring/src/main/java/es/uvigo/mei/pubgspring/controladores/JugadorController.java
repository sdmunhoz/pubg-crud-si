package es.uvigo.mei.pubgspring.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.uvigo.mei.pubgspring.entidades.Jugador;
import es.uvigo.mei.pubgspring.entidades.Partida;
import es.uvigo.mei.pubgspring.entidades.JugadorPartida;
import es.uvigo.mei.pubgspring.servicios.JugadorService;
import es.uvigo.mei.pubgspring.servicios.PartidaService;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {
    @Autowired
    JugadorService jugadorService;

    @Autowired
    PartidaService partidaService;

    @ModelAttribute("partidas")
    public List<Partida> crearListaPartidas() {
        return partidaService.buscarTodos();
    }

    /**
     * Model encapsula el modelo (en este caso sera un Model vacio para ser
     * inicializado)
     */

    @GetMapping
    public String prepararListarJugadores(Model modelo) {
        List<Jugador> jugadores = jugadorService.buscarTodos();
        modelo.addAttribute("jugadores", jugadores);
        modelo.addAttribute("nombreJugador", "");
        modelo.addAttribute("cuentaJugador", "");
        return "jugador/listadoJugadores";
    }

    /**
     * @RequestParam captura los parámetros de la peticion (en este caso cuerpo del
     *               POST) cuyo nombre coincida con el nombre de los parametros
     */
    @PostMapping
    public String actualizarListarJugadores(@RequestParam(required = false) String nombreJugador,
                                            @RequestParam(required = false) String cuentaJugador, @RequestParam(required = false) Long idPartida,
                                            Model modelo) {
        List<Jugador> jugadores;
        System.out.println('1');
        if (idPartida != null) {
            jugadores = jugadorService.buscarPorPartidaId(idPartida);
            System.out.println('1');
        } else if ((nombreJugador != null) && !nombreJugador.isEmpty()) {
            System.out.println('2');
            jugadores = jugadorService.buscarPorNombre(nombreJugador);
        } else if ((cuentaJugador != null) && !cuentaJugador.isEmpty()) {
            System.out.println('3');
            jugadores = jugadorService.buscarPorCuenta(cuentaJugador);
        } else {
            System.out.println('4');
            jugadores = jugadorService.buscarTodos();
        }
        modelo.addAttribute("jugadores", jugadores);
        return "jugador/listadoJugadores";
    }

    /**
     * @PathVariable vincula el parametro a un segmento de la URI
     */
    @GetMapping("{id}/eliminar")
    public String borrarJugador(@PathVariable("id") Long id, Model modelo) {
        Jugador jugador = jugadorService.buscarPorId(id);
        if (jugador != null) {
            jugadorService.eliminar(jugador);
            return "redirect:/jugadores";
        } else {
            modelo.addAttribute("mensajeError", "Jugador no encontrado");
            return "error";
        }
    }

    /**
     * ModelAndView encapsula (equivalente a modificar el Model recibido como
     * parametro y retornar un String con la siguiente vista)
     */
    @GetMapping("nuevo")
    public ModelAndView prepararNuevoJugador() {
        Jugador jugador = new Jugador();

        ModelAndView result = new ModelAndView();
        result.addObject("jugador", jugador);
        result.addObject("esNuevo", true);
        result.addObject("partidasJugador", new ArrayList<>());
        result.setViewName("jugador/editarJugador");
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
    public String crearJugador(@Valid @ModelAttribute Jugador jugador, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            jugadorService.crear(jugador);
            return "redirect:/jugadores";
        } else {
            return null;
        }
    }

    @GetMapping("{id}")
    public String prepararEditarJugador(@PathVariable("id") Long id, Model modelo) {
        try {
            Jugador jugador = jugadorService.buscarPorId(id);
            modelo.addAttribute("jugador", jugador);
            modelo.addAttribute("esNuevo", false);
            modelo.addAttribute("partidasJugador", jugadorService.buscarJugadorPartidaPorJugadorId(id));
            return "jugador/editarJugador";
        } catch (EntityNotFoundException e) {
            modelo.addAttribute("error", "Jugador no encontrado");
            return "error";
        }
    }

    @PostMapping("{id}")
    public String actualizarJugador(@Valid @ModelAttribute Jugador jugador, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            jugadorService.modificar(jugador);
            return "redirect:/jugadores";
        } else {
            return null;
        }
    }

    @PostMapping(path = "{id}", params = "anadirPartida")
    public String anadirJugadorPartida(@PathVariable("id") Long idJugador, @RequestParam Long idPartida,
                                        @RequestParam Date fecha) {
        if ((idPartida != null) && (fecha != null)) {
            Partida partida = partidaService.buscarPorId(idPartida);
            Jugador jugador = jugadorService.buscarPorId(idJugador);
            jugadorService.crearJugadorPartida(new JugadorPartida(jugador, partida, fecha));
        }
        return "redirect:/jugadores/"+idJugador;
    }

    @PostMapping(path = "{id}", params = "eliminarPartida")
    public String eliminarPartidasJugador(@PathVariable("id") Long idJugador, @RequestParam("eliminarPartida") Long idPartida) {
        if (idJugador != null) {
            jugadorService.eliminarJugadorPartida(idPartida, idJugador);
        }
        return "redirect:/jugadores/"+idJugador;
    }
}
