package es.uvigo.mei.pubgspring.entidades;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

    @Entity
    @IdClass(JugadorPartidaId.class)
    public class JugadorPartida implements Serializable {

        @Id
        @ManyToOne
        private Jugador jugador;

        @Id
        @ManyToOne
        private Partida partida;

        private Date fecha;

        public JugadorPartida() {
        }

        public JugadorPartida(Jugador jugador, Partida partida, Date fecha) {
            this.jugador = jugador;
            this.partida = partida;
            this.fecha = fecha;
        }

        public Jugador getJugador() {
            return jugador;
        }

        public void setJugador(Jugador jugador) {
            this.jugador = jugador;
        }

        public Partida getPartida() {
            return partida;
        }

        public void setPartida(Partida partida) {
            this.partida = partida;
        }

        public Date getFecha() {
            return fecha;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 53 * hash + Objects.hashCode(this.jugador);
            hash = 53 * hash + Objects.hashCode(this.partida);
            hash = 53 * hash + Objects.hashCode(this.fecha);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final JugadorPartida other = (JugadorPartida) obj;
            if (!Objects.equals(this.partida, other.partida)) {
                return false;
            }
            if (!Objects.equals(this.jugador, other.jugador)) {
                return false;
            }
            if (!Objects.equals(this.fecha, other.fecha)) {
                return false;
            }
            return true;
        }




}
