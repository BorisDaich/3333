package biz.daich.tambour.shlicht.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link biz.daich.tambour.shlicht.domain.Catalogs} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CatalogsDTO implements Serializable {

    @NotNull
    @Size(max = 36)
    private String id;

    @Lob
    private String externalId;

    @Lob
    private String name;

    @Lob
    private String version;

    @NotNull
    private Boolean isActive;

    private Instant createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CatalogsDTO)) {
            return false;
        }

        CatalogsDTO catalogsDTO = (CatalogsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, catalogsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CatalogsDTO{" +
            "id='" + getId() + "'" +
            ", externalId='" + getExternalId() + "'" +
            ", name='" + getName() + "'" +
            ", version='" + getVersion() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            "}";
    }
}
