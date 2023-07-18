package biz.daich.tambour.shlicht.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Catalogcolors.
 */
@Entity
@Table(name = "catalogcolors")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Catalogcolors implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 36)
    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Lob
    @Column(name = "code", nullable = false)
    private String code;

    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "base_material")
    private Long baseMaterial;

    @Column(name = "lab_l")
    private Double labL;

    @Column(name = "lab_a")
    private Double labA;

    @Column(name = "lab_b")
    private Double labB;

    @NotNull
    @Column(name = "a", nullable = false)
    private Double a;

    @NotNull
    @Column(name = "b", nullable = false)
    private Double b;

    @NotNull
    @Column(name = "c", nullable = false)
    private Double c;

    @NotNull
    @Column(name = "d", nullable = false)
    private Double d;

    @NotNull
    @Column(name = "e", nullable = false)
    private Double e;

    @NotNull
    @Column(name = "f", nullable = false)
    private Double f;

    @NotNull
    @Column(name = "g", nullable = false)
    private Double g;

    @NotNull
    @Column(name = "h", nullable = false)
    private Double h;

    @NotNull
    @Column(name = "i", nullable = false)
    private Double i;

    @NotNull
    @Column(name = "j", nullable = false)
    private Double j;

    @NotNull
    @Column(name = "k", nullable = false)
    private Double k;

    @NotNull
    @Column(name = "l", nullable = false)
    private Double l;

    @NotNull
    @Column(name = "m", nullable = false)
    private Double m;

    @NotNull
    @Column(name = "n", nullable = false)
    private Double n;

    @NotNull
    @Column(name = "o", nullable = false)
    private Double o;

    @NotNull
    @Column(name = "p", nullable = false)
    private Double p;

    @NotNull
    @Column(name = "q", nullable = false)
    private Double q;

    @NotNull
    @Column(name = "r", nullable = false)
    private Double r;

    @NotNull
    @Column(name = "s", nullable = false)
    private Double s;

    @NotNull
    @Column(name = "t", nullable = false)
    private Double t;

    @NotNull
    @Column(name = "u", nullable = false)
    private Double u;

    @NotNull
    @Column(name = "v", nullable = false)
    private Double v;

    @NotNull
    @Column(name = "w", nullable = false)
    private Double w;

    @NotNull
    @Column(name = "x", nullable = false)
    private Double x;

    @NotNull
    @Column(name = "y", nullable = false)
    private Double y;

    @NotNull
    @Column(name = "z", nullable = false)
    private Double z;

    @Transient
    private boolean isPersisted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "catalogColors", "ids" }, allowSetters = true)
    private Catalogs catalog;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Catalogcolors id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Catalogcolors code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Catalogcolors name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBaseMaterial() {
        return this.baseMaterial;
    }

    public Catalogcolors baseMaterial(Long baseMaterial) {
        this.setBaseMaterial(baseMaterial);
        return this;
    }

    public void setBaseMaterial(Long baseMaterial) {
        this.baseMaterial = baseMaterial;
    }

    public Double getLabL() {
        return this.labL;
    }

    public Catalogcolors labL(Double labL) {
        this.setLabL(labL);
        return this;
    }

    public void setLabL(Double labL) {
        this.labL = labL;
    }

    public Double getLabA() {
        return this.labA;
    }

    public Catalogcolors labA(Double labA) {
        this.setLabA(labA);
        return this;
    }

    public void setLabA(Double labA) {
        this.labA = labA;
    }

    public Double getLabB() {
        return this.labB;
    }

    public Catalogcolors labB(Double labB) {
        this.setLabB(labB);
        return this;
    }

    public void setLabB(Double labB) {
        this.labB = labB;
    }

    public Double getA() {
        return this.a;
    }

    public Catalogcolors a(Double a) {
        this.setA(a);
        return this;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return this.b;
    }

    public Catalogcolors b(Double b) {
        this.setB(b);
        return this;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return this.c;
    }

    public Catalogcolors c(Double c) {
        this.setC(c);
        return this;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getD() {
        return this.d;
    }

    public Catalogcolors d(Double d) {
        this.setD(d);
        return this;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Double getE() {
        return this.e;
    }

    public Catalogcolors e(Double e) {
        this.setE(e);
        return this;
    }

    public void setE(Double e) {
        this.e = e;
    }

    public Double getF() {
        return this.f;
    }

    public Catalogcolors f(Double f) {
        this.setF(f);
        return this;
    }

    public void setF(Double f) {
        this.f = f;
    }

    public Double getG() {
        return this.g;
    }

    public Catalogcolors g(Double g) {
        this.setG(g);
        return this;
    }

    public void setG(Double g) {
        this.g = g;
    }

    public Double getH() {
        return this.h;
    }

    public Catalogcolors h(Double h) {
        this.setH(h);
        return this;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public Double getI() {
        return this.i;
    }

    public Catalogcolors i(Double i) {
        this.setI(i);
        return this;
    }

    public void setI(Double i) {
        this.i = i;
    }

    public Double getJ() {
        return this.j;
    }

    public Catalogcolors j(Double j) {
        this.setJ(j);
        return this;
    }

    public void setJ(Double j) {
        this.j = j;
    }

    public Double getK() {
        return this.k;
    }

    public Catalogcolors k(Double k) {
        this.setK(k);
        return this;
    }

    public void setK(Double k) {
        this.k = k;
    }

    public Double getL() {
        return this.l;
    }

    public Catalogcolors l(Double l) {
        this.setL(l);
        return this;
    }

    public void setL(Double l) {
        this.l = l;
    }

    public Double getM() {
        return this.m;
    }

    public Catalogcolors m(Double m) {
        this.setM(m);
        return this;
    }

    public void setM(Double m) {
        this.m = m;
    }

    public Double getN() {
        return this.n;
    }

    public Catalogcolors n(Double n) {
        this.setN(n);
        return this;
    }

    public void setN(Double n) {
        this.n = n;
    }

    public Double getO() {
        return this.o;
    }

    public Catalogcolors o(Double o) {
        this.setO(o);
        return this;
    }

    public void setO(Double o) {
        this.o = o;
    }

    public Double getP() {
        return this.p;
    }

    public Catalogcolors p(Double p) {
        this.setP(p);
        return this;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getQ() {
        return this.q;
    }

    public Catalogcolors q(Double q) {
        this.setQ(q);
        return this;
    }

    public void setQ(Double q) {
        this.q = q;
    }

    public Double getR() {
        return this.r;
    }

    public Catalogcolors r(Double r) {
        this.setR(r);
        return this;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getS() {
        return this.s;
    }

    public Catalogcolors s(Double s) {
        this.setS(s);
        return this;
    }

    public void setS(Double s) {
        this.s = s;
    }

    public Double getT() {
        return this.t;
    }

    public Catalogcolors t(Double t) {
        this.setT(t);
        return this;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public Double getU() {
        return this.u;
    }

    public Catalogcolors u(Double u) {
        this.setU(u);
        return this;
    }

    public void setU(Double u) {
        this.u = u;
    }

    public Double getV() {
        return this.v;
    }

    public Catalogcolors v(Double v) {
        this.setV(v);
        return this;
    }

    public void setV(Double v) {
        this.v = v;
    }

    public Double getW() {
        return this.w;
    }

    public Catalogcolors w(Double w) {
        this.setW(w);
        return this;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public Double getX() {
        return this.x;
    }

    public Catalogcolors x(Double x) {
        this.setX(x);
        return this;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return this.y;
    }

    public Catalogcolors y(Double y) {
        this.setY(y);
        return this;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return this.z;
    }

    public Catalogcolors z(Double z) {
        this.setZ(z);
        return this;
    }

    public void setZ(Double z) {
        this.z = z;
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

    public Catalogcolors setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Catalogs getCatalog() {
        return this.catalog;
    }

    public void setCatalog(Catalogs catalogs) {
        this.catalog = catalogs;
    }

    public Catalogcolors catalog(Catalogs catalogs) {
        this.setCatalog(catalogs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Catalogcolors)) {
            return false;
        }
        return id != null && id.equals(((Catalogcolors) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Catalogcolors{" +
            "id=" + getId() +
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
            "}";
    }
}
