package es.uvigo.mei.pubgspring.entidades;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String area;
    private String camara;
    private int duracion;
    private String mapa;
    private String modo;
    private int tam_equipo;
    private boolean es_evento;
    private boolean es_juegocustom;

    public Partida() {
    }

    public Partida(String area, String camara, int duracion, String mapa, String modo, int tam_equipo, boolean es_evento, boolean es_juegocustom) {
        this.area = area;
        this.camara = camara;
        this.duracion = duracion;
        this.mapa = mapa;
        this.modo = modo;
        this.tam_equipo = tam_equipo;
        this.es_evento = es_evento;
        this.es_juegocustom = es_juegocustom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCamara() {
        return camara;
    }

    public void setCamara(String camara) {
        this.camara = camara;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public int getTam_equipo() {
        return tam_equipo;
    }

    public void setTam_equipo(int tam_equipo) {
        this.tam_equipo = tam_equipo;
    }

    public boolean isEs_evento() {
        return es_evento;
    }

    public void setEs_evento(boolean es_evento) {
        this.es_evento = es_evento;
    }

    public boolean isEs_juegocustom() {
        return es_juegocustom;
    }

    public void setEs_juegocustom(boolean es_juegocustom) {
        this.es_juegocustom = es_juegocustom;
    }

    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        } else {
            int hash = Objects.hashCode(this.mapa);
            return hash;
        }
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
        final Partida other = (Partida) obj;
        if (this.id != null) {
            return this.id.equals(other.id);
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Partida{" + "id=" + id + ", mapa=" + mapa + ", modo=" + modo + '}';
    }

}
