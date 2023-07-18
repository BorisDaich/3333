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
 * A Batches.
 */
@Entity
@Table(name = "batches")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Batches implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @NotNull
    @Size(max = 255)
    @Column(name = "po_name", length = 255, nullable = false)
    private String poName;

    @NotNull
    @Column(name = "sequence_in_po", nullable = false)
    private Integer sequenceInPo;

    @Lob
    @Column(name = "scanner_id", nullable = false)
    private String scannerId;

    @Size(max = 36)
    @Column(name = "previous_production_batch_id", length = 36)
    private String previousProductionBatchId;

    @NotNull
    @Size(max = 16)
    @Column(name = "state", length = 16, nullable = false)
    private String state;

    @NotNull
    @Column(name = "inspection_sequence", nullable = false, unique = true)
    private Double inspectionSequence;

    @NotNull
    @Column(name = "ordered_quantity", nullable = false)
    private Integer orderedQuantity;

    @NotNull
    @Column(name = "producing_quantity", nullable = false)
    private Integer producingQuantity;

    @NotNull
    @Column(name = "total_producing_quantity", nullable = false)
    private Integer totalProducingQuantity;

    @NotNull
    @Column(name = "remaining_quantity", nullable = false)
    private Integer remainingQuantity;

    @NotNull
    @Column(name = "totalremaining_quantity", nullable = false)
    private Integer totalremainingQuantity;

    @NotNull
    @Column(name = "inspected_quantity", nullable = false)
    private Integer inspectedQuantity;

    @NotNull
    @Column(name = "total_inspected_quantity", nullable = false)
    private Integer totalInspectedQuantity;

    @NotNull
    @Column(name = "failed_quantity", nullable = false)
    private Integer failedQuantity;

    @NotNull
    @Column(name = "total_failed_quantity", nullable = false)
    private Integer totalFailedQuantity;

    @Size(max = 36)
    @Column(name = "color_id", length = 36)
    private String colorId;

    @Lob
    @Column(name = "color_code", nullable = false)
    private String colorCode;

    @Lob
    @Column(name = "color_name", nullable = false)
    private String colorName;

    @Column(name = "color_basematerial")
    private Long colorBasematerial;

    @Column(name = "color_lab_l")
    private Double colorLabL;

    @Column(name = "color_lab_a")
    private Double colorLabA;

    @Column(name = "color_lab_b")
    private Double colorLabB;

    @NotNull
    @Column(name = "color_a", nullable = false)
    private Double colorA;

    @NotNull
    @Column(name = "color_b", nullable = false)
    private Double colorB;

    @NotNull
    @Column(name = "color_c", nullable = false)
    private Double colorC;

    @NotNull
    @Column(name = "color_d", nullable = false)
    private Double colorD;

    @NotNull
    @Column(name = "color_e", nullable = false)
    private Double colorE;

    @NotNull
    @Column(name = "color_f", nullable = false)
    private Double colorF;

    @NotNull
    @Column(name = "color_g", nullable = false)
    private Double colorG;

    @NotNull
    @Column(name = "color_h", nullable = false)
    private Double colorH;

    @NotNull
    @Column(name = "color_i", nullable = false)
    private Double colorI;

    @NotNull
    @Column(name = "color_j", nullable = false)
    private Double colorJ;

    @NotNull
    @Column(name = "color_k", nullable = false)
    private Double colorK;

    @NotNull
    @Column(name = "color_l", nullable = false)
    private Double colorL;

    @NotNull
    @Column(name = "color_m", nullable = false)
    private Double colorM;

    @NotNull
    @Column(name = "color_n", nullable = false)
    private Double colorN;

    @NotNull
    @Column(name = "color_o", nullable = false)
    private Double colorO;

    @NotNull
    @Column(name = "color_p", nullable = false)
    private Double colorP;

    @NotNull
    @Column(name = "color_q", nullable = false)
    private Double colorQ;

    @NotNull
    @Column(name = "color_r", nullable = false)
    private Double colorR;

    @NotNull
    @Column(name = "color_s", nullable = false)
    private Double colorS;

    @NotNull
    @Column(name = "color_t", nullable = false)
    private Double colorT;

    @NotNull
    @Column(name = "color_u", nullable = false)
    private Double colorU;

    @NotNull
    @Column(name = "color_v", nullable = false)
    private Double colorV;

    @NotNull
    @Column(name = "color_w", nullable = false)
    private Double colorW;

    @NotNull
    @Column(name = "color_x", nullable = false)
    private Double colorX;

    @NotNull
    @Column(name = "color_y", nullable = false)
    private Double colorY;

    @NotNull
    @Column(name = "color_z", nullable = false)
    private Double colorZ;

    @Size(max = 36)
    @Column(name = "catalog_id", length = 36)
    private String catalogId;

    @Lob
    @Column(name = "catalog_externalid", nullable = false)
    private String catalogExternalid;

    @Lob
    @Column(name = "catalog_name", nullable = false)
    private String catalogName;

    @Lob
    @Column(name = "catalog_version")
    private String catalogVersion;

    @NotNull
    @Column(name = "catalog_isactive", nullable = false)
    private Boolean catalogIsactive;

    @Column(name = "catalog_createdtime")
    private Instant catalogCreatedtime;

    @NotNull
    @Column(name = "base_material_id", nullable = false)
    private Long baseMaterialId;

    @NotNull
    @Column(name = "ordered_time", nullable = false)
    private Instant orderedTime;

    @Column(name = "started_time")
    private Instant startedTime;

    @Column(name = "modified_time")
    private Instant modifiedTime;

    @Column(name = "suspended_time")
    private Instant suspendedTime;

    @Column(name = "finished_time")
    private Instant finishedTime;

    @Lob
    @Column(name = "original")
    private String original;

    @Transient
    private boolean isPersisted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "catalogColors", "ids" }, allowSetters = true)
    private Catalogs colorCatalog;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productionBatch")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "image", "productionBatch" }, allowSetters = true)
    private Set<Scans> ids = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Batches id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoName() {
        return this.poName;
    }

    public Batches poName(String poName) {
        this.setPoName(poName);
        return this;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public Integer getSequenceInPo() {
        return this.sequenceInPo;
    }

    public Batches sequenceInPo(Integer sequenceInPo) {
        this.setSequenceInPo(sequenceInPo);
        return this;
    }

    public void setSequenceInPo(Integer sequenceInPo) {
        this.sequenceInPo = sequenceInPo;
    }

    public String getScannerId() {
        return this.scannerId;
    }

    public Batches scannerId(String scannerId) {
        this.setScannerId(scannerId);
        return this;
    }

    public void setScannerId(String scannerId) {
        this.scannerId = scannerId;
    }

    public String getPreviousProductionBatchId() {
        return this.previousProductionBatchId;
    }

    public Batches previousProductionBatchId(String previousProductionBatchId) {
        this.setPreviousProductionBatchId(previousProductionBatchId);
        return this;
    }

    public void setPreviousProductionBatchId(String previousProductionBatchId) {
        this.previousProductionBatchId = previousProductionBatchId;
    }

    public String getState() {
        return this.state;
    }

    public Batches state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getInspectionSequence() {
        return this.inspectionSequence;
    }

    public Batches inspectionSequence(Double inspectionSequence) {
        this.setInspectionSequence(inspectionSequence);
        return this;
    }

    public void setInspectionSequence(Double inspectionSequence) {
        this.inspectionSequence = inspectionSequence;
    }

    public Integer getOrderedQuantity() {
        return this.orderedQuantity;
    }

    public Batches orderedQuantity(Integer orderedQuantity) {
        this.setOrderedQuantity(orderedQuantity);
        return this;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public Integer getProducingQuantity() {
        return this.producingQuantity;
    }

    public Batches producingQuantity(Integer producingQuantity) {
        this.setProducingQuantity(producingQuantity);
        return this;
    }

    public void setProducingQuantity(Integer producingQuantity) {
        this.producingQuantity = producingQuantity;
    }

    public Integer getTotalProducingQuantity() {
        return this.totalProducingQuantity;
    }

    public Batches totalProducingQuantity(Integer totalProducingQuantity) {
        this.setTotalProducingQuantity(totalProducingQuantity);
        return this;
    }

    public void setTotalProducingQuantity(Integer totalProducingQuantity) {
        this.totalProducingQuantity = totalProducingQuantity;
    }

    public Integer getRemainingQuantity() {
        return this.remainingQuantity;
    }

    public Batches remainingQuantity(Integer remainingQuantity) {
        this.setRemainingQuantity(remainingQuantity);
        return this;
    }

    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Integer getTotalremainingQuantity() {
        return this.totalremainingQuantity;
    }

    public Batches totalremainingQuantity(Integer totalremainingQuantity) {
        this.setTotalremainingQuantity(totalremainingQuantity);
        return this;
    }

    public void setTotalremainingQuantity(Integer totalremainingQuantity) {
        this.totalremainingQuantity = totalremainingQuantity;
    }

    public Integer getInspectedQuantity() {
        return this.inspectedQuantity;
    }

    public Batches inspectedQuantity(Integer inspectedQuantity) {
        this.setInspectedQuantity(inspectedQuantity);
        return this;
    }

    public void setInspectedQuantity(Integer inspectedQuantity) {
        this.inspectedQuantity = inspectedQuantity;
    }

    public Integer getTotalInspectedQuantity() {
        return this.totalInspectedQuantity;
    }

    public Batches totalInspectedQuantity(Integer totalInspectedQuantity) {
        this.setTotalInspectedQuantity(totalInspectedQuantity);
        return this;
    }

    public void setTotalInspectedQuantity(Integer totalInspectedQuantity) {
        this.totalInspectedQuantity = totalInspectedQuantity;
    }

    public Integer getFailedQuantity() {
        return this.failedQuantity;
    }

    public Batches failedQuantity(Integer failedQuantity) {
        this.setFailedQuantity(failedQuantity);
        return this;
    }

    public void setFailedQuantity(Integer failedQuantity) {
        this.failedQuantity = failedQuantity;
    }

    public Integer getTotalFailedQuantity() {
        return this.totalFailedQuantity;
    }

    public Batches totalFailedQuantity(Integer totalFailedQuantity) {
        this.setTotalFailedQuantity(totalFailedQuantity);
        return this;
    }

    public void setTotalFailedQuantity(Integer totalFailedQuantity) {
        this.totalFailedQuantity = totalFailedQuantity;
    }

    public String getColorId() {
        return this.colorId;
    }

    public Batches colorId(String colorId) {
        this.setColorId(colorId);
        return this;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorCode() {
        return this.colorCode;
    }

    public Batches colorCode(String colorCode) {
        this.setColorCode(colorCode);
        return this;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return this.colorName;
    }

    public Batches colorName(String colorName) {
        this.setColorName(colorName);
        return this;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Long getColorBasematerial() {
        return this.colorBasematerial;
    }

    public Batches colorBasematerial(Long colorBasematerial) {
        this.setColorBasematerial(colorBasematerial);
        return this;
    }

    public void setColorBasematerial(Long colorBasematerial) {
        this.colorBasematerial = colorBasematerial;
    }

    public Double getColorLabL() {
        return this.colorLabL;
    }

    public Batches colorLabL(Double colorLabL) {
        this.setColorLabL(colorLabL);
        return this;
    }

    public void setColorLabL(Double colorLabL) {
        this.colorLabL = colorLabL;
    }

    public Double getColorLabA() {
        return this.colorLabA;
    }

    public Batches colorLabA(Double colorLabA) {
        this.setColorLabA(colorLabA);
        return this;
    }

    public void setColorLabA(Double colorLabA) {
        this.colorLabA = colorLabA;
    }

    public Double getColorLabB() {
        return this.colorLabB;
    }

    public Batches colorLabB(Double colorLabB) {
        this.setColorLabB(colorLabB);
        return this;
    }

    public void setColorLabB(Double colorLabB) {
        this.colorLabB = colorLabB;
    }

    public Double getColorA() {
        return this.colorA;
    }

    public Batches colorA(Double colorA) {
        this.setColorA(colorA);
        return this;
    }

    public void setColorA(Double colorA) {
        this.colorA = colorA;
    }

    public Double getColorB() {
        return this.colorB;
    }

    public Batches colorB(Double colorB) {
        this.setColorB(colorB);
        return this;
    }

    public void setColorB(Double colorB) {
        this.colorB = colorB;
    }

    public Double getColorC() {
        return this.colorC;
    }

    public Batches colorC(Double colorC) {
        this.setColorC(colorC);
        return this;
    }

    public void setColorC(Double colorC) {
        this.colorC = colorC;
    }

    public Double getColorD() {
        return this.colorD;
    }

    public Batches colorD(Double colorD) {
        this.setColorD(colorD);
        return this;
    }

    public void setColorD(Double colorD) {
        this.colorD = colorD;
    }

    public Double getColorE() {
        return this.colorE;
    }

    public Batches colorE(Double colorE) {
        this.setColorE(colorE);
        return this;
    }

    public void setColorE(Double colorE) {
        this.colorE = colorE;
    }

    public Double getColorF() {
        return this.colorF;
    }

    public Batches colorF(Double colorF) {
        this.setColorF(colorF);
        return this;
    }

    public void setColorF(Double colorF) {
        this.colorF = colorF;
    }

    public Double getColorG() {
        return this.colorG;
    }

    public Batches colorG(Double colorG) {
        this.setColorG(colorG);
        return this;
    }

    public void setColorG(Double colorG) {
        this.colorG = colorG;
    }

    public Double getColorH() {
        return this.colorH;
    }

    public Batches colorH(Double colorH) {
        this.setColorH(colorH);
        return this;
    }

    public void setColorH(Double colorH) {
        this.colorH = colorH;
    }

    public Double getColorI() {
        return this.colorI;
    }

    public Batches colorI(Double colorI) {
        this.setColorI(colorI);
        return this;
    }

    public void setColorI(Double colorI) {
        this.colorI = colorI;
    }

    public Double getColorJ() {
        return this.colorJ;
    }

    public Batches colorJ(Double colorJ) {
        this.setColorJ(colorJ);
        return this;
    }

    public void setColorJ(Double colorJ) {
        this.colorJ = colorJ;
    }

    public Double getColorK() {
        return this.colorK;
    }

    public Batches colorK(Double colorK) {
        this.setColorK(colorK);
        return this;
    }

    public void setColorK(Double colorK) {
        this.colorK = colorK;
    }

    public Double getColorL() {
        return this.colorL;
    }

    public Batches colorL(Double colorL) {
        this.setColorL(colorL);
        return this;
    }

    public void setColorL(Double colorL) {
        this.colorL = colorL;
    }

    public Double getColorM() {
        return this.colorM;
    }

    public Batches colorM(Double colorM) {
        this.setColorM(colorM);
        return this;
    }

    public void setColorM(Double colorM) {
        this.colorM = colorM;
    }

    public Double getColorN() {
        return this.colorN;
    }

    public Batches colorN(Double colorN) {
        this.setColorN(colorN);
        return this;
    }

    public void setColorN(Double colorN) {
        this.colorN = colorN;
    }

    public Double getColorO() {
        return this.colorO;
    }

    public Batches colorO(Double colorO) {
        this.setColorO(colorO);
        return this;
    }

    public void setColorO(Double colorO) {
        this.colorO = colorO;
    }

    public Double getColorP() {
        return this.colorP;
    }

    public Batches colorP(Double colorP) {
        this.setColorP(colorP);
        return this;
    }

    public void setColorP(Double colorP) {
        this.colorP = colorP;
    }

    public Double getColorQ() {
        return this.colorQ;
    }

    public Batches colorQ(Double colorQ) {
        this.setColorQ(colorQ);
        return this;
    }

    public void setColorQ(Double colorQ) {
        this.colorQ = colorQ;
    }

    public Double getColorR() {
        return this.colorR;
    }

    public Batches colorR(Double colorR) {
        this.setColorR(colorR);
        return this;
    }

    public void setColorR(Double colorR) {
        this.colorR = colorR;
    }

    public Double getColorS() {
        return this.colorS;
    }

    public Batches colorS(Double colorS) {
        this.setColorS(colorS);
        return this;
    }

    public void setColorS(Double colorS) {
        this.colorS = colorS;
    }

    public Double getColorT() {
        return this.colorT;
    }

    public Batches colorT(Double colorT) {
        this.setColorT(colorT);
        return this;
    }

    public void setColorT(Double colorT) {
        this.colorT = colorT;
    }

    public Double getColorU() {
        return this.colorU;
    }

    public Batches colorU(Double colorU) {
        this.setColorU(colorU);
        return this;
    }

    public void setColorU(Double colorU) {
        this.colorU = colorU;
    }

    public Double getColorV() {
        return this.colorV;
    }

    public Batches colorV(Double colorV) {
        this.setColorV(colorV);
        return this;
    }

    public void setColorV(Double colorV) {
        this.colorV = colorV;
    }

    public Double getColorW() {
        return this.colorW;
    }

    public Batches colorW(Double colorW) {
        this.setColorW(colorW);
        return this;
    }

    public void setColorW(Double colorW) {
        this.colorW = colorW;
    }

    public Double getColorX() {
        return this.colorX;
    }

    public Batches colorX(Double colorX) {
        this.setColorX(colorX);
        return this;
    }

    public void setColorX(Double colorX) {
        this.colorX = colorX;
    }

    public Double getColorY() {
        return this.colorY;
    }

    public Batches colorY(Double colorY) {
        this.setColorY(colorY);
        return this;
    }

    public void setColorY(Double colorY) {
        this.colorY = colorY;
    }

    public Double getColorZ() {
        return this.colorZ;
    }

    public Batches colorZ(Double colorZ) {
        this.setColorZ(colorZ);
        return this;
    }

    public void setColorZ(Double colorZ) {
        this.colorZ = colorZ;
    }

    public String getCatalogId() {
        return this.catalogId;
    }

    public Batches catalogId(String catalogId) {
        this.setCatalogId(catalogId);
        return this;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogExternalid() {
        return this.catalogExternalid;
    }

    public Batches catalogExternalid(String catalogExternalid) {
        this.setCatalogExternalid(catalogExternalid);
        return this;
    }

    public void setCatalogExternalid(String catalogExternalid) {
        this.catalogExternalid = catalogExternalid;
    }

    public String getCatalogName() {
        return this.catalogName;
    }

    public Batches catalogName(String catalogName) {
        this.setCatalogName(catalogName);
        return this;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogVersion() {
        return this.catalogVersion;
    }

    public Batches catalogVersion(String catalogVersion) {
        this.setCatalogVersion(catalogVersion);
        return this;
    }

    public void setCatalogVersion(String catalogVersion) {
        this.catalogVersion = catalogVersion;
    }

    public Boolean getCatalogIsactive() {
        return this.catalogIsactive;
    }

    public Batches catalogIsactive(Boolean catalogIsactive) {
        this.setCatalogIsactive(catalogIsactive);
        return this;
    }

    public void setCatalogIsactive(Boolean catalogIsactive) {
        this.catalogIsactive = catalogIsactive;
    }

    public Instant getCatalogCreatedtime() {
        return this.catalogCreatedtime;
    }

    public Batches catalogCreatedtime(Instant catalogCreatedtime) {
        this.setCatalogCreatedtime(catalogCreatedtime);
        return this;
    }

    public void setCatalogCreatedtime(Instant catalogCreatedtime) {
        this.catalogCreatedtime = catalogCreatedtime;
    }

    public Long getBaseMaterialId() {
        return this.baseMaterialId;
    }

    public Batches baseMaterialId(Long baseMaterialId) {
        this.setBaseMaterialId(baseMaterialId);
        return this;
    }

    public void setBaseMaterialId(Long baseMaterialId) {
        this.baseMaterialId = baseMaterialId;
    }

    public Instant getOrderedTime() {
        return this.orderedTime;
    }

    public Batches orderedTime(Instant orderedTime) {
        this.setOrderedTime(orderedTime);
        return this;
    }

    public void setOrderedTime(Instant orderedTime) {
        this.orderedTime = orderedTime;
    }

    public Instant getStartedTime() {
        return this.startedTime;
    }

    public Batches startedTime(Instant startedTime) {
        this.setStartedTime(startedTime);
        return this;
    }

    public void setStartedTime(Instant startedTime) {
        this.startedTime = startedTime;
    }

    public Instant getModifiedTime() {
        return this.modifiedTime;
    }

    public Batches modifiedTime(Instant modifiedTime) {
        this.setModifiedTime(modifiedTime);
        return this;
    }

    public void setModifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Instant getSuspendedTime() {
        return this.suspendedTime;
    }

    public Batches suspendedTime(Instant suspendedTime) {
        this.setSuspendedTime(suspendedTime);
        return this;
    }

    public void setSuspendedTime(Instant suspendedTime) {
        this.suspendedTime = suspendedTime;
    }

    public Instant getFinishedTime() {
        return this.finishedTime;
    }

    public Batches finishedTime(Instant finishedTime) {
        this.setFinishedTime(finishedTime);
        return this;
    }

    public void setFinishedTime(Instant finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getOriginal() {
        return this.original;
    }

    public Batches original(String original) {
        this.setOriginal(original);
        return this;
    }

    public void setOriginal(String original) {
        this.original = original;
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

    public Batches setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Catalogs getColorCatalog() {
        return this.colorCatalog;
    }

    public void setColorCatalog(Catalogs catalogs) {
        this.colorCatalog = catalogs;
    }

    public Batches colorCatalog(Catalogs catalogs) {
        this.setColorCatalog(catalogs);
        return this;
    }

    public Set<Scans> getIds() {
        return this.ids;
    }

    public void setIds(Set<Scans> scans) {
        if (this.ids != null) {
            this.ids.forEach(i -> i.setProductionBatch(null));
        }
        if (scans != null) {
            scans.forEach(i -> i.setProductionBatch(this));
        }
        this.ids = scans;
    }

    public Batches ids(Set<Scans> scans) {
        this.setIds(scans);
        return this;
    }

    public Batches addId(Scans scans) {
        this.ids.add(scans);
        scans.setProductionBatch(this);
        return this;
    }

    public Batches removeId(Scans scans) {
        this.ids.remove(scans);
        scans.setProductionBatch(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Batches)) {
            return false;
        }
        return id != null && id.equals(((Batches) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Batches{" +
            "id=" + getId() +
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
            "}";
    }
}
