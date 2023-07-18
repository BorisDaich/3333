package biz.daich.tambour.shlicht.web.rest;

import biz.daich.tambour.shlicht.repository.CatalogcolorsRepository;
import biz.daich.tambour.shlicht.service.CatalogcolorsQueryService;
import biz.daich.tambour.shlicht.service.CatalogcolorsService;
import biz.daich.tambour.shlicht.service.criteria.CatalogcolorsCriteria;
import biz.daich.tambour.shlicht.service.dto.CatalogcolorsDTO;
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
 * REST controller for managing {@link biz.daich.tambour.shlicht.domain.Catalogcolors}.
 */
@RestController
@RequestMapping("/api")
public class CatalogcolorsResource {

    private final Logger log = LoggerFactory.getLogger(CatalogcolorsResource.class);

    private static final String ENTITY_NAME = "catalogcolors";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CatalogcolorsService catalogcolorsService;

    private final CatalogcolorsRepository catalogcolorsRepository;

    private final CatalogcolorsQueryService catalogcolorsQueryService;

    public CatalogcolorsResource(
        CatalogcolorsService catalogcolorsService,
        CatalogcolorsRepository catalogcolorsRepository,
        CatalogcolorsQueryService catalogcolorsQueryService
    ) {
        this.catalogcolorsService = catalogcolorsService;
        this.catalogcolorsRepository = catalogcolorsRepository;
        this.catalogcolorsQueryService = catalogcolorsQueryService;
    }

    /**
     * {@code POST  /catalogcolors} : Create a new catalogcolors.
     *
     * @param catalogcolorsDTO the catalogcolorsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new catalogcolorsDTO, or with status {@code 400 (Bad Request)} if the catalogcolors has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/catalogcolors")
    public ResponseEntity<CatalogcolorsDTO> createCatalogcolors(@Valid @RequestBody CatalogcolorsDTO catalogcolorsDTO)
        throws URISyntaxException {
        log.debug("REST request to save Catalogcolors : {}", catalogcolorsDTO);
        if (catalogcolorsDTO.getId() != null) {
            throw new BadRequestAlertException("A new catalogcolors cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatalogcolorsDTO result = catalogcolorsService.save(catalogcolorsDTO);
        return ResponseEntity
            .created(new URI("/api/catalogcolors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /catalogcolors/:id} : Updates an existing catalogcolors.
     *
     * @param id the id of the catalogcolorsDTO to save.
     * @param catalogcolorsDTO the catalogcolorsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated catalogcolorsDTO,
     * or with status {@code 400 (Bad Request)} if the catalogcolorsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the catalogcolorsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/catalogcolors/{id}")
    public ResponseEntity<CatalogcolorsDTO> updateCatalogcolors(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody CatalogcolorsDTO catalogcolorsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Catalogcolors : {}, {}", id, catalogcolorsDTO);
        if (catalogcolorsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, catalogcolorsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!catalogcolorsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CatalogcolorsDTO result = catalogcolorsService.update(catalogcolorsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, catalogcolorsDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /catalogcolors/:id} : Partial updates given fields of an existing catalogcolors, field will ignore if it is null
     *
     * @param id the id of the catalogcolorsDTO to save.
     * @param catalogcolorsDTO the catalogcolorsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated catalogcolorsDTO,
     * or with status {@code 400 (Bad Request)} if the catalogcolorsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the catalogcolorsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the catalogcolorsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/catalogcolors/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CatalogcolorsDTO> partialUpdateCatalogcolors(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody CatalogcolorsDTO catalogcolorsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Catalogcolors partially : {}, {}", id, catalogcolorsDTO);
        if (catalogcolorsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, catalogcolorsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!catalogcolorsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CatalogcolorsDTO> result = catalogcolorsService.partialUpdate(catalogcolorsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, catalogcolorsDTO.getId())
        );
    }

    /**
     * {@code GET  /catalogcolors} : get all the catalogcolors.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of catalogcolors in body.
     */
    @GetMapping("/catalogcolors")
    public ResponseEntity<List<CatalogcolorsDTO>> getAllCatalogcolors(
        CatalogcolorsCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Catalogcolors by criteria: {}", criteria);

        Page<CatalogcolorsDTO> page = catalogcolorsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /catalogcolors/count} : count all the catalogcolors.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/catalogcolors/count")
    public ResponseEntity<Long> countCatalogcolors(CatalogcolorsCriteria criteria) {
        log.debug("REST request to count Catalogcolors by criteria: {}", criteria);
        return ResponseEntity.ok().body(catalogcolorsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /catalogcolors/:id} : get the "id" catalogcolors.
     *
     * @param id the id of the catalogcolorsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the catalogcolorsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/catalogcolors/{id}")
    public ResponseEntity<CatalogcolorsDTO> getCatalogcolors(@PathVariable String id) {
        log.debug("REST request to get Catalogcolors : {}", id);
        Optional<CatalogcolorsDTO> catalogcolorsDTO = catalogcolorsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catalogcolorsDTO);
    }

    /**
     * {@code DELETE  /catalogcolors/:id} : delete the "id" catalogcolors.
     *
     * @param id the id of the catalogcolorsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/catalogcolors/{id}")
    public ResponseEntity<Void> deleteCatalogcolors(@PathVariable String id) {
        log.debug("REST request to delete Catalogcolors : {}", id);
        catalogcolorsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
