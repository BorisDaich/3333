package biz.daich.tambour.shlicht.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link biz.daich.tambour.shlicht.domain.Catalogcolors} entity. This class is used
 * in {@link biz.daich.tambour.shlicht.web.rest.CatalogcolorsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /catalogcolors?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CatalogcolorsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter id;

    private LongFilter baseMaterial;

    private DoubleFilter labL;

    private DoubleFilter labA;

    private DoubleFilter labB;

    private DoubleFilter a;

    private DoubleFilter b;

    private DoubleFilter c;

    private DoubleFilter d;

    private DoubleFilter e;

    private DoubleFilter f;

    private DoubleFilter g;

    private DoubleFilter h;

    private DoubleFilter i;

    private DoubleFilter j;

    private DoubleFilter k;

    private DoubleFilter l;

    private DoubleFilter m;

    private DoubleFilter n;

    private DoubleFilter o;

    private DoubleFilter p;

    private DoubleFilter q;

    private DoubleFilter r;

    private DoubleFilter s;

    private DoubleFilter t;

    private DoubleFilter u;

    private DoubleFilter v;

    private DoubleFilter w;

    private DoubleFilter x;

    private DoubleFilter y;

    private DoubleFilter z;

    private StringFilter catalogId;

    private Boolean distinct;

    public CatalogcolorsCriteria() {}

    public CatalogcolorsCriteria(CatalogcolorsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.baseMaterial = other.baseMaterial == null ? null : other.baseMaterial.copy();
        this.labL = other.labL == null ? null : other.labL.copy();
        this.labA = other.labA == null ? null : other.labA.copy();
        this.labB = other.labB == null ? null : other.labB.copy();
        this.a = other.a == null ? null : other.a.copy();
        this.b = other.b == null ? null : other.b.copy();
        this.c = other.c == null ? null : other.c.copy();
        this.d = other.d == null ? null : other.d.copy();
        this.e = other.e == null ? null : other.e.copy();
        this.f = other.f == null ? null : other.f.copy();
        this.g = other.g == null ? null : other.g.copy();
        this.h = other.h == null ? null : other.h.copy();
        this.i = other.i == null ? null : other.i.copy();
        this.j = other.j == null ? null : other.j.copy();
        this.k = other.k == null ? null : other.k.copy();
        this.l = other.l == null ? null : other.l.copy();
        this.m = other.m == null ? null : other.m.copy();
        this.n = other.n == null ? null : other.n.copy();
        this.o = other.o == null ? null : other.o.copy();
        this.p = other.p == null ? null : other.p.copy();
        this.q = other.q == null ? null : other.q.copy();
        this.r = other.r == null ? null : other.r.copy();
        this.s = other.s == null ? null : other.s.copy();
        this.t = other.t == null ? null : other.t.copy();
        this.u = other.u == null ? null : other.u.copy();
        this.v = other.v == null ? null : other.v.copy();
        this.w = other.w == null ? null : other.w.copy();
        this.x = other.x == null ? null : other.x.copy();
        this.y = other.y == null ? null : other.y.copy();
        this.z = other.z == null ? null : other.z.copy();
        this.catalogId = other.catalogId == null ? null : other.catalogId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CatalogcolorsCriteria copy() {
        return new CatalogcolorsCriteria(this);
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

    public LongFilter getBaseMaterial() {
        return baseMaterial;
    }

    public LongFilter baseMaterial() {
        if (baseMaterial == null) {
            baseMaterial = new LongFilter();
        }
        return baseMaterial;
    }

    public void setBaseMaterial(LongFilter baseMaterial) {
        this.baseMaterial = baseMaterial;
    }

    public DoubleFilter getLabL() {
        return labL;
    }

    public DoubleFilter labL() {
        if (labL == null) {
            labL = new DoubleFilter();
        }
        return labL;
    }

    public void setLabL(DoubleFilter labL) {
        this.labL = labL;
    }

    public DoubleFilter getLabA() {
        return labA;
    }

    public DoubleFilter labA() {
        if (labA == null) {
            labA = new DoubleFilter();
        }
        return labA;
    }

    public void setLabA(DoubleFilter labA) {
        this.labA = labA;
    }

    public DoubleFilter getLabB() {
        return labB;
    }

    public DoubleFilter labB() {
        if (labB == null) {
            labB = new DoubleFilter();
        }
        return labB;
    }

    public void setLabB(DoubleFilter labB) {
        this.labB = labB;
    }

    public DoubleFilter getA() {
        return a;
    }

    public DoubleFilter a() {
        if (a == null) {
            a = new DoubleFilter();
        }
        return a;
    }

    public void setA(DoubleFilter a) {
        this.a = a;
    }

    public DoubleFilter getB() {
        return b;
    }

    public DoubleFilter b() {
        if (b == null) {
            b = new DoubleFilter();
        }
        return b;
    }

    public void setB(DoubleFilter b) {
        this.b = b;
    }

    public DoubleFilter getC() {
        return c;
    }

    public DoubleFilter c() {
        if (c == null) {
            c = new DoubleFilter();
        }
        return c;
    }

    public void setC(DoubleFilter c) {
        this.c = c;
    }

    public DoubleFilter getD() {
        return d;
    }

    public DoubleFilter d() {
        if (d == null) {
            d = new DoubleFilter();
        }
        return d;
    }

    public void setD(DoubleFilter d) {
        this.d = d;
    }

    public DoubleFilter getE() {
        return e;
    }

    public DoubleFilter e() {
        if (e == null) {
            e = new DoubleFilter();
        }
        return e;
    }

    public void setE(DoubleFilter e) {
        this.e = e;
    }

    public DoubleFilter getF() {
        return f;
    }

    public DoubleFilter f() {
        if (f == null) {
            f = new DoubleFilter();
        }
        return f;
    }

    public void setF(DoubleFilter f) {
        this.f = f;
    }

    public DoubleFilter getG() {
        return g;
    }

    public DoubleFilter g() {
        if (g == null) {
            g = new DoubleFilter();
        }
        return g;
    }

    public void setG(DoubleFilter g) {
        this.g = g;
    }

    public DoubleFilter getH() {
        return h;
    }

    public DoubleFilter h() {
        if (h == null) {
            h = new DoubleFilter();
        }
        return h;
    }

    public void setH(DoubleFilter h) {
        this.h = h;
    }

    public DoubleFilter getI() {
        return i;
    }

    public DoubleFilter i() {
        if (i == null) {
            i = new DoubleFilter();
        }
        return i;
    }

    public void setI(DoubleFilter i) {
        this.i = i;
    }

    public DoubleFilter getJ() {
        return j;
    }

    public DoubleFilter j() {
        if (j == null) {
            j = new DoubleFilter();
        }
        return j;
    }

    public void setJ(DoubleFilter j) {
        this.j = j;
    }

    public DoubleFilter getK() {
        return k;
    }

    public DoubleFilter k() {
        if (k == null) {
            k = new DoubleFilter();
        }
        return k;
    }

    public void setK(DoubleFilter k) {
        this.k = k;
    }

    public DoubleFilter getL() {
        return l;
    }

    public DoubleFilter l() {
        if (l == null) {
            l = new DoubleFilter();
        }
        return l;
    }

    public void setL(DoubleFilter l) {
        this.l = l;
    }

    public DoubleFilter getM() {
        return m;
    }

    public DoubleFilter m() {
        if (m == null) {
            m = new DoubleFilter();
        }
        return m;
    }

    public void setM(DoubleFilter m) {
        this.m = m;
    }

    public DoubleFilter getN() {
        return n;
    }

    public DoubleFilter n() {
        if (n == null) {
            n = new DoubleFilter();
        }
        return n;
    }

    public void setN(DoubleFilter n) {
        this.n = n;
    }

    public DoubleFilter getO() {
        return o;
    }

    public DoubleFilter o() {
        if (o == null) {
            o = new DoubleFilter();
        }
        return o;
    }

    public void setO(DoubleFilter o) {
        this.o = o;
    }

    public DoubleFilter getP() {
        return p;
    }

    public DoubleFilter p() {
        if (p == null) {
            p = new DoubleFilter();
        }
        return p;
    }

    public void setP(DoubleFilter p) {
        this.p = p;
    }

    public DoubleFilter getQ() {
        return q;
    }

    public DoubleFilter q() {
        if (q == null) {
            q = new DoubleFilter();
        }
        return q;
    }

    public void setQ(DoubleFilter q) {
        this.q = q;
    }

    public DoubleFilter getR() {
        return r;
    }

    public DoubleFilter r() {
        if (r == null) {
            r = new DoubleFilter();
        }
        return r;
    }

    public void setR(DoubleFilter r) {
        this.r = r;
    }

    public DoubleFilter getS() {
        return s;
    }

    public DoubleFilter s() {
        if (s == null) {
            s = new DoubleFilter();
        }
        return s;
    }

    public void setS(DoubleFilter s) {
        this.s = s;
    }

    public DoubleFilter getT() {
        return t;
    }

    public DoubleFilter t() {
        if (t == null) {
            t = new DoubleFilter();
        }
        return t;
    }

    public void setT(DoubleFilter t) {
        this.t = t;
    }

    public DoubleFilter getU() {
        return u;
    }

    public DoubleFilter u() {
        if (u == null) {
            u = new DoubleFilter();
        }
        return u;
    }

    public void setU(DoubleFilter u) {
        this.u = u;
    }

    public DoubleFilter getV() {
        return v;
    }

    public DoubleFilter v() {
        if (v == null) {
            v = new DoubleFilter();
        }
        return v;
    }

    public void setV(DoubleFilter v) {
        this.v = v;
    }

    public DoubleFilter getW() {
        return w;
    }

    public DoubleFilter w() {
        if (w == null) {
            w = new DoubleFilter();
        }
        return w;
    }

    public void setW(DoubleFilter w) {
        this.w = w;
    }

    public DoubleFilter getX() {
        return x;
    }

    public DoubleFilter x() {
        if (x == null) {
            x = new DoubleFilter();
        }
        return x;
    }

    public void setX(DoubleFilter x) {
        this.x = x;
    }

    public DoubleFilter getY() {
        return y;
    }

    public DoubleFilter y() {
        if (y == null) {
            y = new DoubleFilter();
        }
        return y;
    }

    public void setY(DoubleFilter y) {
        this.y = y;
    }

    public DoubleFilter getZ() {
        return z;
    }

    public DoubleFilter z() {
        if (z == null) {
            z = new DoubleFilter();
        }
        return z;
    }

    public void setZ(DoubleFilter z) {
        this.z = z;
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
        final CatalogcolorsCriteria that = (CatalogcolorsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(baseMaterial, that.baseMaterial) &&
            Objects.equals(labL, that.labL) &&
            Objects.equals(labA, that.labA) &&
            Objects.equals(labB, that.labB) &&
            Objects.equals(a, that.a) &&
            Objects.equals(b, that.b) &&
            Objects.equals(c, that.c) &&
            Objects.equals(d, that.d) &&
            Objects.equals(e, that.e) &&
            Objects.equals(f, that.f) &&
            Objects.equals(g, that.g) &&
            Objects.equals(h, that.h) &&
            Objects.equals(i, that.i) &&
            Objects.equals(j, that.j) &&
            Objects.equals(k, that.k) &&
            Objects.equals(l, that.l) &&
            Objects.equals(m, that.m) &&
            Objects.equals(n, that.n) &&
            Objects.equals(o, that.o) &&
            Objects.equals(p, that.p) &&
            Objects.equals(q, that.q) &&
            Objects.equals(r, that.r) &&
            Objects.equals(s, that.s) &&
            Objects.equals(t, that.t) &&
            Objects.equals(u, that.u) &&
            Objects.equals(v, that.v) &&
            Objects.equals(w, that.w) &&
            Objects.equals(x, that.x) &&
            Objects.equals(y, that.y) &&
            Objects.equals(z, that.z) &&
            Objects.equals(catalogId, that.catalogId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            baseMaterial,
            labL,
            labA,
            labB,
            a,
            b,
            c,
            d,
            e,
            f,
            g,
            h,
            i,
            j,
            k,
            l,
            m,
            n,
            o,
            p,
            q,
            r,
            s,
            t,
            u,
            v,
            w,
            x,
            y,
            z,
            catalogId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CatalogcolorsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (baseMaterial != null ? "baseMaterial=" + baseMaterial + ", " : "") +
            (labL != null ? "labL=" + labL + ", " : "") +
            (labA != null ? "labA=" + labA + ", " : "") +
            (labB != null ? "labB=" + labB + ", " : "") +
            (a != null ? "a=" + a + ", " : "") +
            (b != null ? "b=" + b + ", " : "") +
            (c != null ? "c=" + c + ", " : "") +
            (d != null ? "d=" + d + ", " : "") +
            (e != null ? "e=" + e + ", " : "") +
            (f != null ? "f=" + f + ", " : "") +
            (g != null ? "g=" + g + ", " : "") +
            (h != null ? "h=" + h + ", " : "") +
            (i != null ? "i=" + i + ", " : "") +
            (j != null ? "j=" + j + ", " : "") +
            (k != null ? "k=" + k + ", " : "") +
            (l != null ? "l=" + l + ", " : "") +
            (m != null ? "m=" + m + ", " : "") +
            (n != null ? "n=" + n + ", " : "") +
            (o != null ? "o=" + o + ", " : "") +
            (p != null ? "p=" + p + ", " : "") +
            (q != null ? "q=" + q + ", " : "") +
            (r != null ? "r=" + r + ", " : "") +
            (s != null ? "s=" + s + ", " : "") +
            (t != null ? "t=" + t + ", " : "") +
            (u != null ? "u=" + u + ", " : "") +
            (v != null ? "v=" + v + ", " : "") +
            (w != null ? "w=" + w + ", " : "") +
            (x != null ? "x=" + x + ", " : "") +
            (y != null ? "y=" + y + ", " : "") +
            (z != null ? "z=" + z + ", " : "") +
            (catalogId != null ? "catalogId=" + catalogId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
