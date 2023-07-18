package biz.daich.tambour.shlicht.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link biz.daich.tambour.shlicht.domain.Batches} entity. This class is used
 * in {@link biz.daich.tambour.shlicht.web.rest.BatchesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /batches?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BatchesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter id;

    private StringFilter poName;

    private IntegerFilter sequenceInPo;

    private StringFilter previousProductionBatchId;

    private StringFilter state;

    private DoubleFilter inspectionSequence;

    private IntegerFilter orderedQuantity;

    private IntegerFilter producingQuantity;

    private IntegerFilter totalProducingQuantity;

    private IntegerFilter remainingQuantity;

    private IntegerFilter totalremainingQuantity;

    private IntegerFilter inspectedQuantity;

    private IntegerFilter totalInspectedQuantity;

    private IntegerFilter failedQuantity;

    private IntegerFilter totalFailedQuantity;

    private StringFilter colorId;

    private LongFilter colorBasematerial;

    private DoubleFilter colorLabL;

    private DoubleFilter colorLabA;

    private DoubleFilter colorLabB;

    private DoubleFilter colorA;

    private DoubleFilter colorB;

    private DoubleFilter colorC;

    private DoubleFilter colorD;

    private DoubleFilter colorE;

    private DoubleFilter colorF;

    private DoubleFilter colorG;

    private DoubleFilter colorH;

    private DoubleFilter colorI;

    private DoubleFilter colorJ;

    private DoubleFilter colorK;

    private DoubleFilter colorL;

    private DoubleFilter colorM;

    private DoubleFilter colorN;

    private DoubleFilter colorO;

    private DoubleFilter colorP;

    private DoubleFilter colorQ;

    private DoubleFilter colorR;

    private DoubleFilter colorS;

    private DoubleFilter colorT;

    private DoubleFilter colorU;

    private DoubleFilter colorV;

    private DoubleFilter colorW;

    private DoubleFilter colorX;

    private DoubleFilter colorY;

    private DoubleFilter colorZ;

    private StringFilter catalogId;

    private BooleanFilter catalogIsactive;

    private InstantFilter catalogCreatedtime;

    private LongFilter baseMaterialId;

    private InstantFilter orderedTime;

    private InstantFilter startedTime;

    private InstantFilter modifiedTime;

    private InstantFilter suspendedTime;

    private InstantFilter finishedTime;

    private StringFilter colorCatalogId;

    private Boolean distinct;

    public BatchesCriteria() {}

    public BatchesCriteria(BatchesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.poName = other.poName == null ? null : other.poName.copy();
        this.sequenceInPo = other.sequenceInPo == null ? null : other.sequenceInPo.copy();
        this.previousProductionBatchId = other.previousProductionBatchId == null ? null : other.previousProductionBatchId.copy();
        this.state = other.state == null ? null : other.state.copy();
        this.inspectionSequence = other.inspectionSequence == null ? null : other.inspectionSequence.copy();
        this.orderedQuantity = other.orderedQuantity == null ? null : other.orderedQuantity.copy();
        this.producingQuantity = other.producingQuantity == null ? null : other.producingQuantity.copy();
        this.totalProducingQuantity = other.totalProducingQuantity == null ? null : other.totalProducingQuantity.copy();
        this.remainingQuantity = other.remainingQuantity == null ? null : other.remainingQuantity.copy();
        this.totalremainingQuantity = other.totalremainingQuantity == null ? null : other.totalremainingQuantity.copy();
        this.inspectedQuantity = other.inspectedQuantity == null ? null : other.inspectedQuantity.copy();
        this.totalInspectedQuantity = other.totalInspectedQuantity == null ? null : other.totalInspectedQuantity.copy();
        this.failedQuantity = other.failedQuantity == null ? null : other.failedQuantity.copy();
        this.totalFailedQuantity = other.totalFailedQuantity == null ? null : other.totalFailedQuantity.copy();
        this.colorId = other.colorId == null ? null : other.colorId.copy();
        this.colorBasematerial = other.colorBasematerial == null ? null : other.colorBasematerial.copy();
        this.colorLabL = other.colorLabL == null ? null : other.colorLabL.copy();
        this.colorLabA = other.colorLabA == null ? null : other.colorLabA.copy();
        this.colorLabB = other.colorLabB == null ? null : other.colorLabB.copy();
        this.colorA = other.colorA == null ? null : other.colorA.copy();
        this.colorB = other.colorB == null ? null : other.colorB.copy();
        this.colorC = other.colorC == null ? null : other.colorC.copy();
        this.colorD = other.colorD == null ? null : other.colorD.copy();
        this.colorE = other.colorE == null ? null : other.colorE.copy();
        this.colorF = other.colorF == null ? null : other.colorF.copy();
        this.colorG = other.colorG == null ? null : other.colorG.copy();
        this.colorH = other.colorH == null ? null : other.colorH.copy();
        this.colorI = other.colorI == null ? null : other.colorI.copy();
        this.colorJ = other.colorJ == null ? null : other.colorJ.copy();
        this.colorK = other.colorK == null ? null : other.colorK.copy();
        this.colorL = other.colorL == null ? null : other.colorL.copy();
        this.colorM = other.colorM == null ? null : other.colorM.copy();
        this.colorN = other.colorN == null ? null : other.colorN.copy();
        this.colorO = other.colorO == null ? null : other.colorO.copy();
        this.colorP = other.colorP == null ? null : other.colorP.copy();
        this.colorQ = other.colorQ == null ? null : other.colorQ.copy();
        this.colorR = other.colorR == null ? null : other.colorR.copy();
        this.colorS = other.colorS == null ? null : other.colorS.copy();
        this.colorT = other.colorT == null ? null : other.colorT.copy();
        this.colorU = other.colorU == null ? null : other.colorU.copy();
        this.colorV = other.colorV == null ? null : other.colorV.copy();
        this.colorW = other.colorW == null ? null : other.colorW.copy();
        this.colorX = other.colorX == null ? null : other.colorX.copy();
        this.colorY = other.colorY == null ? null : other.colorY.copy();
        this.colorZ = other.colorZ == null ? null : other.colorZ.copy();
        this.catalogId = other.catalogId == null ? null : other.catalogId.copy();
        this.catalogIsactive = other.catalogIsactive == null ? null : other.catalogIsactive.copy();
        this.catalogCreatedtime = other.catalogCreatedtime == null ? null : other.catalogCreatedtime.copy();
        this.baseMaterialId = other.baseMaterialId == null ? null : other.baseMaterialId.copy();
        this.orderedTime = other.orderedTime == null ? null : other.orderedTime.copy();
        this.startedTime = other.startedTime == null ? null : other.startedTime.copy();
        this.modifiedTime = other.modifiedTime == null ? null : other.modifiedTime.copy();
        this.suspendedTime = other.suspendedTime == null ? null : other.suspendedTime.copy();
        this.finishedTime = other.finishedTime == null ? null : other.finishedTime.copy();
        this.colorCatalogId = other.colorCatalogId == null ? null : other.colorCatalogId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BatchesCriteria copy() {
        return new BatchesCriteria(this);
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

    public StringFilter getPoName() {
        return poName;
    }

    public StringFilter poName() {
        if (poName == null) {
            poName = new StringFilter();
        }
        return poName;
    }

    public void setPoName(StringFilter poName) {
        this.poName = poName;
    }

    public IntegerFilter getSequenceInPo() {
        return sequenceInPo;
    }

    public IntegerFilter sequenceInPo() {
        if (sequenceInPo == null) {
            sequenceInPo = new IntegerFilter();
        }
        return sequenceInPo;
    }

    public void setSequenceInPo(IntegerFilter sequenceInPo) {
        this.sequenceInPo = sequenceInPo;
    }

    public StringFilter getPreviousProductionBatchId() {
        return previousProductionBatchId;
    }

    public StringFilter previousProductionBatchId() {
        if (previousProductionBatchId == null) {
            previousProductionBatchId = new StringFilter();
        }
        return previousProductionBatchId;
    }

    public void setPreviousProductionBatchId(StringFilter previousProductionBatchId) {
        this.previousProductionBatchId = previousProductionBatchId;
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

    public DoubleFilter getInspectionSequence() {
        return inspectionSequence;
    }

    public DoubleFilter inspectionSequence() {
        if (inspectionSequence == null) {
            inspectionSequence = new DoubleFilter();
        }
        return inspectionSequence;
    }

    public void setInspectionSequence(DoubleFilter inspectionSequence) {
        this.inspectionSequence = inspectionSequence;
    }

    public IntegerFilter getOrderedQuantity() {
        return orderedQuantity;
    }

    public IntegerFilter orderedQuantity() {
        if (orderedQuantity == null) {
            orderedQuantity = new IntegerFilter();
        }
        return orderedQuantity;
    }

    public void setOrderedQuantity(IntegerFilter orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public IntegerFilter getProducingQuantity() {
        return producingQuantity;
    }

    public IntegerFilter producingQuantity() {
        if (producingQuantity == null) {
            producingQuantity = new IntegerFilter();
        }
        return producingQuantity;
    }

    public void setProducingQuantity(IntegerFilter producingQuantity) {
        this.producingQuantity = producingQuantity;
    }

    public IntegerFilter getTotalProducingQuantity() {
        return totalProducingQuantity;
    }

    public IntegerFilter totalProducingQuantity() {
        if (totalProducingQuantity == null) {
            totalProducingQuantity = new IntegerFilter();
        }
        return totalProducingQuantity;
    }

    public void setTotalProducingQuantity(IntegerFilter totalProducingQuantity) {
        this.totalProducingQuantity = totalProducingQuantity;
    }

    public IntegerFilter getRemainingQuantity() {
        return remainingQuantity;
    }

    public IntegerFilter remainingQuantity() {
        if (remainingQuantity == null) {
            remainingQuantity = new IntegerFilter();
        }
        return remainingQuantity;
    }

    public void setRemainingQuantity(IntegerFilter remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public IntegerFilter getTotalremainingQuantity() {
        return totalremainingQuantity;
    }

    public IntegerFilter totalremainingQuantity() {
        if (totalremainingQuantity == null) {
            totalremainingQuantity = new IntegerFilter();
        }
        return totalremainingQuantity;
    }

    public void setTotalremainingQuantity(IntegerFilter totalremainingQuantity) {
        this.totalremainingQuantity = totalremainingQuantity;
    }

    public IntegerFilter getInspectedQuantity() {
        return inspectedQuantity;
    }

    public IntegerFilter inspectedQuantity() {
        if (inspectedQuantity == null) {
            inspectedQuantity = new IntegerFilter();
        }
        return inspectedQuantity;
    }

    public void setInspectedQuantity(IntegerFilter inspectedQuantity) {
        this.inspectedQuantity = inspectedQuantity;
    }

    public IntegerFilter getTotalInspectedQuantity() {
        return totalInspectedQuantity;
    }

    public IntegerFilter totalInspectedQuantity() {
        if (totalInspectedQuantity == null) {
            totalInspectedQuantity = new IntegerFilter();
        }
        return totalInspectedQuantity;
    }

    public void setTotalInspectedQuantity(IntegerFilter totalInspectedQuantity) {
        this.totalInspectedQuantity = totalInspectedQuantity;
    }

    public IntegerFilter getFailedQuantity() {
        return failedQuantity;
    }

    public IntegerFilter failedQuantity() {
        if (failedQuantity == null) {
            failedQuantity = new IntegerFilter();
        }
        return failedQuantity;
    }

    public void setFailedQuantity(IntegerFilter failedQuantity) {
        this.failedQuantity = failedQuantity;
    }

    public IntegerFilter getTotalFailedQuantity() {
        return totalFailedQuantity;
    }

    public IntegerFilter totalFailedQuantity() {
        if (totalFailedQuantity == null) {
            totalFailedQuantity = new IntegerFilter();
        }
        return totalFailedQuantity;
    }

    public void setTotalFailedQuantity(IntegerFilter totalFailedQuantity) {
        this.totalFailedQuantity = totalFailedQuantity;
    }

    public StringFilter getColorId() {
        return colorId;
    }

    public StringFilter colorId() {
        if (colorId == null) {
            colorId = new StringFilter();
        }
        return colorId;
    }

    public void setColorId(StringFilter colorId) {
        this.colorId = colorId;
    }

    public LongFilter getColorBasematerial() {
        return colorBasematerial;
    }

    public LongFilter colorBasematerial() {
        if (colorBasematerial == null) {
            colorBasematerial = new LongFilter();
        }
        return colorBasematerial;
    }

    public void setColorBasematerial(LongFilter colorBasematerial) {
        this.colorBasematerial = colorBasematerial;
    }

    public DoubleFilter getColorLabL() {
        return colorLabL;
    }

    public DoubleFilter colorLabL() {
        if (colorLabL == null) {
            colorLabL = new DoubleFilter();
        }
        return colorLabL;
    }

    public void setColorLabL(DoubleFilter colorLabL) {
        this.colorLabL = colorLabL;
    }

    public DoubleFilter getColorLabA() {
        return colorLabA;
    }

    public DoubleFilter colorLabA() {
        if (colorLabA == null) {
            colorLabA = new DoubleFilter();
        }
        return colorLabA;
    }

    public void setColorLabA(DoubleFilter colorLabA) {
        this.colorLabA = colorLabA;
    }

    public DoubleFilter getColorLabB() {
        return colorLabB;
    }

    public DoubleFilter colorLabB() {
        if (colorLabB == null) {
            colorLabB = new DoubleFilter();
        }
        return colorLabB;
    }

    public void setColorLabB(DoubleFilter colorLabB) {
        this.colorLabB = colorLabB;
    }

    public DoubleFilter getColorA() {
        return colorA;
    }

    public DoubleFilter colorA() {
        if (colorA == null) {
            colorA = new DoubleFilter();
        }
        return colorA;
    }

    public void setColorA(DoubleFilter colorA) {
        this.colorA = colorA;
    }

    public DoubleFilter getColorB() {
        return colorB;
    }

    public DoubleFilter colorB() {
        if (colorB == null) {
            colorB = new DoubleFilter();
        }
        return colorB;
    }

    public void setColorB(DoubleFilter colorB) {
        this.colorB = colorB;
    }

    public DoubleFilter getColorC() {
        return colorC;
    }

    public DoubleFilter colorC() {
        if (colorC == null) {
            colorC = new DoubleFilter();
        }
        return colorC;
    }

    public void setColorC(DoubleFilter colorC) {
        this.colorC = colorC;
    }

    public DoubleFilter getColorD() {
        return colorD;
    }

    public DoubleFilter colorD() {
        if (colorD == null) {
            colorD = new DoubleFilter();
        }
        return colorD;
    }

    public void setColorD(DoubleFilter colorD) {
        this.colorD = colorD;
    }

    public DoubleFilter getColorE() {
        return colorE;
    }

    public DoubleFilter colorE() {
        if (colorE == null) {
            colorE = new DoubleFilter();
        }
        return colorE;
    }

    public void setColorE(DoubleFilter colorE) {
        this.colorE = colorE;
    }

    public DoubleFilter getColorF() {
        return colorF;
    }

    public DoubleFilter colorF() {
        if (colorF == null) {
            colorF = new DoubleFilter();
        }
        return colorF;
    }

    public void setColorF(DoubleFilter colorF) {
        this.colorF = colorF;
    }

    public DoubleFilter getColorG() {
        return colorG;
    }

    public DoubleFilter colorG() {
        if (colorG == null) {
            colorG = new DoubleFilter();
        }
        return colorG;
    }

    public void setColorG(DoubleFilter colorG) {
        this.colorG = colorG;
    }

    public DoubleFilter getColorH() {
        return colorH;
    }

    public DoubleFilter colorH() {
        if (colorH == null) {
            colorH = new DoubleFilter();
        }
        return colorH;
    }

    public void setColorH(DoubleFilter colorH) {
        this.colorH = colorH;
    }

    public DoubleFilter getColorI() {
        return colorI;
    }

    public DoubleFilter colorI() {
        if (colorI == null) {
            colorI = new DoubleFilter();
        }
        return colorI;
    }

    public void setColorI(DoubleFilter colorI) {
        this.colorI = colorI;
    }

    public DoubleFilter getColorJ() {
        return colorJ;
    }

    public DoubleFilter colorJ() {
        if (colorJ == null) {
            colorJ = new DoubleFilter();
        }
        return colorJ;
    }

    public void setColorJ(DoubleFilter colorJ) {
        this.colorJ = colorJ;
    }

    public DoubleFilter getColorK() {
        return colorK;
    }

    public DoubleFilter colorK() {
        if (colorK == null) {
            colorK = new DoubleFilter();
        }
        return colorK;
    }

    public void setColorK(DoubleFilter colorK) {
        this.colorK = colorK;
    }

    public DoubleFilter getColorL() {
        return colorL;
    }

    public DoubleFilter colorL() {
        if (colorL == null) {
            colorL = new DoubleFilter();
        }
        return colorL;
    }

    public void setColorL(DoubleFilter colorL) {
        this.colorL = colorL;
    }

    public DoubleFilter getColorM() {
        return colorM;
    }

    public DoubleFilter colorM() {
        if (colorM == null) {
            colorM = new DoubleFilter();
        }
        return colorM;
    }

    public void setColorM(DoubleFilter colorM) {
        this.colorM = colorM;
    }

    public DoubleFilter getColorN() {
        return colorN;
    }

    public DoubleFilter colorN() {
        if (colorN == null) {
            colorN = new DoubleFilter();
        }
        return colorN;
    }

    public void setColorN(DoubleFilter colorN) {
        this.colorN = colorN;
    }

    public DoubleFilter getColorO() {
        return colorO;
    }

    public DoubleFilter colorO() {
        if (colorO == null) {
            colorO = new DoubleFilter();
        }
        return colorO;
    }

    public void setColorO(DoubleFilter colorO) {
        this.colorO = colorO;
    }

    public DoubleFilter getColorP() {
        return colorP;
    }

    public DoubleFilter colorP() {
        if (colorP == null) {
            colorP = new DoubleFilter();
        }
        return colorP;
    }

    public void setColorP(DoubleFilter colorP) {
        this.colorP = colorP;
    }

    public DoubleFilter getColorQ() {
        return colorQ;
    }

    public DoubleFilter colorQ() {
        if (colorQ == null) {
            colorQ = new DoubleFilter();
        }
        return colorQ;
    }

    public void setColorQ(DoubleFilter colorQ) {
        this.colorQ = colorQ;
    }

    public DoubleFilter getColorR() {
        return colorR;
    }

    public DoubleFilter colorR() {
        if (colorR == null) {
            colorR = new DoubleFilter();
        }
        return colorR;
    }

    public void setColorR(DoubleFilter colorR) {
        this.colorR = colorR;
    }

    public DoubleFilter getColorS() {
        return colorS;
    }

    public DoubleFilter colorS() {
        if (colorS == null) {
            colorS = new DoubleFilter();
        }
        return colorS;
    }

    public void setColorS(DoubleFilter colorS) {
        this.colorS = colorS;
    }

    public DoubleFilter getColorT() {
        return colorT;
    }

    public DoubleFilter colorT() {
        if (colorT == null) {
            colorT = new DoubleFilter();
        }
        return colorT;
    }

    public void setColorT(DoubleFilter colorT) {
        this.colorT = colorT;
    }

    public DoubleFilter getColorU() {
        return colorU;
    }

    public DoubleFilter colorU() {
        if (colorU == null) {
            colorU = new DoubleFilter();
        }
        return colorU;
    }

    public void setColorU(DoubleFilter colorU) {
        this.colorU = colorU;
    }

    public DoubleFilter getColorV() {
        return colorV;
    }

    public DoubleFilter colorV() {
        if (colorV == null) {
            colorV = new DoubleFilter();
        }
        return colorV;
    }

    public void setColorV(DoubleFilter colorV) {
        this.colorV = colorV;
    }

    public DoubleFilter getColorW() {
        return colorW;
    }

    public DoubleFilter colorW() {
        if (colorW == null) {
            colorW = new DoubleFilter();
        }
        return colorW;
    }

    public void setColorW(DoubleFilter colorW) {
        this.colorW = colorW;
    }

    public DoubleFilter getColorX() {
        return colorX;
    }

    public DoubleFilter colorX() {
        if (colorX == null) {
            colorX = new DoubleFilter();
        }
        return colorX;
    }

    public void setColorX(DoubleFilter colorX) {
        this.colorX = colorX;
    }

    public DoubleFilter getColorY() {
        return colorY;
    }

    public DoubleFilter colorY() {
        if (colorY == null) {
            colorY = new DoubleFilter();
        }
        return colorY;
    }

    public void setColorY(DoubleFilter colorY) {
        this.colorY = colorY;
    }

    public DoubleFilter getColorZ() {
        return colorZ;
    }

    public DoubleFilter colorZ() {
        if (colorZ == null) {
            colorZ = new DoubleFilter();
        }
        return colorZ;
    }

    public void setColorZ(DoubleFilter colorZ) {
        this.colorZ = colorZ;
    }

    public StringFilter getCatalogId() {
        return catalogId;
    }

    public StringFilter catalogId() {
        if (catalogId == null) {
            catalogId = new StringFilter();
        }
        return catalogId;
    }

    public void setCatalogId(StringFilter catalogId) {
        this.catalogId = catalogId;
    }

    public BooleanFilter getCatalogIsactive() {
        return catalogIsactive;
    }

    public BooleanFilter catalogIsactive() {
        if (catalogIsactive == null) {
            catalogIsactive = new BooleanFilter();
        }
        return catalogIsactive;
    }

    public void setCatalogIsactive(BooleanFilter catalogIsactive) {
        this.catalogIsactive = catalogIsactive;
    }

    public InstantFilter getCatalogCreatedtime() {
        return catalogCreatedtime;
    }

    public InstantFilter catalogCreatedtime() {
        if (catalogCreatedtime == null) {
            catalogCreatedtime = new InstantFilter();
        }
        return catalogCreatedtime;
    }

    public void setCatalogCreatedtime(InstantFilter catalogCreatedtime) {
        this.catalogCreatedtime = catalogCreatedtime;
    }

    public LongFilter getBaseMaterialId() {
        return baseMaterialId;
    }

    public LongFilter baseMaterialId() {
        if (baseMaterialId == null) {
            baseMaterialId = new LongFilter();
        }
        return baseMaterialId;
    }

    public void setBaseMaterialId(LongFilter baseMaterialId) {
        this.baseMaterialId = baseMaterialId;
    }

    public InstantFilter getOrderedTime() {
        return orderedTime;
    }

    public InstantFilter orderedTime() {
        if (orderedTime == null) {
            orderedTime = new InstantFilter();
        }
        return orderedTime;
    }

    public void setOrderedTime(InstantFilter orderedTime) {
        this.orderedTime = orderedTime;
    }

    public InstantFilter getStartedTime() {
        return startedTime;
    }

    public InstantFilter startedTime() {
        if (startedTime == null) {
            startedTime = new InstantFilter();
        }
        return startedTime;
    }

    public void setStartedTime(InstantFilter startedTime) {
        this.startedTime = startedTime;
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

    public InstantFilter getSuspendedTime() {
        return suspendedTime;
    }

    public InstantFilter suspendedTime() {
        if (suspendedTime == null) {
            suspendedTime = new InstantFilter();
        }
        return suspendedTime;
    }

    public void setSuspendedTime(InstantFilter suspendedTime) {
        this.suspendedTime = suspendedTime;
    }

    public InstantFilter getFinishedTime() {
        return finishedTime;
    }

    public InstantFilter finishedTime() {
        if (finishedTime == null) {
            finishedTime = new InstantFilter();
        }
        return finishedTime;
    }

    public void setFinishedTime(InstantFilter finishedTime) {
        this.finishedTime = finishedTime;
    }

    public StringFilter getColorCatalogId() {
        return colorCatalogId;
    }

    public StringFilter colorCatalogId() {
        if (colorCatalogId == null) {
            colorCatalogId = new StringFilter();
        }
        return colorCatalogId;
    }

    public void setColorCatalogId(StringFilter colorCatalogId) {
        this.colorCatalogId = colorCatalogId;
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
        final BatchesCriteria that = (BatchesCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(poName, that.poName) &&
            Objects.equals(sequenceInPo, that.sequenceInPo) &&
            Objects.equals(previousProductionBatchId, that.previousProductionBatchId) &&
            Objects.equals(state, that.state) &&
            Objects.equals(inspectionSequence, that.inspectionSequence) &&
            Objects.equals(orderedQuantity, that.orderedQuantity) &&
            Objects.equals(producingQuantity, that.producingQuantity) &&
            Objects.equals(totalProducingQuantity, that.totalProducingQuantity) &&
            Objects.equals(remainingQuantity, that.remainingQuantity) &&
            Objects.equals(totalremainingQuantity, that.totalremainingQuantity) &&
            Objects.equals(inspectedQuantity, that.inspectedQuantity) &&
            Objects.equals(totalInspectedQuantity, that.totalInspectedQuantity) &&
            Objects.equals(failedQuantity, that.failedQuantity) &&
            Objects.equals(totalFailedQuantity, that.totalFailedQuantity) &&
            Objects.equals(colorId, that.colorId) &&
            Objects.equals(colorBasematerial, that.colorBasematerial) &&
            Objects.equals(colorLabL, that.colorLabL) &&
            Objects.equals(colorLabA, that.colorLabA) &&
            Objects.equals(colorLabB, that.colorLabB) &&
            Objects.equals(colorA, that.colorA) &&
            Objects.equals(colorB, that.colorB) &&
            Objects.equals(colorC, that.colorC) &&
            Objects.equals(colorD, that.colorD) &&
            Objects.equals(colorE, that.colorE) &&
            Objects.equals(colorF, that.colorF) &&
            Objects.equals(colorG, that.colorG) &&
            Objects.equals(colorH, that.colorH) &&
            Objects.equals(colorI, that.colorI) &&
            Objects.equals(colorJ, that.colorJ) &&
            Objects.equals(colorK, that.colorK) &&
            Objects.equals(colorL, that.colorL) &&
            Objects.equals(colorM, that.colorM) &&
            Objects.equals(colorN, that.colorN) &&
            Objects.equals(colorO, that.colorO) &&
            Objects.equals(colorP, that.colorP) &&
            Objects.equals(colorQ, that.colorQ) &&
            Objects.equals(colorR, that.colorR) &&
            Objects.equals(colorS, that.colorS) &&
            Objects.equals(colorT, that.colorT) &&
            Objects.equals(colorU, that.colorU) &&
            Objects.equals(colorV, that.colorV) &&
            Objects.equals(colorW, that.colorW) &&
            Objects.equals(colorX, that.colorX) &&
            Objects.equals(colorY, that.colorY) &&
            Objects.equals(colorZ, that.colorZ) &&
            Objects.equals(catalogId, that.catalogId) &&
            Objects.equals(catalogIsactive, that.catalogIsactive) &&
            Objects.equals(catalogCreatedtime, that.catalogCreatedtime) &&
            Objects.equals(baseMaterialId, that.baseMaterialId) &&
            Objects.equals(orderedTime, that.orderedTime) &&
            Objects.equals(startedTime, that.startedTime) &&
            Objects.equals(modifiedTime, that.modifiedTime) &&
            Objects.equals(suspendedTime, that.suspendedTime) &&
            Objects.equals(finishedTime, that.finishedTime) &&
            Objects.equals(colorCatalogId, that.colorCatalogId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            poName,
            sequenceInPo,
            previousProductionBatchId,
            state,
            inspectionSequence,
            orderedQuantity,
            producingQuantity,
            totalProducingQuantity,
            remainingQuantity,
            totalremainingQuantity,
            inspectedQuantity,
            totalInspectedQuantity,
            failedQuantity,
            totalFailedQuantity,
            colorId,
            colorBasematerial,
            colorLabL,
            colorLabA,
            colorLabB,
            colorA,
            colorB,
            colorC,
            colorD,
            colorE,
            colorF,
            colorG,
            colorH,
            colorI,
            colorJ,
            colorK,
            colorL,
            colorM,
            colorN,
            colorO,
            colorP,
            colorQ,
            colorR,
            colorS,
            colorT,
            colorU,
            colorV,
            colorW,
            colorX,
            colorY,
            colorZ,
            catalogId,
            catalogIsactive,
            catalogCreatedtime,
            baseMaterialId,
            orderedTime,
            startedTime,
            modifiedTime,
            suspendedTime,
            finishedTime,
            colorCatalogId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BatchesCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (poName != null ? "poName=" + poName + ", " : "") +
            (sequenceInPo != null ? "sequenceInPo=" + sequenceInPo + ", " : "") +
            (previousProductionBatchId != null ? "previousProductionBatchId=" + previousProductionBatchId + ", " : "") +
            (state != null ? "state=" + state + ", " : "") +
            (inspectionSequence != null ? "inspectionSequence=" + inspectionSequence + ", " : "") +
            (orderedQuantity != null ? "orderedQuantity=" + orderedQuantity + ", " : "") +
            (producingQuantity != null ? "producingQuantity=" + producingQuantity + ", " : "") +
            (totalProducingQuantity != null ? "totalProducingQuantity=" + totalProducingQuantity + ", " : "") +
            (remainingQuantity != null ? "remainingQuantity=" + remainingQuantity + ", " : "") +
            (totalremainingQuantity != null ? "totalremainingQuantity=" + totalremainingQuantity + ", " : "") +
            (inspectedQuantity != null ? "inspectedQuantity=" + inspectedQuantity + ", " : "") +
            (totalInspectedQuantity != null ? "totalInspectedQuantity=" + totalInspectedQuantity + ", " : "") +
            (failedQuantity != null ? "failedQuantity=" + failedQuantity + ", " : "") +
            (totalFailedQuantity != null ? "totalFailedQuantity=" + totalFailedQuantity + ", " : "") +
            (colorId != null ? "colorId=" + colorId + ", " : "") +
            (colorBasematerial != null ? "colorBasematerial=" + colorBasematerial + ", " : "") +
            (colorLabL != null ? "colorLabL=" + colorLabL + ", " : "") +
            (colorLabA != null ? "colorLabA=" + colorLabA + ", " : "") +
            (colorLabB != null ? "colorLabB=" + colorLabB + ", " : "") +
            (colorA != null ? "colorA=" + colorA + ", " : "") +
            (colorB != null ? "colorB=" + colorB + ", " : "") +
            (colorC != null ? "colorC=" + colorC + ", " : "") +
            (colorD != null ? "colorD=" + colorD + ", " : "") +
            (colorE != null ? "colorE=" + colorE + ", " : "") +
            (colorF != null ? "colorF=" + colorF + ", " : "") +
            (colorG != null ? "colorG=" + colorG + ", " : "") +
            (colorH != null ? "colorH=" + colorH + ", " : "") +
            (colorI != null ? "colorI=" + colorI + ", " : "") +
            (colorJ != null ? "colorJ=" + colorJ + ", " : "") +
            (colorK != null ? "colorK=" + colorK + ", " : "") +
            (colorL != null ? "colorL=" + colorL + ", " : "") +
            (colorM != null ? "colorM=" + colorM + ", " : "") +
            (colorN != null ? "colorN=" + colorN + ", " : "") +
            (colorO != null ? "colorO=" + colorO + ", " : "") +
            (colorP != null ? "colorP=" + colorP + ", " : "") +
            (colorQ != null ? "colorQ=" + colorQ + ", " : "") +
            (colorR != null ? "colorR=" + colorR + ", " : "") +
            (colorS != null ? "colorS=" + colorS + ", " : "") +
            (colorT != null ? "colorT=" + colorT + ", " : "") +
            (colorU != null ? "colorU=" + colorU + ", " : "") +
            (colorV != null ? "colorV=" + colorV + ", " : "") +
            (colorW != null ? "colorW=" + colorW + ", " : "") +
            (colorX != null ? "colorX=" + colorX + ", " : "") +
            (colorY != null ? "colorY=" + colorY + ", " : "") +
            (colorZ != null ? "colorZ=" + colorZ + ", " : "") +
            (catalogId != null ? "catalogId=" + catalogId + ", " : "") +
            (catalogIsactive != null ? "catalogIsactive=" + catalogIsactive + ", " : "") +
            (catalogCreatedtime != null ? "catalogCreatedtime=" + catalogCreatedtime + ", " : "") +
            (baseMaterialId != null ? "baseMaterialId=" + baseMaterialId + ", " : "") +
            (orderedTime != null ? "orderedTime=" + orderedTime + ", " : "") +
            (startedTime != null ? "startedTime=" + startedTime + ", " : "") +
            (modifiedTime != null ? "modifiedTime=" + modifiedTime + ", " : "") +
            (suspendedTime != null ? "suspendedTime=" + suspendedTime + ", " : "") +
            (finishedTime != null ? "finishedTime=" + finishedTime + ", " : "") +
            (colorCatalogId != null ? "colorCatalogId=" + colorCatalogId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
