package es.uvigo.mei.pubgspring.servicios;

import es.uvigo.mei.pubgspring.daos.*;
import es.uvigo.mei.pubgspring.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KillServiceImpl implements KillService {
    @Autowired
    private KillDAO killDAO;

    @Autowired
    private JugadorDAO jugadorDAO;

    @Autowired
    private ArmaDAO armaDAO;

    @Autowired
    private PartidaDAO partidaDAO;

    @Autowired
    private UbicacionDAO ubicacionDAO;

    @Override
    @Transactional
    public Kills crear(Kills kill) {
        return killDAO.save(kill);
    }

    @Override
    @Transactional
    public Kills modificar(Kills kill) {
        return killDAO.save(kill);
    }

    @Override
    @Transactional
    public void eliminar(Kills kill) {
        killDAO.delete(kill);
    }

    @Override
    @Transactional(readOnly = true)
    public Kills buscarPorId(Long id) {
        return killDAO.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Kills> buscarTodos() {
        return killDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Kills> buscarPorAsesino(Long idAsesino) {
        return killDAO.findByAsesinoId(idAsesino);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Kills> buscarPorVictima(Long idVictima) {
        return killDAO.findByVictimaId(idVictima);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Kills> buscarPorArma(Long idArma) {
        return killDAO.findByArmaId(idArma);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Kills> buscarPorUbicacion(Long idUbicacion) {
        return killDAO.findByUbicacionId(idUbicacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Kills> buscarPorPartida(Long idPartida) {
        return killDAO.findByPartidaId(idPartida);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jugador> buscarJugadores() {
        return jugadorDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Arma> buscarArmas() {
        return armaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarPartidas() {
        return partidaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ubicacion> buscarUbicaciones() {
        return ubicacionDAO.findAll();
    }



}
