package biz.daich.tambour.shlicht.service;

import biz.daich.tambour.shlicht.domain.Batches;
import biz.daich.tambour.shlicht.repository.BatchesRepository;
import biz.daich.tambour.shlicht.service.dto.BatchesDTO;
import biz.daich.tambour.shlicht.service.mapper.BatchesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Batches}.
 */
@Service
@Transactional
public class BatchesService {

    private final Logger log = LoggerFactory.getLogger(BatchesService.class);

    private final BatchesRepository batchesRepository;

    private final BatchesMapper batchesMapper;

    public BatchesService(BatchesRepository batchesRepository, BatchesMapper batchesMapper) {
        this.batchesRepository = batchesRepository;
        this.batchesMapper = batchesMapper;
    }

    /**
     * Save a batches.
     *
     * @param batchesDTO the entity to save.
     * @return the persisted entity.
     */
    public BatchesDTO save(BatchesDTO batchesDTO) {
        log.debug("Request to save Batches : {}", batchesDTO);
        Batches batches = batchesMapper.toEntity(batchesDTO);
        batches = batchesRepository.save(batches);
        return batchesMapper.toDto(batches);
    }

    /**
     * Update a batches.
     *
     * @param batchesDTO the entity to save.
     * @return the persisted entity.
     */
    public BatchesDTO update(BatchesDTO batchesDTO) {
        log.debug("Request to update Batches : {}", batchesDTO);
        Batches batches = batchesMapper.toEntity(batchesDTO);
        batches.setIsPersisted();
        batches = batchesRepository.save(batches);
        return batchesMapper.toDto(batches);
    }

    /**
     * Partially update a batches.
     *
     * @param batchesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BatchesDTO> partialUpdate(BatchesDTO batchesDTO) {
        log.debug("Request to partially update Batches : {}", batchesDTO);

        return batchesRepository
            .findById(batchesDTO.getId())
            .map(existingBatches -> {
                batchesMapper.partialUpdate(existingBatches, batchesDTO);

                return existingBatches;
            })
            .map(batchesRepository::save)
            .map(batchesMapper::toDto);
    }

    /**
     * Get all the batches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BatchesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Batches");
        return batchesRepository.findAll(pageable).map(batchesMapper::toDto);
    }

    /**
     * Get one batches by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BatchesDTO> findOne(String id) {
        log.debug("Request to get Batches : {}", id);
        return batchesRepository.findById(id).map(batchesMapper::toDto);
    }

    /**
     * Delete the batches by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Batches : {}", id);
        batchesRepository.deleteById(id);
    }
}
