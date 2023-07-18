package biz.daich.tambour.shlicht.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Catalogs.
 */
@Entity
@Table(name = "catalogs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Catalogs implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Lob
    @Column(name = "external_id", nullable = false)
    private String externalId;

    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "version")
    private String version;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_time")
    private Instant createdTime;

    @Transient
    private boolean isPersisted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalog")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "catalog" }, allowSetters = true)
    private Set<Catalogcolors> catalogColors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "colorCatalog")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "colorCatalog", "ids" }, allowSetters = true)
    private Set<Batches> ids = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Catalogs id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public Catalogs externalId(String externalId) {
        this.setExternalId(externalId);
        return this;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return this.name;
    }

    public Catalogs name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return this.version;
    }

    public Catalogs version(String version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Catalogs isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getCreatedTime() {
        return this.createdTime;
    }

    public Catalogs createdTime(Instant createdTime) {
        this.setCreatedTime(createdTime);
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
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

    public Catalogs setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Set<Catalogcolors> getCatalogColors() {
        return this.catalogColors;
    }

    public void setCatalogColors(Set<Catalogcolors> catalogcolors) {
        if (this.catalogColors != null) {
            this.catalogColors.forEach(i -> i.setCatalog(null));
        }
        if (catalogcolors != null) {
            catalogcolors.forEach(i -> i.setCatalog(this));
        }
        this.catalogColors = catalogcolors;
    }

    public Catalogs catalogColors(Set<Catalogcolors> catalogcolors) {
        this.setCatalogColors(catalogcolors);
        return this;
    }

    public Catalogs addCatalogColors(Catalogcolors catalogcolors) {
        this.catalogColors.add(catalogcolors);
        catalogcolors.setCatalog(this);
        return this;
    }

    public Catalogs removeCatalogColors(Catalogcolors catalogcolors) {
        this.catalogColors.remove(catalogcolors);
        catalogcolors.setCatalog(null);
        return this;
    }

    public Set<Batches> getIds() {
        return this.ids;
    }

    public void setIds(Set<Batches> batches) {
        if (this.ids != null) {
            this.ids.forEach(i -> i.setColorCatalog(null));
        }
        if (batches != null) {
            batches.forEach(i -> i.setColorCatalog(this));
        }
        this.ids = batches;
    }

    public Catalogs ids(Set<Batches> batches) {
        this.setIds(batches);
        return this;
    }

    public Catalogs addId(Batches batches) {
        this.ids.add(batches);
        batches.setColorCatalog(this);
        return this;
    }

    public Catalogs removeId(Batches batches) {
        this.ids.remove(batches);
        batches.setColorCatalog(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Catalogs)) {
            return false;
        }
        return id != null && id.equals(((Catalogs) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Catalogs{" +
            "id=" + getId() +
            ", externalId='" + getExternalId() + "'" +
            ", name='" + getName() + "'" +
            ", version='" + getVersion() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            "}";
    }
}
