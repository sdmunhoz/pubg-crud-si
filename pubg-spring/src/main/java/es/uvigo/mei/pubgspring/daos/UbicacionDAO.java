package es.uvigo.mei.pubgspring.daos;

import java.util.List;

import es.uvigo.mei.pubgspring.entidades.Parametro;
import es.uvigo.mei.pubgspring.entidades.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UbicacionDAO extends JpaRepository<Ubicacion, Long> {
    List<Ubicacion> findByZonaContaining(String zona);

    List<Ubicacion> findByTiempoContaining(String tiempo);

    @Query(value = "SELECT P.* FROM PARAMETRO P, UBICACION U WHERE P.UBICACION_ID=u.id and u.id=:id", nativeQuery = true)
    List<Parametro> findParameteresByUbicacionId(@Param("id") Long id);
}
