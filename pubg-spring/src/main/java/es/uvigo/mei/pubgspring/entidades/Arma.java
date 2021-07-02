package es.uvigo.mei.pubgspring.entidades;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Arma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String tipo;

    private Double tipo_municion;

    private int danho;

    private int capacidad_balas;

    private int rango;

    private Double tasa_disparo;

    private int n_disparos_matar;

    private int n_disparos_matar_cabeza;


    public Arma() {
    }

    public Arma(String nombre, String tipo, Double tipo_municion, int danho, int capacidad_balas, int rango,
                Double tasa_disparo, int n_disparos_matar, int n_disparos_matar_cabeza) {
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

    public Double getTipo_municion() {
        return tipo_municion;
    }

    public void setTipo_municion(Double tipo_municion) {
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

    public Double getTasa_disparo() {
        return tasa_disparo;
    }

    public void setTasa_disparo(Double tasa_disparo) {
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
    public String toString() {
        return "Arma{" + "id=" + id + ", nombre=" + nombre + '}';
    }


}
