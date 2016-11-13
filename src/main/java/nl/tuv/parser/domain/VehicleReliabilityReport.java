package nl.tuv.parser.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
@Entity
public class VehicleReliabilityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Vehicle vehicle;

    private Integer rank;

    private Double faultRate;

    private GeneralReliabilityReport generalReliabilityReport;

    private Integer percentage;

    private Integer percentageOut;

    private String url;

    public VehicleReliabilityReport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getFaultRate() {
        return faultRate;
    }

    public void setFaultRate(Double faultRate) {
        this.faultRate = faultRate;
    }

    public GeneralReliabilityReport getGeneralReliabilityReport() {
        return generalReliabilityReport;
    }

    public void setGeneralReliabilityReport(GeneralReliabilityReport generalReliabilityReport) {
        this.generalReliabilityReport = generalReliabilityReport;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getPercentageOut() {
        return percentageOut;
    }

    public void setPercentageOut(Integer percentageOut) {
        this.percentageOut = percentageOut;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VehicleReliabilityReport{");
        sb.append("id=").append(id);
        sb.append(", vehicle=").append(vehicle);
        sb.append(", rank=").append(rank);
        sb.append(", faultRate=").append(faultRate);
        sb.append(", generalReliabilityReport=").append(generalReliabilityReport);
        sb.append(", percentage=").append(percentage);
        sb.append(", percentageOut=").append(percentageOut);
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleReliabilityReport)) return false;

        VehicleReliabilityReport that = (VehicleReliabilityReport) o;

        if (!id.equals(that.id)) return false;
        if (!vehicle.equals(that.vehicle)) return false;
        if (!rank.equals(that.rank)) return false;
        if (!faultRate.equals(that.faultRate)) return false;
        if (generalReliabilityReport != null ? !generalReliabilityReport.equals(that.generalReliabilityReport) : that.generalReliabilityReport != null)
            return false;
        if (percentage != null ? !percentage.equals(that.percentage) : that.percentage != null) return false;
        if (percentageOut != null ? !percentageOut.equals(that.percentageOut) : that.percentageOut != null)
            return false;
        return url != null ? url.equals(that.url) : that.url == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + vehicle.hashCode();
        result = 31 * result + rank.hashCode();
        result = 31 * result + faultRate.hashCode();
        result = 31 * result + (generalReliabilityReport != null ? generalReliabilityReport.hashCode() : 0);
        result = 31 * result + (percentage != null ? percentage.hashCode() : 0);
        result = 31 * result + (percentageOut != null ? percentageOut.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
