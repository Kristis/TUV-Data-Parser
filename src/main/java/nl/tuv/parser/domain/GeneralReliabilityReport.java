package nl.tuv.parser.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
@Entity
public class GeneralReliabilityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer reportYear;

    private Integer minCarAge;

    private Integer maxCarAge;

    private Integer vintage;

    private Double averageFaultRate;

    private Integer amountOfModels;

    private String url;

    public GeneralReliabilityReport(Integer reportYear, Integer carAge, Integer maxCarAge, Integer vintage, Double averageFaultRate, Integer amountOfModels, String url) {
        this.reportYear = reportYear;
        this.minCarAge = carAge;
        this.maxCarAge = maxCarAge;
        this.vintage = vintage;
        this.averageFaultRate = averageFaultRate;
        this.amountOfModels = amountOfModels;
        this.url = url;
    }

    public GeneralReliabilityReport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReportYear() {
        return reportYear;
    }

    public void setReportYear(Integer reportYear) {
        this.reportYear = reportYear;
    }

    public Integer getMinCarAge() {
        return minCarAge;
    }

    public void setMinCarAge(Integer minCarAge) {
        this.minCarAge = minCarAge;
    }

    public Integer getVintage() {
        return vintage;
    }

    public void setVintage(Integer vintage) {
        this.vintage = vintage;
    }

    public Double getAverageFaultRate() {
        return averageFaultRate;
    }

    public void setAverageFaultRate(Double averageFaultRate) {
        this.averageFaultRate = averageFaultRate;
    }

    public Integer getAmountOfModels() {
        return amountOfModels;
    }

    public void setAmountOfModels(Integer amountOfModels) {
        this.amountOfModels = amountOfModels;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getMaxCarAge() {
        return maxCarAge;
    }

    public void setMaxCarAge(Integer maxCarAge) {
        this.maxCarAge = maxCarAge;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GeneralReliabilityReportFactory{");
        sb.append("id=").append(id);
        sb.append(", reportYear=").append(reportYear);
        sb.append(", minCarAge=").append(minCarAge);
        sb.append(", vintage=").append(vintage);
        sb.append(", averageFaultRate=").append(averageFaultRate);
        sb.append(", amountOfModels=").append(amountOfModels);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneralReliabilityReport)) return false;

        GeneralReliabilityReport that = (GeneralReliabilityReport) o;

        if (!id.equals(that.id)) return false;
        if (!reportYear.equals(that.reportYear)) return false;
        if (!minCarAge.equals(that.minCarAge)) return false;
        if (!vintage.equals(that.vintage)) return false;
        if (!averageFaultRate.equals(that.averageFaultRate)) return false;
        return amountOfModels.equals(that.amountOfModels);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + reportYear.hashCode();
        result = 31 * result + minCarAge.hashCode();
        result = 31 * result + vintage.hashCode();
        result = 31 * result + averageFaultRate.hashCode();
        result = 31 * result + amountOfModels.hashCode();
        return result;
    }
}
