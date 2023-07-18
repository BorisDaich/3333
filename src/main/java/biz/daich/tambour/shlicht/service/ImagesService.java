package biz.daich.tambour.shlicht.service;

import biz.daich.tambour.shlicht.domain.Images;
import biz.daich.tambour.shlicht.repository.ImagesRepository;
import biz.daich.tambour.shlicht.service.dto.ImagesDTO;
import biz.daich.tambour.shlicht.service.mapper.ImagesMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Images}.
 */
@Service
@Transactional
public class ImagesService {

    private final Logger log = LoggerFactory.getLogger(ImagesService.class);

    private final ImagesRepository imagesRepository;

    private final ImagesMapper imagesMapper;

    public ImagesService(ImagesRepository imagesRepository, ImagesMapper imagesMapper) {
        this.imagesRepository = imagesRepository;
        this.imagesMapper = imagesMapper;
    }

    /**
     * Save a images.
     *
     * @param imagesDTO the entity to save.
     * @return the persisted entity.
     */
    public ImagesDTO save(ImagesDTO imagesDTO) {
        log.debug("Request to save Images : {}", imagesDTO);
        Images images = imagesMapper.toEntity(imagesDTO);
        images = imagesRepository.save(images);
        return imagesMapper.toDto(images);
    }

    /**
     * Update a images.
     *
     * @param imagesDTO the entity to save.
     * @return the persisted entity.
     */
    public ImagesDTO update(ImagesDTO imagesDTO) {
        log.debug("Request to update Images : {}", imagesDTO);
        Images images = imagesMapper.toEntity(imagesDTO);
        images.setIsPersisted();
        images = imagesRepository.save(images);
        return imagesMapper.toDto(images);
    }

    /**
     * Partially update a images.
     *
     * @param imagesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ImagesDTO> partialUpdate(ImagesDTO imagesDTO) {
        log.debug("Request to partially update Images : {}", imagesDTO);

        return imagesRepository
            .findById(imagesDTO.getId())
            .map(existingImages -> {
                imagesMapper.partialUpdate(existingImages, imagesDTO);

                return existingImages;
            })
            .map(imagesRepository::save)
            .map(imagesMapper::toDto);
    }

    /**
     * Get all the images.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ImagesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Images");
        return imagesRepository.findAll(pageable).map(imagesMapper::toDto);
    }

    /**
     *  Get all the images where Id is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ImagesDTO> findAllWhereIdIsNull() {
        log.debug("Request to get all images where Id is null");
        return StreamSupport
            .stream(imagesRepository.findAll().spliterator(), false)
            .filter(images -> images.getId() == null)
            .map(imagesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one images by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ImagesDTO> findOne(String id) {
        log.debug("Request to get Images : {}", id);
        return imagesRepository.findById(id).map(imagesMapper::toDto);
    }

    /**
     * Delete the images by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Images : {}", id);
        imagesRepository.deleteById(id);
    }
}
