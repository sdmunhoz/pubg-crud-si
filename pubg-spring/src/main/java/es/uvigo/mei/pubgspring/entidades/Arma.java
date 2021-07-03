package es.uvigo.mei.pubgspring.entidades;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Arma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String tipo;

    private String tipo_municion;

    private int danho;

    private int capacidad_balas;

    private int rango;

    private String tasa_disparo;

    private int n_disparos_matar;

    private int n_disparos_matar_cabeza;


    public Arma() {
    }

    public Arma(String nombre, String tipo, String tipo_municion, int danho, int capacidad_balas, int rango,
                String tasa_disparo, int n_disparos_matar, int n_disparos_matar_cabeza) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.tipo_municion = tipo_municion;
        this.danho = danho;
        this.capacidad_balas = capacidad_balas;
        this.rango = rango;
        this.tasa_disparo = tasa_disparo;
        this.n_disparos_matar = n_disparos_matar;
        this.n_disparos_matar_cabeza = n_disparos_matar_cabeza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo_municion() {
        return tipo_municion;
    }

    public void setTipo_municion(String tipo_municion) {
        this.tipo_municion = tipo_municion;
    }

    public int getDanho() {
        return danho;
    }

    public void setDanho(int danho) {
        this.danho = danho;
    }

    public int getCapacidad_balas() {
        return capacidad_balas;
    }

    public void setCapacidad_balas(int capacidad_balas) {
        this.capacidad_balas = capacidad_balas;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public String getTasa_disparo() {
        return tasa_disparo;
    }

    public void setTasa_disparo(String tasa_disparo) {
        this.tasa_disparo = tasa_disparo;
    }

    public int getN_disparos_matar() {
        return n_disparos_matar;
    }

    public void setN_disparos_matar(int n_disparos_matar) {
        this.n_disparos_matar = n_disparos_matar;
    }

    public int getN_disparos_matar_cabeza() {
        return n_disparos_matar_cabeza;
    }

    public void setN_disparos_matar_cabeza(int n_disparos_matar_cabeza) {
        this.n_disparos_matar_cabeza = n_disparos_matar_cabeza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arma arma = (Arma) o;
        return danho == arma.danho && capacidad_balas == arma.capacidad_balas && rango == arma.rango && n_disparos_matar == arma.n_disparos_matar && n_disparos_matar_cabeza == arma.n_disparos_matar_cabeza && id.equals(arma.id) && nombre.equals(arma.nombre) && Objects.equals(tipo, arma.tipo) && Objects.equals(tipo_municion, arma.tipo_municion) && Objects.equals(tasa_disparo, arma.tasa_disparo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, tipo, tipo_municion, danho, capacidad_balas, rango, tasa_disparo, n_disparos_matar, n_disparos_matar_cabeza);
    }

    @Override
    public String toString() {
        return "Arma{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", tipo_municion='" + tipo_municion + '\'' +
                ", danho=" + danho +
                ", capacidad_balas=" + capacidad_balas +
                ", rango=" + rango +
                ", tasa_disparo='" + tasa_disparo + '\'' +
                ", n_disparos_matar=" + n_disparos_matar +
                ", n_disparos_matar_cabeza=" + n_disparos_matar_cabeza +
                '}';
    }


}
