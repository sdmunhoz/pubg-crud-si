package es.uvigo.mei.pubgspring.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Kills implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Jugador asesino;

    @ManyToOne
    private Jugador victima;

    @ManyToOne
    private Partida partida;

    @ManyToOne
    private Arma arma;

    @ManyToOne
    private Ubicacion ubicacion;

    private String causa;

    private String razon;

    private String categoria;

    private int distancia;

    public Kills(){}

    public Kills(Long id, Jugador asesino, Jugador victima, Partida partida, Arma arma, Ubicacion ubicacion, String causa, String razon, String categoria, int distancia) {
        this.id = id;
        this.asesino = asesino;
        this.victima = victima;
        this.partida = partida;
        this.arma = arma;
        this.ubicacion = ubicacion;
        this.causa = causa;
        this.razon = razon;
        this.categoria = categoria;
        this.distancia = distancia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jugador getAsesino() {
        return asesino;
    }

    public void setAsesino(Jugador asesino) {
        this.asesino = asesino;
    }

    public Jugador getVictima() {
        return victima;
    }

    public void setVictima(Jugador victima) {
        this.victima = victima;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kills kills = (Kills) o;
        return distancia == kills.distancia && id.equals(kills.id) && Objects.equals(asesino, kills.asesino) && Objects.equals(victima, kills.victima) && Objects.equals(partida, kills.partida) && Objects.equals(arma, kills.arma) && Objects.equals(ubicacion, kills.ubicacion) && Objects.equals(causa, kills.causa) && Objects.equals(razon, kills.razon) && Objects.equals(categoria, kills.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, asesino, victima, partida, arma, ubicacion, causa, razon, categoria, distancia);
    }

    @Override
    public String toString() {
        return "Kill{" +
                "id=" + id +
                ", asesino=" + asesino +
                ", victima=" + victima +
                ", partida=" + partida +
                ", arma=" + arma +
                ", ubicacion=" + ubicacion +
                ", causa='" + causa + '\'' +
                ", razon='" + razon + '\'' +
                ", categoria='" + categoria + '\'' +
                ", distancia=" + distancia +
                '}';
    }
}
