package es.uvigo.mei.pubgspring.servicios;

import java.util.List;

import es.uvigo.mei.pubgspring.entidades.Ubicacion;

public interface UbicacionService {
    public Ubicacion crear(Ubicacion ubicacion);

    public Ubicacion modificar(Ubicacion ubicacion);

    public void eliminar(Ubicacion ubicacion);

    public Ubicacion buscarPorId(Long id);

    public List<Ubicacion> buscarTodos();

    public List<Ubicacion> buscarPorZona(String area);

    public List<Ubicacion> buscarPorTiempo(String camara);


}
