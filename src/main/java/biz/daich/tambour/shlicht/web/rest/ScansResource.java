package biz.daich.tambour.shlicht.web.rest;

import biz.daich.tambour.shlicht.repository.ScansRepository;
import biz.daich.tambour.shlicht.service.ScansQueryService;
import biz.daich.tambour.shlicht.service.ScansService;
import biz.daich.tambour.shlicht.service.criteria.ScansCriteria;
import biz.daich.tambour.shlicht.service.dto.ScansDTO;
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
 * REST controller for managing {@link biz.daich.tambour.shlicht.domain.Scans}.
 */
@RestController
@RequestMapping("/api")
public class ScansResource {

    private final Logger log = LoggerFactory.getLogger(ScansResource.class);

    private static final String ENTITY_NAME = "scans";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScansService scansService;

    private final ScansRepository scansRepository;

    private final ScansQueryService scansQueryService;

    public ScansResource(ScansService scansService, ScansRepository scansRepository, ScansQueryService scansQueryService) {
        this.scansService = scansService;
        this.scansRepository = scansRepository;
        this.scansQueryService = scansQueryService;
    }

    /**
     * {@code POST  /scans} : Create a new scans.
     *
     * @param scansDTO the scansDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scansDTO, or with status {@code 400 (Bad Request)} if the scans has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/scans")
    public ResponseEntity<ScansDTO> createScans(@Valid @RequestBody ScansDTO scansDTO) throws URISyntaxException {
        log.debug("REST request to save Scans : {}", scansDTO);
        if (scansDTO.getId() != null) {
            throw new BadRequestAlertException("A new scans cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScansDTO result = scansService.save(scansDTO);
        return ResponseEntity
            .created(new URI("/api/scans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /scans/:id} : Updates an existing scans.
     *
     * @param id the id of the scansDTO to save.
     * @param scansDTO the scansDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scansDTO,
     * or with status {@code 400 (Bad Request)} if the scansDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the scansDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/scans/{id}")
    public ResponseEntity<ScansDTO> updateScans(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody ScansDTO scansDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Scans : {}, {}", id, scansDTO);
        if (scansDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, scansDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!scansRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ScansDTO result = scansService.update(scansDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, scansDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /scans/:id} : Partial updates given fields of an existing scans, field will ignore if it is null
     *
     * @param id the id of the scansDTO to save.
     * @param scansDTO the scansDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scansDTO,
     * or with status {@code 400 (Bad Request)} if the scansDTO is not valid,
     * or with status {@code 404 (Not Found)} if the scansDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the scansDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/scans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ScansDTO> partialUpdateScans(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody ScansDTO scansDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Scans partially : {}, {}", id, scansDTO);
        if (scansDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, scansDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!scansRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ScansDTO> result = scansService.partialUpdate(scansDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, scansDTO.getId())
        );
    }

    /**
     * {@code GET  /scans} : get all the scans.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scans in body.
     */
    @GetMapping("/scans")
    public ResponseEntity<List<ScansDTO>> getAllScans(
        ScansCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Scans by criteria: {}", criteria);

        Page<ScansDTO> page = scansQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /scans/count} : count all the scans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/scans/count")
    public ResponseEntity<Long> countScans(ScansCriteria criteria) {
        log.debug("REST request to count Scans by criteria: {}", criteria);
        return ResponseEntity.ok().body(scansQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /scans/:id} : get the "id" scans.
     *
     * @param id the id of the scansDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scansDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/scans/{id}")
    public ResponseEntity<ScansDTO> getScans(@PathVariable String id) {
        log.debug("REST request to get Scans : {}", id);
        Optional<ScansDTO> scansDTO = scansService.findOne(id);
        return ResponseUtil.wrapOrNotFound(scansDTO);
    }

    /**
     * {@code DELETE  /scans/:id} : delete the "id" scans.
     *
     * @param id the id of the scansDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/scans/{id}")
    public ResponseEntity<Void> deleteScans(@PathVariable String id) {
        log.debug("REST request to delete Scans : {}", id);
        scansService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
