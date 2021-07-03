package es.uvigo.mei.pubgspring.servicios;

import java.util.List;

import es.uvigo.mei.pubgspring.daos.ParametroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.pubgspring.entidades.Parametro;

@Service
public class ParametroServiceImpl implements ParametroService {
    @Autowired
    private ParametroDAO parametroDAO;

    @Override
    @Transactional
    public Parametro crear(Parametro parametro) {
        return parametroDAO.save(parametro);
    }

    @Override
    @Transactional
    public Parametro modificar(Parametro parametro) {
        return parametroDAO.save(parametro);
    }

    @Override
    @Transactional
    public void eliminar(Parametro Parametro) {
        parametroDAO.delete(Parametro);
    }

    @Override
    @Transactional(readOnly = true)
    public Parametro buscarPorId(Long id) {
        return parametroDAO.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Parametro> buscarTodos() {
        return parametroDAO.findAll();
    }



}
