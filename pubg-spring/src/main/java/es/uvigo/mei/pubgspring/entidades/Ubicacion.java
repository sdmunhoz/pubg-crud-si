package es.uvigo.mei.pubgspring.entidades;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Ubicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Parametro> parametros = new ArrayList<>();

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

    public void anadirParametro(Parametro nuevoParametro) {
        nuevoParametro.setUbicacion(this);
    }

    protected void anadirParametroInterno(Parametro nuevoParametro) {
        if (parametros == null) {
            this.parametros = new ArrayList<>();
        }
        if (!this.parametros.contains(nuevoParametro)) {
            this.parametros.add(nuevoParametro);
        }
    }

    public void eliminarParametro(Parametro parametro) {
        parametro.setUbicacion(null);
    }

    protected void eliminarParametroInterno(Parametro parametro) {
        if (parametros != null) {
            this.parametros.remove(parametro);
        }
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
