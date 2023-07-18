package biz.daich.tambour.shlicht.service;

import biz.daich.tambour.shlicht.domain.*; // for static metamodels
import biz.daich.tambour.shlicht.domain.Scans;
import biz.daich.tambour.shlicht.repository.ScansRepository;
import biz.daich.tambour.shlicht.service.criteria.ScansCriteria;
import biz.daich.tambour.shlicht.service.dto.ScansDTO;
import biz.daich.tambour.shlicht.service.mapper.ScansMapper;
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
 * Service for executing complex queries for {@link Scans} entities in the database.
 * The main input is a {@link ScansCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ScansDTO} or a {@link Page} of {@link ScansDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ScansQueryService extends QueryService<Scans> {

    private final Logger log = LoggerFactory.getLogger(ScansQueryService.class);

    private final ScansRepository scansRepository;

    private final ScansMapper scansMapper;

    public ScansQueryService(ScansRepository scansRepository, ScansMapper scansMapper) {
        this.scansRepository = scansRepository;
        this.scansMapper = scansMapper;
    }

    /**
     * Return a {@link List} of {@link ScansDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ScansDTO> findByCriteria(ScansCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Scans> specification = createSpecification(criteria);
        return scansMapper.toDto(scansRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ScansDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ScansDTO> findByCriteria(ScansCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Scans> specification = createSpecification(criteria);
        return scansRepository.findAll(specification, page).map(scansMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ScansCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Scans> specification = createSpecification(criteria);
        return scansRepository.count(specification);
    }

    /**
     * Function to convert {@link ScansCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Scans> createSpecification(ScansCriteria criteria) {
        Specification<Scans> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getId(), Scans_.id));
            }
            if (criteria.getSequenceInBatch() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSequenceInBatch(), Scans_.sequenceInBatch));
            }
            if (criteria.getState() != null) {
                specification = specification.and(buildStringSpecification(criteria.getState(), Scans_.state));
            }
            if (criteria.getdE() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getdE(), Scans_.dE));
            }
            if (criteria.getCreatedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedTime(), Scans_.createdTime));
            }
            if (criteria.getScannedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScannedTime(), Scans_.scannedTime));
            }
            if (criteria.getInspectedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInspectedTime(), Scans_.inspectedTime));
            }
            if (criteria.getModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifiedTime(), Scans_.modifiedTime));
            }
            if (criteria.getEjectedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEjectedTime(), Scans_.ejectedTime));
            }
            if (criteria.getImageId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getImageId(), root -> root.join(Scans_.image, JoinType.LEFT).get(Images_.id))
                    );
            }
            if (criteria.getProductionBatchId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getProductionBatchId(),
                            root -> root.join(Scans_.productionBatch, JoinType.LEFT).get(Batches_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
