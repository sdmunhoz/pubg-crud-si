package es.uvigo.mei.pubgspring.servicios;

import es.uvigo.mei.pubgspring.entidades.Arma;

import java.util.Date;
import java.util.List;

public interface ArmaService {

    public Arma crear(Arma arma);
    public Arma modificar(Arma arma);
    public void eliminar(Arma arma);
    public Arma buscarPorId(Long id);
    public List<Arma> buscarTodos();
    public List<Arma> buscarPorNombre(String nombre);
    public List<Arma> buscarPorTipo(String tipo);
    public List<Arma> buscarPorTipoMunicion(String tipoMunicion);

}
