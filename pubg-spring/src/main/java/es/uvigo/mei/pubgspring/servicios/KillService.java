package es.uvigo.mei.pubgspring.servicios;

import es.uvigo.mei.pubgspring.entidades.*;

import java.util.List;

public interface KillService {

    public Kills crear(Kills kill);
    public Kills modificar(Kills kill);
    public void eliminar(Kills kill);
    public Kills buscarPorId(Long id);
    public List<Kills> buscarTodos();
    public List<Kills> buscarPorAsesino(Long idAsesino);
    public List<Kills> buscarPorVictima(Long idVictima);
    public List<Kills> buscarPorArma(Long idArma);
    public List<Kills> buscarPorUbicacion(Long idUbicacion);
    public List<Kills> buscarPorPartida(Long idPartida);

    public List<Jugador> buscarJugadores();
    public List<Arma> buscarArmas();
    public List<Partida> buscarPartidas();
    public List<Ubicacion> buscarUbicaciones();

}
