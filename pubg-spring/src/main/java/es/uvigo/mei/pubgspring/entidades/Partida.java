package es.uvigo.mei.pubgspring.entidades;


import java.io.Serializable;
import java.util.Objects;
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
    private int tamEq;
    private boolean esEvento;
    private boolean esJuegocustom;

    public Partida() {
    }

    public Partida(String area, String camara, int duracion, String mapa, String modo, int tamEq, boolean esEvento, boolean esJuegocustom) {
        this.area = area;
        this.camara = camara;
        this.duracion = duracion;
        this.mapa = mapa;
        this.modo = modo;
        this.tamEq = tamEq;
        this.esEvento = esEvento;
        this.esJuegocustom = esJuegocustom;
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

    public int getTamEq() {
        return tamEq;
    }

    public void setTamEq(int tamEq) {
        this.tamEq = tamEq;
    }

    public boolean getEsEvento() {
        return esEvento;
    }

    public void setEsEvento(boolean esEvento) {
        this.esEvento = esEvento;
    }

    public boolean getEsJuegocustom() {
        return esJuegocustom;
    }

    public void setEsJuegocustom(boolean esJuegocustom) {
        this.esJuegocustom = esJuegocustom;
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
