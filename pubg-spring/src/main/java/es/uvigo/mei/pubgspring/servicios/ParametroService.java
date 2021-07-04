package es.uvigo.mei.pubgspring.servicios;

import java.util.List;

import es.uvigo.mei.pubgspring.entidades.Parametro;

public interface ParametroService {
    public Parametro crear(Parametro parametro);

    public Parametro modificar(Parametro parametro);

    public void eliminar(Parametro parametro);

    public Parametro buscarPorId(Long id);

    public List<Parametro> buscarTodos();

    public List<Parametro> buscarPorUbicacionId(Long idUbicacion);


}
