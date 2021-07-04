package es.uvigo.mei.pubgspring.controladores;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import es.uvigo.mei.pubgspring.entidades.Kills;
import es.uvigo.mei.pubgspring.servicios.KillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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


import es.uvigo.mei.pubgspring.entidades.Partida;
import es.uvigo.mei.pubgspring.servicios.PartidaService;
import es.uvigo.mei.pubgspring.entidades.Jugador;
import es.uvigo.mei.pubgspring.entidades.JugadorPartida;
import es.uvigo.mei.pubgspring.servicios.JugadorService;


@Controller
@RequestMapping("/partidas")
public class PartidaController {
    @Autowired
    PartidaService partidaService;

    @Autowired
    JugadorService jugadorService;

    @Autowired
    KillService killService;

    @ModelAttribute("jugadores")
    public List<Jugador> crearListaJugadores() {
        return jugadorService.buscarTodos();
    }

    @GetMapping
    public String prepararListarPartidas(Model modelo) {
        modelo.addAttribute("partidas", partidaService.buscarTodos());
        modelo.addAttribute("areaPartida", "");
        modelo.addAttribute("camaraPartida", "");
        modelo.addAttribute("duracionPartida", "");
        modelo.addAttribute("mapaPartida", "");
        modelo.addAttribute("modoPartida", "");
        modelo.addAttribute("tamEquipoPartida", "");
        modelo.addAttribute("esEventoPartida", "");
        modelo.addAttribute("esJuegocustomPartida", "");
        return "partidas/listadoPartidas";
    }

    @PostMapping
    public String actualizarListarPartida(@RequestParam(required = false) Long idJugador,
                                          @RequestParam(required = false) String mapaPartida,
                                          @RequestParam(required = false) String modoPartida,
                                           Model modelo) {
        List<Partida> partidas;
        if (idJugador != null) {
            partidas = partidaService.buscarPorJugadorId(idJugador);
        } else if ((mapaPartida != null) && !mapaPartida.isEmpty()) {
            partidas = partidaService.buscarPorMapa(mapaPartida);
        } else if ((modoPartida != null) && !modoPartida.isEmpty()) {
            partidas = partidaService.buscarPorModo(modoPartida);
        } else {
            partidas = partidaService.buscarTodos();
        }
        modelo.addAttribute("partidas", partidas);
        return "partidas/listadoPartidas";
    }

    @GetMapping("nuevo")
    public ModelAndView prepararNuevaPartida() {
        Partida partida = new Partida();

        ModelAndView result = new ModelAndView();
        result.addObject("partida", partida);
        result.addObject("esNuevo", true);
        result.setViewName("partidas/editarPartida");
        return result;
    }

    @PostMapping("nuevo")
    public String crearPartida(@Valid @ModelAttribute Partida partida, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            partidaService.crear(partida);
            return "redirect:/partidas";
        } else {
            return null;
        }
    }

    @GetMapping("{id}")
    public String prepararEditarPartida(@PathVariable("id") Long id, Model modelo) {
        try {
            Partida partida = partidaService.buscarPorId(id);
            List<JugadorPartida> jugadoresPartida = jugadorService.buscarJugadorPartidaPorPartidaId(id);
            modelo.addAttribute("partida", partida);
            modelo.addAttribute("esNuevo", false);
            modelo.addAttribute("jugadoresPartida", jugadoresPartida);
            return "partidas/editarPartida";
        } catch (EntityNotFoundException e) {
            modelo.addAttribute("error", "Partida no encontrada");
            return "error";
        }
    }

    @PostMapping(path="{id}", params="actualizar")
    public String actualizarPartida(@Valid @ModelAttribute Partida partida, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            partidaService.modificar(partida);
            return "redirect:/partidas";
        } else {
            return null;
        }
    }

    @PostMapping(path = "{id}", params = "anadirJugador")
    public String anadirJugador(@PathVariable("id") Long idPartida, @RequestParam("idJugador") Long idJugador,
                                @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        if ((idJugador != null) && (fecha != null)) {
            Partida partida = partidaService.buscarPorId(idPartida);
            Jugador jugador = jugadorService.buscarPorId(idJugador);
            jugadorService.crearJugadorPartida(new JugadorPartida(jugador, partida, fecha));
        }
        return "redirect:/partidas/"+idPartida;
    }

    @PostMapping(path = "{id}", params = "eliminarJugador")
    public String eliminarJugador(@PathVariable("id") Long idPartida, @RequestParam("eliminarJugador") Long idJugador) {

        if (idPartida != null) {
            jugadorService.eliminarJugadorPartida(idJugador, idPartida);
            List<Kills> killsV = (List<Kills>) killService.buscarPorVictima(idJugador);
            if(killsV!=null) {
                for (Kills k : killsV) {
                    killService.eliminar(k);
                }
            }
            List<Kills> killsA = (List<Kills>) killService.buscarPorAsesino(idJugador);
            if(killsA!=null) {
                for (Kills k : killsA) {
                    killService.eliminar(k);
                }
            }
        }
        return "redirect:/partidas/"+idPartida;
    }


    /**
     * @PathVariable vincula el parametro a un segmento de la URI
     */
    @GetMapping("{id}/eliminar")
    public String borrarPartida(@PathVariable("id") Long id, Model modelo) {
        Partida partida = partidaService.buscarPorId(id);
        if (partida != null) {
            List<JugadorPartida> jugadorPartida = (List<JugadorPartida>) jugadorService.buscarJugadorPartidaPorPartidaId(id);
            if(jugadorPartida!=null) {
                for (JugadorPartida jp : jugadorPartida) {
                    jugadorService.eliminarJugadorPartida(jp);
                }
            }
            List<Kills> kills = (List<Kills>) killService.buscarPorPartida(id);
            if(kills!=null) {
                for (Kills k : kills) {
                    killService.eliminar(k);
                }
            }
            partidaService.eliminar(partida);
            return "redirect:/partidas";
        } else {
            modelo.addAttribute("mensajeError", "Partida no encontrada");
            return "error";
        }
    }

}
