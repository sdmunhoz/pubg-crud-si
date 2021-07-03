package es.uvigo.mei.pubgspring.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uvigo.mei.pubgspring.entidades.Partida;

@Repository
public interface PartidaDAO extends JpaRepository<Partida, Long>{
    List<Partida> findByAreaContaining(String patron);
    List<Partida> findByCamaraContaining(String camara);
    List<Partida> findByDuracionContaining(int duracion);
    List<Partida> findByMapaContaining(String mapa);
    List<Partida> findByModoContaining(String modo);
    List<Partida> findByTamEqContaining(int tamEq);
    List<Partida> findByEsEventoContaining(boolean esEvento);
    List<Partida> findByEsJuegocustomContaining(boolean esJuegocustom);
    @Query("SELECT aa.partida FROM JugadorPartida AS aa WHERE aa.jugador.id = :jugadorId")
    List<Partida> findByJugadorId(Long jugadorId);
}
