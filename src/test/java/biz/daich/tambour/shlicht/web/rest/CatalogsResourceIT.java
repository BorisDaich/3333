package biz.daich.tambour.shlicht.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import biz.daich.tambour.shlicht.IntegrationTest;
import biz.daich.tambour.shlicht.domain.Catalogs;
import biz.daich.tambour.shlicht.repository.CatalogsRepository;
import biz.daich.tambour.shlicht.service.dto.CatalogsDTO;
import biz.daich.tambour.shlicht.service.mapper.CatalogsMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CatalogsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CatalogsResourceIT {

    private static final String DEFAULT_EXTERNAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Instant DEFAULT_CREATED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/catalogs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CatalogsRepository catalogsRepository;

    @Autowired
    private CatalogsMapper catalogsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCatalogsMockMvc;

    private Catalogs catalogs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Catalogs createEntity(EntityManager em) {
        Catalogs catalogs = new Catalogs()
            .externalId(DEFAULT_EXTERNAL_ID)
            .name(DEFAULT_NAME)
            .version(DEFAULT_VERSION)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdTime(DEFAULT_CREATED_TIME);
        return catalogs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Catalogs createUpdatedEntity(EntityManager em) {
        Catalogs catalogs = new Catalogs()
            .externalId(UPDATED_EXTERNAL_ID)
            .name(UPDATED_NAME)
            .version(UPDATED_VERSION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdTime(UPDATED_CREATED_TIME);
        return catalogs;
    }

    @BeforeEach
    public void initTest() {
        catalogs = createEntity(em);
    }

    @Test
    @Transactional
    void createCatalogs() throws Exception {
        int databaseSizeBeforeCreate = catalogsRepository.findAll().size();
        // Create the Catalogs
        CatalogsDTO catalogsDTO = catalogsMapper.toDto(catalogs);
        restCatalogsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogsDTO)))
            .andExpect(status().isCreated());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeCreate + 1);
        Catalogs testCatalogs = catalogsList.get(catalogsList.size() - 1);
        assertThat(testCatalogs.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testCatalogs.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCatalogs.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testCatalogs.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCatalogs.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
    }

    @Test
    @Transactional
    void createCatalogsWithExistingId() throws Exception {
        // Create the Catalogs with an existing ID
        catalogs.setId("existing_id");
        CatalogsDTO catalogsDTO = catalogsMapper.toDto(catalogs);

        int databaseSizeBeforeCreate = catalogsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatalogsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogsRepository.findAll().size();
        // set the field null
        catalogs.setIsActive(null);

        // Create the Catalogs, which fails.
        CatalogsDTO catalogsDTO = catalogsMapper.toDto(catalogs);

        restCatalogsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogsDTO)))
            .andExpect(status().isBadRequest());

        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCatalogs() throws Exception {
        // Initialize the database
        catalogs.setId(UUID.randomUUID().toString());
        catalogsRepository.saveAndFlush(catalogs);

        // Get all the catalogsList
        restCatalogsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catalogs.getId())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.toString())));
    }

    @Test
    @Transactional
    void getCatalogs() throws Exception {
        // Initialize the database
        catalogs.setId(UUID.randomUUID().toString());
        catalogsRepository.saveAndFlush(catalogs);

        // Get the catalogs
        restCatalogsMockMvc
            .perform(get(ENTITY_API_URL_ID, catalogs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(catalogs.getId()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdTime").value(DEFAULT_CREATED_TIME.toString()));
    }

    @Test
    @Transactional
    void getCatalogsByIdFiltering() throws Exception {
        // Initialize the database
        catalogsRepository.saveAndFlush(catalogs);

        String id = catalogs.getId();

        defaultCatalogsShouldBeFound("id.equals=" + id);
        defaultCatalogsShouldNotBeFound("id.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllCatalogsByIsActiveIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogsRepository.saveAndFlush(catalogs);

        // Get all the catalogsList where isActive equals to DEFAULT_IS_ACTIVE
        defaultCatalogsShouldBeFound("isActive.equals=" + DEFAULT_IS_ACTIVE);

        // Get all the catalogsList where isActive equals to UPDATED_IS_ACTIVE
        defaultCatalogsShouldNotBeFound("isActive.equals=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    void getAllCatalogsByIsActiveIsInShouldWork() throws Exception {
        // Initialize the database
        catalogsRepository.saveAndFlush(catalogs);

        // Get all the catalogsList where isActive in DEFAULT_IS_ACTIVE or UPDATED_IS_ACTIVE
        defaultCatalogsShouldBeFound("isActive.in=" + DEFAULT_IS_ACTIVE + "," + UPDATED_IS_ACTIVE);

        // Get all the catalogsList where isActive equals to UPDATED_IS_ACTIVE
        defaultCatalogsShouldNotBeFound("isActive.in=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    void getAllCatalogsByIsActiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogsRepository.saveAndFlush(catalogs);

        // Get all the catalogsList where isActive is not null
        defaultCatalogsShouldBeFound("isActive.specified=true");

        // Get all the catalogsList where isActive is null
        defaultCatalogsShouldNotBeFound("isActive.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogsByCreatedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogsRepository.saveAndFlush(catalogs);

        // Get all the catalogsList where createdTime equals to DEFAULT_CREATED_TIME
        defaultCatalogsShouldBeFound("createdTime.equals=" + DEFAULT_CREATED_TIME);

        // Get all the catalogsList where createdTime equals to UPDATED_CREATED_TIME
        defaultCatalogsShouldNotBeFound("createdTime.equals=" + UPDATED_CREATED_TIME);
    }

    @Test
    @Transactional
    void getAllCatalogsByCreatedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        catalogsRepository.saveAndFlush(catalogs);

        // Get all the catalogsList where createdTime in DEFAULT_CREATED_TIME or UPDATED_CREATED_TIME
        defaultCatalogsShouldBeFound("createdTime.in=" + DEFAULT_CREATED_TIME + "," + UPDATED_CREATED_TIME);

        // Get all the catalogsList where createdTime equals to UPDATED_CREATED_TIME
        defaultCatalogsShouldNotBeFound("createdTime.in=" + UPDATED_CREATED_TIME);
    }

    @Test
    @Transactional
    void getAllCatalogsByCreatedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogsRepository.saveAndFlush(catalogs);

        // Get all the catalogsList where createdTime is not null
        defaultCatalogsShouldBeFound("createdTime.specified=true");

        // Get all the catalogsList where createdTime is null
        defaultCatalogsShouldNotBeFound("createdTime.specified=false");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCatalogsShouldBeFound(String filter) throws Exception {
        restCatalogsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catalogs.getId())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.toString())));

        // Check, that the count call also returns 1
        restCatalogsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCatalogsShouldNotBeFound(String filter) throws Exception {
        restCatalogsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCatalogsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCatalogs() throws Exception {
        // Get the catalogs
        restCatalogsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCatalogs() throws Exception {
        // Initialize the database
        catalogs.setId(UUID.randomUUID().toString());
        catalogsRepository.saveAndFlush(catalogs);

        int databaseSizeBeforeUpdate = catalogsRepository.findAll().size();

        // Update the catalogs
        Catalogs updatedCatalogs = catalogsRepository.findById(catalogs.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCatalogs are not directly saved in db
        em.detach(updatedCatalogs);
        updatedCatalogs
            .externalId(UPDATED_EXTERNAL_ID)
            .name(UPDATED_NAME)
            .version(UPDATED_VERSION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdTime(UPDATED_CREATED_TIME);
        CatalogsDTO catalogsDTO = catalogsMapper.toDto(updatedCatalogs);

        restCatalogsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, catalogsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(catalogsDTO))
            )
            .andExpect(status().isOk());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeUpdate);
        Catalogs testCatalogs = catalogsList.get(catalogsList.size() - 1);
        assertThat(testCatalogs.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testCatalogs.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCatalogs.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testCatalogs.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCatalogs.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
    }

    @Test
    @Transactional
    void putNonExistingCatalogs() throws Exception {
        int databaseSizeBeforeUpdate = catalogsRepository.findAll().size();
        catalogs.setId(UUID.randomUUID().toString());

        // Create the Catalogs
        CatalogsDTO catalogsDTO = catalogsMapper.toDto(catalogs);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatalogsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, catalogsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(catalogsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCatalogs() throws Exception {
        int databaseSizeBeforeUpdate = catalogsRepository.findAll().size();
        catalogs.setId(UUID.randomUUID().toString());

        // Create the Catalogs
        CatalogsDTO catalogsDTO = catalogsMapper.toDto(catalogs);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(catalogsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCatalogs() throws Exception {
        int databaseSizeBeforeUpdate = catalogsRepository.findAll().size();
        catalogs.setId(UUID.randomUUID().toString());

        // Create the Catalogs
        CatalogsDTO catalogsDTO = catalogsMapper.toDto(catalogs);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCatalogsWithPatch() throws Exception {
        // Initialize the database
        catalogs.setId(UUID.randomUUID().toString());
        catalogsRepository.saveAndFlush(catalogs);

        int databaseSizeBeforeUpdate = catalogsRepository.findAll().size();

        // Update the catalogs using partial update
        Catalogs partialUpdatedCatalogs = new Catalogs();
        partialUpdatedCatalogs.setId(catalogs.getId());

        partialUpdatedCatalogs.createdTime(UPDATED_CREATED_TIME);

        restCatalogsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCatalogs.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCatalogs))
            )
            .andExpect(status().isOk());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeUpdate);
        Catalogs testCatalogs = catalogsList.get(catalogsList.size() - 1);
        assertThat(testCatalogs.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testCatalogs.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCatalogs.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testCatalogs.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCatalogs.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
    }

    @Test
    @Transactional
    void fullUpdateCatalogsWithPatch() throws Exception {
        // Initialize the database
        catalogs.setId(UUID.randomUUID().toString());
        catalogsRepository.saveAndFlush(catalogs);

        int databaseSizeBeforeUpdate = catalogsRepository.findAll().size();

        // Update the catalogs using partial update
        Catalogs partialUpdatedCatalogs = new Catalogs();
        partialUpdatedCatalogs.setId(catalogs.getId());

        partialUpdatedCatalogs
            .externalId(UPDATED_EXTERNAL_ID)
            .name(UPDATED_NAME)
            .version(UPDATED_VERSION)
            .isActive(UPDATED_IS_ACTIVE)
            .createdTime(UPDATED_CREATED_TIME);

        restCatalogsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCatalogs.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCatalogs))
            )
            .andExpect(status().isOk());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeUpdate);
        Catalogs testCatalogs = catalogsList.get(catalogsList.size() - 1);
        assertThat(testCatalogs.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testCatalogs.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCatalogs.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testCatalogs.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCatalogs.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingCatalogs() throws Exception {
        int databaseSizeBeforeUpdate = catalogsRepository.findAll().size();
        catalogs.setId(UUID.randomUUID().toString());

        // Create the Catalogs
        CatalogsDTO catalogsDTO = catalogsMapper.toDto(catalogs);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatalogsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, catalogsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(catalogsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCatalogs() throws Exception {
        int databaseSizeBeforeUpdate = catalogsRepository.findAll().size();
        catalogs.setId(UUID.randomUUID().toString());

        // Create the Catalogs
        CatalogsDTO catalogsDTO = catalogsMapper.toDto(catalogs);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(catalogsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCatalogs() throws Exception {
        int databaseSizeBeforeUpdate = catalogsRepository.findAll().size();
        catalogs.setId(UUID.randomUUID().toString());

        // Create the Catalogs
        CatalogsDTO catalogsDTO = catalogsMapper.toDto(catalogs);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(catalogsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Catalogs in the database
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCatalogs() throws Exception {
        // Initialize the database
        catalogs.setId(UUID.randomUUID().toString());
        catalogsRepository.saveAndFlush(catalogs);

        int databaseSizeBeforeDelete = catalogsRepository.findAll().size();

        // Delete the catalogs
        restCatalogsMockMvc
            .perform(delete(ENTITY_API_URL_ID, catalogs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Catalogs> catalogsList = catalogsRepository.findAll();
        assertThat(catalogsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
