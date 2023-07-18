package biz.daich.tambour.shlicht.web.rest;

import biz.daich.tambour.shlicht.repository.BatchesRepository;
import biz.daich.tambour.shlicht.service.BatchesQueryService;
import biz.daich.tambour.shlicht.service.BatchesService;
import biz.daich.tambour.shlicht.service.criteria.BatchesCriteria;
import biz.daich.tambour.shlicht.service.dto.BatchesDTO;
import biz.daich.tambour.shlicht.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link biz.daich.tambour.shlicht.domain.Batches}.
 */
@RestController
@RequestMapping("/api")
public class BatchesResource {

    private final Logger log = LoggerFactory.getLogger(BatchesResource.class);

    private static final String ENTITY_NAME = "batches";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BatchesService batchesService;

    private final BatchesRepository batchesRepository;

    private final BatchesQueryService batchesQueryService;

    public BatchesResource(BatchesService batchesService, BatchesRepository batchesRepository, BatchesQueryService batchesQueryService) {
        this.batchesService = batchesService;
        this.batchesRepository = batchesRepository;
        this.batchesQueryService = batchesQueryService;
    }

    /**
     * {@code POST  /batches} : Create a new batches.
     *
     * @param batchesDTO the batchesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new batchesDTO, or with status {@code 400 (Bad Request)} if the batches has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/batches")
    public ResponseEntity<BatchesDTO> createBatches(@Valid @RequestBody BatchesDTO batchesDTO) throws URISyntaxException {
        log.debug("REST request to save Batches : {}", batchesDTO);
        if (batchesDTO.getId() != null) {
            throw new BadRequestAlertException("A new batches cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BatchesDTO result = batchesService.save(batchesDTO);
        return ResponseEntity
            .created(new URI("/api/batches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /batches/:id} : Updates an existing batches.
     *
     * @param id the id of the batchesDTO to save.
     * @param batchesDTO the batchesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated batchesDTO,
     * or with status {@code 400 (Bad Request)} if the batchesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the batchesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/batches/{id}")
    public ResponseEntity<BatchesDTO> updateBatches(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody BatchesDTO batchesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Batches : {}, {}", id, batchesDTO);
        if (batchesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, batchesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!batchesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BatchesDTO result = batchesService.update(batchesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, batchesDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /batches/:id} : Partial updates given fields of an existing batches, field will ignore if it is null
     *
     * @param id the id of the batchesDTO to save.
     * @param batchesDTO the batchesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated batchesDTO,
     * or with status {@code 400 (Bad Request)} if the batchesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the batchesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the batchesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/batches/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BatchesDTO> partialUpdateBatches(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody BatchesDTO batchesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Batches partially : {}, {}", id, batchesDTO);
        if (batchesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, batchesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!batchesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BatchesDTO> result = batchesService.partialUpdate(batchesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, batchesDTO.getId())
        );
    }

    /**
     * {@code GET  /batches} : get all the batches.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of batches in body.
     */
    @GetMapping("/batches")
    public ResponseEntity<List<BatchesDTO>> getAllBatches(
        BatchesCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Batches by criteria: {}", criteria);

        Page<BatchesDTO> page = batchesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /batches/count} : count all the batches.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/batches/count")
    public ResponseEntity<Long> countBatches(BatchesCriteria criteria) {
        log.debug("REST request to count Batches by criteria: {}", criteria);
        return ResponseEntity.ok().body(batchesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /batches/:id} : get the "id" batches.
     *
     * @param id the id of the batchesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the batchesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/batches/{id}")
    public ResponseEntity<BatchesDTO> getBatches(@PathVariable String id) {
        log.debug("REST request to get Batches : {}", id);
        Optional<BatchesDTO> batchesDTO = batchesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(batchesDTO);
    }

    /**
     * {@code DELETE  /batches/:id} : delete the "id" batches.
     *
     * @param id the id of the batchesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/batches/{id}")
    public ResponseEntity<Void> deleteBatches(@PathVariable String id) {
        log.debug("REST request to delete Batches : {}", id);
        batchesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
