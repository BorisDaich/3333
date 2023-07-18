package biz.daich.tambour.shlicht.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Scans.
 */
@Entity
@Table(name = "scans")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Scans implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Lob
    @Column(name = "scanner_id", nullable = false)
    private String scannerId;

    @NotNull
    @Column(name = "sequence_in_batch", nullable = false)
    private Integer sequenceInBatch;

    @NotNull
    @Size(max = 16)
    @Column(name = "state", length = 16, nullable = false)
    private String state;

    @NotNull
    @Column(name = "d_e", nullable = false)
    private Double dE;

    @NotNull
    @Column(name = "created_time", nullable = false)
    private Instant createdTime;

    @Column(name = "scanned_time")
    private Instant scannedTime;

    @Column(name = "inspected_time")
    private Instant inspectedTime;

    @Column(name = "modified_time")
    private Instant modifiedTime;

    @Column(name = "ejected_time")
    private Instant ejectedTime;

    @Transient
    private boolean isPersisted;

    @JsonIgnoreProperties(value = { "scans" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Images image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "colorCatalog" }, allowSetters = true)
    private Batches productionBatch;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Scans id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScannerId() {
        return this.scannerId;
    }

    public Scans scannerId(String scannerId) {
        this.setScannerId(scannerId);
        return this;
    }

    public void setScannerId(String scannerId) {
        this.scannerId = scannerId;
    }

    public Integer getSequenceInBatch() {
        return this.sequenceInBatch;
    }

    public Scans sequenceInBatch(Integer sequenceInBatch) {
        this.setSequenceInBatch(sequenceInBatch);
        return this;
    }

    public void setSequenceInBatch(Integer sequenceInBatch) {
        this.sequenceInBatch = sequenceInBatch;
    }

    public String getState() {
        return this.state;
    }

    public Scans state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getdE() {
        return this.dE;
    }

    public Scans dE(Double dE) {
        this.setdE(dE);
        return this;
    }

    public void setdE(Double dE) {
        this.dE = dE;
    }

    public Instant getCreatedTime() {
        return this.createdTime;
    }

    public Scans createdTime(Instant createdTime) {
        this.setCreatedTime(createdTime);
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getScannedTime() {
        return this.scannedTime;
    }

    public Scans scannedTime(Instant scannedTime) {
        this.setScannedTime(scannedTime);
        return this;
    }

    public void setScannedTime(Instant scannedTime) {
        this.scannedTime = scannedTime;
    }

    public Instant getInspectedTime() {
        return this.inspectedTime;
    }

    public Scans inspectedTime(Instant inspectedTime) {
        this.setInspectedTime(inspectedTime);
        return this;
    }

    public void setInspectedTime(Instant inspectedTime) {
        this.inspectedTime = inspectedTime;
    }

    public Instant getModifiedTime() {
        return this.modifiedTime;
    }

    public Scans modifiedTime(Instant modifiedTime) {
        this.setModifiedTime(modifiedTime);
        return this;
    }

    public void setModifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Instant getEjectedTime() {
        return this.ejectedTime;
    }

    public Scans ejectedTime(Instant ejectedTime) {
        this.setEjectedTime(ejectedTime);
        return this;
    }

    public void setEjectedTime(Instant ejectedTime) {
        this.ejectedTime = ejectedTime;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public Scans setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Images getImage() {
        return this.image;
    }

    public void setImage(Images images) {
        this.image = images;
    }

    public Scans image(Images images) {
        this.setImage(images);
        return this;
    }

    public Batches getProductionBatch() {
        return this.productionBatch;
    }

    public void setProductionBatch(Batches batches) {
        this.productionBatch = batches;
    }

    public Scans productionBatch(Batches batches) {
        this.setProductionBatch(batches);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Scans)) {
            return false;
        }
        return id != null && id.equals(((Scans) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Scans{" +
            "id=" + getId() +
            ", scannerId='" + getScannerId() + "'" +
            ", sequenceInBatch=" + getSequenceInBatch() +
            ", state='" + getState() + "'" +
            ", dE=" + getdE() +
            ", createdTime='" + getCreatedTime() + "'" +
            ", scannedTime='" + getScannedTime() + "'" +
            ", inspectedTime='" + getInspectedTime() + "'" +
            ", modifiedTime='" + getModifiedTime() + "'" +
            ", ejectedTime='" + getEjectedTime() + "'" +
            "}";
    }
}
