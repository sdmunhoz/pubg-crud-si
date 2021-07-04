package es.uvigo.mei.pubgspring.daos;

import es.uvigo.mei.pubgspring.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KillDAO extends JpaRepository<Kills, Long> {

    List<Kills> findByAsesinoId(Long asesinoId);
    List<Kills> findByVictimaId(Long victimaId);
    List<Kills> findByArmaId(Long armaId);
    List<Kills> findByPartidaId(Long partidaId);
    List<Kills> findByUbicacionId(Long ubicacionId);

}
