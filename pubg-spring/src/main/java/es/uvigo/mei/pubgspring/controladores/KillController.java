package es.uvigo.mei.pubgspring.controladores;

import es.uvigo.mei.pubgspring.entidades.*;
import es.uvigo.mei.pubgspring.servicios.JugadorService;
import es.uvigo.mei.pubgspring.servicios.KillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/kills")
public class KillController {
    @Autowired
    KillService killService;

    @Autowired
    JugadorService jugadorService;



    /**
     * Model encapsula el modelo (en este caso sera un Model vacio para ser
     * inicializado)
     */

    @GetMapping
    public String prepararListarKills(Model modelo) {
        modelo.addAttribute("kills", killService.buscarTodos());
        modelo.addAttribute("asesinos", killService.buscarJugadores());
        modelo.addAttribute("victimas", killService.buscarJugadores());
        modelo.addAttribute("armas", killService.buscarArmas());
        modelo.addAttribute("partidas", killService.buscarPartidas());
        modelo.addAttribute("ubicaciones", killService.buscarUbicaciones());
        return "kills/listadoKills";
    }

    /**
     * @RequestParam captura los parámetros de la peticion (en este caso cuerpo del
     *               POST) cuyo nombre coincida con el nombre de los parametros
     */
    @PostMapping
    public String actualizarListarKills(@RequestParam(required = false) Long idAsesino,
                                        @RequestParam(required = false) Long idVictima,
                                        @RequestParam(required = false) Long idArma,
                                        @RequestParam(required = false) Long idPartida,
                                        @RequestParam(required = false) Long idUbicacion,
                                        Model modelo) {
        List<Kills> kills;
        if (idAsesino != null) {
            kills = killService.buscarPorAsesino(idAsesino);
        } else if (idVictima != null) {
            kills = killService.buscarPorVictima(idVictima);
        } else if (idArma != null) {
            kills = killService.buscarPorArma(idArma);
        } else if (idPartida != null) {
            kills = killService.buscarPorPartida(idPartida);
        } else if (idUbicacion != null) {
            kills = killService.buscarPorUbicacion(idUbicacion);
        } else {
            kills = killService.buscarTodos();
        }
        modelo.addAttribute("kills", kills);
        modelo.addAttribute("asesinos", killService.buscarJugadores());
        modelo.addAttribute("victimas", killService.buscarJugadores());
        modelo.addAttribute("armas", killService.buscarArmas());
        modelo.addAttribute("partidas", killService.buscarPartidas());
        modelo.addAttribute("ubicaciones", killService.buscarUbicaciones());
        return "kills/listadoKills";
    }

    /**
     * @PathVariable vincula el parametro a un segmento de la URI
     */
    @GetMapping("{id}/eliminar")
    public String borrarKill(@PathVariable("id") Long id, Model modelo) {
        Kills kill = killService.buscarPorId(id);
        if (kill != null) {
            killService.eliminar(kill);
            return "redirect:/kills";
        } else {
            modelo.addAttribute("mensajeError", "Kill no encontrada");
            return "error";
        }
    }

    /**
     * ModelAndView encapsula (equivalente a modificar el Model recibido como
     * parametro y retornar un String con la siguiente vista)
     */
    @GetMapping("nuevo")
    public ModelAndView prepararNuevoKill() {
        Kills kill = new Kills();

        ModelAndView result = new ModelAndView();
        result.addObject("kill", kill);
        result.addObject("esNuevo", true);
        result.addObject("asesinos", killService.buscarJugadores());
        result.addObject("victimas", killService.buscarJugadores());
        result.addObject("armas", killService.buscarArmas());
        result.addObject("partidas", killService.buscarPartidas());
        result.addObject("ubicaciones", killService.buscarUbicaciones());

        result.setViewName("kills/editarKill");
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
    public String crearKill(@Valid @ModelAttribute Kills kill, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            List<JugadorPartida> j = jugadorService.buscarJugadorPartidaPorJugadorId(kill.getAsesino().getId());
            List<JugadorPartida> j2 = jugadorService.buscarJugadorPartidaPorJugadorId(kill.getVictima().getId());
            List<JugadorPartida> p = jugadorService.buscarJugadorPartidaPorPartidaId(kill.getPartida().getId());

            if(p==null){
                jugadorService.crearJugadorPartida(kill.getVictima(),kill.getPartida(),null);
                jugadorService.crearJugadorPartida(kill.getAsesino(),kill.getPartida(),null);

            }else{
                if(j==null){
                    jugadorService.crearJugadorPartida(kill.getAsesino(),kill.getPartida(),new Date(1997-04-24));
                }
                if(j2==null){
                    jugadorService.crearJugadorPartida(kill.getVictima(),kill.getPartida(),new Date(1997-04-24));
                }
                if(j!=null || j2!=null){
                    boolean esta1 =false;
                    boolean esta2 =false;
                    for (JugadorPartida jp:j) {
                        if(jp.getPartida().getId()==kill.getPartida().getId()){
                            esta1=true;
                        }
                    }
                    for (JugadorPartida jp2:j2) {
                        if(jp2.getPartida().getId()==kill.getPartida().getId()){
                            esta2=true;
                        }
                    }
                    if (esta1==false){
                        jugadorService.crearJugadorPartida(kill.getAsesino(),kill.getPartida(),new Date(1997-04-24));
                    }
                    if(esta2==false){
                        jugadorService.crearJugadorPartida(kill.getVictima(),kill.getPartida(),new Date(1997-04-24));
                    }
                }
            }


            killService.crear(kill);
            return "redirect:/kills";
        } else {
            return null;
        }
    }

    @GetMapping("{id}")
    public String prepararEditarKill(@PathVariable("id") Long id, Model modelo) {
        try {
            Kills kill = killService.buscarPorId(id);
            modelo.addAttribute("kill", kill);
            modelo.addAttribute("esNuevo", false);
            modelo.addAttribute("asesinos", killService.buscarJugadores());
            modelo.addAttribute("victimas", killService.buscarJugadores());
            modelo.addAttribute("armas", killService.buscarArmas());
            modelo.addAttribute("partidas", killService.buscarPartidas());
            modelo.addAttribute("ubicaciones", killService.buscarUbicaciones());

            return "kills/editarKill";
        } catch (EntityNotFoundException e) {
            modelo.addAttribute("error", "Kill no encontrada");
            return "error";
        }
    }

    @PostMapping("{id}")
    public String actualizarKill(@Valid @ModelAttribute Kills kill, BindingResult resultado) {
        if (!resultado.hasErrors()) {
            killService.modificar(kill);
            return "redirect:/kills";
        } else {
            return null;
        }
    }


}

