package es.uvigo.mei.pubgspring.servicios;

import java.util.Date;
import java.util.List;

import es.uvigo.mei.pubgspring.entidades.Jugador;
import es.uvigo.mei.pubgspring.entidades.JugadorPartida;
import es.uvigo.mei.pubgspring.entidades.Partida;

public interface JugadorService {
    public Jugador crear(Jugador jugador);
    public Jugador modificar(Jugador jugador);
    public void eliminar(Jugador jugador);
    public Jugador buscarPorId(Long id);
    public List<Jugador> buscarTodos();
    public List<Jugador> buscarPorNombre(String nombre);
    public List<Jugador> buscarPorCuenta(String cuenta);
    public List<Jugador> buscarPorPartidaId(Long partidaId);

    public JugadorPartida crearJugadorPartida(JugadorPartida jugadorPartida);
    public JugadorPartida crearJugadorPartida(Jugador jugador, Partida partida, Date fecha);
    public void eliminarJugadorPartida(JugadorPartida jugadorPartida);
    public void eliminarJugadorPartida(Long idJugador, Long idPartida);
    public JugadorPartida buscarJugadorPartidaPorJugadorIdPartidaId(Long jugadorId, Long partidaId);
    public List<JugadorPartida> buscarJugadorPartidaPorJugadorId(Long jugadorId);
    public List<JugadorPartida> buscarJugadorPartidaPorPartidaId(Long partidaId);
}
