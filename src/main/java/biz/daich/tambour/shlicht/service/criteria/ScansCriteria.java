package biz.daich.tambour.shlicht.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link biz.daich.tambour.shlicht.domain.Scans} entity. This class is used
 * in {@link biz.daich.tambour.shlicht.web.rest.ScansResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /scans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ScansCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter id;

    private IntegerFilter sequenceInBatch;

    private StringFilter state;

    private DoubleFilter dE;

    private InstantFilter createdTime;

    private InstantFilter scannedTime;

    private InstantFilter inspectedTime;

    private InstantFilter modifiedTime;

    private InstantFilter ejectedTime;

    private StringFilter imageId;

    private StringFilter productionBatchId;

    private Boolean distinct;

    public ScansCriteria() {}

    public ScansCriteria(ScansCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sequenceInBatch = other.sequenceInBatch == null ? null : other.sequenceInBatch.copy();
        this.state = other.state == null ? null : other.state.copy();
        this.dE = other.dE == null ? null : other.dE.copy();
        this.createdTime = other.createdTime == null ? null : other.createdTime.copy();
        this.scannedTime = other.scannedTime == null ? null : other.scannedTime.copy();
        this.inspectedTime = other.inspectedTime == null ? null : other.inspectedTime.copy();
        this.modifiedTime = other.modifiedTime == null ? null : other.modifiedTime.copy();
        this.ejectedTime = other.ejectedTime == null ? null : other.ejectedTime.copy();
        this.imageId = other.imageId == null ? null : other.imageId.copy();
        this.productionBatchId = other.productionBatchId == null ? null : other.productionBatchId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ScansCriteria copy() {
        return new ScansCriteria(this);
    }

    public StringFilter getId() {
        return id;
    }

    public StringFilter id() {
        if (id == null) {
            id = new StringFilter();
        }
        return id;
    }

    public void setId(StringFilter id) {
        this.id = id;
    }

    public IntegerFilter getSequenceInBatch() {
        return sequenceInBatch;
    }

    public IntegerFilter sequenceInBatch() {
        if (sequenceInBatch == null) {
            sequenceInBatch = new IntegerFilter();
        }
        return sequenceInBatch;
    }

    public void setSequenceInBatch(IntegerFilter sequenceInBatch) {
        this.sequenceInBatch = sequenceInBatch;
    }

    public StringFilter getState() {
        return state;
    }

    public StringFilter state() {
        if (state == null) {
            state = new StringFilter();
        }
        return state;
    }

    public void setState(StringFilter state) {
        this.state = state;
    }

    public DoubleFilter getdE() {
        return dE;
    }

    public DoubleFilter dE() {
        if (dE == null) {
            dE = new DoubleFilter();
        }
        return dE;
    }

    public void setdE(DoubleFilter dE) {
        this.dE = dE;
    }

    public InstantFilter getCreatedTime() {
        return createdTime;
    }

    public InstantFilter createdTime() {
        if (createdTime == null) {
            createdTime = new InstantFilter();
        }
        return createdTime;
    }

    public void setCreatedTime(InstantFilter createdTime) {
        this.createdTime = createdTime;
    }

    public InstantFilter getScannedTime() {
        return scannedTime;
    }

    public InstantFilter scannedTime() {
        if (scannedTime == null) {
            scannedTime = new InstantFilter();
        }
        return scannedTime;
    }

    public void setScannedTime(InstantFilter scannedTime) {
        this.scannedTime = scannedTime;
    }

    public InstantFilter getInspectedTime() {
        return inspectedTime;
    }

    public InstantFilter inspectedTime() {
        if (inspectedTime == null) {
            inspectedTime = new InstantFilter();
        }
        return inspectedTime;
    }

    public void setInspectedTime(InstantFilter inspectedTime) {
        this.inspectedTime = inspectedTime;
    }

    public InstantFilter getModifiedTime() {
        return modifiedTime;
    }

    public InstantFilter modifiedTime() {
        if (modifiedTime == null) {
            modifiedTime = new InstantFilter();
        }
        return modifiedTime;
    }

    public void setModifiedTime(InstantFilter modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public InstantFilter getEjectedTime() {
        return ejectedTime;
    }

    public InstantFilter ejectedTime() {
        if (ejectedTime == null) {
            ejectedTime = new InstantFilter();
        }
        return ejectedTime;
    }

    public void setEjectedTime(InstantFilter ejectedTime) {
        this.ejectedTime = ejectedTime;
    }

    public StringFilter getImageId() {
        return imageId;
    }

    public StringFilter imageId() {
        if (imageId == null) {
            imageId = new StringFilter();
        }
        return imageId;
    }

    public void setImageId(StringFilter imageId) {
        this.imageId = imageId;
    }

    public StringFilter getProductionBatchId() {
        return productionBatchId;
    }

    public StringFilter productionBatchId() {
        if (productionBatchId == null) {
            productionBatchId = new StringFilter();
        }
        return productionBatchId;
    }

    public void setProductionBatchId(StringFilter productionBatchId) {
        this.productionBatchId = productionBatchId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ScansCriteria that = (ScansCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(sequenceInBatch, that.sequenceInBatch) &&
            Objects.equals(state, that.state) &&
            Objects.equals(dE, that.dE) &&
            Objects.equals(createdTime, that.createdTime) &&
            Objects.equals(scannedTime, that.scannedTime) &&
            Objects.equals(inspectedTime, that.inspectedTime) &&
            Objects.equals(modifiedTime, that.modifiedTime) &&
            Objects.equals(ejectedTime, that.ejectedTime) &&
            Objects.equals(imageId, that.imageId) &&
            Objects.equals(productionBatchId, that.productionBatchId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            sequenceInBatch,
            state,
            dE,
            createdTime,
            scannedTime,
            inspectedTime,
            modifiedTime,
            ejectedTime,
            imageId,
            productionBatchId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ScansCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (sequenceInBatch != null ? "sequenceInBatch=" + sequenceInBatch + ", " : "") +
            (state != null ? "state=" + state + ", " : "") +
            (dE != null ? "dE=" + dE + ", " : "") +
            (createdTime != null ? "createdTime=" + createdTime + ", " : "") +
            (scannedTime != null ? "scannedTime=" + scannedTime + ", " : "") +
            (inspectedTime != null ? "inspectedTime=" + inspectedTime + ", " : "") +
            (modifiedTime != null ? "modifiedTime=" + modifiedTime + ", " : "") +
            (ejectedTime != null ? "ejectedTime=" + ejectedTime + ", " : "") +
            (imageId != null ? "imageId=" + imageId + ", " : "") +
            (productionBatchId != null ? "productionBatchId=" + productionBatchId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
