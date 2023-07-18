package biz.daich.tambour.shlicht.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link biz.daich.tambour.shlicht.domain.Images} entity. This class is used
 * in {@link biz.daich.tambour.shlicht.web.rest.ImagesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /images?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ImagesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter id;

    private IntegerFilter rawWidth;

    private IntegerFilter rawHeight;

    private StringFilter idId;

    private Boolean distinct;

    public ImagesCriteria() {}

    public ImagesCriteria(ImagesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.rawWidth = other.rawWidth == null ? null : other.rawWidth.copy();
        this.rawHeight = other.rawHeight == null ? null : other.rawHeight.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ImagesCriteria copy() {
        return new ImagesCriteria(this);
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

    public IntegerFilter getRawWidth() {
        return rawWidth;
    }

    public IntegerFilter rawWidth() {
        if (rawWidth == null) {
            rawWidth = new IntegerFilter();
        }
        return rawWidth;
    }

    public void setRawWidth(IntegerFilter rawWidth) {
        this.rawWidth = rawWidth;
    }

    public IntegerFilter getRawHeight() {
        return rawHeight;
    }

    public IntegerFilter rawHeight() {
        if (rawHeight == null) {
            rawHeight = new IntegerFilter();
        }
        return rawHeight;
    }

    public void setRawHeight(IntegerFilter rawHeight) {
        this.rawHeight = rawHeight;
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
        final ImagesCriteria that = (ImagesCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(rawWidth, that.rawWidth) &&
            Objects.equals(rawHeight, that.rawHeight) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rawWidth, rawHeight, idId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImagesCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (rawWidth != null ? "rawWidth=" + rawWidth + ", " : "") +
            (rawHeight != null ? "rawHeight=" + rawHeight + ", " : "") +
            (idId != null ? "idId=" + idId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
