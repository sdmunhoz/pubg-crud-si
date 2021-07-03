package es.uvigo.mei.pubgspring.controladores;


import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/partidas")
public class PartidaController {
    @Autowired
    PartidaService partidaService;


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
        return "partida/listadoPartidas";
    }

    @PostMapping
    public String actualizarListarPartida(@RequestParam(required = false) String areaPartida,@RequestParam(required = false) String camaraPartida,
                                          @RequestParam(required = false) String mapaPartida, @RequestParam(required = false) String modoPartida,
                                           Model modelo) {
        List<Partida> partidas;
        if ((areaPartida != null) && !areaPartida.isEmpty()) {
            partidas = partidaService.buscarPorArea(areaPartida);
        } else if ((camaraPartida != null) && !camaraPartida.isEmpty()) {
            partidas = partidaService.buscarPorCamara(camaraPartida);
        } else if ((mapaPartida != null) && !mapaPartida.isEmpty()) {
            partidas = partidaService.buscarPorMapa(mapaPartida);
        } else if ((modoPartida != null) && !modoPartida.isEmpty()) {
            partidas = partidaService.buscarPorModo(modoPartida);
        } else {
            partidas = partidaService.buscarTodos();
        }
        modelo.addAttribute("partidas", partidas);
        return "partida/listadoPartidas";
    }

    @GetMapping("nuevo")
    public ModelAndView prepararNuevaPartida() {
        Partida partida = new Partida();

        ModelAndView result = new ModelAndView();
        result.addObject("partida", partida);
        result.addObject("esNuevo", true);
        result.setViewName("partida/editarPartida");
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
            modelo.addAttribute("partida", partida);
            modelo.addAttribute("esNuevo", false);
            return "partida/editarPartida";
        } catch (EntityNotFoundException e) {
            modelo.addAttribute("error", "Partida no encontrada");
            return "error";
        }
    }

    @PostMapping("{id}")
    public String actualizarPartida(@Valid @ModelAttribute Partida partida, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            partidaService.modificar(partida);
            return "redirect:/partidas";
        } else {
            return null;
        }
    }
}
