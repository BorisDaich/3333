package biz.daich.tambour.shlicht.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Images.
 */
@Entity
@Table(name = "images")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Images implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Lob
    @Column(name = "png_content")
    private byte[] pngContent;

    @Column(name = "png_content_content_type")
    private String pngContentContentType;

    @Column(name = "raw_width")
    private Integer rawWidth;

    @Column(name = "raw_height")
    private Integer rawHeight;

    @Lob
    @Column(name = "raw_format")
    private String rawFormat;

    @Lob
    @Column(name = "raw_content")
    private byte[] rawContent;

    @Column(name = "raw_content_content_type")
    private String rawContentContentType;

    @Transient
    private boolean isPersisted;

    @JsonIgnoreProperties(value = { "image", "productionBatch" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "image")
    private Scans scans;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Images id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getPngContent() {
        return this.pngContent;
    }

    public Images pngContent(byte[] pngContent) {
        this.setPngContent(pngContent);
        return this;
    }

    public void setPngContent(byte[] pngContent) {
        this.pngContent = pngContent;
    }

    public String getPngContentContentType() {
        return this.pngContentContentType;
    }

    public Images pngContentContentType(String pngContentContentType) {
        this.pngContentContentType = pngContentContentType;
        return this;
    }

    public void setPngContentContentType(String pngContentContentType) {
        this.pngContentContentType = pngContentContentType;
    }

    public Integer getRawWidth() {
        return this.rawWidth;
    }

    public Images rawWidth(Integer rawWidth) {
        this.setRawWidth(rawWidth);
        return this;
    }

    public void setRawWidth(Integer rawWidth) {
        this.rawWidth = rawWidth;
    }

    public Integer getRawHeight() {
        return this.rawHeight;
    }

    public Images rawHeight(Integer rawHeight) {
        this.setRawHeight(rawHeight);
        return this;
    }

    public void setRawHeight(Integer rawHeight) {
        this.rawHeight = rawHeight;
    }

    public String getRawFormat() {
        return this.rawFormat;
    }

    public Images rawFormat(String rawFormat) {
        this.setRawFormat(rawFormat);
        return this;
    }

    public void setRawFormat(String rawFormat) {
        this.rawFormat = rawFormat;
    }

    public byte[] getRawContent() {
        return this.rawContent;
    }

    public Images rawContent(byte[] rawContent) {
        this.setRawContent(rawContent);
        return this;
    }

    public void setRawContent(byte[] rawContent) {
        this.rawContent = rawContent;
    }

    public String getRawContentContentType() {
        return this.rawContentContentType;
    }

    public Images rawContentContentType(String rawContentContentType) {
        this.rawContentContentType = rawContentContentType;
        return this;
    }

    public void setRawContentContentType(String rawContentContentType) {
        this.rawContentContentType = rawContentContentType;
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

    public Images setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Scans getScans() {
        return this.scans;
    }

    public void setScans(Scans scans) {
        if (this.scans != null) {
            this.scans.setImage(null);
        }
        if (scans != null) {
            scans.setImage(this);
        }
        this.scans = scans;
    }

    public Images scans(Scans scans) {
        this.setScans(scans);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Images)) {
            return false;
        }
        return id != null && id.equals(((Images) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Images{" +
            "id=" + getId() +
            ", pngContent='" + getPngContent() + "'" +
            ", pngContentContentType='" + getPngContentContentType() + "'" +
            ", rawWidth=" + getRawWidth() +
            ", rawHeight=" + getRawHeight() +
            ", rawFormat='" + getRawFormat() + "'" +
            ", rawContent='" + getRawContent() + "'" +
            ", rawContentContentType='" + getRawContentContentType() + "'" +
            "}";
    }
}
