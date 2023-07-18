package biz.daich.tambour.shlicht.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link biz.daich.tambour.shlicht.domain.Images} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ImagesDTO implements Serializable {

    @NotNull
    @Size(max = 36)
    private String id;

    @Lob
    private byte[] pngContent;

    private String pngContentContentType;
    private Integer rawWidth;

    private Integer rawHeight;

    @Lob
    private String rawFormat;

    @Lob
    private byte[] rawContent;

    private String rawContentContentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getPngContent() {
        return pngContent;
    }

    public void setPngContent(byte[] pngContent) {
        this.pngContent = pngContent;
    }

    public String getPngContentContentType() {
        return pngContentContentType;
    }

    public void setPngContentContentType(String pngContentContentType) {
        this.pngContentContentType = pngContentContentType;
    }

    public Integer getRawWidth() {
        return rawWidth;
    }

    public void setRawWidth(Integer rawWidth) {
        this.rawWidth = rawWidth;
    }

    public Integer getRawHeight() {
        return rawHeight;
    }

    public void setRawHeight(Integer rawHeight) {
        this.rawHeight = rawHeight;
    }

    public String getRawFormat() {
        return rawFormat;
    }

    public void setRawFormat(String rawFormat) {
        this.rawFormat = rawFormat;
    }

    public byte[] getRawContent() {
        return rawContent;
    }

    public void setRawContent(byte[] rawContent) {
        this.rawContent = rawContent;
    }

    public String getRawContentContentType() {
        return rawContentContentType;
    }

    public void setRawContentContentType(String rawContentContentType) {
        this.rawContentContentType = rawContentContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImagesDTO)) {
            return false;
        }

        ImagesDTO imagesDTO = (ImagesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, imagesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImagesDTO{" +
            "id='" + getId() + "'" +
            ", pngContent='" + getPngContent() + "'" +
            ", rawWidth=" + getRawWidth() +
            ", rawHeight=" + getRawHeight() +
            ", rawFormat='" + getRawFormat() + "'" +
            ", rawContent='" + getRawContent() + "'" +
            "}";
    }
}
