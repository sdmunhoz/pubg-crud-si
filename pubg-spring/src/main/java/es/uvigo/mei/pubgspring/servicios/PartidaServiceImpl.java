package es.uvigo.mei.pubgspring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.pubgspring.daos.PartidaDAO;
import es.uvigo.mei.pubgspring.entidades.Partida;

@Service
public class PartidaServiceImpl implements PartidaService {
    @Autowired
    PartidaDAO partidaDAO;

    @Override
    @Transactional
    public Partida crear(Partida partida) {
        return partidaDAO.save(partida);
    }

    @Override
    @Transactional
    public Partida modificar(Partida partida) {
        return partidaDAO.save(partida);
    }

    @Override
    @Transactional
    public void eliminar(Partida partida) {
        partidaDAO.delete(partida);
    }

    @Override
    @Transactional(readOnly = true)
    public Partida buscarPorId(Long id) {
        return partidaDAO.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarTodos() {
        return partidaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarPorArea(String area) {
        return partidaDAO.findByAreaContaining(area);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarPorCamara(String camara) {
        return partidaDAO.findByCamaraContaining(camara);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarPorDuracion(int duracion) {
        return partidaDAO.findByDuracionContaining(duracion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarPorMapa(String mapa) {
        return partidaDAO.findByMapaContaining(mapa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarPorModo(String modo) {
        return partidaDAO.findByModoContaining(modo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarPorTamEq(int tamEq) {
        return partidaDAO.findByTamEqContaining(tamEq);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarPorEsEvento(boolean esEvento) {
        return partidaDAO.findByEsEventoContaining(esEvento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarPorEsJuegocustom(boolean esJuegocustom) {
        return partidaDAO.findByEsJuegocustomContaining(esJuegocustom);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarPorJugadorId(Long idJugador) {
        return partidaDAO.findByJugadorId(idJugador);
    }


}
