package es.uvigo.mei.pubgspring.servicios;

import java.util.List;

import es.uvigo.mei.pubgspring.entidades.Partida;

public interface PartidaService {
    public Partida crear(Partida partida);
    public Partida modificar(Partida partida);
    public void eliminar(Partida partida);
    public Partida buscarPorId(Long id);
    public List<Partida> buscarTodos();
    public List<Partida> buscarPorArea(String area);
    public List<Partida> buscarPorCamara(String camara);
    public List<Partida> buscarPorDuracion(int duracion);
    public List<Partida> buscarPorMapa(String mapa);
    public List<Partida> buscarPorModo(String modo);
    public List<Partida> buscarPorTamEq(int tamEq);
    public List<Partida> buscarPorEsEvento(boolean esEvento);
    public List<Partida> buscarPorEsJuegocustom(boolean esJuegocustom);
    public List<Partida> buscarPorJugadorId(Long idJugador);




}
