package biz.daich.tambour.shlicht.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link biz.daich.tambour.shlicht.domain.Catalogcolors} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CatalogcolorsDTO implements Serializable {

    @NotNull
    @Size(max = 36)
    private String id;

    @Lob
    private String code;

    @Lob
    private String name;

    private Long baseMaterial;

    private Double labL;

    private Double labA;

    private Double labB;

    @NotNull
    private Double a;

    @NotNull
    private Double b;

    @NotNull
    private Double c;

    @NotNull
    private Double d;

    @NotNull
    private Double e;

    @NotNull
    private Double f;

    @NotNull
    private Double g;

    @NotNull
    private Double h;

    @NotNull
    private Double i;

    @NotNull
    private Double j;

    @NotNull
    private Double k;

    @NotNull
    private Double l;

    @NotNull
    private Double m;

    @NotNull
    private Double n;

    @NotNull
    private Double o;

    @NotNull
    private Double p;

    @NotNull
    private Double q;

    @NotNull
    private Double r;

    @NotNull
    private Double s;

    @NotNull
    private Double t;

    @NotNull
    private Double u;

    @NotNull
    private Double v;

    @NotNull
    private Double w;

    @NotNull
    private Double x;

    @NotNull
    private Double y;

    @NotNull
    private Double z;

    private CatalogsDTO catalog;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBaseMaterial() {
        return baseMaterial;
    }

    public void setBaseMaterial(Long baseMaterial) {
        this.baseMaterial = baseMaterial;
    }

    public Double getLabL() {
        return labL;
    }

    public void setLabL(Double labL) {
        this.labL = labL;
    }

    public Double getLabA() {
        return labA;
    }

    public void setLabA(Double labA) {
        this.labA = labA;
    }

    public Double getLabB() {
        return labB;
    }

    public void setLabB(Double labB) {
        this.labB = labB;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Double getE() {
        return e;
    }

    public void setE(Double e) {
        this.e = e;
    }

    public Double getF() {
        return f;
    }

    public void setF(Double f) {
        this.f = f;
    }

    public Double getG() {
        return g;
    }

    public void setG(Double g) {
        this.g = g;
    }

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public Double getI() {
        return i;
    }

    public void setI(Double i) {
        this.i = i;
    }

    public Double getJ() {
        return j;
    }

    public void setJ(Double j) {
        this.j = j;
    }

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }

    public Double getL() {
        return l;
    }

    public void setL(Double l) {
        this.l = l;
    }

    public Double getM() {
        return m;
    }

    public void setM(Double m) {
        this.m = m;
    }

    public Double getN() {
        return n;
    }

    public void setN(Double n) {
        this.n = n;
    }

    public Double getO() {
        return o;
    }

    public void setO(Double o) {
        this.o = o;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getQ() {
        return q;
    }

    public void setQ(Double q) {
        this.q = q;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getS() {
        return s;
    }

    public void setS(Double s) {
        this.s = s;
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public Double getU() {
        return u;
    }

    public void setU(Double u) {
        this.u = u;
    }

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

    public Double getW() {
        return w;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public CatalogsDTO getCatalog() {
        return catalog;
    }

    public void setCatalog(CatalogsDTO catalog) {
        this.catalog = catalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CatalogcolorsDTO)) {
            return false;
        }

        CatalogcolorsDTO catalogcolorsDTO = (CatalogcolorsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, catalogcolorsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CatalogcolorsDTO{" +
            "id='" + getId() + "'" +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", baseMaterial=" + getBaseMaterial() +
            ", labL=" + getLabL() +
            ", labA=" + getLabA() +
            ", labB=" + getLabB() +
            ", a=" + getA() +
            ", b=" + getB() +
            ", c=" + getC() +
            ", d=" + getD() +
            ", e=" + getE() +
            ", f=" + getF() +
            ", g=" + getG() +
            ", h=" + getH() +
            ", i=" + getI() +
            ", j=" + getJ() +
            ", k=" + getK() +
            ", l=" + getL() +
            ", m=" + getM() +
            ", n=" + getN() +
            ", o=" + getO() +
            ", p=" + getP() +
            ", q=" + getQ() +
            ", r=" + getR() +
            ", s=" + getS() +
            ", t=" + getT() +
            ", u=" + getU() +
            ", v=" + getV() +
            ", w=" + getW() +
            ", x=" + getX() +
            ", y=" + getY() +
            ", z=" + getZ() +
            ", catalog=" + getCatalog() +
            "}";
    }
}
