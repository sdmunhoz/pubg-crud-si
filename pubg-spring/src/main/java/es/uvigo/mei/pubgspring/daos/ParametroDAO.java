package es.uvigo.mei.pubgspring.daos;

import java.util.List;

import es.uvigo.mei.pubgspring.entidades.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParametroDAO extends JpaRepository<Parametro, Long> {

    List<Parametro> findByUbicacionId(Long ubicacionId);
}

