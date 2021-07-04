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

    private String tipoMunicion;

    private int danho;

    private int capacidadBalas;

    private int rango;

    private String tasaDisparo;

    private int nDisparosMatar;

    private int nDisparosMatarCabeza;


    public Arma() {
    }

    public Arma(String nombre, String tipo, String tipoMunicion, int danho, int capacidadBalas,
                int rango, String tasaDisparo, int nDisparosMatar, int nDisparosMatarCabeza) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.tipoMunicion = tipoMunicion;
        this.danho = danho;
        this.capacidadBalas = capacidadBalas;
        this.rango = rango;
        this.tasaDisparo = tasaDisparo;
        this.nDisparosMatar = nDisparosMatar;
        this.nDisparosMatarCabeza = nDisparosMatarCabeza;
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

    public String getTipoMunicion() {
        return tipoMunicion;
    }

    public void setTipoMunicion(String tipoMunicion) {
        this.tipoMunicion = tipoMunicion;
    }

    public int getDanho() {
        return danho;
    }

    public void setDanho(int danho) {
        this.danho = danho;
    }

    public int getCapacidadBalas() {
        return capacidadBalas;
    }

    public void setCapacidadBalas(int capacidadBalas) {
        this.capacidadBalas = capacidadBalas;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public String getTasaDisparo() {
        return tasaDisparo;
    }

    public void setTasaDisparo(String tasaDisparo) {
        this.tasaDisparo = tasaDisparo;
    }

    public int getnDisparosMatar() {
        return nDisparosMatar;
    }

    public void setnDisparosMatar(int nDisparosMatar) {
        this.nDisparosMatar = nDisparosMatar;
    }

    public int getnDisparosMatarCabeza() {
        return nDisparosMatarCabeza;
    }

    public void setnDisparosMatarCabeza(int nDisparosMatarCabeza) {
        this.nDisparosMatarCabeza = nDisparosMatarCabeza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arma arma = (Arma) o;
        return danho == arma.danho && capacidadBalas == arma.capacidadBalas && rango == arma.rango && nDisparosMatar == arma.nDisparosMatar && nDisparosMatarCabeza == arma.nDisparosMatarCabeza && id.equals(arma.id) && Objects.equals(nombre, arma.nombre) && Objects.equals(tipo, arma.tipo) && Objects.equals(tipoMunicion, arma.tipoMunicion) && Objects.equals(tasaDisparo, arma.tasaDisparo);
    }

    @Override
    public int hashCode() {
        if(this.id != null){
           return this.id.hashCode();
        }

        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.nombre);
        hash = 41 * hash + Objects.hashCode(this.tipo);
        hash = 41 * hash + Objects.hashCode(this.tipoMunicion);
        hash = 41 * hash + Objects.hashCode(this.danho);
        hash = 41 * hash + Objects.hashCode(this.capacidadBalas);
        hash = 41 * hash + Objects.hashCode(this.rango);
        hash = 41 * hash + Objects.hashCode(this.tasaDisparo);
        hash = 41 * hash + Objects.hashCode(this.nDisparosMatar);
        hash = 41 * hash + Objects.hashCode(this.nDisparosMatarCabeza);
        return hash;
    }

    @Override
    public String toString() {
        return "Arma{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", tipoMunicion='" + tipoMunicion + '\'' +
                ", danho=" + danho +
                ", capacidadBalas=" + capacidadBalas +
                ", rango=" + rango +
                ", tasaDisparo='" + tasaDisparo + '\'' +
                ", nDisparosMatar=" + nDisparosMatar +
                ", nDisparosMatarCabeza=" + nDisparosMatarCabeza +
                '}';
    }
}
