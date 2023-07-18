package biz.daich.tambour.shlicht.service;

import biz.daich.tambour.shlicht.domain.*; // for static metamodels
import biz.daich.tambour.shlicht.domain.Catalogs;
import biz.daich.tambour.shlicht.repository.CatalogsRepository;
import biz.daich.tambour.shlicht.service.criteria.CatalogsCriteria;
import biz.daich.tambour.shlicht.service.dto.CatalogsDTO;
import biz.daich.tambour.shlicht.service.mapper.CatalogsMapper;
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
 * Service for executing complex queries for {@link Catalogs} entities in the database.
 * The main input is a {@link CatalogsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CatalogsDTO} or a {@link Page} of {@link CatalogsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CatalogsQueryService extends QueryService<Catalogs> {

    private final Logger log = LoggerFactory.getLogger(CatalogsQueryService.class);

    private final CatalogsRepository catalogsRepository;

    private final CatalogsMapper catalogsMapper;

    public CatalogsQueryService(CatalogsRepository catalogsRepository, CatalogsMapper catalogsMapper) {
        this.catalogsRepository = catalogsRepository;
        this.catalogsMapper = catalogsMapper;
    }

    /**
     * Return a {@link List} of {@link CatalogsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CatalogsDTO> findByCriteria(CatalogsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Catalogs> specification = createSpecification(criteria);
        return catalogsMapper.toDto(catalogsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CatalogsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CatalogsDTO> findByCriteria(CatalogsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Catalogs> specification = createSpecification(criteria);
        return catalogsRepository.findAll(specification, page).map(catalogsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CatalogsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Catalogs> specification = createSpecification(criteria);
        return catalogsRepository.count(specification);
    }

    /**
     * Function to convert {@link CatalogsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Catalogs> createSpecification(CatalogsCriteria criteria) {
        Specification<Catalogs> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getId(), Catalogs_.id));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Catalogs_.isActive));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), Catalogs_.createdTime));
            }
            if (criteria.getCatalogColorsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCatalogColorsId(),
                            root -> root.join(Catalogs_.catalogColors, JoinType.LEFT).get(Catalogcolors_.id)
                        )
                    );
            }
            if (criteria.getIdId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getIdId(), root -> root.join(Catalogs_.ids, JoinType.LEFT).get(Batches_.id))
                    );
            }
        }
        return specification;
    }
}
