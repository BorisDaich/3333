package biz.daich.tambour.shlicht.service;

import biz.daich.tambour.shlicht.domain.Scans;
import biz.daich.tambour.shlicht.repository.ScansRepository;
import biz.daich.tambour.shlicht.service.dto.ScansDTO;
import biz.daich.tambour.shlicht.service.mapper.ScansMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Scans}.
 */
@Service
@Transactional
public class ScansService {

    private final Logger log = LoggerFactory.getLogger(ScansService.class);

    private final ScansRepository scansRepository;

    private final ScansMapper scansMapper;

    public ScansService(ScansRepository scansRepository, ScansMapper scansMapper) {
        this.scansRepository = scansRepository;
        this.scansMapper = scansMapper;
    }

    /**
     * Save a scans.
     *
     * @param scansDTO the entity to save.
     * @return the persisted entity.
     */
    public ScansDTO save(ScansDTO scansDTO) {
        log.debug("Request to save Scans : {}", scansDTO);
        Scans scans = scansMapper.toEntity(scansDTO);
        scans = scansRepository.save(scans);
        return scansMapper.toDto(scans);
    }

    /**
     * Update a scans.
     *
     * @param scansDTO the entity to save.
     * @return the persisted entity.
     */
    public ScansDTO update(ScansDTO scansDTO) {
        log.debug("Request to update Scans : {}", scansDTO);
        Scans scans = scansMapper.toEntity(scansDTO);
        scans.setIsPersisted();
        scans = scansRepository.save(scans);
        return scansMapper.toDto(scans);
    }

    /**
     * Partially update a scans.
     *
     * @param scansDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ScansDTO> partialUpdate(ScansDTO scansDTO) {
        log.debug("Request to partially update Scans : {}", scansDTO);

        return scansRepository
            .findById(scansDTO.getId())
            .map(existingScans -> {
                scansMapper.partialUpdate(existingScans, scansDTO);

                return existingScans;
            })
            .map(scansRepository::save)
            .map(scansMapper::toDto);
    }

    /**
     * Get all the scans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ScansDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Scans");
        return scansRepository.findAll(pageable).map(scansMapper::toDto);
    }

    /**
     * Get one scans by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ScansDTO> findOne(String id) {
        log.debug("Request to get Scans : {}", id);
        return scansRepository.findById(id).map(scansMapper::toDto);
    }

    /**
     * Delete the scans by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Scans : {}", id);
        scansRepository.deleteById(id);
    }
}
