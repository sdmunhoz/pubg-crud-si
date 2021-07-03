package es.uvigo.mei.pubgspring.daos;

import es.uvigo.mei.pubgspring.entidades.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import es.uvigo.mei.pubgspring.entidades.Arma;
import java.util.List;

@Repository
public interface ArmaDAO extends JpaRepository<Arma, Long> {
    List<Arma> findByNombreContaining(String nombre);
    List<Arma> findByTipoContaining(String tipo);
    List<Arma> findByTipoMunicionContaining(String tipoMunicion);
}
