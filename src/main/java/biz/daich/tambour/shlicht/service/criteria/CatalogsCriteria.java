package biz.daich.tambour.shlicht.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link biz.daich.tambour.shlicht.domain.Catalogs} entity. This class is used
 * in {@link biz.daich.tambour.shlicht.web.rest.CatalogsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /catalogs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CatalogsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter id;

    private BooleanFilter isActive;

    private InstantFilter createdTime;

    private StringFilter catalogColorsId;

    private StringFilter idId;

    private Boolean distinct;

    public CatalogsCriteria() {}

    public CatalogsCriteria(CatalogsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.createdTime = other.createdTime == null ? null : other.createdTime.copy();
        this.catalogColorsId = other.catalogColorsId == null ? null : other.catalogColorsId.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CatalogsCriteria copy() {
        return new CatalogsCriteria(this);
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

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public BooleanFilter isActive() {
        if (isActive == null) {
            isActive = new BooleanFilter();
        }
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
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

    public StringFilter getCatalogColorsId() {
        return catalogColorsId;
    }

    public StringFilter catalogColorsId() {
        if (catalogColorsId == null) {
            catalogColorsId = new StringFilter();
        }
        return catalogColorsId;
    }

    public void setCatalogColorsId(StringFilter catalogColorsId) {
        this.catalogColorsId = catalogColorsId;
    }

    public StringFilter getIdId() {
        return idId;
    }

    public StringFilter idId() {
        if (idId == null) {
            idId = new StringFilter();
        }
        return idId;
    }

    public void setIdId(StringFilter idId) {
        this.idId = idId;
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
        final CatalogsCriteria that = (CatalogsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(createdTime, that.createdTime) &&
            Objects.equals(catalogColorsId, that.catalogColorsId) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, createdTime, catalogColorsId, idId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CatalogsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (isActive != null ? "isActive=" + isActive + ", " : "") +
            (createdTime != null ? "createdTime=" + createdTime + ", " : "") +
            (catalogColorsId != null ? "catalogColorsId=" + catalogColorsId + ", " : "") +
            (idId != null ? "idId=" + idId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
