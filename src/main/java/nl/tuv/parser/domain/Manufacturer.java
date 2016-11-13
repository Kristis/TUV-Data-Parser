package nl.tuv.parser.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer founded;

    private String description;

    private String url;

    public Manufacturer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFounded() {
        return founded;
    }

    public void setFounded(Integer founded) {
        this.founded = founded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manufacturer)) return false;

        Manufacturer that = (Manufacturer) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (founded != null ? !founded.equals(that.founded) : that.founded != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return url != null ? url.equals(that.url) : that.url == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (founded != null ? founded.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Manufacturer{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", founded=").append(founded);
        sb.append(", description='").append(description).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
