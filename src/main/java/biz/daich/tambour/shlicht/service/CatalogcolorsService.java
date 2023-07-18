package biz.daich.tambour.shlicht.service;

import biz.daich.tambour.shlicht.domain.Catalogcolors;
import biz.daich.tambour.shlicht.repository.CatalogcolorsRepository;
import biz.daich.tambour.shlicht.service.dto.CatalogcolorsDTO;
import biz.daich.tambour.shlicht.service.mapper.CatalogcolorsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Catalogcolors}.
 */
@Service
@Transactional
public class CatalogcolorsService {

    private final Logger log = LoggerFactory.getLogger(CatalogcolorsService.class);

    private final CatalogcolorsRepository catalogcolorsRepository;

    private final CatalogcolorsMapper catalogcolorsMapper;

    public CatalogcolorsService(CatalogcolorsRepository catalogcolorsRepository, CatalogcolorsMapper catalogcolorsMapper) {
        this.catalogcolorsRepository = catalogcolorsRepository;
        this.catalogcolorsMapper = catalogcolorsMapper;
    }

    /**
     * Save a catalogcolors.
     *
     * @param catalogcolorsDTO the entity to save.
     * @return the persisted entity.
     */
    public CatalogcolorsDTO save(CatalogcolorsDTO catalogcolorsDTO) {
        log.debug("Request to save Catalogcolors : {}", catalogcolorsDTO);
        Catalogcolors catalogcolors = catalogcolorsMapper.toEntity(catalogcolorsDTO);
        catalogcolors = catalogcolorsRepository.save(catalogcolors);
        return catalogcolorsMapper.toDto(catalogcolors);
    }

    /**
     * Update a catalogcolors.
     *
     * @param catalogcolorsDTO the entity to save.
     * @return the persisted entity.
     */
    public CatalogcolorsDTO update(CatalogcolorsDTO catalogcolorsDTO) {
        log.debug("Request to update Catalogcolors : {}", catalogcolorsDTO);
        Catalogcolors catalogcolors = catalogcolorsMapper.toEntity(catalogcolorsDTO);
        catalogcolors.setIsPersisted();
        catalogcolors = catalogcolorsRepository.save(catalogcolors);
        return catalogcolorsMapper.toDto(catalogcolors);
    }

    /**
     * Partially update a catalogcolors.
     *
     * @param catalogcolorsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CatalogcolorsDTO> partialUpdate(CatalogcolorsDTO catalogcolorsDTO) {
        log.debug("Request to partially update Catalogcolors : {}", catalogcolorsDTO);

        return catalogcolorsRepository
            .findById(catalogcolorsDTO.getId())
            .map(existingCatalogcolors -> {
                catalogcolorsMapper.partialUpdate(existingCatalogcolors, catalogcolorsDTO);

                return existingCatalogcolors;
            })
            .map(catalogcolorsRepository::save)
            .map(catalogcolorsMapper::toDto);
    }

    /**
     * Get all the catalogcolors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CatalogcolorsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Catalogcolors");
        return catalogcolorsRepository.findAll(pageable).map(catalogcolorsMapper::toDto);
    }

    /**
     * Get one catalogcolors by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CatalogcolorsDTO> findOne(String id) {
        log.debug("Request to get Catalogcolors : {}", id);
        return catalogcolorsRepository.findById(id).map(catalogcolorsMapper::toDto);
    }

    /**
     * Delete the catalogcolors by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Catalogcolors : {}", id);
        catalogcolorsRepository.deleteById(id);
    }
}
