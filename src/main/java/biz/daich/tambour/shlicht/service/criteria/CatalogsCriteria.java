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

    private Boolean distinct;

    public CatalogsCriteria() {}

    public CatalogsCriteria(CatalogsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.createdTime = other.createdTime == null ? null : other.createdTime.copy();
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
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, createdTime, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CatalogsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (isActive != null ? "isActive=" + isActive + ", " : "") +
            (createdTime != null ? "createdTime=" + createdTime + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
