package biz.daich.tambour.shlicht.service;

import biz.daich.tambour.shlicht.domain.*; // for static metamodels
import biz.daich.tambour.shlicht.domain.Catalogcolors;
import biz.daich.tambour.shlicht.repository.CatalogcolorsRepository;
import biz.daich.tambour.shlicht.service.criteria.CatalogcolorsCriteria;
import biz.daich.tambour.shlicht.service.dto.CatalogcolorsDTO;
import biz.daich.tambour.shlicht.service.mapper.CatalogcolorsMapper;
import jakarta.persistence.criteria.JoinType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Catalogcolors} entities in the database.
 * The main input is a {@link CatalogcolorsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CatalogcolorsDTO} or a {@link Page} of {@link CatalogcolorsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CatalogcolorsQueryService extends QueryService<Catalogcolors> {

    private final Logger log = LoggerFactory.getLogger(CatalogcolorsQueryService.class);

    private final CatalogcolorsRepository catalogcolorsRepository;

    private final CatalogcolorsMapper catalogcolorsMapper;

    public CatalogcolorsQueryService(CatalogcolorsRepository catalogcolorsRepository, CatalogcolorsMapper catalogcolorsMapper) {
        this.catalogcolorsRepository = catalogcolorsRepository;
        this.catalogcolorsMapper = catalogcolorsMapper;
    }

    /**
     * Return a {@link List} of {@link CatalogcolorsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CatalogcolorsDTO> findByCriteria(CatalogcolorsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Catalogcolors> specification = createSpecification(criteria);
        return catalogcolorsMapper.toDto(catalogcolorsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CatalogcolorsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CatalogcolorsDTO> findByCriteria(CatalogcolorsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Catalogcolors> specification = createSpecification(criteria);
        return catalogcolorsRepository.findAll(specification, page).map(catalogcolorsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CatalogcolorsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Catalogcolors> specification = createSpecification(criteria);
        return catalogcolorsRepository.count(specification);
    }

    /**
     * Function to convert {@link CatalogcolorsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Catalogcolors> createSpecification(CatalogcolorsCriteria criteria) {
        Specification<Catalogcolors> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getId(), Catalogcolors_.id));
            }
            if (criteria.getBaseMaterial() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBaseMaterial(), Catalogcolors_.baseMaterial));
            }
            if (criteria.getLabL() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLabL(), Catalogcolors_.labL));
            }
            if (criteria.getLabA() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLabA(), Catalogcolors_.labA));
            }
            if (criteria.getLabB() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLabB(), Catalogcolors_.labB));
            }
            if (criteria.getA() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getA(), Catalogcolors_.a));
            }
            if (criteria.getB() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getB(), Catalogcolors_.b));
            }
            if (criteria.getC() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getC(), Catalogcolors_.c));
            }
            if (criteria.getD() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getD(), Catalogcolors_.d));
            }
            if (criteria.getE() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getE(), Catalogcolors_.e));
            }
            if (criteria.getF() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getF(), Catalogcolors_.f));
            }
            if (criteria.getG() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getG(), Catalogcolors_.g));
            }
            if (criteria.getH() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getH(), Catalogcolors_.h));
            }
            if (criteria.getI() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getI(), Catalogcolors_.i));
            }
            if (criteria.getJ() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getJ(), Catalogcolors_.j));
            }
            if (criteria.getK() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getK(), Catalogcolors_.k));
            }
            if (criteria.getL() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getL(), Catalogcolors_.l));
            }
            if (criteria.getM() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getM(), Catalogcolors_.m));
            }
            if (criteria.getN() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getN(), Catalogcolors_.n));
            }
            if (criteria.getO() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getO(), Catalogcolors_.o));
            }
            if (criteria.getP() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getP(), Catalogcolors_.p));
            }
            if (criteria.getQ() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQ(), Catalogcolors_.q));
            }
            if (criteria.getR() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getR(), Catalogcolors_.r));
            }
            if (criteria.getS() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getS(), Catalogcolors_.s));
            }
            if (criteria.getT() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getT(), Catalogcolors_.t));
            }
            if (criteria.getU() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getU(), Catalogcolors_.u));
            }
            if (criteria.getV() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getV(), Catalogcolors_.v));
            }
            if (criteria.getW() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getW(), Catalogcolors_.w));
            }
            if (criteria.getX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getX(), Catalogcolors_.x));
            }
            if (criteria.getY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getY(), Catalogcolors_.y));
            }
            if (criteria.getZ() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getZ(), Catalogcolors_.z));
            }
            if (criteria.getCatalogId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCatalogId(),
                            root -> root.join(Catalogcolors_.catalog, JoinType.LEFT).get(Catalogs_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
