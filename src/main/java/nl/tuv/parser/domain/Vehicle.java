package nl.tuv.parser.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String modelName;

    private String url;

    private Manufacturer manufacturer;

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Vehicle{");
        sb.append("id=").append(id);
        sb.append(", modelName='").append(modelName).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", manufacturer=").append(manufacturer);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (!id.equals(vehicle.id)) return false;
        if (!modelName.equals(vehicle.modelName)) return false;
        return url != null ? url.equals(vehicle.url) : vehicle.url == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + modelName.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
