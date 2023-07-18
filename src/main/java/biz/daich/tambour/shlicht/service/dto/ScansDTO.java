package biz.daich.tambour.shlicht.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link biz.daich.tambour.shlicht.domain.Scans} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ScansDTO implements Serializable {

    @NotNull
    @Size(max = 36)
    private String id;

    @Lob
    private String scannerId;

    @NotNull
    private Integer sequenceInBatch;

    @NotNull
    @Size(max = 16)
    private String state;

    @NotNull
    private Double dE;

    @NotNull
    private Instant createdTime;

    private Instant scannedTime;

    private Instant inspectedTime;

    private Instant modifiedTime;

    private Instant ejectedTime;

    private ImagesDTO image;

    private BatchesDTO productionBatch;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScannerId() {
        return scannerId;
    }

    public void setScannerId(String scannerId) {
        this.scannerId = scannerId;
    }

    public Integer getSequenceInBatch() {
        return sequenceInBatch;
    }

    public void setSequenceInBatch(Integer sequenceInBatch) {
        this.sequenceInBatch = sequenceInBatch;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getdE() {
        return dE;
    }

    public void setdE(Double dE) {
        this.dE = dE;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getScannedTime() {
        return scannedTime;
    }

    public void setScannedTime(Instant scannedTime) {
        this.scannedTime = scannedTime;
    }

    public Instant getInspectedTime() {
        return inspectedTime;
    }

    public void setInspectedTime(Instant inspectedTime) {
        this.inspectedTime = inspectedTime;
    }

    public Instant getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Instant getEjectedTime() {
        return ejectedTime;
    }

    public void setEjectedTime(Instant ejectedTime) {
        this.ejectedTime = ejectedTime;
    }

    public ImagesDTO getImage() {
        return image;
    }

    public void setImage(ImagesDTO image) {
        this.image = image;
    }

    public BatchesDTO getProductionBatch() {
        return productionBatch;
    }

    public void setProductionBatch(BatchesDTO productionBatch) {
        this.productionBatch = productionBatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScansDTO)) {
            return false;
        }

        ScansDTO scansDTO = (ScansDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, scansDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ScansDTO{" +
            "id='" + getId() + "'" +
            ", scannerId='" + getScannerId() + "'" +
            ", sequenceInBatch=" + getSequenceInBatch() +
            ", state='" + getState() + "'" +
            ", dE=" + getdE() +
            ", createdTime='" + getCreatedTime() + "'" +
            ", scannedTime='" + getScannedTime() + "'" +
            ", inspectedTime='" + getInspectedTime() + "'" +
            ", modifiedTime='" + getModifiedTime() + "'" +
            ", ejectedTime='" + getEjectedTime() + "'" +
            ", image=" + getImage() +
            ", productionBatch=" + getProductionBatch() +
            "}";
    }
}
