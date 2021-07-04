package es.uvigo.mei.pubgspring.servicios;

import es.uvigo.mei.pubgspring.daos.ArmaDAO;
import es.uvigo.mei.pubgspring.entidades.Arma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArmaServiceImpl implements ArmaService {
    @Autowired
    private ArmaDAO armaDAO;

    @Override
    @Transactional
    public Arma crear(Arma arma) {
        return armaDAO.save(arma);
    }

    @Override
    @Transactional
    public Arma modificar(Arma arma) {
        return armaDAO.save(arma);
    }

    @Override
    @Transactional
    public void eliminar(Arma arma) {
        armaDAO.delete(arma);
    }

    @Override
    @Transactional(readOnly = true)
    public Arma buscarPorId(Long id) {
        return armaDAO.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Arma> buscarTodos() {
        return armaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Arma> buscarPorNombre(String nombre) {
        return armaDAO.findByNombreContaining(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Arma> buscarPorTipo(String tipo) {
        return armaDAO.findByTipoContaining(tipo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Arma> buscarPorTipoMunicion(String tipoMunicion) {
        return armaDAO.findByTipoMunicionContaining(tipoMunicion);
    }

}
