package es.uvigo.mei.pubgspring.entidades;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ubicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zona;

    private String tiempo;

    public Ubicacion(){}

    public Ubicacion(Long id, String zona, String tiempo) {
        this.id = id;
        this.zona = zona;
        this.tiempo = tiempo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return Objects.equals(id, ubicacion.id) && Objects.equals(zona, ubicacion.zona) && Objects.equals(tiempo, ubicacion.tiempo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zona, tiempo);
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "id=" + id +
                ", zona='" + zona + '\'' +
                ", tiempo='" + tiempo + '\'' +
                '}';
    }


}
