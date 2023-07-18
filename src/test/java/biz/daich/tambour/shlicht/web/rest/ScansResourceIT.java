package biz.daich.tambour.shlicht.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import biz.daich.tambour.shlicht.IntegrationTest;
import biz.daich.tambour.shlicht.domain.Batches;
import biz.daich.tambour.shlicht.domain.Images;
import biz.daich.tambour.shlicht.domain.Scans;
import biz.daich.tambour.shlicht.repository.ScansRepository;
import biz.daich.tambour.shlicht.service.dto.ScansDTO;
import biz.daich.tambour.shlicht.service.mapper.ScansMapper;
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
 * Integration tests for the {@link ScansResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ScansResourceIT {

    private static final String DEFAULT_SCANNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SCANNER_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_SEQUENCE_IN_BATCH = 1;
    private static final Integer UPDATED_SEQUENCE_IN_BATCH = 2;
    private static final Integer SMALLER_SEQUENCE_IN_BATCH = 1 - 1;

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final Double DEFAULT_D_E = 1D;
    private static final Double UPDATED_D_E = 2D;
    private static final Double SMALLER_D_E = 1D - 1D;

    private static final Instant DEFAULT_CREATED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SCANNED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SCANNED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INSPECTED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSPECTED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_EJECTED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EJECTED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/scans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ScansRepository scansRepository;

    @Autowired
    private ScansMapper scansMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restScansMockMvc;

    private Scans scans;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Scans createEntity(EntityManager em) {
        Scans scans = new Scans()
            .scannerId(DEFAULT_SCANNER_ID)
            .sequenceInBatch(DEFAULT_SEQUENCE_IN_BATCH)
            .state(DEFAULT_STATE)
            .dE(DEFAULT_D_E)
            .createdTime(DEFAULT_CREATED_TIME)
            .scannedTime(DEFAULT_SCANNED_TIME)
            .inspectedTime(DEFAULT_INSPECTED_TIME)
            .modifiedTime(DEFAULT_MODIFIED_TIME)
            .ejectedTime(DEFAULT_EJECTED_TIME);
        return scans;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Scans createUpdatedEntity(EntityManager em) {
        Scans scans = new Scans()
            .scannerId(UPDATED_SCANNER_ID)
            .sequenceInBatch(UPDATED_SEQUENCE_IN_BATCH)
            .state(UPDATED_STATE)
            .dE(UPDATED_D_E)
            .createdTime(UPDATED_CREATED_TIME)
            .scannedTime(UPDATED_SCANNED_TIME)
            .inspectedTime(UPDATED_INSPECTED_TIME)
            .modifiedTime(UPDATED_MODIFIED_TIME)
            .ejectedTime(UPDATED_EJECTED_TIME);
        return scans;
    }

    @BeforeEach
    public void initTest() {
        scans = createEntity(em);
    }

    @Test
    @Transactional
    void createScans() throws Exception {
        int databaseSizeBeforeCreate = scansRepository.findAll().size();
        // Create the Scans
        ScansDTO scansDTO = scansMapper.toDto(scans);
        restScansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(scansDTO)))
            .andExpect(status().isCreated());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeCreate + 1);
        Scans testScans = scansList.get(scansList.size() - 1);
        assertThat(testScans.getScannerId()).isEqualTo(DEFAULT_SCANNER_ID);
        assertThat(testScans.getSequenceInBatch()).isEqualTo(DEFAULT_SEQUENCE_IN_BATCH);
        assertThat(testScans.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testScans.getdE()).isEqualTo(DEFAULT_D_E);
        assertThat(testScans.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
        assertThat(testScans.getScannedTime()).isEqualTo(DEFAULT_SCANNED_TIME);
        assertThat(testScans.getInspectedTime()).isEqualTo(DEFAULT_INSPECTED_TIME);
        assertThat(testScans.getModifiedTime()).isEqualTo(DEFAULT_MODIFIED_TIME);
        assertThat(testScans.getEjectedTime()).isEqualTo(DEFAULT_EJECTED_TIME);
    }

    @Test
    @Transactional
    void createScansWithExistingId() throws Exception {
        // Create the Scans with an existing ID
        scans.setId("existing_id");
        ScansDTO scansDTO = scansMapper.toDto(scans);

        int databaseSizeBeforeCreate = scansRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restScansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(scansDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSequenceInBatchIsRequired() throws Exception {
        int databaseSizeBeforeTest = scansRepository.findAll().size();
        // set the field null
        scans.setSequenceInBatch(null);

        // Create the Scans, which fails.
        ScansDTO scansDTO = scansMapper.toDto(scans);

        restScansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(scansDTO)))
            .andExpect(status().isBadRequest());

        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = scansRepository.findAll().size();
        // set the field null
        scans.setState(null);

        // Create the Scans, which fails.
        ScansDTO scansDTO = scansMapper.toDto(scans);

        restScansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(scansDTO)))
            .andExpect(status().isBadRequest());

        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkdEIsRequired() throws Exception {
        int databaseSizeBeforeTest = scansRepository.findAll().size();
        // set the field null
        scans.setdE(null);

        // Create the Scans, which fails.
        ScansDTO scansDTO = scansMapper.toDto(scans);

        restScansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(scansDTO)))
            .andExpect(status().isBadRequest());

        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = scansRepository.findAll().size();
        // set the field null
        scans.setCreatedTime(null);

        // Create the Scans, which fails.
        ScansDTO scansDTO = scansMapper.toDto(scans);

        restScansMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(scansDTO)))
            .andExpect(status().isBadRequest());

        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllScans() throws Exception {
        // Initialize the database
        scans.setId(UUID.randomUUID().toString());
        scansRepository.saveAndFlush(scans);

        // Get all the scansList
        restScansMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scans.getId())))
            .andExpect(jsonPath("$.[*].scannerId").value(hasItem(DEFAULT_SCANNER_ID.toString())))
            .andExpect(jsonPath("$.[*].sequenceInBatch").value(hasItem(DEFAULT_SEQUENCE_IN_BATCH)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].dE").value(hasItem(DEFAULT_D_E.doubleValue())))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.toString())))
            .andExpect(jsonPath("$.[*].scannedTime").value(hasItem(DEFAULT_SCANNED_TIME.toString())))
            .andExpect(jsonPath("$.[*].inspectedTime").value(hasItem(DEFAULT_INSPECTED_TIME.toString())))
            .andExpect(jsonPath("$.[*].modifiedTime").value(hasItem(DEFAULT_MODIFIED_TIME.toString())))
            .andExpect(jsonPath("$.[*].ejectedTime").value(hasItem(DEFAULT_EJECTED_TIME.toString())));
    }

    @Test
    @Transactional
    void getScans() throws Exception {
        // Initialize the database
        scans.setId(UUID.randomUUID().toString());
        scansRepository.saveAndFlush(scans);

        // Get the scans
        restScansMockMvc
            .perform(get(ENTITY_API_URL_ID, scans.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(scans.getId()))
            .andExpect(jsonPath("$.scannerId").value(DEFAULT_SCANNER_ID.toString()))
            .andExpect(jsonPath("$.sequenceInBatch").value(DEFAULT_SEQUENCE_IN_BATCH))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.dE").value(DEFAULT_D_E.doubleValue()))
            .andExpect(jsonPath("$.createdTime").value(DEFAULT_CREATED_TIME.toString()))
            .andExpect(jsonPath("$.scannedTime").value(DEFAULT_SCANNED_TIME.toString()))
            .andExpect(jsonPath("$.inspectedTime").value(DEFAULT_INSPECTED_TIME.toString()))
            .andExpect(jsonPath("$.modifiedTime").value(DEFAULT_MODIFIED_TIME.toString()))
            .andExpect(jsonPath("$.ejectedTime").value(DEFAULT_EJECTED_TIME.toString()));
    }

    @Test
    @Transactional
    void getScansByIdFiltering() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        String id = scans.getId();

        defaultScansShouldBeFound("id.equals=" + id);
        defaultScansShouldNotBeFound("id.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllScansBySequenceInBatchIsEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where sequenceInBatch equals to DEFAULT_SEQUENCE_IN_BATCH
        defaultScansShouldBeFound("sequenceInBatch.equals=" + DEFAULT_SEQUENCE_IN_BATCH);

        // Get all the scansList where sequenceInBatch equals to UPDATED_SEQUENCE_IN_BATCH
        defaultScansShouldNotBeFound("sequenceInBatch.equals=" + UPDATED_SEQUENCE_IN_BATCH);
    }

    @Test
    @Transactional
    void getAllScansBySequenceInBatchIsInShouldWork() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where sequenceInBatch in DEFAULT_SEQUENCE_IN_BATCH or UPDATED_SEQUENCE_IN_BATCH
        defaultScansShouldBeFound("sequenceInBatch.in=" + DEFAULT_SEQUENCE_IN_BATCH + "," + UPDATED_SEQUENCE_IN_BATCH);

        // Get all the scansList where sequenceInBatch equals to UPDATED_SEQUENCE_IN_BATCH
        defaultScansShouldNotBeFound("sequenceInBatch.in=" + UPDATED_SEQUENCE_IN_BATCH);
    }

    @Test
    @Transactional
    void getAllScansBySequenceInBatchIsNullOrNotNull() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where sequenceInBatch is not null
        defaultScansShouldBeFound("sequenceInBatch.specified=true");

        // Get all the scansList where sequenceInBatch is null
        defaultScansShouldNotBeFound("sequenceInBatch.specified=false");
    }

    @Test
    @Transactional
    void getAllScansBySequenceInBatchIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where sequenceInBatch is greater than or equal to DEFAULT_SEQUENCE_IN_BATCH
        defaultScansShouldBeFound("sequenceInBatch.greaterThanOrEqual=" + DEFAULT_SEQUENCE_IN_BATCH);

        // Get all the scansList where sequenceInBatch is greater than or equal to UPDATED_SEQUENCE_IN_BATCH
        defaultScansShouldNotBeFound("sequenceInBatch.greaterThanOrEqual=" + UPDATED_SEQUENCE_IN_BATCH);
    }

    @Test
    @Transactional
    void getAllScansBySequenceInBatchIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where sequenceInBatch is less than or equal to DEFAULT_SEQUENCE_IN_BATCH
        defaultScansShouldBeFound("sequenceInBatch.lessThanOrEqual=" + DEFAULT_SEQUENCE_IN_BATCH);

        // Get all the scansList where sequenceInBatch is less than or equal to SMALLER_SEQUENCE_IN_BATCH
        defaultScansShouldNotBeFound("sequenceInBatch.lessThanOrEqual=" + SMALLER_SEQUENCE_IN_BATCH);
    }

    @Test
    @Transactional
    void getAllScansBySequenceInBatchIsLessThanSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where sequenceInBatch is less than DEFAULT_SEQUENCE_IN_BATCH
        defaultScansShouldNotBeFound("sequenceInBatch.lessThan=" + DEFAULT_SEQUENCE_IN_BATCH);

        // Get all the scansList where sequenceInBatch is less than UPDATED_SEQUENCE_IN_BATCH
        defaultScansShouldBeFound("sequenceInBatch.lessThan=" + UPDATED_SEQUENCE_IN_BATCH);
    }

    @Test
    @Transactional
    void getAllScansBySequenceInBatchIsGreaterThanSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where sequenceInBatch is greater than DEFAULT_SEQUENCE_IN_BATCH
        defaultScansShouldNotBeFound("sequenceInBatch.greaterThan=" + DEFAULT_SEQUENCE_IN_BATCH);

        // Get all the scansList where sequenceInBatch is greater than SMALLER_SEQUENCE_IN_BATCH
        defaultScansShouldBeFound("sequenceInBatch.greaterThan=" + SMALLER_SEQUENCE_IN_BATCH);
    }

    @Test
    @Transactional
    void getAllScansByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where state equals to DEFAULT_STATE
        defaultScansShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the scansList where state equals to UPDATED_STATE
        defaultScansShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllScansByStateIsInShouldWork() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where state in DEFAULT_STATE or UPDATED_STATE
        defaultScansShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the scansList where state equals to UPDATED_STATE
        defaultScansShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllScansByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where state is not null
        defaultScansShouldBeFound("state.specified=true");

        // Get all the scansList where state is null
        defaultScansShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    void getAllScansByStateContainsSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where state contains DEFAULT_STATE
        defaultScansShouldBeFound("state.contains=" + DEFAULT_STATE);

        // Get all the scansList where state contains UPDATED_STATE
        defaultScansShouldNotBeFound("state.contains=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllScansByStateNotContainsSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where state does not contain DEFAULT_STATE
        defaultScansShouldNotBeFound("state.doesNotContain=" + DEFAULT_STATE);

        // Get all the scansList where state does not contain UPDATED_STATE
        defaultScansShouldBeFound("state.doesNotContain=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllScansBydEIsEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where dE equals to DEFAULT_D_E
        defaultScansShouldBeFound("dE.equals=" + DEFAULT_D_E);

        // Get all the scansList where dE equals to UPDATED_D_E
        defaultScansShouldNotBeFound("dE.equals=" + UPDATED_D_E);
    }

    @Test
    @Transactional
    void getAllScansBydEIsInShouldWork() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where dE in DEFAULT_D_E or UPDATED_D_E
        defaultScansShouldBeFound("dE.in=" + DEFAULT_D_E + "," + UPDATED_D_E);

        // Get all the scansList where dE equals to UPDATED_D_E
        defaultScansShouldNotBeFound("dE.in=" + UPDATED_D_E);
    }

    @Test
    @Transactional
    void getAllScansBydEIsNullOrNotNull() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where dE is not null
        defaultScansShouldBeFound("dE.specified=true");

        // Get all the scansList where dE is null
        defaultScansShouldNotBeFound("dE.specified=false");
    }

    @Test
    @Transactional
    void getAllScansBydEIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where dE is greater than or equal to DEFAULT_D_E
        defaultScansShouldBeFound("dE.greaterThanOrEqual=" + DEFAULT_D_E);

        // Get all the scansList where dE is greater than or equal to UPDATED_D_E
        defaultScansShouldNotBeFound("dE.greaterThanOrEqual=" + UPDATED_D_E);
    }

    @Test
    @Transactional
    void getAllScansBydEIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where dE is less than or equal to DEFAULT_D_E
        defaultScansShouldBeFound("dE.lessThanOrEqual=" + DEFAULT_D_E);

        // Get all the scansList where dE is less than or equal to SMALLER_D_E
        defaultScansShouldNotBeFound("dE.lessThanOrEqual=" + SMALLER_D_E);
    }

    @Test
    @Transactional
    void getAllScansBydEIsLessThanSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where dE is less than DEFAULT_D_E
        defaultScansShouldNotBeFound("dE.lessThan=" + DEFAULT_D_E);

        // Get all the scansList where dE is less than UPDATED_D_E
        defaultScansShouldBeFound("dE.lessThan=" + UPDATED_D_E);
    }

    @Test
    @Transactional
    void getAllScansBydEIsGreaterThanSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where dE is greater than DEFAULT_D_E
        defaultScansShouldNotBeFound("dE.greaterThan=" + DEFAULT_D_E);

        // Get all the scansList where dE is greater than SMALLER_D_E
        defaultScansShouldBeFound("dE.greaterThan=" + SMALLER_D_E);
    }

    @Test
    @Transactional
    void getAllScansByCreatedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where createdTime equals to DEFAULT_CREATED_TIME
        defaultScansShouldBeFound("createdTime.equals=" + DEFAULT_CREATED_TIME);

        // Get all the scansList where createdTime equals to UPDATED_CREATED_TIME
        defaultScansShouldNotBeFound("createdTime.equals=" + UPDATED_CREATED_TIME);
    }

    @Test
    @Transactional
    void getAllScansByCreatedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where createdTime in DEFAULT_CREATED_TIME or UPDATED_CREATED_TIME
        defaultScansShouldBeFound("createdTime.in=" + DEFAULT_CREATED_TIME + "," + UPDATED_CREATED_TIME);

        // Get all the scansList where createdTime equals to UPDATED_CREATED_TIME
        defaultScansShouldNotBeFound("createdTime.in=" + UPDATED_CREATED_TIME);
    }

    @Test
    @Transactional
    void getAllScansByCreatedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where createdTime is not null
        defaultScansShouldBeFound("createdTime.specified=true");

        // Get all the scansList where createdTime is null
        defaultScansShouldNotBeFound("createdTime.specified=false");
    }

    @Test
    @Transactional
    void getAllScansByScannedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where scannedTime equals to DEFAULT_SCANNED_TIME
        defaultScansShouldBeFound("scannedTime.equals=" + DEFAULT_SCANNED_TIME);

        // Get all the scansList where scannedTime equals to UPDATED_SCANNED_TIME
        defaultScansShouldNotBeFound("scannedTime.equals=" + UPDATED_SCANNED_TIME);
    }

    @Test
    @Transactional
    void getAllScansByScannedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where scannedTime in DEFAULT_SCANNED_TIME or UPDATED_SCANNED_TIME
        defaultScansShouldBeFound("scannedTime.in=" + DEFAULT_SCANNED_TIME + "," + UPDATED_SCANNED_TIME);

        // Get all the scansList where scannedTime equals to UPDATED_SCANNED_TIME
        defaultScansShouldNotBeFound("scannedTime.in=" + UPDATED_SCANNED_TIME);
    }

    @Test
    @Transactional
    void getAllScansByScannedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where scannedTime is not null
        defaultScansShouldBeFound("scannedTime.specified=true");

        // Get all the scansList where scannedTime is null
        defaultScansShouldNotBeFound("scannedTime.specified=false");
    }

    @Test
    @Transactional
    void getAllScansByInspectedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where inspectedTime equals to DEFAULT_INSPECTED_TIME
        defaultScansShouldBeFound("inspectedTime.equals=" + DEFAULT_INSPECTED_TIME);

        // Get all the scansList where inspectedTime equals to UPDATED_INSPECTED_TIME
        defaultScansShouldNotBeFound("inspectedTime.equals=" + UPDATED_INSPECTED_TIME);
    }

    @Test
    @Transactional
    void getAllScansByInspectedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where inspectedTime in DEFAULT_INSPECTED_TIME or UPDATED_INSPECTED_TIME
        defaultScansShouldBeFound("inspectedTime.in=" + DEFAULT_INSPECTED_TIME + "," + UPDATED_INSPECTED_TIME);

        // Get all the scansList where inspectedTime equals to UPDATED_INSPECTED_TIME
        defaultScansShouldNotBeFound("inspectedTime.in=" + UPDATED_INSPECTED_TIME);
    }

    @Test
    @Transactional
    void getAllScansByInspectedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where inspectedTime is not null
        defaultScansShouldBeFound("inspectedTime.specified=true");

        // Get all the scansList where inspectedTime is null
        defaultScansShouldNotBeFound("inspectedTime.specified=false");
    }

    @Test
    @Transactional
    void getAllScansByModifiedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where modifiedTime equals to DEFAULT_MODIFIED_TIME
        defaultScansShouldBeFound("modifiedTime.equals=" + DEFAULT_MODIFIED_TIME);

        // Get all the scansList where modifiedTime equals to UPDATED_MODIFIED_TIME
        defaultScansShouldNotBeFound("modifiedTime.equals=" + UPDATED_MODIFIED_TIME);
    }

    @Test
    @Transactional
    void getAllScansByModifiedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where modifiedTime in DEFAULT_MODIFIED_TIME or UPDATED_MODIFIED_TIME
        defaultScansShouldBeFound("modifiedTime.in=" + DEFAULT_MODIFIED_TIME + "," + UPDATED_MODIFIED_TIME);

        // Get all the scansList where modifiedTime equals to UPDATED_MODIFIED_TIME
        defaultScansShouldNotBeFound("modifiedTime.in=" + UPDATED_MODIFIED_TIME);
    }

    @Test
    @Transactional
    void getAllScansByModifiedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where modifiedTime is not null
        defaultScansShouldBeFound("modifiedTime.specified=true");

        // Get all the scansList where modifiedTime is null
        defaultScansShouldNotBeFound("modifiedTime.specified=false");
    }

    @Test
    @Transactional
    void getAllScansByEjectedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where ejectedTime equals to DEFAULT_EJECTED_TIME
        defaultScansShouldBeFound("ejectedTime.equals=" + DEFAULT_EJECTED_TIME);

        // Get all the scansList where ejectedTime equals to UPDATED_EJECTED_TIME
        defaultScansShouldNotBeFound("ejectedTime.equals=" + UPDATED_EJECTED_TIME);
    }

    @Test
    @Transactional
    void getAllScansByEjectedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where ejectedTime in DEFAULT_EJECTED_TIME or UPDATED_EJECTED_TIME
        defaultScansShouldBeFound("ejectedTime.in=" + DEFAULT_EJECTED_TIME + "," + UPDATED_EJECTED_TIME);

        // Get all the scansList where ejectedTime equals to UPDATED_EJECTED_TIME
        defaultScansShouldNotBeFound("ejectedTime.in=" + UPDATED_EJECTED_TIME);
    }

    @Test
    @Transactional
    void getAllScansByEjectedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        scansRepository.saveAndFlush(scans);

        // Get all the scansList where ejectedTime is not null
        defaultScansShouldBeFound("ejectedTime.specified=true");

        // Get all the scansList where ejectedTime is null
        defaultScansShouldNotBeFound("ejectedTime.specified=false");
    }

    @Test
    @Transactional
    void getAllScansByImageIsEqualToSomething() throws Exception {
        Images image;
        if (TestUtil.findAll(em, Images.class).isEmpty()) {
            scansRepository.saveAndFlush(scans);
            image = ImagesResourceIT.createEntity(em);
        } else {
            image = TestUtil.findAll(em, Images.class).get(0);
        }
        em.persist(image);
        em.flush();
        scans.setImage(image);
        scansRepository.saveAndFlush(scans);
        String imageId = image.getId();
        // Get all the scansList where image equals to imageId
        defaultScansShouldBeFound("imageId.equals=" + imageId);

        // Get all the scansList where image equals to "invalid-id"
        defaultScansShouldNotBeFound("imageId.equals=" + "invalid-id");
    }

    @Test
    @Transactional
    void getAllScansByProductionBatchIsEqualToSomething() throws Exception {
        Batches productionBatch;
        if (TestUtil.findAll(em, Batches.class).isEmpty()) {
            scansRepository.saveAndFlush(scans);
            productionBatch = BatchesResourceIT.createEntity(em);
        } else {
            productionBatch = TestUtil.findAll(em, Batches.class).get(0);
        }
        em.persist(productionBatch);
        em.flush();
        scans.setProductionBatch(productionBatch);
        scansRepository.saveAndFlush(scans);
        String productionBatchId = productionBatch.getId();
        // Get all the scansList where productionBatch equals to productionBatchId
        defaultScansShouldBeFound("productionBatchId.equals=" + productionBatchId);

        // Get all the scansList where productionBatch equals to "invalid-id"
        defaultScansShouldNotBeFound("productionBatchId.equals=" + "invalid-id");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultScansShouldBeFound(String filter) throws Exception {
        restScansMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scans.getId())))
            .andExpect(jsonPath("$.[*].scannerId").value(hasItem(DEFAULT_SCANNER_ID.toString())))
            .andExpect(jsonPath("$.[*].sequenceInBatch").value(hasItem(DEFAULT_SEQUENCE_IN_BATCH)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].dE").value(hasItem(DEFAULT_D_E.doubleValue())))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.toString())))
            .andExpect(jsonPath("$.[*].scannedTime").value(hasItem(DEFAULT_SCANNED_TIME.toString())))
            .andExpect(jsonPath("$.[*].inspectedTime").value(hasItem(DEFAULT_INSPECTED_TIME.toString())))
            .andExpect(jsonPath("$.[*].modifiedTime").value(hasItem(DEFAULT_MODIFIED_TIME.toString())))
            .andExpect(jsonPath("$.[*].ejectedTime").value(hasItem(DEFAULT_EJECTED_TIME.toString())));

        // Check, that the count call also returns 1
        restScansMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultScansShouldNotBeFound(String filter) throws Exception {
        restScansMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restScansMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingScans() throws Exception {
        // Get the scans
        restScansMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingScans() throws Exception {
        // Initialize the database
        scans.setId(UUID.randomUUID().toString());
        scansRepository.saveAndFlush(scans);

        int databaseSizeBeforeUpdate = scansRepository.findAll().size();

        // Update the scans
        Scans updatedScans = scansRepository.findById(scans.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedScans are not directly saved in db
        em.detach(updatedScans);
        updatedScans
            .scannerId(UPDATED_SCANNER_ID)
            .sequenceInBatch(UPDATED_SEQUENCE_IN_BATCH)
            .state(UPDATED_STATE)
            .dE(UPDATED_D_E)
            .createdTime(UPDATED_CREATED_TIME)
            .scannedTime(UPDATED_SCANNED_TIME)
            .inspectedTime(UPDATED_INSPECTED_TIME)
            .modifiedTime(UPDATED_MODIFIED_TIME)
            .ejectedTime(UPDATED_EJECTED_TIME);
        ScansDTO scansDTO = scansMapper.toDto(updatedScans);

        restScansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, scansDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(scansDTO))
            )
            .andExpect(status().isOk());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeUpdate);
        Scans testScans = scansList.get(scansList.size() - 1);
        assertThat(testScans.getScannerId()).isEqualTo(UPDATED_SCANNER_ID);
        assertThat(testScans.getSequenceInBatch()).isEqualTo(UPDATED_SEQUENCE_IN_BATCH);
        assertThat(testScans.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testScans.getdE()).isEqualTo(UPDATED_D_E);
        assertThat(testScans.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
        assertThat(testScans.getScannedTime()).isEqualTo(UPDATED_SCANNED_TIME);
        assertThat(testScans.getInspectedTime()).isEqualTo(UPDATED_INSPECTED_TIME);
        assertThat(testScans.getModifiedTime()).isEqualTo(UPDATED_MODIFIED_TIME);
        assertThat(testScans.getEjectedTime()).isEqualTo(UPDATED_EJECTED_TIME);
    }

    @Test
    @Transactional
    void putNonExistingScans() throws Exception {
        int databaseSizeBeforeUpdate = scansRepository.findAll().size();
        scans.setId(UUID.randomUUID().toString());

        // Create the Scans
        ScansDTO scansDTO = scansMapper.toDto(scans);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, scansDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(scansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchScans() throws Exception {
        int databaseSizeBeforeUpdate = scansRepository.findAll().size();
        scans.setId(UUID.randomUUID().toString());

        // Create the Scans
        ScansDTO scansDTO = scansMapper.toDto(scans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScansMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(scansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamScans() throws Exception {
        int databaseSizeBeforeUpdate = scansRepository.findAll().size();
        scans.setId(UUID.randomUUID().toString());

        // Create the Scans
        ScansDTO scansDTO = scansMapper.toDto(scans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScansMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(scansDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateScansWithPatch() throws Exception {
        // Initialize the database
        scans.setId(UUID.randomUUID().toString());
        scansRepository.saveAndFlush(scans);

        int databaseSizeBeforeUpdate = scansRepository.findAll().size();

        // Update the scans using partial update
        Scans partialUpdatedScans = new Scans();
        partialUpdatedScans.setId(scans.getId());

        partialUpdatedScans
            .scannerId(UPDATED_SCANNER_ID)
            .sequenceInBatch(UPDATED_SEQUENCE_IN_BATCH)
            .createdTime(UPDATED_CREATED_TIME)
            .scannedTime(UPDATED_SCANNED_TIME);

        restScansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedScans.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedScans))
            )
            .andExpect(status().isOk());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeUpdate);
        Scans testScans = scansList.get(scansList.size() - 1);
        assertThat(testScans.getScannerId()).isEqualTo(UPDATED_SCANNER_ID);
        assertThat(testScans.getSequenceInBatch()).isEqualTo(UPDATED_SEQUENCE_IN_BATCH);
        assertThat(testScans.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testScans.getdE()).isEqualTo(DEFAULT_D_E);
        assertThat(testScans.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
        assertThat(testScans.getScannedTime()).isEqualTo(UPDATED_SCANNED_TIME);
        assertThat(testScans.getInspectedTime()).isEqualTo(DEFAULT_INSPECTED_TIME);
        assertThat(testScans.getModifiedTime()).isEqualTo(DEFAULT_MODIFIED_TIME);
        assertThat(testScans.getEjectedTime()).isEqualTo(DEFAULT_EJECTED_TIME);
    }

    @Test
    @Transactional
    void fullUpdateScansWithPatch() throws Exception {
        // Initialize the database
        scans.setId(UUID.randomUUID().toString());
        scansRepository.saveAndFlush(scans);

        int databaseSizeBeforeUpdate = scansRepository.findAll().size();

        // Update the scans using partial update
        Scans partialUpdatedScans = new Scans();
        partialUpdatedScans.setId(scans.getId());

        partialUpdatedScans
            .scannerId(UPDATED_SCANNER_ID)
            .sequenceInBatch(UPDATED_SEQUENCE_IN_BATCH)
            .state(UPDATED_STATE)
            .dE(UPDATED_D_E)
            .createdTime(UPDATED_CREATED_TIME)
            .scannedTime(UPDATED_SCANNED_TIME)
            .inspectedTime(UPDATED_INSPECTED_TIME)
            .modifiedTime(UPDATED_MODIFIED_TIME)
            .ejectedTime(UPDATED_EJECTED_TIME);

        restScansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedScans.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedScans))
            )
            .andExpect(status().isOk());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeUpdate);
        Scans testScans = scansList.get(scansList.size() - 1);
        assertThat(testScans.getScannerId()).isEqualTo(UPDATED_SCANNER_ID);
        assertThat(testScans.getSequenceInBatch()).isEqualTo(UPDATED_SEQUENCE_IN_BATCH);
        assertThat(testScans.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testScans.getdE()).isEqualTo(UPDATED_D_E);
        assertThat(testScans.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
        assertThat(testScans.getScannedTime()).isEqualTo(UPDATED_SCANNED_TIME);
        assertThat(testScans.getInspectedTime()).isEqualTo(UPDATED_INSPECTED_TIME);
        assertThat(testScans.getModifiedTime()).isEqualTo(UPDATED_MODIFIED_TIME);
        assertThat(testScans.getEjectedTime()).isEqualTo(UPDATED_EJECTED_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingScans() throws Exception {
        int databaseSizeBeforeUpdate = scansRepository.findAll().size();
        scans.setId(UUID.randomUUID().toString());

        // Create the Scans
        ScansDTO scansDTO = scansMapper.toDto(scans);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, scansDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(scansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchScans() throws Exception {
        int databaseSizeBeforeUpdate = scansRepository.findAll().size();
        scans.setId(UUID.randomUUID().toString());

        // Create the Scans
        ScansDTO scansDTO = scansMapper.toDto(scans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScansMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(scansDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamScans() throws Exception {
        int databaseSizeBeforeUpdate = scansRepository.findAll().size();
        scans.setId(UUID.randomUUID().toString());

        // Create the Scans
        ScansDTO scansDTO = scansMapper.toDto(scans);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScansMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(scansDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Scans in the database
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteScans() throws Exception {
        // Initialize the database
        scans.setId(UUID.randomUUID().toString());
        scansRepository.saveAndFlush(scans);

        int databaseSizeBeforeDelete = scansRepository.findAll().size();

        // Delete the scans
        restScansMockMvc
            .perform(delete(ENTITY_API_URL_ID, scans.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Scans> scansList = scansRepository.findAll();
        assertThat(scansList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
