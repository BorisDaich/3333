package biz.daich.tambour.shlicht.service;

import biz.daich.tambour.shlicht.domain.*; // for static metamodels
import biz.daich.tambour.shlicht.domain.Batches;
import biz.daich.tambour.shlicht.repository.BatchesRepository;
import biz.daich.tambour.shlicht.service.criteria.BatchesCriteria;
import biz.daich.tambour.shlicht.service.dto.BatchesDTO;
import biz.daich.tambour.shlicht.service.mapper.BatchesMapper;
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
 * Service for executing complex queries for {@link Batches} entities in the database.
 * The main input is a {@link BatchesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BatchesDTO} or a {@link Page} of {@link BatchesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BatchesQueryService extends QueryService<Batches> {

    private final Logger log = LoggerFactory.getLogger(BatchesQueryService.class);

    private final BatchesRepository batchesRepository;

    private final BatchesMapper batchesMapper;

    public BatchesQueryService(BatchesRepository batchesRepository, BatchesMapper batchesMapper) {
        this.batchesRepository = batchesRepository;
        this.batchesMapper = batchesMapper;
    }

    /**
     * Return a {@link List} of {@link BatchesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BatchesDTO> findByCriteria(BatchesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Batches> specification = createSpecification(criteria);
        return batchesMapper.toDto(batchesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BatchesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BatchesDTO> findByCriteria(BatchesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Batches> specification = createSpecification(criteria);
        return batchesRepository.findAll(specification, page).map(batchesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BatchesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Batches> specification = createSpecification(criteria);
        return batchesRepository.count(specification);
    }

    /**
     * Function to convert {@link BatchesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Batches> createSpecification(BatchesCriteria criteria) {
        Specification<Batches> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getId(), Batches_.id));
            }
            if (criteria.getPoName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPoName(), Batches_.poName));
            }
            if (criteria.getSequenceInPo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSequenceInPo(), Batches_.sequenceInPo));
            }
            if (criteria.getPreviousProductionBatchId() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getPreviousProductionBatchId(), Batches_.previousProductionBatchId)
                    );
            }
            if (criteria.getState() != null) {
                specification = specification.and(buildStringSpecification(criteria.getState(), Batches_.state));
            }
            if (criteria.getInspectionSequence() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInspectionSequence(), Batches_.inspectionSequence));
            }
            if (criteria.getOrderedQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderedQuantity(), Batches_.orderedQuantity));
            }
            if (criteria.getProducingQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProducingQuantity(), Batches_.producingQuantity));
            }
            if (criteria.getTotalProducingQuantity() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalProducingQuantity(), Batches_.totalProducingQuantity));
            }
            if (criteria.getRemainingQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRemainingQuantity(), Batches_.remainingQuantity));
            }
            if (criteria.getTotalremainingQuantity() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalremainingQuantity(), Batches_.totalremainingQuantity));
            }
            if (criteria.getInspectedQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInspectedQuantity(), Batches_.inspectedQuantity));
            }
            if (criteria.getTotalInspectedQuantity() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalInspectedQuantity(), Batches_.totalInspectedQuantity));
            }
            if (criteria.getFailedQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFailedQuantity(), Batches_.failedQuantity));
            }
            if (criteria.getTotalFailedQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalFailedQuantity(), Batches_.totalFailedQuantity));
            }
            if (criteria.getColorId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColorId(), Batches_.colorId));
            }
            if (criteria.getColorBasematerial() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorBasematerial(), Batches_.colorBasematerial));
            }
            if (criteria.getColorLabL() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorLabL(), Batches_.colorLabL));
            }
            if (criteria.getColorLabA() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorLabA(), Batches_.colorLabA));
            }
            if (criteria.getColorLabB() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorLabB(), Batches_.colorLabB));
            }
            if (criteria.getColorA() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorA(), Batches_.colorA));
            }
            if (criteria.getColorB() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorB(), Batches_.colorB));
            }
            if (criteria.getColorC() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorC(), Batches_.colorC));
            }
            if (criteria.getColorD() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorD(), Batches_.colorD));
            }
            if (criteria.getColorE() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorE(), Batches_.colorE));
            }
            if (criteria.getColorF() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorF(), Batches_.colorF));
            }
            if (criteria.getColorG() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorG(), Batches_.colorG));
            }
            if (criteria.getColorH() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorH(), Batches_.colorH));
            }
            if (criteria.getColorI() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorI(), Batches_.colorI));
            }
            if (criteria.getColorJ() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorJ(), Batches_.colorJ));
            }
            if (criteria.getColorK() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorK(), Batches_.colorK));
            }
            if (criteria.getColorL() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorL(), Batches_.colorL));
            }
            if (criteria.getColorM() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorM(), Batches_.colorM));
            }
            if (criteria.getColorN() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorN(), Batches_.colorN));
            }
            if (criteria.getColorO() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorO(), Batches_.colorO));
            }
            if (criteria.getColorP() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorP(), Batches_.colorP));
            }
            if (criteria.getColorQ() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorQ(), Batches_.colorQ));
            }
            if (criteria.getColorR() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorR(), Batches_.colorR));
            }
            if (criteria.getColorS() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorS(), Batches_.colorS));
            }
            if (criteria.getColorT() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorT(), Batches_.colorT));
            }
            if (criteria.getColorU() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorU(), Batches_.colorU));
            }
            if (criteria.getColorV() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorV(), Batches_.colorV));
            }
            if (criteria.getColorW() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorW(), Batches_.colorW));
            }
            if (criteria.getColorX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorX(), Batches_.colorX));
            }
            if (criteria.getColorY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorY(), Batches_.colorY));
            }
            if (criteria.getColorZ() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColorZ(), Batches_.colorZ));
            }
            if (criteria.getCatalogId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCatalogId(), Batches_.catalogId));
            }
            if (criteria.getCatalogIsactive() != null) {
                specification = specification.and(buildSpecification(criteria.getCatalogIsactive(), Batches_.catalogIsactive));
            }
            if (criteria.getCatalogCreatedtime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCatalogCreatedtime(), Batches_.catalogCreatedtime));
            }
            if (criteria.getBaseMaterialId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBaseMaterialId(), Batches_.baseMaterialId));
            }
            if (criteria.getOrderedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderedTime(), Batches_.orderedTime));
            }
            if (criteria.getStartedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartedTime(), Batches_.startedTime));
            }
            if (criteria.getModifiedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifiedTime(), Batches_.modifiedTime));
            }
            if (criteria.getSuspendedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSuspendedTime(), Batches_.suspendedTime));
            }
            if (criteria.getFinishedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinishedTime(), Batches_.finishedTime));
            }
            if (criteria.getColorCatalogId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getColorCatalogId(),
                            root -> root.join(Batches_.colorCatalog, JoinType.LEFT).get(Catalogs_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
