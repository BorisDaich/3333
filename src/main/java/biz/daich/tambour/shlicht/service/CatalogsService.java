package biz.daich.tambour.shlicht.service;

import biz.daich.tambour.shlicht.domain.Catalogs;
import biz.daich.tambour.shlicht.repository.CatalogsRepository;
import biz.daich.tambour.shlicht.service.dto.CatalogsDTO;
import biz.daich.tambour.shlicht.service.mapper.CatalogsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Catalogs}.
 */
@Service
@Transactional
public class CatalogsService {

    private final Logger log = LoggerFactory.getLogger(CatalogsService.class);

    private final CatalogsRepository catalogsRepository;

    private final CatalogsMapper catalogsMapper;

    public CatalogsService(CatalogsRepository catalogsRepository, CatalogsMapper catalogsMapper) {
        this.catalogsRepository = catalogsRepository;
        this.catalogsMapper = catalogsMapper;
    }

    /**
     * Save a catalogs.
     *
     * @param catalogsDTO the entity to save.
     * @return the persisted entity.
     */
    public CatalogsDTO save(CatalogsDTO catalogsDTO) {
        log.debug("Request to save Catalogs : {}", catalogsDTO);
        Catalogs catalogs = catalogsMapper.toEntity(catalogsDTO);
        catalogs = catalogsRepository.save(catalogs);
        return catalogsMapper.toDto(catalogs);
    }

    /**
     * Update a catalogs.
     *
     * @param catalogsDTO the entity to save.
     * @return the persisted entity.
     */
    public CatalogsDTO update(CatalogsDTO catalogsDTO) {
        log.debug("Request to update Catalogs : {}", catalogsDTO);
        Catalogs catalogs = catalogsMapper.toEntity(catalogsDTO);
        catalogs.setIsPersisted();
        catalogs = catalogsRepository.save(catalogs);
        return catalogsMapper.toDto(catalogs);
    }

    /**
     * Partially update a catalogs.
     *
     * @param catalogsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CatalogsDTO> partialUpdate(CatalogsDTO catalogsDTO) {
        log.debug("Request to partially update Catalogs : {}", catalogsDTO);

        return catalogsRepository
            .findById(catalogsDTO.getId())
            .map(existingCatalogs -> {
                catalogsMapper.partialUpdate(existingCatalogs, catalogsDTO);

                return existingCatalogs;
            })
            .map(catalogsRepository::save)
            .map(catalogsMapper::toDto);
    }

    /**
     * Get all the catalogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CatalogsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Catalogs");
        return catalogsRepository.findAll(pageable).map(catalogsMapper::toDto);
    }

    /**
     * Get one catalogs by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CatalogsDTO> findOne(String id) {
        log.debug("Request to get Catalogs : {}", id);
        return catalogsRepository.findById(id).map(catalogsMapper::toDto);
    }

    /**
     * Delete the catalogs by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Catalogs : {}", id);
        catalogsRepository.deleteById(id);
    }
}
