package es.uvigo.mei.pubgspring.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Parametro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Ubicacion ubicacion;

    private String tamCupula;

    private boolean esZonaAzul;

    private boolean esZonaRoja;




    public Parametro(){}

    public Parametro(Long id, boolean esZonaAzul, boolean esZonaRoja,String tamCupula, Ubicacion ubicacion ) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.tamCupula = tamCupula;
        this.esZonaAzul = esZonaAzul;
        this.esZonaRoja = esZonaRoja;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ubicacion getUbicacion(){
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion){
        this.ubicacion = ubicacion;
    }

    public String getTamCupula() {
        return tamCupula;
    }

    public void setTamCupula(String tamCupula) {
        this.tamCupula = tamCupula;
    }

    public boolean isEsZonaAzul() {
        return esZonaAzul;
    }

    public void setEsZonaAzul(boolean esZonaAzul) {
        this.esZonaAzul = esZonaAzul;
    }

    public boolean isEsZonaRoja() {
        return esZonaRoja;
    }

    public void setEsZonaRoja(boolean esZonaRoja) {
        this.esZonaRoja = esZonaRoja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parametro that = (Parametro) o;
        return esZonaAzul == that.esZonaAzul && esZonaRoja == that.esZonaRoja && Objects.equals(id, that.id) && Objects.equals(ubicacion, that.ubicacion) && Objects.equals(tamCupula, that.tamCupula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ubicacion, tamCupula, esZonaAzul, esZonaRoja);
    }

    @Override
    public String toString() {
        return "Parametro{" +
                "id=" + id +
                ", ubicacion=" + ubicacion +
                ", tamCupula='" + tamCupula + '\'' +
                ", esZonaAzul=" + esZonaAzul +
                ", esZonaRoja=" + esZonaRoja +
                '}';
    }
}
