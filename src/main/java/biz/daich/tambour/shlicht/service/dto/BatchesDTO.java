package biz.daich.tambour.shlicht.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link biz.daich.tambour.shlicht.domain.Batches} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BatchesDTO implements Serializable {

    @NotNull
    @Size(max = 36)
    private String id;

    @NotNull
    @Size(max = 255)
    private String poName;

    @NotNull
    private Integer sequenceInPo;

    @Lob
    private String scannerId;

    @Size(max = 36)
    private String previousProductionBatchId;

    @NotNull
    @Size(max = 16)
    private String state;

    @NotNull
    private Double inspectionSequence;

    @NotNull
    private Integer orderedQuantity;

    @NotNull
    private Integer producingQuantity;

    @NotNull
    private Integer totalProducingQuantity;

    @NotNull
    private Integer remainingQuantity;

    @NotNull
    private Integer totalremainingQuantity;

    @NotNull
    private Integer inspectedQuantity;

    @NotNull
    private Integer totalInspectedQuantity;

    @NotNull
    private Integer failedQuantity;

    @NotNull
    private Integer totalFailedQuantity;

    @Size(max = 36)
    private String colorId;

    @Lob
    private String colorCode;

    @Lob
    private String colorName;

    private Long colorBasematerial;

    private Double colorLabL;

    private Double colorLabA;

    private Double colorLabB;

    @NotNull
    private Double colorA;

    @NotNull
    private Double colorB;

    @NotNull
    private Double colorC;

    @NotNull
    private Double colorD;

    @NotNull
    private Double colorE;

    @NotNull
    private Double colorF;

    @NotNull
    private Double colorG;

    @NotNull
    private Double colorH;

    @NotNull
    private Double colorI;

    @NotNull
    private Double colorJ;

    @NotNull
    private Double colorK;

    @NotNull
    private Double colorL;

    @NotNull
    private Double colorM;

    @NotNull
    private Double colorN;

    @NotNull
    private Double colorO;

    @NotNull
    private Double colorP;

    @NotNull
    private Double colorQ;

    @NotNull
    private Double colorR;

    @NotNull
    private Double colorS;

    @NotNull
    private Double colorT;

    @NotNull
    private Double colorU;

    @NotNull
    private Double colorV;

    @NotNull
    private Double colorW;

    @NotNull
    private Double colorX;

    @NotNull
    private Double colorY;

    @NotNull
    private Double colorZ;

    @Size(max = 36)
    private String catalogId;

    @Lob
    private String catalogExternalid;

    @Lob
    private String catalogName;

    @Lob
    private String catalogVersion;

    @NotNull
    private Boolean catalogIsactive;

    private Instant catalogCreatedtime;

    @NotNull
    private Long baseMaterialId;

    @NotNull
    private Instant orderedTime;

    private Instant startedTime;

    private Instant modifiedTime;

    private Instant suspendedTime;

    private Instant finishedTime;

    @Lob
    private String original;

    private CatalogsDTO colorCatalog;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoName() {
        return poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public Integer getSequenceInPo() {
        return sequenceInPo;
    }

    public void setSequenceInPo(Integer sequenceInPo) {
        this.sequenceInPo = sequenceInPo;
    }

    public String getScannerId() {
        return scannerId;
    }

    public void setScannerId(String scannerId) {
        this.scannerId = scannerId;
    }

    public String getPreviousProductionBatchId() {
        return previousProductionBatchId;
    }

    public void setPreviousProductionBatchId(String previousProductionBatchId) {
        this.previousProductionBatchId = previousProductionBatchId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getInspectionSequence() {
        return inspectionSequence;
    }

    public void setInspectionSequence(Double inspectionSequence) {
        this.inspectionSequence = inspectionSequence;
    }

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public Integer getProducingQuantity() {
        return producingQuantity;
    }

    public void setProducingQuantity(Integer producingQuantity) {
        this.producingQuantity = producingQuantity;
    }

    public Integer getTotalProducingQuantity() {
        return totalProducingQuantity;
    }

    public void setTotalProducingQuantity(Integer totalProducingQuantity) {
        this.totalProducingQuantity = totalProducingQuantity;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Integer getTotalremainingQuantity() {
        return totalremainingQuantity;
    }

    public void setTotalremainingQuantity(Integer totalremainingQuantity) {
        this.totalremainingQuantity = totalremainingQuantity;
    }

    public Integer getInspectedQuantity() {
        return inspectedQuantity;
    }

    public void setInspectedQuantity(Integer inspectedQuantity) {
        this.inspectedQuantity = inspectedQuantity;
    }

    public Integer getTotalInspectedQuantity() {
        return totalInspectedQuantity;
    }

    public void setTotalInspectedQuantity(Integer totalInspectedQuantity) {
        this.totalInspectedQuantity = totalInspectedQuantity;
    }

    public Integer getFailedQuantity() {
        return failedQuantity;
    }

    public void setFailedQuantity(Integer failedQuantity) {
        this.failedQuantity = failedQuantity;
    }

    public Integer getTotalFailedQuantity() {
        return totalFailedQuantity;
    }

    public void setTotalFailedQuantity(Integer totalFailedQuantity) {
        this.totalFailedQuantity = totalFailedQuantity;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Long getColorBasematerial() {
        return colorBasematerial;
    }

    public void setColorBasematerial(Long colorBasematerial) {
        this.colorBasematerial = colorBasematerial;
    }

    public Double getColorLabL() {
        return colorLabL;
    }

    public void setColorLabL(Double colorLabL) {
        this.colorLabL = colorLabL;
    }

    public Double getColorLabA() {
        return colorLabA;
    }

    public void setColorLabA(Double colorLabA) {
        this.colorLabA = colorLabA;
    }

    public Double getColorLabB() {
        return colorLabB;
    }

    public void setColorLabB(Double colorLabB) {
        this.colorLabB = colorLabB;
    }

    public Double getColorA() {
        return colorA;
    }

    public void setColorA(Double colorA) {
        this.colorA = colorA;
    }

    public Double getColorB() {
        return colorB;
    }

    public void setColorB(Double colorB) {
        this.colorB = colorB;
    }

    public Double getColorC() {
        return colorC;
    }

    public void setColorC(Double colorC) {
        this.colorC = colorC;
    }

    public Double getColorD() {
        return colorD;
    }

    public void setColorD(Double colorD) {
        this.colorD = colorD;
    }

    public Double getColorE() {
        return colorE;
    }

    public void setColorE(Double colorE) {
        this.colorE = colorE;
    }

    public Double getColorF() {
        return colorF;
    }

    public void setColorF(Double colorF) {
        this.colorF = colorF;
    }

    public Double getColorG() {
        return colorG;
    }

    public void setColorG(Double colorG) {
        this.colorG = colorG;
    }

    public Double getColorH() {
        return colorH;
    }

    public void setColorH(Double colorH) {
        this.colorH = colorH;
    }

    public Double getColorI() {
        return colorI;
    }

    public void setColorI(Double colorI) {
        this.colorI = colorI;
    }

    public Double getColorJ() {
        return colorJ;
    }

    public void setColorJ(Double colorJ) {
        this.colorJ = colorJ;
    }

    public Double getColorK() {
        return colorK;
    }

    public void setColorK(Double colorK) {
        this.colorK = colorK;
    }

    public Double getColorL() {
        return colorL;
    }

    public void setColorL(Double colorL) {
        this.colorL = colorL;
    }

    public Double getColorM() {
        return colorM;
    }

    public void setColorM(Double colorM) {
        this.colorM = colorM;
    }

    public Double getColorN() {
        return colorN;
    }

    public void setColorN(Double colorN) {
        this.colorN = colorN;
    }

    public Double getColorO() {
        return colorO;
    }

    public void setColorO(Double colorO) {
        this.colorO = colorO;
    }

    public Double getColorP() {
        return colorP;
    }

    public void setColorP(Double colorP) {
        this.colorP = colorP;
    }

    public Double getColorQ() {
        return colorQ;
    }

    public void setColorQ(Double colorQ) {
        this.colorQ = colorQ;
    }

    public Double getColorR() {
        return colorR;
    }

    public void setColorR(Double colorR) {
        this.colorR = colorR;
    }

    public Double getColorS() {
        return colorS;
    }

    public void setColorS(Double colorS) {
        this.colorS = colorS;
    }

    public Double getColorT() {
        return colorT;
    }

    public void setColorT(Double colorT) {
        this.colorT = colorT;
    }

    public Double getColorU() {
        return colorU;
    }

    public void setColorU(Double colorU) {
        this.colorU = colorU;
    }

    public Double getColorV() {
        return colorV;
    }

    public void setColorV(Double colorV) {
        this.colorV = colorV;
    }

    public Double getColorW() {
        return colorW;
    }

    public void setColorW(Double colorW) {
        this.colorW = colorW;
    }

    public Double getColorX() {
        return colorX;
    }

    public void setColorX(Double colorX) {
        this.colorX = colorX;
    }

    public Double getColorY() {
        return colorY;
    }

    public void setColorY(Double colorY) {
        this.colorY = colorY;
    }

    public Double getColorZ() {
        return colorZ;
    }

    public void setColorZ(Double colorZ) {
        this.colorZ = colorZ;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogExternalid() {
        return catalogExternalid;
    }

    public void setCatalogExternalid(String catalogExternalid) {
        this.catalogExternalid = catalogExternalid;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogVersion() {
        return catalogVersion;
    }

    public void setCatalogVersion(String catalogVersion) {
        this.catalogVersion = catalogVersion;
    }

    public Boolean getCatalogIsactive() {
        return catalogIsactive;
    }

    public void setCatalogIsactive(Boolean catalogIsactive) {
        this.catalogIsactive = catalogIsactive;
    }

    public Instant getCatalogCreatedtime() {
        return catalogCreatedtime;
    }

    public void setCatalogCreatedtime(Instant catalogCreatedtime) {
        this.catalogCreatedtime = catalogCreatedtime;
    }

    public Long getBaseMaterialId() {
        return baseMaterialId;
    }

    public void setBaseMaterialId(Long baseMaterialId) {
        this.baseMaterialId = baseMaterialId;
    }

    public Instant getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(Instant orderedTime) {
        this.orderedTime = orderedTime;
    }

    public Instant getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(Instant startedTime) {
        this.startedTime = startedTime;
    }

    public Instant getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Instant getSuspendedTime() {
        return suspendedTime;
    }

    public void setSuspendedTime(Instant suspendedTime) {
        this.suspendedTime = suspendedTime;
    }

    public Instant getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Instant finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public CatalogsDTO getColorCatalog() {
        return colorCatalog;
    }

    public void setColorCatalog(CatalogsDTO colorCatalog) {
        this.colorCatalog = colorCatalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BatchesDTO)) {
            return false;
        }

        BatchesDTO batchesDTO = (BatchesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, batchesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BatchesDTO{" +
            "id='" + getId() + "'" +
            ", poName='" + getPoName() + "'" +
            ", sequenceInPo=" + getSequenceInPo() +
            ", scannerId='" + getScannerId() + "'" +
            ", previousProductionBatchId='" + getPreviousProductionBatchId() + "'" +
            ", state='" + getState() + "'" +
            ", inspectionSequence=" + getInspectionSequence() +
            ", orderedQuantity=" + getOrderedQuantity() +
            ", producingQuantity=" + getProducingQuantity() +
            ", totalProducingQuantity=" + getTotalProducingQuantity() +
            ", remainingQuantity=" + getRemainingQuantity() +
            ", totalremainingQuantity=" + getTotalremainingQuantity() +
            ", inspectedQuantity=" + getInspectedQuantity() +
            ", totalInspectedQuantity=" + getTotalInspectedQuantity() +
            ", failedQuantity=" + getFailedQuantity() +
            ", totalFailedQuantity=" + getTotalFailedQuantity() +
            ", colorId='" + getColorId() + "'" +
            ", colorCode='" + getColorCode() + "'" +
            ", colorName='" + getColorName() + "'" +
            ", colorBasematerial=" + getColorBasematerial() +
            ", colorLabL=" + getColorLabL() +
            ", colorLabA=" + getColorLabA() +
            ", colorLabB=" + getColorLabB() +
            ", colorA=" + getColorA() +
            ", colorB=" + getColorB() +
            ", colorC=" + getColorC() +
            ", colorD=" + getColorD() +
            ", colorE=" + getColorE() +
            ", colorF=" + getColorF() +
            ", colorG=" + getColorG() +
            ", colorH=" + getColorH() +
            ", colorI=" + getColorI() +
            ", colorJ=" + getColorJ() +
            ", colorK=" + getColorK() +
            ", colorL=" + getColorL() +
            ", colorM=" + getColorM() +
            ", colorN=" + getColorN() +
            ", colorO=" + getColorO() +
            ", colorP=" + getColorP() +
            ", colorQ=" + getColorQ() +
            ", colorR=" + getColorR() +
            ", colorS=" + getColorS() +
            ", colorT=" + getColorT() +
            ", colorU=" + getColorU() +
            ", colorV=" + getColorV() +
            ", colorW=" + getColorW() +
            ", colorX=" + getColorX() +
            ", colorY=" + getColorY() +
            ", colorZ=" + getColorZ() +
            ", catalogId='" + getCatalogId() + "'" +
            ", catalogExternalid='" + getCatalogExternalid() + "'" +
            ", catalogName='" + getCatalogName() + "'" +
            ", catalogVersion='" + getCatalogVersion() + "'" +
            ", catalogIsactive='" + getCatalogIsactive() + "'" +
            ", catalogCreatedtime='" + getCatalogCreatedtime() + "'" +
            ", baseMaterialId=" + getBaseMaterialId() +
            ", orderedTime='" + getOrderedTime() + "'" +
            ", startedTime='" + getStartedTime() + "'" +
            ", modifiedTime='" + getModifiedTime() + "'" +
            ", suspendedTime='" + getSuspendedTime() + "'" +
            ", finishedTime='" + getFinishedTime() + "'" +
            ", original='" + getOriginal() + "'" +
            ", colorCatalog=" + getColorCatalog() +
            "}";
    }
}
