package es.uvigo.mei.pubgspring.servicios;

import java.util.Date;
import java.util.List;

import es.uvigo.mei.pubgspring.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.pubgspring.daos.JugadorDAO;
import es.uvigo.mei.pubgspring.daos.JugadorPartidaDAO;
import es.uvigo.mei.pubgspring.daos.PartidaDAO;

@Service
public class JugadorServiceImpl implements JugadorService {
    @Autowired
    private JugadorDAO jugadorDAO;

    @Autowired
    private JugadorPartidaDAO jugadorPartidaDAO;

    @Override
    @Transactional
    public Jugador crear(Jugador jugador) {
        return jugadorDAO.save(jugador);
    }

    @Override
    @Transactional
    public Jugador modificar(Jugador jugador) {
        return jugadorDAO.save(jugador);
    }

    @Override
    @Transactional
    public void eliminar(Jugador jugador) {
        jugadorDAO.delete(jugador);
    }

    @Override
    @Transactional(readOnly = true)
    public Jugador buscarPorId(Long id) {
        return jugadorDAO.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jugador> buscarTodos() {
        return jugadorDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jugador> buscarPorNombre(String nombre) {
        return jugadorDAO.findByNombreContaining(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jugador> buscarPorCuenta(String cuenta) {
        return jugadorDAO.findByCuentaContaining(cuenta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jugador> buscarPorPartidaId(Long partidaId) {
        return jugadorDAO.findByPartidaId(partidaId);
    }

    @Override
    public JugadorPartida crearJugadorPartida(JugadorPartida jugadorPartida) {
        return jugadorPartidaDAO.save(jugadorPartida);
    }

    @Override
    @Transactional
    public JugadorPartida crearJugadorPartida(Jugador jugador, Partida partida, Date fecha) {
        return this.crearJugadorPartida(new JugadorPartida(jugador, partida, fecha));
    }

    @Override
    @Transactional
    public void eliminarJugadorPartida(JugadorPartida jugadorPartida) {
        jugadorPartidaDAO.delete(jugadorPartida);
    }

    @Override
    public void eliminarJugadorPartida(Long jugadorId, Long partidaId) {
        JugadorPartida aa = jugadorPartidaDAO.getOne(new JugadorPartidaId(jugadorId, partidaId));
        this.eliminarJugadorPartida(aa);
    }

    @Override
    @Transactional(readOnly = true)
    public JugadorPartida buscarJugadorPartidaPorJugadorIdPartidaId(Long jugadorId, Long partidaId) {
        return jugadorPartidaDAO.getOne(new JugadorPartidaId(jugadorId, partidaId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<JugadorPartida> buscarJugadorPartidaPorJugadorId(Long jugadorId) {
        return jugadorPartidaDAO.findByJugadorId(jugadorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JugadorPartida> buscarJugadorPartidaPorPartidaId(Long partidaId) {
        return jugadorPartidaDAO.findByPartidaId(partidaId);
    }


}
