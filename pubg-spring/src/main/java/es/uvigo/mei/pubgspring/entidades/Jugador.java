package es.uvigo.mei.pubgspring.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Jugador implements Serializable {
    @Id
    private String cuenta;

    private String nombre;


    public Jugador() {
    }

    public Jugador(String cuenta, String nombre) {
        this.cuenta = cuenta;
        this.nombre = nombre;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public int hashCode() {
        int hash = Objects.hashCode(this.cuenta);
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
        final Jugador other = (Jugador) obj;
        if (!Objects.equals(this.cuenta, other.cuenta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Jugador{" + "cuenta=" + cuenta + ", nombre=" + nombre + '}';
    }

}