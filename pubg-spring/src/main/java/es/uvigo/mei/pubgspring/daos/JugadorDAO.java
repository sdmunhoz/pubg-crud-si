package es.uvigo.mei.pubgspring.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uvigo.mei.pubgspring.entidades.Jugador;

@Repository
public interface JugadorDAO extends JpaRepository<Jugador, Long>{
    List<Jugador> findByNombreContaining(String nombre);
    List<Jugador> findByCuentaContaining(String cuenta);
    @Query("SELECT aa.jugador FROM JugadorPartida AS aa WHERE aa.partida.id = :partidaId")
    List<Jugador> findByPartidaId(Long partidaId);
}
