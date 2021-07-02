package es.uvigo.mei.pubgspring.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uvigo.mei.pubgspring.entidades.JugadorPartida;
import es.uvigo.mei.pubgspring.entidades.JugadorPartidaId;

@Repository
public interface JugadorPartidaDAO extends JpaRepository<JugadorPartida, JugadorPartidaId>{
    List<JugadorPartida> findByJugadorId(Long jugadorId);
    List<JugadorPartida> findByPartidaId(Long partidaId);
}
