package es.uvigo.mei.pubgspring.servicios;

import java.util.List;

import es.uvigo.mei.pubgspring.entidades.Parametro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.pubgspring.daos.UbicacionDAO;
import es.uvigo.mei.pubgspring.entidades.Ubicacion;

@Service
public class UbicacionServiceImpl implements UbicacionService {
    @Autowired
    private UbicacionDAO ubicacionDAO;

    @Override
    @Transactional
    public Ubicacion crear(Ubicacion ubicacion) {
        return ubicacionDAO.save(ubicacion);
    }

    @Override
    @Transactional
    public Ubicacion modificar(Ubicacion ubicacion) {
        return ubicacionDAO.save(ubicacion);
    }

    @Override
    @Transactional
    public void eliminar(Ubicacion ubicacion) {
        ubicacionDAO.delete(ubicacion);
    }

    @Override
    @Transactional(readOnly = true)
    public Ubicacion buscarPorId(Long id) {
        return ubicacionDAO.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ubicacion> buscarTodos() {
        return ubicacionDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ubicacion> buscarPorZona(String nombre) {
        return ubicacionDAO.findByZonaContaining(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ubicacion> buscarPorTiempo(String cuenta) {
        return ubicacionDAO.findByTiempoContaining(cuenta);
    }

    @Override
    public List<Parametro> buscarParametrosPorId(Long id) {
        return ubicacionDAO.findParameteresByUbicacionId(id);
    }


}
