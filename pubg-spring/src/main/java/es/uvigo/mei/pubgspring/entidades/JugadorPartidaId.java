package es.uvigo.mei.pubgspring.entidades;

import java.io.Serializable;
import java.util.Objects;

public class JugadorPartidaId implements Serializable {
    private Long jugador;
    private Long partida;

    public JugadorPartidaId() {
        super();
    }

    public JugadorPartidaId(Long jugador, Long partida) {
        super();
        this.jugador = jugador;
        this.partida = partida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JugadorPartidaId that = (JugadorPartidaId) o;
        return Objects.equals(jugador, that.jugador) && Objects.equals(partida, that.partida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jugador, partida);
    }


}
