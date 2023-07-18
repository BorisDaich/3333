package biz.daich.tambour.shlicht.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import biz.daich.tambour.shlicht.IntegrationTest;
import biz.daich.tambour.shlicht.domain.Batches;
import biz.daich.tambour.shlicht.domain.Catalogs;
import biz.daich.tambour.shlicht.domain.Scans;
import biz.daich.tambour.shlicht.repository.BatchesRepository;
import biz.daich.tambour.shlicht.service.criteria.BatchesCriteria;
import biz.daich.tambour.shlicht.service.dto.BatchesDTO;
import biz.daich.tambour.shlicht.service.mapper.BatchesMapper;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link BatchesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BatchesResourceIT {

    private static final String DEFAULT_PO_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PO_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SEQUENCE_IN_PO = 1;
    private static final Integer UPDATED_SEQUENCE_IN_PO = 2;
    private static final Integer SMALLER_SEQUENCE_IN_PO = 1 - 1;

    private static final String DEFAULT_SCANNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_SCANNER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID = "AAAAAAAAAA";
    private static final String UPDATED_PREVIOUS_PRODUCTION_BATCH_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final Double DEFAULT_INSPECTION_SEQUENCE = 1D;
    private static final Double UPDATED_INSPECTION_SEQUENCE = 2D;
    private static final Double SMALLER_INSPECTION_SEQUENCE = 1D - 1D;

    private static final Integer DEFAULT_ORDERED_QUANTITY = 1;
    private static final Integer UPDATED_ORDERED_QUANTITY = 2;
    private static final Integer SMALLER_ORDERED_QUANTITY = 1 - 1;

    private static final Integer DEFAULT_PRODUCING_QUANTITY = 1;
    private static final Integer UPDATED_PRODUCING_QUANTITY = 2;
    private static final Integer SMALLER_PRODUCING_QUANTITY = 1 - 1;

    private static final Integer DEFAULT_TOTAL_PRODUCING_QUANTITY = 1;
    private static final Integer UPDATED_TOTAL_PRODUCING_QUANTITY = 2;
    private static final Integer SMALLER_TOTAL_PRODUCING_QUANTITY = 1 - 1;

    private static final Integer DEFAULT_REMAINING_QUANTITY = 1;
    private static final Integer UPDATED_REMAINING_QUANTITY = 2;
    private static final Integer SMALLER_REMAINING_QUANTITY = 1 - 1;

    private static final Integer DEFAULT_TOTALREMAINING_QUANTITY = 1;
    private static final Integer UPDATED_TOTALREMAINING_QUANTITY = 2;
    private static final Integer SMALLER_TOTALREMAINING_QUANTITY = 1 - 1;

    private static final Integer DEFAULT_INSPECTED_QUANTITY = 1;
    private static final Integer UPDATED_INSPECTED_QUANTITY = 2;
    private static final Integer SMALLER_INSPECTED_QUANTITY = 1 - 1;

    private static final Integer DEFAULT_TOTAL_INSPECTED_QUANTITY = 1;
    private static final Integer UPDATED_TOTAL_INSPECTED_QUANTITY = 2;
    private static final Integer SMALLER_TOTAL_INSPECTED_QUANTITY = 1 - 1;

    private static final Integer DEFAULT_FAILED_QUANTITY = 1;
    private static final Integer UPDATED_FAILED_QUANTITY = 2;
    private static final Integer SMALLER_FAILED_QUANTITY = 1 - 1;

    private static final Integer DEFAULT_TOTAL_FAILED_QUANTITY = 1;
    private static final Integer UPDATED_TOTAL_FAILED_QUANTITY = 2;
    private static final Integer SMALLER_TOTAL_FAILED_QUANTITY = 1 - 1;

    private static final String DEFAULT_COLOR_ID = "AAAAAAAAAA";
    private static final String UPDATED_COLOR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COLOR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COLOR_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_COLOR_BASEMATERIAL = 1L;
    private static final Long UPDATED_COLOR_BASEMATERIAL = 2L;
    private static final Long SMALLER_COLOR_BASEMATERIAL = 1L - 1L;

    private static final Double DEFAULT_COLOR_LAB_L = 1D;
    private static final Double UPDATED_COLOR_LAB_L = 2D;
    private static final Double SMALLER_COLOR_LAB_L = 1D - 1D;

    private static final Double DEFAULT_COLOR_LAB_A = 1D;
    private static final Double UPDATED_COLOR_LAB_A = 2D;
    private static final Double SMALLER_COLOR_LAB_A = 1D - 1D;

    private static final Double DEFAULT_COLOR_LAB_B = 1D;
    private static final Double UPDATED_COLOR_LAB_B = 2D;
    private static final Double SMALLER_COLOR_LAB_B = 1D - 1D;

    private static final Double DEFAULT_COLOR_A = 1D;
    private static final Double UPDATED_COLOR_A = 2D;
    private static final Double SMALLER_COLOR_A = 1D - 1D;

    private static final Double DEFAULT_COLOR_B = 1D;
    private static final Double UPDATED_COLOR_B = 2D;
    private static final Double SMALLER_COLOR_B = 1D - 1D;

    private static final Double DEFAULT_COLOR_C = 1D;
    private static final Double UPDATED_COLOR_C = 2D;
    private static final Double SMALLER_COLOR_C = 1D - 1D;

    private static final Double DEFAULT_COLOR_D = 1D;
    private static final Double UPDATED_COLOR_D = 2D;
    private static final Double SMALLER_COLOR_D = 1D - 1D;

    private static final Double DEFAULT_COLOR_E = 1D;
    private static final Double UPDATED_COLOR_E = 2D;
    private static final Double SMALLER_COLOR_E = 1D - 1D;

    private static final Double DEFAULT_COLOR_F = 1D;
    private static final Double UPDATED_COLOR_F = 2D;
    private static final Double SMALLER_COLOR_F = 1D - 1D;

    private static final Double DEFAULT_COLOR_G = 1D;
    private static final Double UPDATED_COLOR_G = 2D;
    private static final Double SMALLER_COLOR_G = 1D - 1D;

    private static final Double DEFAULT_COLOR_H = 1D;
    private static final Double UPDATED_COLOR_H = 2D;
    private static final Double SMALLER_COLOR_H = 1D - 1D;

    private static final Double DEFAULT_COLOR_I = 1D;
    private static final Double UPDATED_COLOR_I = 2D;
    private static final Double SMALLER_COLOR_I = 1D - 1D;

    private static final Double DEFAULT_COLOR_J = 1D;
    private static final Double UPDATED_COLOR_J = 2D;
    private static final Double SMALLER_COLOR_J = 1D - 1D;

    private static final Double DEFAULT_COLOR_K = 1D;
    private static final Double UPDATED_COLOR_K = 2D;
    private static final Double SMALLER_COLOR_K = 1D - 1D;

    private static final Double DEFAULT_COLOR_L = 1D;
    private static final Double UPDATED_COLOR_L = 2D;
    private static final Double SMALLER_COLOR_L = 1D - 1D;

    private static final Double DEFAULT_COLOR_M = 1D;
    private static final Double UPDATED_COLOR_M = 2D;
    private static final Double SMALLER_COLOR_M = 1D - 1D;

    private static final Double DEFAULT_COLOR_N = 1D;
    private static final Double UPDATED_COLOR_N = 2D;
    private static final Double SMALLER_COLOR_N = 1D - 1D;

    private static final Double DEFAULT_COLOR_O = 1D;
    private static final Double UPDATED_COLOR_O = 2D;
    private static final Double SMALLER_COLOR_O = 1D - 1D;

    private static final Double DEFAULT_COLOR_P = 1D;
    private static final Double UPDATED_COLOR_P = 2D;
    private static final Double SMALLER_COLOR_P = 1D - 1D;

    private static final Double DEFAULT_COLOR_Q = 1D;
    private static final Double UPDATED_COLOR_Q = 2D;
    private static final Double SMALLER_COLOR_Q = 1D - 1D;

    private static final Double DEFAULT_COLOR_R = 1D;
    private static final Double UPDATED_COLOR_R = 2D;
    private static final Double SMALLER_COLOR_R = 1D - 1D;

    private static final Double DEFAULT_COLOR_S = 1D;
    private static final Double UPDATED_COLOR_S = 2D;
    private static final Double SMALLER_COLOR_S = 1D - 1D;

    private static final Double DEFAULT_COLOR_T = 1D;
    private static final Double UPDATED_COLOR_T = 2D;
    private static final Double SMALLER_COLOR_T = 1D - 1D;

    private static final Double DEFAULT_COLOR_U = 1D;
    private static final Double UPDATED_COLOR_U = 2D;
    private static final Double SMALLER_COLOR_U = 1D - 1D;

    private static final Double DEFAULT_COLOR_V = 1D;
    private static final Double UPDATED_COLOR_V = 2D;
    private static final Double SMALLER_COLOR_V = 1D - 1D;

    private static final Double DEFAULT_COLOR_W = 1D;
    private static final Double UPDATED_COLOR_W = 2D;
    private static final Double SMALLER_COLOR_W = 1D - 1D;

    private static final Double DEFAULT_COLOR_X = 1D;
    private static final Double UPDATED_COLOR_X = 2D;
    private static final Double SMALLER_COLOR_X = 1D - 1D;

    private static final Double DEFAULT_COLOR_Y = 1D;
    private static final Double UPDATED_COLOR_Y = 2D;
    private static final Double SMALLER_COLOR_Y = 1D - 1D;

    private static final Double DEFAULT_COLOR_Z = 1D;
    private static final Double UPDATED_COLOR_Z = 2D;
    private static final Double SMALLER_COLOR_Z = 1D - 1D;

    private static final String DEFAULT_CATALOG_ID = "AAAAAAAAAA";
    private static final String UPDATED_CATALOG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CATALOG_EXTERNALID = "AAAAAAAAAA";
    private static final String UPDATED_CATALOG_EXTERNALID = "BBBBBBBBBB";

    private static final String DEFAULT_CATALOG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATALOG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATALOG_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_CATALOG_VERSION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CATALOG_ISACTIVE = false;
    private static final Boolean UPDATED_CATALOG_ISACTIVE = true;

    private static final Instant DEFAULT_CATALOG_CREATEDTIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CATALOG_CREATEDTIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_BASE_MATERIAL_ID = 1L;
    private static final Long UPDATED_BASE_MATERIAL_ID = 2L;
    private static final Long SMALLER_BASE_MATERIAL_ID = 1L - 1L;

    private static final Instant DEFAULT_ORDERED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ORDERED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_STARTED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STARTED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SUSPENDED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUSPENDED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FINISHED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FINISHED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ORIGINAL = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINAL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/batches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private BatchesRepository batchesRepository;

    @Autowired
    private BatchesMapper batchesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBatchesMockMvc;

    private Batches batches;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Batches createEntity(EntityManager em) {
        Batches batches = new Batches()
            .poName(DEFAULT_PO_NAME)
            .sequenceInPo(DEFAULT_SEQUENCE_IN_PO)
            .scannerId(DEFAULT_SCANNER_ID)
            .previousProductionBatchId(DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID)
            .state(DEFAULT_STATE)
            .inspectionSequence(DEFAULT_INSPECTION_SEQUENCE)
            .orderedQuantity(DEFAULT_ORDERED_QUANTITY)
            .producingQuantity(DEFAULT_PRODUCING_QUANTITY)
            .totalProducingQuantity(DEFAULT_TOTAL_PRODUCING_QUANTITY)
            .remainingQuantity(DEFAULT_REMAINING_QUANTITY)
            .totalremainingQuantity(DEFAULT_TOTALREMAINING_QUANTITY)
            .inspectedQuantity(DEFAULT_INSPECTED_QUANTITY)
            .totalInspectedQuantity(DEFAULT_TOTAL_INSPECTED_QUANTITY)
            .failedQuantity(DEFAULT_FAILED_QUANTITY)
            .totalFailedQuantity(DEFAULT_TOTAL_FAILED_QUANTITY)
            .colorId(DEFAULT_COLOR_ID)
            .colorCode(DEFAULT_COLOR_CODE)
            .colorName(DEFAULT_COLOR_NAME)
            .colorBasematerial(DEFAULT_COLOR_BASEMATERIAL)
            .colorLabL(DEFAULT_COLOR_LAB_L)
            .colorLabA(DEFAULT_COLOR_LAB_A)
            .colorLabB(DEFAULT_COLOR_LAB_B)
            .colorA(DEFAULT_COLOR_A)
            .colorB(DEFAULT_COLOR_B)
            .colorC(DEFAULT_COLOR_C)
            .colorD(DEFAULT_COLOR_D)
            .colorE(DEFAULT_COLOR_E)
            .colorF(DEFAULT_COLOR_F)
            .colorG(DEFAULT_COLOR_G)
            .colorH(DEFAULT_COLOR_H)
            .colorI(DEFAULT_COLOR_I)
            .colorJ(DEFAULT_COLOR_J)
            .colorK(DEFAULT_COLOR_K)
            .colorL(DEFAULT_COLOR_L)
            .colorM(DEFAULT_COLOR_M)
            .colorN(DEFAULT_COLOR_N)
            .colorO(DEFAULT_COLOR_O)
            .colorP(DEFAULT_COLOR_P)
            .colorQ(DEFAULT_COLOR_Q)
            .colorR(DEFAULT_COLOR_R)
            .colorS(DEFAULT_COLOR_S)
            .colorT(DEFAULT_COLOR_T)
            .colorU(DEFAULT_COLOR_U)
            .colorV(DEFAULT_COLOR_V)
            .colorW(DEFAULT_COLOR_W)
            .colorX(DEFAULT_COLOR_X)
            .colorY(DEFAULT_COLOR_Y)
            .colorZ(DEFAULT_COLOR_Z)
            .catalogId(DEFAULT_CATALOG_ID)
            .catalogExternalid(DEFAULT_CATALOG_EXTERNALID)
            .catalogName(DEFAULT_CATALOG_NAME)
            .catalogVersion(DEFAULT_CATALOG_VERSION)
            .catalogIsactive(DEFAULT_CATALOG_ISACTIVE)
            .catalogCreatedtime(DEFAULT_CATALOG_CREATEDTIME)
            .baseMaterialId(DEFAULT_BASE_MATERIAL_ID)
            .orderedTime(DEFAULT_ORDERED_TIME)
            .startedTime(DEFAULT_STARTED_TIME)
            .modifiedTime(DEFAULT_MODIFIED_TIME)
            .suspendedTime(DEFAULT_SUSPENDED_TIME)
            .finishedTime(DEFAULT_FINISHED_TIME)
            .original(DEFAULT_ORIGINAL);
        return batches;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Batches createUpdatedEntity(EntityManager em) {
        Batches batches = new Batches()
            .poName(UPDATED_PO_NAME)
            .sequenceInPo(UPDATED_SEQUENCE_IN_PO)
            .scannerId(UPDATED_SCANNER_ID)
            .previousProductionBatchId(UPDATED_PREVIOUS_PRODUCTION_BATCH_ID)
            .state(UPDATED_STATE)
            .inspectionSequence(UPDATED_INSPECTION_SEQUENCE)
            .orderedQuantity(UPDATED_ORDERED_QUANTITY)
            .producingQuantity(UPDATED_PRODUCING_QUANTITY)
            .totalProducingQuantity(UPDATED_TOTAL_PRODUCING_QUANTITY)
            .remainingQuantity(UPDATED_REMAINING_QUANTITY)
            .totalremainingQuantity(UPDATED_TOTALREMAINING_QUANTITY)
            .inspectedQuantity(UPDATED_INSPECTED_QUANTITY)
            .totalInspectedQuantity(UPDATED_TOTAL_INSPECTED_QUANTITY)
            .failedQuantity(UPDATED_FAILED_QUANTITY)
            .totalFailedQuantity(UPDATED_TOTAL_FAILED_QUANTITY)
            .colorId(UPDATED_COLOR_ID)
            .colorCode(UPDATED_COLOR_CODE)
            .colorName(UPDATED_COLOR_NAME)
            .colorBasematerial(UPDATED_COLOR_BASEMATERIAL)
            .colorLabL(UPDATED_COLOR_LAB_L)
            .colorLabA(UPDATED_COLOR_LAB_A)
            .colorLabB(UPDATED_COLOR_LAB_B)
            .colorA(UPDATED_COLOR_A)
            .colorB(UPDATED_COLOR_B)
            .colorC(UPDATED_COLOR_C)
            .colorD(UPDATED_COLOR_D)
            .colorE(UPDATED_COLOR_E)
            .colorF(UPDATED_COLOR_F)
            .colorG(UPDATED_COLOR_G)
            .colorH(UPDATED_COLOR_H)
            .colorI(UPDATED_COLOR_I)
            .colorJ(UPDATED_COLOR_J)
            .colorK(UPDATED_COLOR_K)
            .colorL(UPDATED_COLOR_L)
            .colorM(UPDATED_COLOR_M)
            .colorN(UPDATED_COLOR_N)
            .colorO(UPDATED_COLOR_O)
            .colorP(UPDATED_COLOR_P)
            .colorQ(UPDATED_COLOR_Q)
            .colorR(UPDATED_COLOR_R)
            .colorS(UPDATED_COLOR_S)
            .colorT(UPDATED_COLOR_T)
            .colorU(UPDATED_COLOR_U)
            .colorV(UPDATED_COLOR_V)
            .colorW(UPDATED_COLOR_W)
            .colorX(UPDATED_COLOR_X)
            .colorY(UPDATED_COLOR_Y)
            .colorZ(UPDATED_COLOR_Z)
            .catalogId(UPDATED_CATALOG_ID)
            .catalogExternalid(UPDATED_CATALOG_EXTERNALID)
            .catalogName(UPDATED_CATALOG_NAME)
            .catalogVersion(UPDATED_CATALOG_VERSION)
            .catalogIsactive(UPDATED_CATALOG_ISACTIVE)
            .catalogCreatedtime(UPDATED_CATALOG_CREATEDTIME)
            .baseMaterialId(UPDATED_BASE_MATERIAL_ID)
            .orderedTime(UPDATED_ORDERED_TIME)
            .startedTime(UPDATED_STARTED_TIME)
            .modifiedTime(UPDATED_MODIFIED_TIME)
            .suspendedTime(UPDATED_SUSPENDED_TIME)
            .finishedTime(UPDATED_FINISHED_TIME)
            .original(UPDATED_ORIGINAL);
        return batches;
    }

    @BeforeEach
    public void initTest() {
        batches = createEntity(em);
    }

    @Test
    @Transactional
    void createBatches() throws Exception {
        int databaseSizeBeforeCreate = batchesRepository.findAll().size();
        // Create the Batches
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);
        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isCreated());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeCreate + 1);
        Batches testBatches = batchesList.get(batchesList.size() - 1);
        assertThat(testBatches.getPoName()).isEqualTo(DEFAULT_PO_NAME);
        assertThat(testBatches.getSequenceInPo()).isEqualTo(DEFAULT_SEQUENCE_IN_PO);
        assertThat(testBatches.getScannerId()).isEqualTo(DEFAULT_SCANNER_ID);
        assertThat(testBatches.getPreviousProductionBatchId()).isEqualTo(DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID);
        assertThat(testBatches.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testBatches.getInspectionSequence()).isEqualTo(DEFAULT_INSPECTION_SEQUENCE);
        assertThat(testBatches.getOrderedQuantity()).isEqualTo(DEFAULT_ORDERED_QUANTITY);
        assertThat(testBatches.getProducingQuantity()).isEqualTo(DEFAULT_PRODUCING_QUANTITY);
        assertThat(testBatches.getTotalProducingQuantity()).isEqualTo(DEFAULT_TOTAL_PRODUCING_QUANTITY);
        assertThat(testBatches.getRemainingQuantity()).isEqualTo(DEFAULT_REMAINING_QUANTITY);
        assertThat(testBatches.getTotalremainingQuantity()).isEqualTo(DEFAULT_TOTALREMAINING_QUANTITY);
        assertThat(testBatches.getInspectedQuantity()).isEqualTo(DEFAULT_INSPECTED_QUANTITY);
        assertThat(testBatches.getTotalInspectedQuantity()).isEqualTo(DEFAULT_TOTAL_INSPECTED_QUANTITY);
        assertThat(testBatches.getFailedQuantity()).isEqualTo(DEFAULT_FAILED_QUANTITY);
        assertThat(testBatches.getTotalFailedQuantity()).isEqualTo(DEFAULT_TOTAL_FAILED_QUANTITY);
        assertThat(testBatches.getColorId()).isEqualTo(DEFAULT_COLOR_ID);
        assertThat(testBatches.getColorCode()).isEqualTo(DEFAULT_COLOR_CODE);
        assertThat(testBatches.getColorName()).isEqualTo(DEFAULT_COLOR_NAME);
        assertThat(testBatches.getColorBasematerial()).isEqualTo(DEFAULT_COLOR_BASEMATERIAL);
        assertThat(testBatches.getColorLabL()).isEqualTo(DEFAULT_COLOR_LAB_L);
        assertThat(testBatches.getColorLabA()).isEqualTo(DEFAULT_COLOR_LAB_A);
        assertThat(testBatches.getColorLabB()).isEqualTo(DEFAULT_COLOR_LAB_B);
        assertThat(testBatches.getColorA()).isEqualTo(DEFAULT_COLOR_A);
        assertThat(testBatches.getColorB()).isEqualTo(DEFAULT_COLOR_B);
        assertThat(testBatches.getColorC()).isEqualTo(DEFAULT_COLOR_C);
        assertThat(testBatches.getColorD()).isEqualTo(DEFAULT_COLOR_D);
        assertThat(testBatches.getColorE()).isEqualTo(DEFAULT_COLOR_E);
        assertThat(testBatches.getColorF()).isEqualTo(DEFAULT_COLOR_F);
        assertThat(testBatches.getColorG()).isEqualTo(DEFAULT_COLOR_G);
        assertThat(testBatches.getColorH()).isEqualTo(DEFAULT_COLOR_H);
        assertThat(testBatches.getColorI()).isEqualTo(DEFAULT_COLOR_I);
        assertThat(testBatches.getColorJ()).isEqualTo(DEFAULT_COLOR_J);
        assertThat(testBatches.getColorK()).isEqualTo(DEFAULT_COLOR_K);
        assertThat(testBatches.getColorL()).isEqualTo(DEFAULT_COLOR_L);
        assertThat(testBatches.getColorM()).isEqualTo(DEFAULT_COLOR_M);
        assertThat(testBatches.getColorN()).isEqualTo(DEFAULT_COLOR_N);
        assertThat(testBatches.getColorO()).isEqualTo(DEFAULT_COLOR_O);
        assertThat(testBatches.getColorP()).isEqualTo(DEFAULT_COLOR_P);
        assertThat(testBatches.getColorQ()).isEqualTo(DEFAULT_COLOR_Q);
        assertThat(testBatches.getColorR()).isEqualTo(DEFAULT_COLOR_R);
        assertThat(testBatches.getColorS()).isEqualTo(DEFAULT_COLOR_S);
        assertThat(testBatches.getColorT()).isEqualTo(DEFAULT_COLOR_T);
        assertThat(testBatches.getColorU()).isEqualTo(DEFAULT_COLOR_U);
        assertThat(testBatches.getColorV()).isEqualTo(DEFAULT_COLOR_V);
        assertThat(testBatches.getColorW()).isEqualTo(DEFAULT_COLOR_W);
        assertThat(testBatches.getColorX()).isEqualTo(DEFAULT_COLOR_X);
        assertThat(testBatches.getColorY()).isEqualTo(DEFAULT_COLOR_Y);
        assertThat(testBatches.getColorZ()).isEqualTo(DEFAULT_COLOR_Z);
        assertThat(testBatches.getCatalogId()).isEqualTo(DEFAULT_CATALOG_ID);
        assertThat(testBatches.getCatalogExternalid()).isEqualTo(DEFAULT_CATALOG_EXTERNALID);
        assertThat(testBatches.getCatalogName()).isEqualTo(DEFAULT_CATALOG_NAME);
        assertThat(testBatches.getCatalogVersion()).isEqualTo(DEFAULT_CATALOG_VERSION);
        assertThat(testBatches.getCatalogIsactive()).isEqualTo(DEFAULT_CATALOG_ISACTIVE);
        assertThat(testBatches.getCatalogCreatedtime()).isEqualTo(DEFAULT_CATALOG_CREATEDTIME);
        assertThat(testBatches.getBaseMaterialId()).isEqualTo(DEFAULT_BASE_MATERIAL_ID);
        assertThat(testBatches.getOrderedTime()).isEqualTo(DEFAULT_ORDERED_TIME);
        assertThat(testBatches.getStartedTime()).isEqualTo(DEFAULT_STARTED_TIME);
        assertThat(testBatches.getModifiedTime()).isEqualTo(DEFAULT_MODIFIED_TIME);
        assertThat(testBatches.getSuspendedTime()).isEqualTo(DEFAULT_SUSPENDED_TIME);
        assertThat(testBatches.getFinishedTime()).isEqualTo(DEFAULT_FINISHED_TIME);
        assertThat(testBatches.getOriginal()).isEqualTo(DEFAULT_ORIGINAL);
    }

    @Test
    @Transactional
    void createBatchesWithExistingId() throws Exception {
        // Create the Batches with an existing ID
        batches.setId("existing_id");
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        int databaseSizeBeforeCreate = batchesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPoNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setPoName(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSequenceInPoIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setSequenceInPo(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setState(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInspectionSequenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setInspectionSequence(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOrderedQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setOrderedQuantity(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkProducingQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setProducingQuantity(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTotalProducingQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setTotalProducingQuantity(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRemainingQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setRemainingQuantity(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTotalremainingQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setTotalremainingQuantity(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInspectedQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setInspectedQuantity(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTotalInspectedQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setTotalInspectedQuantity(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFailedQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setFailedQuantity(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTotalFailedQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setTotalFailedQuantity(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorAIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorA(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorBIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorB(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorCIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorC(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorDIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorD(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorEIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorE(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorFIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorF(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorGIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorG(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorHIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorH(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorIIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorI(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorJIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorJ(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorKIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorK(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorLIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorL(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorMIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorM(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorNIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorN(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorOIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorO(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorPIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorP(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorQIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorQ(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorRIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorR(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorSIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorS(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorTIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorT(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorUIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorU(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorVIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorV(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorWIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorW(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorXIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorX(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorYIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorY(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkColorZIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setColorZ(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCatalogIsactiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setCatalogIsactive(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBaseMaterialIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setBaseMaterialId(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOrderedTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = batchesRepository.findAll().size();
        // set the field null
        batches.setOrderedTime(null);

        // Create the Batches, which fails.
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        restBatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isBadRequest());

        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBatches() throws Exception {
        // Initialize the database
        batches.setId(UUID.randomUUID().toString());
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList
        restBatchesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batches.getId())))
            .andExpect(jsonPath("$.[*].poName").value(hasItem(DEFAULT_PO_NAME)))
            .andExpect(jsonPath("$.[*].sequenceInPo").value(hasItem(DEFAULT_SEQUENCE_IN_PO)))
            .andExpect(jsonPath("$.[*].scannerId").value(hasItem(DEFAULT_SCANNER_ID.toString())))
            .andExpect(jsonPath("$.[*].previousProductionBatchId").value(hasItem(DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].inspectionSequence").value(hasItem(DEFAULT_INSPECTION_SEQUENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].orderedQuantity").value(hasItem(DEFAULT_ORDERED_QUANTITY)))
            .andExpect(jsonPath("$.[*].producingQuantity").value(hasItem(DEFAULT_PRODUCING_QUANTITY)))
            .andExpect(jsonPath("$.[*].totalProducingQuantity").value(hasItem(DEFAULT_TOTAL_PRODUCING_QUANTITY)))
            .andExpect(jsonPath("$.[*].remainingQuantity").value(hasItem(DEFAULT_REMAINING_QUANTITY)))
            .andExpect(jsonPath("$.[*].totalremainingQuantity").value(hasItem(DEFAULT_TOTALREMAINING_QUANTITY)))
            .andExpect(jsonPath("$.[*].inspectedQuantity").value(hasItem(DEFAULT_INSPECTED_QUANTITY)))
            .andExpect(jsonPath("$.[*].totalInspectedQuantity").value(hasItem(DEFAULT_TOTAL_INSPECTED_QUANTITY)))
            .andExpect(jsonPath("$.[*].failedQuantity").value(hasItem(DEFAULT_FAILED_QUANTITY)))
            .andExpect(jsonPath("$.[*].totalFailedQuantity").value(hasItem(DEFAULT_TOTAL_FAILED_QUANTITY)))
            .andExpect(jsonPath("$.[*].colorId").value(hasItem(DEFAULT_COLOR_ID)))
            .andExpect(jsonPath("$.[*].colorCode").value(hasItem(DEFAULT_COLOR_CODE.toString())))
            .andExpect(jsonPath("$.[*].colorName").value(hasItem(DEFAULT_COLOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].colorBasematerial").value(hasItem(DEFAULT_COLOR_BASEMATERIAL.intValue())))
            .andExpect(jsonPath("$.[*].colorLabL").value(hasItem(DEFAULT_COLOR_LAB_L.doubleValue())))
            .andExpect(jsonPath("$.[*].colorLabA").value(hasItem(DEFAULT_COLOR_LAB_A.doubleValue())))
            .andExpect(jsonPath("$.[*].colorLabB").value(hasItem(DEFAULT_COLOR_LAB_B.doubleValue())))
            .andExpect(jsonPath("$.[*].colorA").value(hasItem(DEFAULT_COLOR_A.doubleValue())))
            .andExpect(jsonPath("$.[*].colorB").value(hasItem(DEFAULT_COLOR_B.doubleValue())))
            .andExpect(jsonPath("$.[*].colorC").value(hasItem(DEFAULT_COLOR_C.doubleValue())))
            .andExpect(jsonPath("$.[*].colorD").value(hasItem(DEFAULT_COLOR_D.doubleValue())))
            .andExpect(jsonPath("$.[*].colorE").value(hasItem(DEFAULT_COLOR_E.doubleValue())))
            .andExpect(jsonPath("$.[*].colorF").value(hasItem(DEFAULT_COLOR_F.doubleValue())))
            .andExpect(jsonPath("$.[*].colorG").value(hasItem(DEFAULT_COLOR_G.doubleValue())))
            .andExpect(jsonPath("$.[*].colorH").value(hasItem(DEFAULT_COLOR_H.doubleValue())))
            .andExpect(jsonPath("$.[*].colorI").value(hasItem(DEFAULT_COLOR_I.doubleValue())))
            .andExpect(jsonPath("$.[*].colorJ").value(hasItem(DEFAULT_COLOR_J.doubleValue())))
            .andExpect(jsonPath("$.[*].colorK").value(hasItem(DEFAULT_COLOR_K.doubleValue())))
            .andExpect(jsonPath("$.[*].colorL").value(hasItem(DEFAULT_COLOR_L.doubleValue())))
            .andExpect(jsonPath("$.[*].colorM").value(hasItem(DEFAULT_COLOR_M.doubleValue())))
            .andExpect(jsonPath("$.[*].colorN").value(hasItem(DEFAULT_COLOR_N.doubleValue())))
            .andExpect(jsonPath("$.[*].colorO").value(hasItem(DEFAULT_COLOR_O.doubleValue())))
            .andExpect(jsonPath("$.[*].colorP").value(hasItem(DEFAULT_COLOR_P.doubleValue())))
            .andExpect(jsonPath("$.[*].colorQ").value(hasItem(DEFAULT_COLOR_Q.doubleValue())))
            .andExpect(jsonPath("$.[*].colorR").value(hasItem(DEFAULT_COLOR_R.doubleValue())))
            .andExpect(jsonPath("$.[*].colorS").value(hasItem(DEFAULT_COLOR_S.doubleValue())))
            .andExpect(jsonPath("$.[*].colorT").value(hasItem(DEFAULT_COLOR_T.doubleValue())))
            .andExpect(jsonPath("$.[*].colorU").value(hasItem(DEFAULT_COLOR_U.doubleValue())))
            .andExpect(jsonPath("$.[*].colorV").value(hasItem(DEFAULT_COLOR_V.doubleValue())))
            .andExpect(jsonPath("$.[*].colorW").value(hasItem(DEFAULT_COLOR_W.doubleValue())))
            .andExpect(jsonPath("$.[*].colorX").value(hasItem(DEFAULT_COLOR_X.doubleValue())))
            .andExpect(jsonPath("$.[*].colorY").value(hasItem(DEFAULT_COLOR_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].colorZ").value(hasItem(DEFAULT_COLOR_Z.doubleValue())))
            .andExpect(jsonPath("$.[*].catalogId").value(hasItem(DEFAULT_CATALOG_ID)))
            .andExpect(jsonPath("$.[*].catalogExternalid").value(hasItem(DEFAULT_CATALOG_EXTERNALID.toString())))
            .andExpect(jsonPath("$.[*].catalogName").value(hasItem(DEFAULT_CATALOG_NAME.toString())))
            .andExpect(jsonPath("$.[*].catalogVersion").value(hasItem(DEFAULT_CATALOG_VERSION.toString())))
            .andExpect(jsonPath("$.[*].catalogIsactive").value(hasItem(DEFAULT_CATALOG_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].catalogCreatedtime").value(hasItem(DEFAULT_CATALOG_CREATEDTIME.toString())))
            .andExpect(jsonPath("$.[*].baseMaterialId").value(hasItem(DEFAULT_BASE_MATERIAL_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderedTime").value(hasItem(DEFAULT_ORDERED_TIME.toString())))
            .andExpect(jsonPath("$.[*].startedTime").value(hasItem(DEFAULT_STARTED_TIME.toString())))
            .andExpect(jsonPath("$.[*].modifiedTime").value(hasItem(DEFAULT_MODIFIED_TIME.toString())))
            .andExpect(jsonPath("$.[*].suspendedTime").value(hasItem(DEFAULT_SUSPENDED_TIME.toString())))
            .andExpect(jsonPath("$.[*].finishedTime").value(hasItem(DEFAULT_FINISHED_TIME.toString())))
            .andExpect(jsonPath("$.[*].original").value(hasItem(DEFAULT_ORIGINAL.toString())));
    }

    @Test
    @Transactional
    void getBatches() throws Exception {
        // Initialize the database
        batches.setId(UUID.randomUUID().toString());
        batchesRepository.saveAndFlush(batches);

        // Get the batches
        restBatchesMockMvc
            .perform(get(ENTITY_API_URL_ID, batches.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(batches.getId()))
            .andExpect(jsonPath("$.poName").value(DEFAULT_PO_NAME))
            .andExpect(jsonPath("$.sequenceInPo").value(DEFAULT_SEQUENCE_IN_PO))
            .andExpect(jsonPath("$.scannerId").value(DEFAULT_SCANNER_ID.toString()))
            .andExpect(jsonPath("$.previousProductionBatchId").value(DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.inspectionSequence").value(DEFAULT_INSPECTION_SEQUENCE.doubleValue()))
            .andExpect(jsonPath("$.orderedQuantity").value(DEFAULT_ORDERED_QUANTITY))
            .andExpect(jsonPath("$.producingQuantity").value(DEFAULT_PRODUCING_QUANTITY))
            .andExpect(jsonPath("$.totalProducingQuantity").value(DEFAULT_TOTAL_PRODUCING_QUANTITY))
            .andExpect(jsonPath("$.remainingQuantity").value(DEFAULT_REMAINING_QUANTITY))
            .andExpect(jsonPath("$.totalremainingQuantity").value(DEFAULT_TOTALREMAINING_QUANTITY))
            .andExpect(jsonPath("$.inspectedQuantity").value(DEFAULT_INSPECTED_QUANTITY))
            .andExpect(jsonPath("$.totalInspectedQuantity").value(DEFAULT_TOTAL_INSPECTED_QUANTITY))
            .andExpect(jsonPath("$.failedQuantity").value(DEFAULT_FAILED_QUANTITY))
            .andExpect(jsonPath("$.totalFailedQuantity").value(DEFAULT_TOTAL_FAILED_QUANTITY))
            .andExpect(jsonPath("$.colorId").value(DEFAULT_COLOR_ID))
            .andExpect(jsonPath("$.colorCode").value(DEFAULT_COLOR_CODE.toString()))
            .andExpect(jsonPath("$.colorName").value(DEFAULT_COLOR_NAME.toString()))
            .andExpect(jsonPath("$.colorBasematerial").value(DEFAULT_COLOR_BASEMATERIAL.intValue()))
            .andExpect(jsonPath("$.colorLabL").value(DEFAULT_COLOR_LAB_L.doubleValue()))
            .andExpect(jsonPath("$.colorLabA").value(DEFAULT_COLOR_LAB_A.doubleValue()))
            .andExpect(jsonPath("$.colorLabB").value(DEFAULT_COLOR_LAB_B.doubleValue()))
            .andExpect(jsonPath("$.colorA").value(DEFAULT_COLOR_A.doubleValue()))
            .andExpect(jsonPath("$.colorB").value(DEFAULT_COLOR_B.doubleValue()))
            .andExpect(jsonPath("$.colorC").value(DEFAULT_COLOR_C.doubleValue()))
            .andExpect(jsonPath("$.colorD").value(DEFAULT_COLOR_D.doubleValue()))
            .andExpect(jsonPath("$.colorE").value(DEFAULT_COLOR_E.doubleValue()))
            .andExpect(jsonPath("$.colorF").value(DEFAULT_COLOR_F.doubleValue()))
            .andExpect(jsonPath("$.colorG").value(DEFAULT_COLOR_G.doubleValue()))
            .andExpect(jsonPath("$.colorH").value(DEFAULT_COLOR_H.doubleValue()))
            .andExpect(jsonPath("$.colorI").value(DEFAULT_COLOR_I.doubleValue()))
            .andExpect(jsonPath("$.colorJ").value(DEFAULT_COLOR_J.doubleValue()))
            .andExpect(jsonPath("$.colorK").value(DEFAULT_COLOR_K.doubleValue()))
            .andExpect(jsonPath("$.colorL").value(DEFAULT_COLOR_L.doubleValue()))
            .andExpect(jsonPath("$.colorM").value(DEFAULT_COLOR_M.doubleValue()))
            .andExpect(jsonPath("$.colorN").value(DEFAULT_COLOR_N.doubleValue()))
            .andExpect(jsonPath("$.colorO").value(DEFAULT_COLOR_O.doubleValue()))
            .andExpect(jsonPath("$.colorP").value(DEFAULT_COLOR_P.doubleValue()))
            .andExpect(jsonPath("$.colorQ").value(DEFAULT_COLOR_Q.doubleValue()))
            .andExpect(jsonPath("$.colorR").value(DEFAULT_COLOR_R.doubleValue()))
            .andExpect(jsonPath("$.colorS").value(DEFAULT_COLOR_S.doubleValue()))
            .andExpect(jsonPath("$.colorT").value(DEFAULT_COLOR_T.doubleValue()))
            .andExpect(jsonPath("$.colorU").value(DEFAULT_COLOR_U.doubleValue()))
            .andExpect(jsonPath("$.colorV").value(DEFAULT_COLOR_V.doubleValue()))
            .andExpect(jsonPath("$.colorW").value(DEFAULT_COLOR_W.doubleValue()))
            .andExpect(jsonPath("$.colorX").value(DEFAULT_COLOR_X.doubleValue()))
            .andExpect(jsonPath("$.colorY").value(DEFAULT_COLOR_Y.doubleValue()))
            .andExpect(jsonPath("$.colorZ").value(DEFAULT_COLOR_Z.doubleValue()))
            .andExpect(jsonPath("$.catalogId").value(DEFAULT_CATALOG_ID))
            .andExpect(jsonPath("$.catalogExternalid").value(DEFAULT_CATALOG_EXTERNALID.toString()))
            .andExpect(jsonPath("$.catalogName").value(DEFAULT_CATALOG_NAME.toString()))
            .andExpect(jsonPath("$.catalogVersion").value(DEFAULT_CATALOG_VERSION.toString()))
            .andExpect(jsonPath("$.catalogIsactive").value(DEFAULT_CATALOG_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.catalogCreatedtime").value(DEFAULT_CATALOG_CREATEDTIME.toString()))
            .andExpect(jsonPath("$.baseMaterialId").value(DEFAULT_BASE_MATERIAL_ID.intValue()))
            .andExpect(jsonPath("$.orderedTime").value(DEFAULT_ORDERED_TIME.toString()))
            .andExpect(jsonPath("$.startedTime").value(DEFAULT_STARTED_TIME.toString()))
            .andExpect(jsonPath("$.modifiedTime").value(DEFAULT_MODIFIED_TIME.toString()))
            .andExpect(jsonPath("$.suspendedTime").value(DEFAULT_SUSPENDED_TIME.toString()))
            .andExpect(jsonPath("$.finishedTime").value(DEFAULT_FINISHED_TIME.toString()))
            .andExpect(jsonPath("$.original").value(DEFAULT_ORIGINAL.toString()));
    }

    @Test
    @Transactional
    void getBatchesByIdFiltering() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        String id = batches.getId();

        defaultBatchesShouldBeFound("id.equals=" + id);
        defaultBatchesShouldNotBeFound("id.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllBatchesByPoNameIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where poName equals to DEFAULT_PO_NAME
        defaultBatchesShouldBeFound("poName.equals=" + DEFAULT_PO_NAME);

        // Get all the batchesList where poName equals to UPDATED_PO_NAME
        defaultBatchesShouldNotBeFound("poName.equals=" + UPDATED_PO_NAME);
    }

    @Test
    @Transactional
    void getAllBatchesByPoNameIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where poName in DEFAULT_PO_NAME or UPDATED_PO_NAME
        defaultBatchesShouldBeFound("poName.in=" + DEFAULT_PO_NAME + "," + UPDATED_PO_NAME);

        // Get all the batchesList where poName equals to UPDATED_PO_NAME
        defaultBatchesShouldNotBeFound("poName.in=" + UPDATED_PO_NAME);
    }

    @Test
    @Transactional
    void getAllBatchesByPoNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where poName is not null
        defaultBatchesShouldBeFound("poName.specified=true");

        // Get all the batchesList where poName is null
        defaultBatchesShouldNotBeFound("poName.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByPoNameContainsSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where poName contains DEFAULT_PO_NAME
        defaultBatchesShouldBeFound("poName.contains=" + DEFAULT_PO_NAME);

        // Get all the batchesList where poName contains UPDATED_PO_NAME
        defaultBatchesShouldNotBeFound("poName.contains=" + UPDATED_PO_NAME);
    }

    @Test
    @Transactional
    void getAllBatchesByPoNameNotContainsSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where poName does not contain DEFAULT_PO_NAME
        defaultBatchesShouldNotBeFound("poName.doesNotContain=" + DEFAULT_PO_NAME);

        // Get all the batchesList where poName does not contain UPDATED_PO_NAME
        defaultBatchesShouldBeFound("poName.doesNotContain=" + UPDATED_PO_NAME);
    }

    @Test
    @Transactional
    void getAllBatchesBySequenceInPoIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where sequenceInPo equals to DEFAULT_SEQUENCE_IN_PO
        defaultBatchesShouldBeFound("sequenceInPo.equals=" + DEFAULT_SEQUENCE_IN_PO);

        // Get all the batchesList where sequenceInPo equals to UPDATED_SEQUENCE_IN_PO
        defaultBatchesShouldNotBeFound("sequenceInPo.equals=" + UPDATED_SEQUENCE_IN_PO);
    }

    @Test
    @Transactional
    void getAllBatchesBySequenceInPoIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where sequenceInPo in DEFAULT_SEQUENCE_IN_PO or UPDATED_SEQUENCE_IN_PO
        defaultBatchesShouldBeFound("sequenceInPo.in=" + DEFAULT_SEQUENCE_IN_PO + "," + UPDATED_SEQUENCE_IN_PO);

        // Get all the batchesList where sequenceInPo equals to UPDATED_SEQUENCE_IN_PO
        defaultBatchesShouldNotBeFound("sequenceInPo.in=" + UPDATED_SEQUENCE_IN_PO);
    }

    @Test
    @Transactional
    void getAllBatchesBySequenceInPoIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where sequenceInPo is not null
        defaultBatchesShouldBeFound("sequenceInPo.specified=true");

        // Get all the batchesList where sequenceInPo is null
        defaultBatchesShouldNotBeFound("sequenceInPo.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesBySequenceInPoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where sequenceInPo is greater than or equal to DEFAULT_SEQUENCE_IN_PO
        defaultBatchesShouldBeFound("sequenceInPo.greaterThanOrEqual=" + DEFAULT_SEQUENCE_IN_PO);

        // Get all the batchesList where sequenceInPo is greater than or equal to UPDATED_SEQUENCE_IN_PO
        defaultBatchesShouldNotBeFound("sequenceInPo.greaterThanOrEqual=" + UPDATED_SEQUENCE_IN_PO);
    }

    @Test
    @Transactional
    void getAllBatchesBySequenceInPoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where sequenceInPo is less than or equal to DEFAULT_SEQUENCE_IN_PO
        defaultBatchesShouldBeFound("sequenceInPo.lessThanOrEqual=" + DEFAULT_SEQUENCE_IN_PO);

        // Get all the batchesList where sequenceInPo is less than or equal to SMALLER_SEQUENCE_IN_PO
        defaultBatchesShouldNotBeFound("sequenceInPo.lessThanOrEqual=" + SMALLER_SEQUENCE_IN_PO);
    }

    @Test
    @Transactional
    void getAllBatchesBySequenceInPoIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where sequenceInPo is less than DEFAULT_SEQUENCE_IN_PO
        defaultBatchesShouldNotBeFound("sequenceInPo.lessThan=" + DEFAULT_SEQUENCE_IN_PO);

        // Get all the batchesList where sequenceInPo is less than UPDATED_SEQUENCE_IN_PO
        defaultBatchesShouldBeFound("sequenceInPo.lessThan=" + UPDATED_SEQUENCE_IN_PO);
    }

    @Test
    @Transactional
    void getAllBatchesBySequenceInPoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where sequenceInPo is greater than DEFAULT_SEQUENCE_IN_PO
        defaultBatchesShouldNotBeFound("sequenceInPo.greaterThan=" + DEFAULT_SEQUENCE_IN_PO);

        // Get all the batchesList where sequenceInPo is greater than SMALLER_SEQUENCE_IN_PO
        defaultBatchesShouldBeFound("sequenceInPo.greaterThan=" + SMALLER_SEQUENCE_IN_PO);
    }

    @Test
    @Transactional
    void getAllBatchesByPreviousProductionBatchIdIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where previousProductionBatchId equals to DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID
        defaultBatchesShouldBeFound("previousProductionBatchId.equals=" + DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID);

        // Get all the batchesList where previousProductionBatchId equals to UPDATED_PREVIOUS_PRODUCTION_BATCH_ID
        defaultBatchesShouldNotBeFound("previousProductionBatchId.equals=" + UPDATED_PREVIOUS_PRODUCTION_BATCH_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByPreviousProductionBatchIdIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where previousProductionBatchId in DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID or UPDATED_PREVIOUS_PRODUCTION_BATCH_ID
        defaultBatchesShouldBeFound(
            "previousProductionBatchId.in=" + DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID + "," + UPDATED_PREVIOUS_PRODUCTION_BATCH_ID
        );

        // Get all the batchesList where previousProductionBatchId equals to UPDATED_PREVIOUS_PRODUCTION_BATCH_ID
        defaultBatchesShouldNotBeFound("previousProductionBatchId.in=" + UPDATED_PREVIOUS_PRODUCTION_BATCH_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByPreviousProductionBatchIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where previousProductionBatchId is not null
        defaultBatchesShouldBeFound("previousProductionBatchId.specified=true");

        // Get all the batchesList where previousProductionBatchId is null
        defaultBatchesShouldNotBeFound("previousProductionBatchId.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByPreviousProductionBatchIdContainsSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where previousProductionBatchId contains DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID
        defaultBatchesShouldBeFound("previousProductionBatchId.contains=" + DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID);

        // Get all the batchesList where previousProductionBatchId contains UPDATED_PREVIOUS_PRODUCTION_BATCH_ID
        defaultBatchesShouldNotBeFound("previousProductionBatchId.contains=" + UPDATED_PREVIOUS_PRODUCTION_BATCH_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByPreviousProductionBatchIdNotContainsSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where previousProductionBatchId does not contain DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID
        defaultBatchesShouldNotBeFound("previousProductionBatchId.doesNotContain=" + DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID);

        // Get all the batchesList where previousProductionBatchId does not contain UPDATED_PREVIOUS_PRODUCTION_BATCH_ID
        defaultBatchesShouldBeFound("previousProductionBatchId.doesNotContain=" + UPDATED_PREVIOUS_PRODUCTION_BATCH_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where state equals to DEFAULT_STATE
        defaultBatchesShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the batchesList where state equals to UPDATED_STATE
        defaultBatchesShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllBatchesByStateIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where state in DEFAULT_STATE or UPDATED_STATE
        defaultBatchesShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the batchesList where state equals to UPDATED_STATE
        defaultBatchesShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllBatchesByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where state is not null
        defaultBatchesShouldBeFound("state.specified=true");

        // Get all the batchesList where state is null
        defaultBatchesShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByStateContainsSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where state contains DEFAULT_STATE
        defaultBatchesShouldBeFound("state.contains=" + DEFAULT_STATE);

        // Get all the batchesList where state contains UPDATED_STATE
        defaultBatchesShouldNotBeFound("state.contains=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllBatchesByStateNotContainsSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where state does not contain DEFAULT_STATE
        defaultBatchesShouldNotBeFound("state.doesNotContain=" + DEFAULT_STATE);

        // Get all the batchesList where state does not contain UPDATED_STATE
        defaultBatchesShouldBeFound("state.doesNotContain=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectionSequenceIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectionSequence equals to DEFAULT_INSPECTION_SEQUENCE
        defaultBatchesShouldBeFound("inspectionSequence.equals=" + DEFAULT_INSPECTION_SEQUENCE);

        // Get all the batchesList where inspectionSequence equals to UPDATED_INSPECTION_SEQUENCE
        defaultBatchesShouldNotBeFound("inspectionSequence.equals=" + UPDATED_INSPECTION_SEQUENCE);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectionSequenceIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectionSequence in DEFAULT_INSPECTION_SEQUENCE or UPDATED_INSPECTION_SEQUENCE
        defaultBatchesShouldBeFound("inspectionSequence.in=" + DEFAULT_INSPECTION_SEQUENCE + "," + UPDATED_INSPECTION_SEQUENCE);

        // Get all the batchesList where inspectionSequence equals to UPDATED_INSPECTION_SEQUENCE
        defaultBatchesShouldNotBeFound("inspectionSequence.in=" + UPDATED_INSPECTION_SEQUENCE);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectionSequenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectionSequence is not null
        defaultBatchesShouldBeFound("inspectionSequence.specified=true");

        // Get all the batchesList where inspectionSequence is null
        defaultBatchesShouldNotBeFound("inspectionSequence.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByInspectionSequenceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectionSequence is greater than or equal to DEFAULT_INSPECTION_SEQUENCE
        defaultBatchesShouldBeFound("inspectionSequence.greaterThanOrEqual=" + DEFAULT_INSPECTION_SEQUENCE);

        // Get all the batchesList where inspectionSequence is greater than or equal to UPDATED_INSPECTION_SEQUENCE
        defaultBatchesShouldNotBeFound("inspectionSequence.greaterThanOrEqual=" + UPDATED_INSPECTION_SEQUENCE);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectionSequenceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectionSequence is less than or equal to DEFAULT_INSPECTION_SEQUENCE
        defaultBatchesShouldBeFound("inspectionSequence.lessThanOrEqual=" + DEFAULT_INSPECTION_SEQUENCE);

        // Get all the batchesList where inspectionSequence is less than or equal to SMALLER_INSPECTION_SEQUENCE
        defaultBatchesShouldNotBeFound("inspectionSequence.lessThanOrEqual=" + SMALLER_INSPECTION_SEQUENCE);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectionSequenceIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectionSequence is less than DEFAULT_INSPECTION_SEQUENCE
        defaultBatchesShouldNotBeFound("inspectionSequence.lessThan=" + DEFAULT_INSPECTION_SEQUENCE);

        // Get all the batchesList where inspectionSequence is less than UPDATED_INSPECTION_SEQUENCE
        defaultBatchesShouldBeFound("inspectionSequence.lessThan=" + UPDATED_INSPECTION_SEQUENCE);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectionSequenceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectionSequence is greater than DEFAULT_INSPECTION_SEQUENCE
        defaultBatchesShouldNotBeFound("inspectionSequence.greaterThan=" + DEFAULT_INSPECTION_SEQUENCE);

        // Get all the batchesList where inspectionSequence is greater than SMALLER_INSPECTION_SEQUENCE
        defaultBatchesShouldBeFound("inspectionSequence.greaterThan=" + SMALLER_INSPECTION_SEQUENCE);
    }

    @Test
    @Transactional
    void getAllBatchesByOrderedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where orderedQuantity equals to DEFAULT_ORDERED_QUANTITY
        defaultBatchesShouldBeFound("orderedQuantity.equals=" + DEFAULT_ORDERED_QUANTITY);

        // Get all the batchesList where orderedQuantity equals to UPDATED_ORDERED_QUANTITY
        defaultBatchesShouldNotBeFound("orderedQuantity.equals=" + UPDATED_ORDERED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByOrderedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where orderedQuantity in DEFAULT_ORDERED_QUANTITY or UPDATED_ORDERED_QUANTITY
        defaultBatchesShouldBeFound("orderedQuantity.in=" + DEFAULT_ORDERED_QUANTITY + "," + UPDATED_ORDERED_QUANTITY);

        // Get all the batchesList where orderedQuantity equals to UPDATED_ORDERED_QUANTITY
        defaultBatchesShouldNotBeFound("orderedQuantity.in=" + UPDATED_ORDERED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByOrderedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where orderedQuantity is not null
        defaultBatchesShouldBeFound("orderedQuantity.specified=true");

        // Get all the batchesList where orderedQuantity is null
        defaultBatchesShouldNotBeFound("orderedQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByOrderedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where orderedQuantity is greater than or equal to DEFAULT_ORDERED_QUANTITY
        defaultBatchesShouldBeFound("orderedQuantity.greaterThanOrEqual=" + DEFAULT_ORDERED_QUANTITY);

        // Get all the batchesList where orderedQuantity is greater than or equal to UPDATED_ORDERED_QUANTITY
        defaultBatchesShouldNotBeFound("orderedQuantity.greaterThanOrEqual=" + UPDATED_ORDERED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByOrderedQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where orderedQuantity is less than or equal to DEFAULT_ORDERED_QUANTITY
        defaultBatchesShouldBeFound("orderedQuantity.lessThanOrEqual=" + DEFAULT_ORDERED_QUANTITY);

        // Get all the batchesList where orderedQuantity is less than or equal to SMALLER_ORDERED_QUANTITY
        defaultBatchesShouldNotBeFound("orderedQuantity.lessThanOrEqual=" + SMALLER_ORDERED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByOrderedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where orderedQuantity is less than DEFAULT_ORDERED_QUANTITY
        defaultBatchesShouldNotBeFound("orderedQuantity.lessThan=" + DEFAULT_ORDERED_QUANTITY);

        // Get all the batchesList where orderedQuantity is less than UPDATED_ORDERED_QUANTITY
        defaultBatchesShouldBeFound("orderedQuantity.lessThan=" + UPDATED_ORDERED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByOrderedQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where orderedQuantity is greater than DEFAULT_ORDERED_QUANTITY
        defaultBatchesShouldNotBeFound("orderedQuantity.greaterThan=" + DEFAULT_ORDERED_QUANTITY);

        // Get all the batchesList where orderedQuantity is greater than SMALLER_ORDERED_QUANTITY
        defaultBatchesShouldBeFound("orderedQuantity.greaterThan=" + SMALLER_ORDERED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByProducingQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where producingQuantity equals to DEFAULT_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("producingQuantity.equals=" + DEFAULT_PRODUCING_QUANTITY);

        // Get all the batchesList where producingQuantity equals to UPDATED_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("producingQuantity.equals=" + UPDATED_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByProducingQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where producingQuantity in DEFAULT_PRODUCING_QUANTITY or UPDATED_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("producingQuantity.in=" + DEFAULT_PRODUCING_QUANTITY + "," + UPDATED_PRODUCING_QUANTITY);

        // Get all the batchesList where producingQuantity equals to UPDATED_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("producingQuantity.in=" + UPDATED_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByProducingQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where producingQuantity is not null
        defaultBatchesShouldBeFound("producingQuantity.specified=true");

        // Get all the batchesList where producingQuantity is null
        defaultBatchesShouldNotBeFound("producingQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByProducingQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where producingQuantity is greater than or equal to DEFAULT_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("producingQuantity.greaterThanOrEqual=" + DEFAULT_PRODUCING_QUANTITY);

        // Get all the batchesList where producingQuantity is greater than or equal to UPDATED_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("producingQuantity.greaterThanOrEqual=" + UPDATED_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByProducingQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where producingQuantity is less than or equal to DEFAULT_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("producingQuantity.lessThanOrEqual=" + DEFAULT_PRODUCING_QUANTITY);

        // Get all the batchesList where producingQuantity is less than or equal to SMALLER_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("producingQuantity.lessThanOrEqual=" + SMALLER_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByProducingQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where producingQuantity is less than DEFAULT_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("producingQuantity.lessThan=" + DEFAULT_PRODUCING_QUANTITY);

        // Get all the batchesList where producingQuantity is less than UPDATED_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("producingQuantity.lessThan=" + UPDATED_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByProducingQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where producingQuantity is greater than DEFAULT_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("producingQuantity.greaterThan=" + DEFAULT_PRODUCING_QUANTITY);

        // Get all the batchesList where producingQuantity is greater than SMALLER_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("producingQuantity.greaterThan=" + SMALLER_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalProducingQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalProducingQuantity equals to DEFAULT_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("totalProducingQuantity.equals=" + DEFAULT_TOTAL_PRODUCING_QUANTITY);

        // Get all the batchesList where totalProducingQuantity equals to UPDATED_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("totalProducingQuantity.equals=" + UPDATED_TOTAL_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalProducingQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalProducingQuantity in DEFAULT_TOTAL_PRODUCING_QUANTITY or UPDATED_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound(
            "totalProducingQuantity.in=" + DEFAULT_TOTAL_PRODUCING_QUANTITY + "," + UPDATED_TOTAL_PRODUCING_QUANTITY
        );

        // Get all the batchesList where totalProducingQuantity equals to UPDATED_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("totalProducingQuantity.in=" + UPDATED_TOTAL_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalProducingQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalProducingQuantity is not null
        defaultBatchesShouldBeFound("totalProducingQuantity.specified=true");

        // Get all the batchesList where totalProducingQuantity is null
        defaultBatchesShouldNotBeFound("totalProducingQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByTotalProducingQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalProducingQuantity is greater than or equal to DEFAULT_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("totalProducingQuantity.greaterThanOrEqual=" + DEFAULT_TOTAL_PRODUCING_QUANTITY);

        // Get all the batchesList where totalProducingQuantity is greater than or equal to UPDATED_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("totalProducingQuantity.greaterThanOrEqual=" + UPDATED_TOTAL_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalProducingQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalProducingQuantity is less than or equal to DEFAULT_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("totalProducingQuantity.lessThanOrEqual=" + DEFAULT_TOTAL_PRODUCING_QUANTITY);

        // Get all the batchesList where totalProducingQuantity is less than or equal to SMALLER_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("totalProducingQuantity.lessThanOrEqual=" + SMALLER_TOTAL_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalProducingQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalProducingQuantity is less than DEFAULT_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("totalProducingQuantity.lessThan=" + DEFAULT_TOTAL_PRODUCING_QUANTITY);

        // Get all the batchesList where totalProducingQuantity is less than UPDATED_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("totalProducingQuantity.lessThan=" + UPDATED_TOTAL_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalProducingQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalProducingQuantity is greater than DEFAULT_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldNotBeFound("totalProducingQuantity.greaterThan=" + DEFAULT_TOTAL_PRODUCING_QUANTITY);

        // Get all the batchesList where totalProducingQuantity is greater than SMALLER_TOTAL_PRODUCING_QUANTITY
        defaultBatchesShouldBeFound("totalProducingQuantity.greaterThan=" + SMALLER_TOTAL_PRODUCING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByRemainingQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where remainingQuantity equals to DEFAULT_REMAINING_QUANTITY
        defaultBatchesShouldBeFound("remainingQuantity.equals=" + DEFAULT_REMAINING_QUANTITY);

        // Get all the batchesList where remainingQuantity equals to UPDATED_REMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("remainingQuantity.equals=" + UPDATED_REMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByRemainingQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where remainingQuantity in DEFAULT_REMAINING_QUANTITY or UPDATED_REMAINING_QUANTITY
        defaultBatchesShouldBeFound("remainingQuantity.in=" + DEFAULT_REMAINING_QUANTITY + "," + UPDATED_REMAINING_QUANTITY);

        // Get all the batchesList where remainingQuantity equals to UPDATED_REMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("remainingQuantity.in=" + UPDATED_REMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByRemainingQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where remainingQuantity is not null
        defaultBatchesShouldBeFound("remainingQuantity.specified=true");

        // Get all the batchesList where remainingQuantity is null
        defaultBatchesShouldNotBeFound("remainingQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByRemainingQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where remainingQuantity is greater than or equal to DEFAULT_REMAINING_QUANTITY
        defaultBatchesShouldBeFound("remainingQuantity.greaterThanOrEqual=" + DEFAULT_REMAINING_QUANTITY);

        // Get all the batchesList where remainingQuantity is greater than or equal to UPDATED_REMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("remainingQuantity.greaterThanOrEqual=" + UPDATED_REMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByRemainingQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where remainingQuantity is less than or equal to DEFAULT_REMAINING_QUANTITY
        defaultBatchesShouldBeFound("remainingQuantity.lessThanOrEqual=" + DEFAULT_REMAINING_QUANTITY);

        // Get all the batchesList where remainingQuantity is less than or equal to SMALLER_REMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("remainingQuantity.lessThanOrEqual=" + SMALLER_REMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByRemainingQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where remainingQuantity is less than DEFAULT_REMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("remainingQuantity.lessThan=" + DEFAULT_REMAINING_QUANTITY);

        // Get all the batchesList where remainingQuantity is less than UPDATED_REMAINING_QUANTITY
        defaultBatchesShouldBeFound("remainingQuantity.lessThan=" + UPDATED_REMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByRemainingQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where remainingQuantity is greater than DEFAULT_REMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("remainingQuantity.greaterThan=" + DEFAULT_REMAINING_QUANTITY);

        // Get all the batchesList where remainingQuantity is greater than SMALLER_REMAINING_QUANTITY
        defaultBatchesShouldBeFound("remainingQuantity.greaterThan=" + SMALLER_REMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalremainingQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalremainingQuantity equals to DEFAULT_TOTALREMAINING_QUANTITY
        defaultBatchesShouldBeFound("totalremainingQuantity.equals=" + DEFAULT_TOTALREMAINING_QUANTITY);

        // Get all the batchesList where totalremainingQuantity equals to UPDATED_TOTALREMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("totalremainingQuantity.equals=" + UPDATED_TOTALREMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalremainingQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalremainingQuantity in DEFAULT_TOTALREMAINING_QUANTITY or UPDATED_TOTALREMAINING_QUANTITY
        defaultBatchesShouldBeFound("totalremainingQuantity.in=" + DEFAULT_TOTALREMAINING_QUANTITY + "," + UPDATED_TOTALREMAINING_QUANTITY);

        // Get all the batchesList where totalremainingQuantity equals to UPDATED_TOTALREMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("totalremainingQuantity.in=" + UPDATED_TOTALREMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalremainingQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalremainingQuantity is not null
        defaultBatchesShouldBeFound("totalremainingQuantity.specified=true");

        // Get all the batchesList where totalremainingQuantity is null
        defaultBatchesShouldNotBeFound("totalremainingQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByTotalremainingQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalremainingQuantity is greater than or equal to DEFAULT_TOTALREMAINING_QUANTITY
        defaultBatchesShouldBeFound("totalremainingQuantity.greaterThanOrEqual=" + DEFAULT_TOTALREMAINING_QUANTITY);

        // Get all the batchesList where totalremainingQuantity is greater than or equal to UPDATED_TOTALREMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("totalremainingQuantity.greaterThanOrEqual=" + UPDATED_TOTALREMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalremainingQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalremainingQuantity is less than or equal to DEFAULT_TOTALREMAINING_QUANTITY
        defaultBatchesShouldBeFound("totalremainingQuantity.lessThanOrEqual=" + DEFAULT_TOTALREMAINING_QUANTITY);

        // Get all the batchesList where totalremainingQuantity is less than or equal to SMALLER_TOTALREMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("totalremainingQuantity.lessThanOrEqual=" + SMALLER_TOTALREMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalremainingQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalremainingQuantity is less than DEFAULT_TOTALREMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("totalremainingQuantity.lessThan=" + DEFAULT_TOTALREMAINING_QUANTITY);

        // Get all the batchesList where totalremainingQuantity is less than UPDATED_TOTALREMAINING_QUANTITY
        defaultBatchesShouldBeFound("totalremainingQuantity.lessThan=" + UPDATED_TOTALREMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalremainingQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalremainingQuantity is greater than DEFAULT_TOTALREMAINING_QUANTITY
        defaultBatchesShouldNotBeFound("totalremainingQuantity.greaterThan=" + DEFAULT_TOTALREMAINING_QUANTITY);

        // Get all the batchesList where totalremainingQuantity is greater than SMALLER_TOTALREMAINING_QUANTITY
        defaultBatchesShouldBeFound("totalremainingQuantity.greaterThan=" + SMALLER_TOTALREMAINING_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectedQuantity equals to DEFAULT_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("inspectedQuantity.equals=" + DEFAULT_INSPECTED_QUANTITY);

        // Get all the batchesList where inspectedQuantity equals to UPDATED_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("inspectedQuantity.equals=" + UPDATED_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectedQuantity in DEFAULT_INSPECTED_QUANTITY or UPDATED_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("inspectedQuantity.in=" + DEFAULT_INSPECTED_QUANTITY + "," + UPDATED_INSPECTED_QUANTITY);

        // Get all the batchesList where inspectedQuantity equals to UPDATED_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("inspectedQuantity.in=" + UPDATED_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectedQuantity is not null
        defaultBatchesShouldBeFound("inspectedQuantity.specified=true");

        // Get all the batchesList where inspectedQuantity is null
        defaultBatchesShouldNotBeFound("inspectedQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByInspectedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectedQuantity is greater than or equal to DEFAULT_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("inspectedQuantity.greaterThanOrEqual=" + DEFAULT_INSPECTED_QUANTITY);

        // Get all the batchesList where inspectedQuantity is greater than or equal to UPDATED_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("inspectedQuantity.greaterThanOrEqual=" + UPDATED_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectedQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectedQuantity is less than or equal to DEFAULT_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("inspectedQuantity.lessThanOrEqual=" + DEFAULT_INSPECTED_QUANTITY);

        // Get all the batchesList where inspectedQuantity is less than or equal to SMALLER_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("inspectedQuantity.lessThanOrEqual=" + SMALLER_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectedQuantity is less than DEFAULT_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("inspectedQuantity.lessThan=" + DEFAULT_INSPECTED_QUANTITY);

        // Get all the batchesList where inspectedQuantity is less than UPDATED_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("inspectedQuantity.lessThan=" + UPDATED_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByInspectedQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where inspectedQuantity is greater than DEFAULT_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("inspectedQuantity.greaterThan=" + DEFAULT_INSPECTED_QUANTITY);

        // Get all the batchesList where inspectedQuantity is greater than SMALLER_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("inspectedQuantity.greaterThan=" + SMALLER_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalInspectedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalInspectedQuantity equals to DEFAULT_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("totalInspectedQuantity.equals=" + DEFAULT_TOTAL_INSPECTED_QUANTITY);

        // Get all the batchesList where totalInspectedQuantity equals to UPDATED_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("totalInspectedQuantity.equals=" + UPDATED_TOTAL_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalInspectedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalInspectedQuantity in DEFAULT_TOTAL_INSPECTED_QUANTITY or UPDATED_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound(
            "totalInspectedQuantity.in=" + DEFAULT_TOTAL_INSPECTED_QUANTITY + "," + UPDATED_TOTAL_INSPECTED_QUANTITY
        );

        // Get all the batchesList where totalInspectedQuantity equals to UPDATED_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("totalInspectedQuantity.in=" + UPDATED_TOTAL_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalInspectedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalInspectedQuantity is not null
        defaultBatchesShouldBeFound("totalInspectedQuantity.specified=true");

        // Get all the batchesList where totalInspectedQuantity is null
        defaultBatchesShouldNotBeFound("totalInspectedQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByTotalInspectedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalInspectedQuantity is greater than or equal to DEFAULT_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("totalInspectedQuantity.greaterThanOrEqual=" + DEFAULT_TOTAL_INSPECTED_QUANTITY);

        // Get all the batchesList where totalInspectedQuantity is greater than or equal to UPDATED_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("totalInspectedQuantity.greaterThanOrEqual=" + UPDATED_TOTAL_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalInspectedQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalInspectedQuantity is less than or equal to DEFAULT_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("totalInspectedQuantity.lessThanOrEqual=" + DEFAULT_TOTAL_INSPECTED_QUANTITY);

        // Get all the batchesList where totalInspectedQuantity is less than or equal to SMALLER_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("totalInspectedQuantity.lessThanOrEqual=" + SMALLER_TOTAL_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalInspectedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalInspectedQuantity is less than DEFAULT_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("totalInspectedQuantity.lessThan=" + DEFAULT_TOTAL_INSPECTED_QUANTITY);

        // Get all the batchesList where totalInspectedQuantity is less than UPDATED_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("totalInspectedQuantity.lessThan=" + UPDATED_TOTAL_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalInspectedQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalInspectedQuantity is greater than DEFAULT_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldNotBeFound("totalInspectedQuantity.greaterThan=" + DEFAULT_TOTAL_INSPECTED_QUANTITY);

        // Get all the batchesList where totalInspectedQuantity is greater than SMALLER_TOTAL_INSPECTED_QUANTITY
        defaultBatchesShouldBeFound("totalInspectedQuantity.greaterThan=" + SMALLER_TOTAL_INSPECTED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByFailedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where failedQuantity equals to DEFAULT_FAILED_QUANTITY
        defaultBatchesShouldBeFound("failedQuantity.equals=" + DEFAULT_FAILED_QUANTITY);

        // Get all the batchesList where failedQuantity equals to UPDATED_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("failedQuantity.equals=" + UPDATED_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByFailedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where failedQuantity in DEFAULT_FAILED_QUANTITY or UPDATED_FAILED_QUANTITY
        defaultBatchesShouldBeFound("failedQuantity.in=" + DEFAULT_FAILED_QUANTITY + "," + UPDATED_FAILED_QUANTITY);

        // Get all the batchesList where failedQuantity equals to UPDATED_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("failedQuantity.in=" + UPDATED_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByFailedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where failedQuantity is not null
        defaultBatchesShouldBeFound("failedQuantity.specified=true");

        // Get all the batchesList where failedQuantity is null
        defaultBatchesShouldNotBeFound("failedQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByFailedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where failedQuantity is greater than or equal to DEFAULT_FAILED_QUANTITY
        defaultBatchesShouldBeFound("failedQuantity.greaterThanOrEqual=" + DEFAULT_FAILED_QUANTITY);

        // Get all the batchesList where failedQuantity is greater than or equal to UPDATED_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("failedQuantity.greaterThanOrEqual=" + UPDATED_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByFailedQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where failedQuantity is less than or equal to DEFAULT_FAILED_QUANTITY
        defaultBatchesShouldBeFound("failedQuantity.lessThanOrEqual=" + DEFAULT_FAILED_QUANTITY);

        // Get all the batchesList where failedQuantity is less than or equal to SMALLER_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("failedQuantity.lessThanOrEqual=" + SMALLER_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByFailedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where failedQuantity is less than DEFAULT_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("failedQuantity.lessThan=" + DEFAULT_FAILED_QUANTITY);

        // Get all the batchesList where failedQuantity is less than UPDATED_FAILED_QUANTITY
        defaultBatchesShouldBeFound("failedQuantity.lessThan=" + UPDATED_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByFailedQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where failedQuantity is greater than DEFAULT_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("failedQuantity.greaterThan=" + DEFAULT_FAILED_QUANTITY);

        // Get all the batchesList where failedQuantity is greater than SMALLER_FAILED_QUANTITY
        defaultBatchesShouldBeFound("failedQuantity.greaterThan=" + SMALLER_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalFailedQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalFailedQuantity equals to DEFAULT_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldBeFound("totalFailedQuantity.equals=" + DEFAULT_TOTAL_FAILED_QUANTITY);

        // Get all the batchesList where totalFailedQuantity equals to UPDATED_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("totalFailedQuantity.equals=" + UPDATED_TOTAL_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalFailedQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalFailedQuantity in DEFAULT_TOTAL_FAILED_QUANTITY or UPDATED_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldBeFound("totalFailedQuantity.in=" + DEFAULT_TOTAL_FAILED_QUANTITY + "," + UPDATED_TOTAL_FAILED_QUANTITY);

        // Get all the batchesList where totalFailedQuantity equals to UPDATED_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("totalFailedQuantity.in=" + UPDATED_TOTAL_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalFailedQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalFailedQuantity is not null
        defaultBatchesShouldBeFound("totalFailedQuantity.specified=true");

        // Get all the batchesList where totalFailedQuantity is null
        defaultBatchesShouldNotBeFound("totalFailedQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByTotalFailedQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalFailedQuantity is greater than or equal to DEFAULT_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldBeFound("totalFailedQuantity.greaterThanOrEqual=" + DEFAULT_TOTAL_FAILED_QUANTITY);

        // Get all the batchesList where totalFailedQuantity is greater than or equal to UPDATED_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("totalFailedQuantity.greaterThanOrEqual=" + UPDATED_TOTAL_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalFailedQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalFailedQuantity is less than or equal to DEFAULT_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldBeFound("totalFailedQuantity.lessThanOrEqual=" + DEFAULT_TOTAL_FAILED_QUANTITY);

        // Get all the batchesList where totalFailedQuantity is less than or equal to SMALLER_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("totalFailedQuantity.lessThanOrEqual=" + SMALLER_TOTAL_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalFailedQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalFailedQuantity is less than DEFAULT_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("totalFailedQuantity.lessThan=" + DEFAULT_TOTAL_FAILED_QUANTITY);

        // Get all the batchesList where totalFailedQuantity is less than UPDATED_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldBeFound("totalFailedQuantity.lessThan=" + UPDATED_TOTAL_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByTotalFailedQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where totalFailedQuantity is greater than DEFAULT_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldNotBeFound("totalFailedQuantity.greaterThan=" + DEFAULT_TOTAL_FAILED_QUANTITY);

        // Get all the batchesList where totalFailedQuantity is greater than SMALLER_TOTAL_FAILED_QUANTITY
        defaultBatchesShouldBeFound("totalFailedQuantity.greaterThan=" + SMALLER_TOTAL_FAILED_QUANTITY);
    }

    @Test
    @Transactional
    void getAllBatchesByColorIdIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorId equals to DEFAULT_COLOR_ID
        defaultBatchesShouldBeFound("colorId.equals=" + DEFAULT_COLOR_ID);

        // Get all the batchesList where colorId equals to UPDATED_COLOR_ID
        defaultBatchesShouldNotBeFound("colorId.equals=" + UPDATED_COLOR_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByColorIdIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorId in DEFAULT_COLOR_ID or UPDATED_COLOR_ID
        defaultBatchesShouldBeFound("colorId.in=" + DEFAULT_COLOR_ID + "," + UPDATED_COLOR_ID);

        // Get all the batchesList where colorId equals to UPDATED_COLOR_ID
        defaultBatchesShouldNotBeFound("colorId.in=" + UPDATED_COLOR_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByColorIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorId is not null
        defaultBatchesShouldBeFound("colorId.specified=true");

        // Get all the batchesList where colorId is null
        defaultBatchesShouldNotBeFound("colorId.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorIdContainsSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorId contains DEFAULT_COLOR_ID
        defaultBatchesShouldBeFound("colorId.contains=" + DEFAULT_COLOR_ID);

        // Get all the batchesList where colorId contains UPDATED_COLOR_ID
        defaultBatchesShouldNotBeFound("colorId.contains=" + UPDATED_COLOR_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByColorIdNotContainsSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorId does not contain DEFAULT_COLOR_ID
        defaultBatchesShouldNotBeFound("colorId.doesNotContain=" + DEFAULT_COLOR_ID);

        // Get all the batchesList where colorId does not contain UPDATED_COLOR_ID
        defaultBatchesShouldBeFound("colorId.doesNotContain=" + UPDATED_COLOR_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBasematerialIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorBasematerial equals to DEFAULT_COLOR_BASEMATERIAL
        defaultBatchesShouldBeFound("colorBasematerial.equals=" + DEFAULT_COLOR_BASEMATERIAL);

        // Get all the batchesList where colorBasematerial equals to UPDATED_COLOR_BASEMATERIAL
        defaultBatchesShouldNotBeFound("colorBasematerial.equals=" + UPDATED_COLOR_BASEMATERIAL);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBasematerialIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorBasematerial in DEFAULT_COLOR_BASEMATERIAL or UPDATED_COLOR_BASEMATERIAL
        defaultBatchesShouldBeFound("colorBasematerial.in=" + DEFAULT_COLOR_BASEMATERIAL + "," + UPDATED_COLOR_BASEMATERIAL);

        // Get all the batchesList where colorBasematerial equals to UPDATED_COLOR_BASEMATERIAL
        defaultBatchesShouldNotBeFound("colorBasematerial.in=" + UPDATED_COLOR_BASEMATERIAL);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBasematerialIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorBasematerial is not null
        defaultBatchesShouldBeFound("colorBasematerial.specified=true");

        // Get all the batchesList where colorBasematerial is null
        defaultBatchesShouldNotBeFound("colorBasematerial.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorBasematerialIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorBasematerial is greater than or equal to DEFAULT_COLOR_BASEMATERIAL
        defaultBatchesShouldBeFound("colorBasematerial.greaterThanOrEqual=" + DEFAULT_COLOR_BASEMATERIAL);

        // Get all the batchesList where colorBasematerial is greater than or equal to UPDATED_COLOR_BASEMATERIAL
        defaultBatchesShouldNotBeFound("colorBasematerial.greaterThanOrEqual=" + UPDATED_COLOR_BASEMATERIAL);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBasematerialIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorBasematerial is less than or equal to DEFAULT_COLOR_BASEMATERIAL
        defaultBatchesShouldBeFound("colorBasematerial.lessThanOrEqual=" + DEFAULT_COLOR_BASEMATERIAL);

        // Get all the batchesList where colorBasematerial is less than or equal to SMALLER_COLOR_BASEMATERIAL
        defaultBatchesShouldNotBeFound("colorBasematerial.lessThanOrEqual=" + SMALLER_COLOR_BASEMATERIAL);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBasematerialIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorBasematerial is less than DEFAULT_COLOR_BASEMATERIAL
        defaultBatchesShouldNotBeFound("colorBasematerial.lessThan=" + DEFAULT_COLOR_BASEMATERIAL);

        // Get all the batchesList where colorBasematerial is less than UPDATED_COLOR_BASEMATERIAL
        defaultBatchesShouldBeFound("colorBasematerial.lessThan=" + UPDATED_COLOR_BASEMATERIAL);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBasematerialIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorBasematerial is greater than DEFAULT_COLOR_BASEMATERIAL
        defaultBatchesShouldNotBeFound("colorBasematerial.greaterThan=" + DEFAULT_COLOR_BASEMATERIAL);

        // Get all the batchesList where colorBasematerial is greater than SMALLER_COLOR_BASEMATERIAL
        defaultBatchesShouldBeFound("colorBasematerial.greaterThan=" + SMALLER_COLOR_BASEMATERIAL);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabLIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabL equals to DEFAULT_COLOR_LAB_L
        defaultBatchesShouldBeFound("colorLabL.equals=" + DEFAULT_COLOR_LAB_L);

        // Get all the batchesList where colorLabL equals to UPDATED_COLOR_LAB_L
        defaultBatchesShouldNotBeFound("colorLabL.equals=" + UPDATED_COLOR_LAB_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabLIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabL in DEFAULT_COLOR_LAB_L or UPDATED_COLOR_LAB_L
        defaultBatchesShouldBeFound("colorLabL.in=" + DEFAULT_COLOR_LAB_L + "," + UPDATED_COLOR_LAB_L);

        // Get all the batchesList where colorLabL equals to UPDATED_COLOR_LAB_L
        defaultBatchesShouldNotBeFound("colorLabL.in=" + UPDATED_COLOR_LAB_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabLIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabL is not null
        defaultBatchesShouldBeFound("colorLabL.specified=true");

        // Get all the batchesList where colorLabL is null
        defaultBatchesShouldNotBeFound("colorLabL.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabLIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabL is greater than or equal to DEFAULT_COLOR_LAB_L
        defaultBatchesShouldBeFound("colorLabL.greaterThanOrEqual=" + DEFAULT_COLOR_LAB_L);

        // Get all the batchesList where colorLabL is greater than or equal to UPDATED_COLOR_LAB_L
        defaultBatchesShouldNotBeFound("colorLabL.greaterThanOrEqual=" + UPDATED_COLOR_LAB_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabLIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabL is less than or equal to DEFAULT_COLOR_LAB_L
        defaultBatchesShouldBeFound("colorLabL.lessThanOrEqual=" + DEFAULT_COLOR_LAB_L);

        // Get all the batchesList where colorLabL is less than or equal to SMALLER_COLOR_LAB_L
        defaultBatchesShouldNotBeFound("colorLabL.lessThanOrEqual=" + SMALLER_COLOR_LAB_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabLIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabL is less than DEFAULT_COLOR_LAB_L
        defaultBatchesShouldNotBeFound("colorLabL.lessThan=" + DEFAULT_COLOR_LAB_L);

        // Get all the batchesList where colorLabL is less than UPDATED_COLOR_LAB_L
        defaultBatchesShouldBeFound("colorLabL.lessThan=" + UPDATED_COLOR_LAB_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabLIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabL is greater than DEFAULT_COLOR_LAB_L
        defaultBatchesShouldNotBeFound("colorLabL.greaterThan=" + DEFAULT_COLOR_LAB_L);

        // Get all the batchesList where colorLabL is greater than SMALLER_COLOR_LAB_L
        defaultBatchesShouldBeFound("colorLabL.greaterThan=" + SMALLER_COLOR_LAB_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabAIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabA equals to DEFAULT_COLOR_LAB_A
        defaultBatchesShouldBeFound("colorLabA.equals=" + DEFAULT_COLOR_LAB_A);

        // Get all the batchesList where colorLabA equals to UPDATED_COLOR_LAB_A
        defaultBatchesShouldNotBeFound("colorLabA.equals=" + UPDATED_COLOR_LAB_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabAIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabA in DEFAULT_COLOR_LAB_A or UPDATED_COLOR_LAB_A
        defaultBatchesShouldBeFound("colorLabA.in=" + DEFAULT_COLOR_LAB_A + "," + UPDATED_COLOR_LAB_A);

        // Get all the batchesList where colorLabA equals to UPDATED_COLOR_LAB_A
        defaultBatchesShouldNotBeFound("colorLabA.in=" + UPDATED_COLOR_LAB_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabAIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabA is not null
        defaultBatchesShouldBeFound("colorLabA.specified=true");

        // Get all the batchesList where colorLabA is null
        defaultBatchesShouldNotBeFound("colorLabA.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabAIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabA is greater than or equal to DEFAULT_COLOR_LAB_A
        defaultBatchesShouldBeFound("colorLabA.greaterThanOrEqual=" + DEFAULT_COLOR_LAB_A);

        // Get all the batchesList where colorLabA is greater than or equal to UPDATED_COLOR_LAB_A
        defaultBatchesShouldNotBeFound("colorLabA.greaterThanOrEqual=" + UPDATED_COLOR_LAB_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabAIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabA is less than or equal to DEFAULT_COLOR_LAB_A
        defaultBatchesShouldBeFound("colorLabA.lessThanOrEqual=" + DEFAULT_COLOR_LAB_A);

        // Get all the batchesList where colorLabA is less than or equal to SMALLER_COLOR_LAB_A
        defaultBatchesShouldNotBeFound("colorLabA.lessThanOrEqual=" + SMALLER_COLOR_LAB_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabAIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabA is less than DEFAULT_COLOR_LAB_A
        defaultBatchesShouldNotBeFound("colorLabA.lessThan=" + DEFAULT_COLOR_LAB_A);

        // Get all the batchesList where colorLabA is less than UPDATED_COLOR_LAB_A
        defaultBatchesShouldBeFound("colorLabA.lessThan=" + UPDATED_COLOR_LAB_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabAIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabA is greater than DEFAULT_COLOR_LAB_A
        defaultBatchesShouldNotBeFound("colorLabA.greaterThan=" + DEFAULT_COLOR_LAB_A);

        // Get all the batchesList where colorLabA is greater than SMALLER_COLOR_LAB_A
        defaultBatchesShouldBeFound("colorLabA.greaterThan=" + SMALLER_COLOR_LAB_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabBIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabB equals to DEFAULT_COLOR_LAB_B
        defaultBatchesShouldBeFound("colorLabB.equals=" + DEFAULT_COLOR_LAB_B);

        // Get all the batchesList where colorLabB equals to UPDATED_COLOR_LAB_B
        defaultBatchesShouldNotBeFound("colorLabB.equals=" + UPDATED_COLOR_LAB_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabBIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabB in DEFAULT_COLOR_LAB_B or UPDATED_COLOR_LAB_B
        defaultBatchesShouldBeFound("colorLabB.in=" + DEFAULT_COLOR_LAB_B + "," + UPDATED_COLOR_LAB_B);

        // Get all the batchesList where colorLabB equals to UPDATED_COLOR_LAB_B
        defaultBatchesShouldNotBeFound("colorLabB.in=" + UPDATED_COLOR_LAB_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabBIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabB is not null
        defaultBatchesShouldBeFound("colorLabB.specified=true");

        // Get all the batchesList where colorLabB is null
        defaultBatchesShouldNotBeFound("colorLabB.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabBIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabB is greater than or equal to DEFAULT_COLOR_LAB_B
        defaultBatchesShouldBeFound("colorLabB.greaterThanOrEqual=" + DEFAULT_COLOR_LAB_B);

        // Get all the batchesList where colorLabB is greater than or equal to UPDATED_COLOR_LAB_B
        defaultBatchesShouldNotBeFound("colorLabB.greaterThanOrEqual=" + UPDATED_COLOR_LAB_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabBIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabB is less than or equal to DEFAULT_COLOR_LAB_B
        defaultBatchesShouldBeFound("colorLabB.lessThanOrEqual=" + DEFAULT_COLOR_LAB_B);

        // Get all the batchesList where colorLabB is less than or equal to SMALLER_COLOR_LAB_B
        defaultBatchesShouldNotBeFound("colorLabB.lessThanOrEqual=" + SMALLER_COLOR_LAB_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabBIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabB is less than DEFAULT_COLOR_LAB_B
        defaultBatchesShouldNotBeFound("colorLabB.lessThan=" + DEFAULT_COLOR_LAB_B);

        // Get all the batchesList where colorLabB is less than UPDATED_COLOR_LAB_B
        defaultBatchesShouldBeFound("colorLabB.lessThan=" + UPDATED_COLOR_LAB_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLabBIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorLabB is greater than DEFAULT_COLOR_LAB_B
        defaultBatchesShouldNotBeFound("colorLabB.greaterThan=" + DEFAULT_COLOR_LAB_B);

        // Get all the batchesList where colorLabB is greater than SMALLER_COLOR_LAB_B
        defaultBatchesShouldBeFound("colorLabB.greaterThan=" + SMALLER_COLOR_LAB_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorAIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorA equals to DEFAULT_COLOR_A
        defaultBatchesShouldBeFound("colorA.equals=" + DEFAULT_COLOR_A);

        // Get all the batchesList where colorA equals to UPDATED_COLOR_A
        defaultBatchesShouldNotBeFound("colorA.equals=" + UPDATED_COLOR_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorAIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorA in DEFAULT_COLOR_A or UPDATED_COLOR_A
        defaultBatchesShouldBeFound("colorA.in=" + DEFAULT_COLOR_A + "," + UPDATED_COLOR_A);

        // Get all the batchesList where colorA equals to UPDATED_COLOR_A
        defaultBatchesShouldNotBeFound("colorA.in=" + UPDATED_COLOR_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorAIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorA is not null
        defaultBatchesShouldBeFound("colorA.specified=true");

        // Get all the batchesList where colorA is null
        defaultBatchesShouldNotBeFound("colorA.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorAIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorA is greater than or equal to DEFAULT_COLOR_A
        defaultBatchesShouldBeFound("colorA.greaterThanOrEqual=" + DEFAULT_COLOR_A);

        // Get all the batchesList where colorA is greater than or equal to UPDATED_COLOR_A
        defaultBatchesShouldNotBeFound("colorA.greaterThanOrEqual=" + UPDATED_COLOR_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorAIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorA is less than or equal to DEFAULT_COLOR_A
        defaultBatchesShouldBeFound("colorA.lessThanOrEqual=" + DEFAULT_COLOR_A);

        // Get all the batchesList where colorA is less than or equal to SMALLER_COLOR_A
        defaultBatchesShouldNotBeFound("colorA.lessThanOrEqual=" + SMALLER_COLOR_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorAIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorA is less than DEFAULT_COLOR_A
        defaultBatchesShouldNotBeFound("colorA.lessThan=" + DEFAULT_COLOR_A);

        // Get all the batchesList where colorA is less than UPDATED_COLOR_A
        defaultBatchesShouldBeFound("colorA.lessThan=" + UPDATED_COLOR_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorAIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorA is greater than DEFAULT_COLOR_A
        defaultBatchesShouldNotBeFound("colorA.greaterThan=" + DEFAULT_COLOR_A);

        // Get all the batchesList where colorA is greater than SMALLER_COLOR_A
        defaultBatchesShouldBeFound("colorA.greaterThan=" + SMALLER_COLOR_A);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorB equals to DEFAULT_COLOR_B
        defaultBatchesShouldBeFound("colorB.equals=" + DEFAULT_COLOR_B);

        // Get all the batchesList where colorB equals to UPDATED_COLOR_B
        defaultBatchesShouldNotBeFound("colorB.equals=" + UPDATED_COLOR_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorB in DEFAULT_COLOR_B or UPDATED_COLOR_B
        defaultBatchesShouldBeFound("colorB.in=" + DEFAULT_COLOR_B + "," + UPDATED_COLOR_B);

        // Get all the batchesList where colorB equals to UPDATED_COLOR_B
        defaultBatchesShouldNotBeFound("colorB.in=" + UPDATED_COLOR_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorB is not null
        defaultBatchesShouldBeFound("colorB.specified=true");

        // Get all the batchesList where colorB is null
        defaultBatchesShouldNotBeFound("colorB.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorBIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorB is greater than or equal to DEFAULT_COLOR_B
        defaultBatchesShouldBeFound("colorB.greaterThanOrEqual=" + DEFAULT_COLOR_B);

        // Get all the batchesList where colorB is greater than or equal to UPDATED_COLOR_B
        defaultBatchesShouldNotBeFound("colorB.greaterThanOrEqual=" + UPDATED_COLOR_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorB is less than or equal to DEFAULT_COLOR_B
        defaultBatchesShouldBeFound("colorB.lessThanOrEqual=" + DEFAULT_COLOR_B);

        // Get all the batchesList where colorB is less than or equal to SMALLER_COLOR_B
        defaultBatchesShouldNotBeFound("colorB.lessThanOrEqual=" + SMALLER_COLOR_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorB is less than DEFAULT_COLOR_B
        defaultBatchesShouldNotBeFound("colorB.lessThan=" + DEFAULT_COLOR_B);

        // Get all the batchesList where colorB is less than UPDATED_COLOR_B
        defaultBatchesShouldBeFound("colorB.lessThan=" + UPDATED_COLOR_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorBIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorB is greater than DEFAULT_COLOR_B
        defaultBatchesShouldNotBeFound("colorB.greaterThan=" + DEFAULT_COLOR_B);

        // Get all the batchesList where colorB is greater than SMALLER_COLOR_B
        defaultBatchesShouldBeFound("colorB.greaterThan=" + SMALLER_COLOR_B);
    }

    @Test
    @Transactional
    void getAllBatchesByColorCIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorC equals to DEFAULT_COLOR_C
        defaultBatchesShouldBeFound("colorC.equals=" + DEFAULT_COLOR_C);

        // Get all the batchesList where colorC equals to UPDATED_COLOR_C
        defaultBatchesShouldNotBeFound("colorC.equals=" + UPDATED_COLOR_C);
    }

    @Test
    @Transactional
    void getAllBatchesByColorCIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorC in DEFAULT_COLOR_C or UPDATED_COLOR_C
        defaultBatchesShouldBeFound("colorC.in=" + DEFAULT_COLOR_C + "," + UPDATED_COLOR_C);

        // Get all the batchesList where colorC equals to UPDATED_COLOR_C
        defaultBatchesShouldNotBeFound("colorC.in=" + UPDATED_COLOR_C);
    }

    @Test
    @Transactional
    void getAllBatchesByColorCIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorC is not null
        defaultBatchesShouldBeFound("colorC.specified=true");

        // Get all the batchesList where colorC is null
        defaultBatchesShouldNotBeFound("colorC.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorCIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorC is greater than or equal to DEFAULT_COLOR_C
        defaultBatchesShouldBeFound("colorC.greaterThanOrEqual=" + DEFAULT_COLOR_C);

        // Get all the batchesList where colorC is greater than or equal to UPDATED_COLOR_C
        defaultBatchesShouldNotBeFound("colorC.greaterThanOrEqual=" + UPDATED_COLOR_C);
    }

    @Test
    @Transactional
    void getAllBatchesByColorCIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorC is less than or equal to DEFAULT_COLOR_C
        defaultBatchesShouldBeFound("colorC.lessThanOrEqual=" + DEFAULT_COLOR_C);

        // Get all the batchesList where colorC is less than or equal to SMALLER_COLOR_C
        defaultBatchesShouldNotBeFound("colorC.lessThanOrEqual=" + SMALLER_COLOR_C);
    }

    @Test
    @Transactional
    void getAllBatchesByColorCIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorC is less than DEFAULT_COLOR_C
        defaultBatchesShouldNotBeFound("colorC.lessThan=" + DEFAULT_COLOR_C);

        // Get all the batchesList where colorC is less than UPDATED_COLOR_C
        defaultBatchesShouldBeFound("colorC.lessThan=" + UPDATED_COLOR_C);
    }

    @Test
    @Transactional
    void getAllBatchesByColorCIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorC is greater than DEFAULT_COLOR_C
        defaultBatchesShouldNotBeFound("colorC.greaterThan=" + DEFAULT_COLOR_C);

        // Get all the batchesList where colorC is greater than SMALLER_COLOR_C
        defaultBatchesShouldBeFound("colorC.greaterThan=" + SMALLER_COLOR_C);
    }

    @Test
    @Transactional
    void getAllBatchesByColorDIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorD equals to DEFAULT_COLOR_D
        defaultBatchesShouldBeFound("colorD.equals=" + DEFAULT_COLOR_D);

        // Get all the batchesList where colorD equals to UPDATED_COLOR_D
        defaultBatchesShouldNotBeFound("colorD.equals=" + UPDATED_COLOR_D);
    }

    @Test
    @Transactional
    void getAllBatchesByColorDIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorD in DEFAULT_COLOR_D or UPDATED_COLOR_D
        defaultBatchesShouldBeFound("colorD.in=" + DEFAULT_COLOR_D + "," + UPDATED_COLOR_D);

        // Get all the batchesList where colorD equals to UPDATED_COLOR_D
        defaultBatchesShouldNotBeFound("colorD.in=" + UPDATED_COLOR_D);
    }

    @Test
    @Transactional
    void getAllBatchesByColorDIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorD is not null
        defaultBatchesShouldBeFound("colorD.specified=true");

        // Get all the batchesList where colorD is null
        defaultBatchesShouldNotBeFound("colorD.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorDIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorD is greater than or equal to DEFAULT_COLOR_D
        defaultBatchesShouldBeFound("colorD.greaterThanOrEqual=" + DEFAULT_COLOR_D);

        // Get all the batchesList where colorD is greater than or equal to UPDATED_COLOR_D
        defaultBatchesShouldNotBeFound("colorD.greaterThanOrEqual=" + UPDATED_COLOR_D);
    }

    @Test
    @Transactional
    void getAllBatchesByColorDIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorD is less than or equal to DEFAULT_COLOR_D
        defaultBatchesShouldBeFound("colorD.lessThanOrEqual=" + DEFAULT_COLOR_D);

        // Get all the batchesList where colorD is less than or equal to SMALLER_COLOR_D
        defaultBatchesShouldNotBeFound("colorD.lessThanOrEqual=" + SMALLER_COLOR_D);
    }

    @Test
    @Transactional
    void getAllBatchesByColorDIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorD is less than DEFAULT_COLOR_D
        defaultBatchesShouldNotBeFound("colorD.lessThan=" + DEFAULT_COLOR_D);

        // Get all the batchesList where colorD is less than UPDATED_COLOR_D
        defaultBatchesShouldBeFound("colorD.lessThan=" + UPDATED_COLOR_D);
    }

    @Test
    @Transactional
    void getAllBatchesByColorDIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorD is greater than DEFAULT_COLOR_D
        defaultBatchesShouldNotBeFound("colorD.greaterThan=" + DEFAULT_COLOR_D);

        // Get all the batchesList where colorD is greater than SMALLER_COLOR_D
        defaultBatchesShouldBeFound("colorD.greaterThan=" + SMALLER_COLOR_D);
    }

    @Test
    @Transactional
    void getAllBatchesByColorEIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorE equals to DEFAULT_COLOR_E
        defaultBatchesShouldBeFound("colorE.equals=" + DEFAULT_COLOR_E);

        // Get all the batchesList where colorE equals to UPDATED_COLOR_E
        defaultBatchesShouldNotBeFound("colorE.equals=" + UPDATED_COLOR_E);
    }

    @Test
    @Transactional
    void getAllBatchesByColorEIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorE in DEFAULT_COLOR_E or UPDATED_COLOR_E
        defaultBatchesShouldBeFound("colorE.in=" + DEFAULT_COLOR_E + "," + UPDATED_COLOR_E);

        // Get all the batchesList where colorE equals to UPDATED_COLOR_E
        defaultBatchesShouldNotBeFound("colorE.in=" + UPDATED_COLOR_E);
    }

    @Test
    @Transactional
    void getAllBatchesByColorEIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorE is not null
        defaultBatchesShouldBeFound("colorE.specified=true");

        // Get all the batchesList where colorE is null
        defaultBatchesShouldNotBeFound("colorE.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorEIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorE is greater than or equal to DEFAULT_COLOR_E
        defaultBatchesShouldBeFound("colorE.greaterThanOrEqual=" + DEFAULT_COLOR_E);

        // Get all the batchesList where colorE is greater than or equal to UPDATED_COLOR_E
        defaultBatchesShouldNotBeFound("colorE.greaterThanOrEqual=" + UPDATED_COLOR_E);
    }

    @Test
    @Transactional
    void getAllBatchesByColorEIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorE is less than or equal to DEFAULT_COLOR_E
        defaultBatchesShouldBeFound("colorE.lessThanOrEqual=" + DEFAULT_COLOR_E);

        // Get all the batchesList where colorE is less than or equal to SMALLER_COLOR_E
        defaultBatchesShouldNotBeFound("colorE.lessThanOrEqual=" + SMALLER_COLOR_E);
    }

    @Test
    @Transactional
    void getAllBatchesByColorEIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorE is less than DEFAULT_COLOR_E
        defaultBatchesShouldNotBeFound("colorE.lessThan=" + DEFAULT_COLOR_E);

        // Get all the batchesList where colorE is less than UPDATED_COLOR_E
        defaultBatchesShouldBeFound("colorE.lessThan=" + UPDATED_COLOR_E);
    }

    @Test
    @Transactional
    void getAllBatchesByColorEIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorE is greater than DEFAULT_COLOR_E
        defaultBatchesShouldNotBeFound("colorE.greaterThan=" + DEFAULT_COLOR_E);

        // Get all the batchesList where colorE is greater than SMALLER_COLOR_E
        defaultBatchesShouldBeFound("colorE.greaterThan=" + SMALLER_COLOR_E);
    }

    @Test
    @Transactional
    void getAllBatchesByColorFIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorF equals to DEFAULT_COLOR_F
        defaultBatchesShouldBeFound("colorF.equals=" + DEFAULT_COLOR_F);

        // Get all the batchesList where colorF equals to UPDATED_COLOR_F
        defaultBatchesShouldNotBeFound("colorF.equals=" + UPDATED_COLOR_F);
    }

    @Test
    @Transactional
    void getAllBatchesByColorFIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorF in DEFAULT_COLOR_F or UPDATED_COLOR_F
        defaultBatchesShouldBeFound("colorF.in=" + DEFAULT_COLOR_F + "," + UPDATED_COLOR_F);

        // Get all the batchesList where colorF equals to UPDATED_COLOR_F
        defaultBatchesShouldNotBeFound("colorF.in=" + UPDATED_COLOR_F);
    }

    @Test
    @Transactional
    void getAllBatchesByColorFIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorF is not null
        defaultBatchesShouldBeFound("colorF.specified=true");

        // Get all the batchesList where colorF is null
        defaultBatchesShouldNotBeFound("colorF.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorFIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorF is greater than or equal to DEFAULT_COLOR_F
        defaultBatchesShouldBeFound("colorF.greaterThanOrEqual=" + DEFAULT_COLOR_F);

        // Get all the batchesList where colorF is greater than or equal to UPDATED_COLOR_F
        defaultBatchesShouldNotBeFound("colorF.greaterThanOrEqual=" + UPDATED_COLOR_F);
    }

    @Test
    @Transactional
    void getAllBatchesByColorFIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorF is less than or equal to DEFAULT_COLOR_F
        defaultBatchesShouldBeFound("colorF.lessThanOrEqual=" + DEFAULT_COLOR_F);

        // Get all the batchesList where colorF is less than or equal to SMALLER_COLOR_F
        defaultBatchesShouldNotBeFound("colorF.lessThanOrEqual=" + SMALLER_COLOR_F);
    }

    @Test
    @Transactional
    void getAllBatchesByColorFIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorF is less than DEFAULT_COLOR_F
        defaultBatchesShouldNotBeFound("colorF.lessThan=" + DEFAULT_COLOR_F);

        // Get all the batchesList where colorF is less than UPDATED_COLOR_F
        defaultBatchesShouldBeFound("colorF.lessThan=" + UPDATED_COLOR_F);
    }

    @Test
    @Transactional
    void getAllBatchesByColorFIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorF is greater than DEFAULT_COLOR_F
        defaultBatchesShouldNotBeFound("colorF.greaterThan=" + DEFAULT_COLOR_F);

        // Get all the batchesList where colorF is greater than SMALLER_COLOR_F
        defaultBatchesShouldBeFound("colorF.greaterThan=" + SMALLER_COLOR_F);
    }

    @Test
    @Transactional
    void getAllBatchesByColorGIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorG equals to DEFAULT_COLOR_G
        defaultBatchesShouldBeFound("colorG.equals=" + DEFAULT_COLOR_G);

        // Get all the batchesList where colorG equals to UPDATED_COLOR_G
        defaultBatchesShouldNotBeFound("colorG.equals=" + UPDATED_COLOR_G);
    }

    @Test
    @Transactional
    void getAllBatchesByColorGIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorG in DEFAULT_COLOR_G or UPDATED_COLOR_G
        defaultBatchesShouldBeFound("colorG.in=" + DEFAULT_COLOR_G + "," + UPDATED_COLOR_G);

        // Get all the batchesList where colorG equals to UPDATED_COLOR_G
        defaultBatchesShouldNotBeFound("colorG.in=" + UPDATED_COLOR_G);
    }

    @Test
    @Transactional
    void getAllBatchesByColorGIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorG is not null
        defaultBatchesShouldBeFound("colorG.specified=true");

        // Get all the batchesList where colorG is null
        defaultBatchesShouldNotBeFound("colorG.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorGIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorG is greater than or equal to DEFAULT_COLOR_G
        defaultBatchesShouldBeFound("colorG.greaterThanOrEqual=" + DEFAULT_COLOR_G);

        // Get all the batchesList where colorG is greater than or equal to UPDATED_COLOR_G
        defaultBatchesShouldNotBeFound("colorG.greaterThanOrEqual=" + UPDATED_COLOR_G);
    }

    @Test
    @Transactional
    void getAllBatchesByColorGIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorG is less than or equal to DEFAULT_COLOR_G
        defaultBatchesShouldBeFound("colorG.lessThanOrEqual=" + DEFAULT_COLOR_G);

        // Get all the batchesList where colorG is less than or equal to SMALLER_COLOR_G
        defaultBatchesShouldNotBeFound("colorG.lessThanOrEqual=" + SMALLER_COLOR_G);
    }

    @Test
    @Transactional
    void getAllBatchesByColorGIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorG is less than DEFAULT_COLOR_G
        defaultBatchesShouldNotBeFound("colorG.lessThan=" + DEFAULT_COLOR_G);

        // Get all the batchesList where colorG is less than UPDATED_COLOR_G
        defaultBatchesShouldBeFound("colorG.lessThan=" + UPDATED_COLOR_G);
    }

    @Test
    @Transactional
    void getAllBatchesByColorGIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorG is greater than DEFAULT_COLOR_G
        defaultBatchesShouldNotBeFound("colorG.greaterThan=" + DEFAULT_COLOR_G);

        // Get all the batchesList where colorG is greater than SMALLER_COLOR_G
        defaultBatchesShouldBeFound("colorG.greaterThan=" + SMALLER_COLOR_G);
    }

    @Test
    @Transactional
    void getAllBatchesByColorHIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorH equals to DEFAULT_COLOR_H
        defaultBatchesShouldBeFound("colorH.equals=" + DEFAULT_COLOR_H);

        // Get all the batchesList where colorH equals to UPDATED_COLOR_H
        defaultBatchesShouldNotBeFound("colorH.equals=" + UPDATED_COLOR_H);
    }

    @Test
    @Transactional
    void getAllBatchesByColorHIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorH in DEFAULT_COLOR_H or UPDATED_COLOR_H
        defaultBatchesShouldBeFound("colorH.in=" + DEFAULT_COLOR_H + "," + UPDATED_COLOR_H);

        // Get all the batchesList where colorH equals to UPDATED_COLOR_H
        defaultBatchesShouldNotBeFound("colorH.in=" + UPDATED_COLOR_H);
    }

    @Test
    @Transactional
    void getAllBatchesByColorHIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorH is not null
        defaultBatchesShouldBeFound("colorH.specified=true");

        // Get all the batchesList where colorH is null
        defaultBatchesShouldNotBeFound("colorH.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorHIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorH is greater than or equal to DEFAULT_COLOR_H
        defaultBatchesShouldBeFound("colorH.greaterThanOrEqual=" + DEFAULT_COLOR_H);

        // Get all the batchesList where colorH is greater than or equal to UPDATED_COLOR_H
        defaultBatchesShouldNotBeFound("colorH.greaterThanOrEqual=" + UPDATED_COLOR_H);
    }

    @Test
    @Transactional
    void getAllBatchesByColorHIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorH is less than or equal to DEFAULT_COLOR_H
        defaultBatchesShouldBeFound("colorH.lessThanOrEqual=" + DEFAULT_COLOR_H);

        // Get all the batchesList where colorH is less than or equal to SMALLER_COLOR_H
        defaultBatchesShouldNotBeFound("colorH.lessThanOrEqual=" + SMALLER_COLOR_H);
    }

    @Test
    @Transactional
    void getAllBatchesByColorHIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorH is less than DEFAULT_COLOR_H
        defaultBatchesShouldNotBeFound("colorH.lessThan=" + DEFAULT_COLOR_H);

        // Get all the batchesList where colorH is less than UPDATED_COLOR_H
        defaultBatchesShouldBeFound("colorH.lessThan=" + UPDATED_COLOR_H);
    }

    @Test
    @Transactional
    void getAllBatchesByColorHIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorH is greater than DEFAULT_COLOR_H
        defaultBatchesShouldNotBeFound("colorH.greaterThan=" + DEFAULT_COLOR_H);

        // Get all the batchesList where colorH is greater than SMALLER_COLOR_H
        defaultBatchesShouldBeFound("colorH.greaterThan=" + SMALLER_COLOR_H);
    }

    @Test
    @Transactional
    void getAllBatchesByColorIIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorI equals to DEFAULT_COLOR_I
        defaultBatchesShouldBeFound("colorI.equals=" + DEFAULT_COLOR_I);

        // Get all the batchesList where colorI equals to UPDATED_COLOR_I
        defaultBatchesShouldNotBeFound("colorI.equals=" + UPDATED_COLOR_I);
    }

    @Test
    @Transactional
    void getAllBatchesByColorIIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorI in DEFAULT_COLOR_I or UPDATED_COLOR_I
        defaultBatchesShouldBeFound("colorI.in=" + DEFAULT_COLOR_I + "," + UPDATED_COLOR_I);

        // Get all the batchesList where colorI equals to UPDATED_COLOR_I
        defaultBatchesShouldNotBeFound("colorI.in=" + UPDATED_COLOR_I);
    }

    @Test
    @Transactional
    void getAllBatchesByColorIIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorI is not null
        defaultBatchesShouldBeFound("colorI.specified=true");

        // Get all the batchesList where colorI is null
        defaultBatchesShouldNotBeFound("colorI.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorIIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorI is greater than or equal to DEFAULT_COLOR_I
        defaultBatchesShouldBeFound("colorI.greaterThanOrEqual=" + DEFAULT_COLOR_I);

        // Get all the batchesList where colorI is greater than or equal to UPDATED_COLOR_I
        defaultBatchesShouldNotBeFound("colorI.greaterThanOrEqual=" + UPDATED_COLOR_I);
    }

    @Test
    @Transactional
    void getAllBatchesByColorIIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorI is less than or equal to DEFAULT_COLOR_I
        defaultBatchesShouldBeFound("colorI.lessThanOrEqual=" + DEFAULT_COLOR_I);

        // Get all the batchesList where colorI is less than or equal to SMALLER_COLOR_I
        defaultBatchesShouldNotBeFound("colorI.lessThanOrEqual=" + SMALLER_COLOR_I);
    }

    @Test
    @Transactional
    void getAllBatchesByColorIIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorI is less than DEFAULT_COLOR_I
        defaultBatchesShouldNotBeFound("colorI.lessThan=" + DEFAULT_COLOR_I);

        // Get all the batchesList where colorI is less than UPDATED_COLOR_I
        defaultBatchesShouldBeFound("colorI.lessThan=" + UPDATED_COLOR_I);
    }

    @Test
    @Transactional
    void getAllBatchesByColorIIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorI is greater than DEFAULT_COLOR_I
        defaultBatchesShouldNotBeFound("colorI.greaterThan=" + DEFAULT_COLOR_I);

        // Get all the batchesList where colorI is greater than SMALLER_COLOR_I
        defaultBatchesShouldBeFound("colorI.greaterThan=" + SMALLER_COLOR_I);
    }

    @Test
    @Transactional
    void getAllBatchesByColorJIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorJ equals to DEFAULT_COLOR_J
        defaultBatchesShouldBeFound("colorJ.equals=" + DEFAULT_COLOR_J);

        // Get all the batchesList where colorJ equals to UPDATED_COLOR_J
        defaultBatchesShouldNotBeFound("colorJ.equals=" + UPDATED_COLOR_J);
    }

    @Test
    @Transactional
    void getAllBatchesByColorJIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorJ in DEFAULT_COLOR_J or UPDATED_COLOR_J
        defaultBatchesShouldBeFound("colorJ.in=" + DEFAULT_COLOR_J + "," + UPDATED_COLOR_J);

        // Get all the batchesList where colorJ equals to UPDATED_COLOR_J
        defaultBatchesShouldNotBeFound("colorJ.in=" + UPDATED_COLOR_J);
    }

    @Test
    @Transactional
    void getAllBatchesByColorJIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorJ is not null
        defaultBatchesShouldBeFound("colorJ.specified=true");

        // Get all the batchesList where colorJ is null
        defaultBatchesShouldNotBeFound("colorJ.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorJIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorJ is greater than or equal to DEFAULT_COLOR_J
        defaultBatchesShouldBeFound("colorJ.greaterThanOrEqual=" + DEFAULT_COLOR_J);

        // Get all the batchesList where colorJ is greater than or equal to UPDATED_COLOR_J
        defaultBatchesShouldNotBeFound("colorJ.greaterThanOrEqual=" + UPDATED_COLOR_J);
    }

    @Test
    @Transactional
    void getAllBatchesByColorJIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorJ is less than or equal to DEFAULT_COLOR_J
        defaultBatchesShouldBeFound("colorJ.lessThanOrEqual=" + DEFAULT_COLOR_J);

        // Get all the batchesList where colorJ is less than or equal to SMALLER_COLOR_J
        defaultBatchesShouldNotBeFound("colorJ.lessThanOrEqual=" + SMALLER_COLOR_J);
    }

    @Test
    @Transactional
    void getAllBatchesByColorJIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorJ is less than DEFAULT_COLOR_J
        defaultBatchesShouldNotBeFound("colorJ.lessThan=" + DEFAULT_COLOR_J);

        // Get all the batchesList where colorJ is less than UPDATED_COLOR_J
        defaultBatchesShouldBeFound("colorJ.lessThan=" + UPDATED_COLOR_J);
    }

    @Test
    @Transactional
    void getAllBatchesByColorJIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorJ is greater than DEFAULT_COLOR_J
        defaultBatchesShouldNotBeFound("colorJ.greaterThan=" + DEFAULT_COLOR_J);

        // Get all the batchesList where colorJ is greater than SMALLER_COLOR_J
        defaultBatchesShouldBeFound("colorJ.greaterThan=" + SMALLER_COLOR_J);
    }

    @Test
    @Transactional
    void getAllBatchesByColorKIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorK equals to DEFAULT_COLOR_K
        defaultBatchesShouldBeFound("colorK.equals=" + DEFAULT_COLOR_K);

        // Get all the batchesList where colorK equals to UPDATED_COLOR_K
        defaultBatchesShouldNotBeFound("colorK.equals=" + UPDATED_COLOR_K);
    }

    @Test
    @Transactional
    void getAllBatchesByColorKIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorK in DEFAULT_COLOR_K or UPDATED_COLOR_K
        defaultBatchesShouldBeFound("colorK.in=" + DEFAULT_COLOR_K + "," + UPDATED_COLOR_K);

        // Get all the batchesList where colorK equals to UPDATED_COLOR_K
        defaultBatchesShouldNotBeFound("colorK.in=" + UPDATED_COLOR_K);
    }

    @Test
    @Transactional
    void getAllBatchesByColorKIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorK is not null
        defaultBatchesShouldBeFound("colorK.specified=true");

        // Get all the batchesList where colorK is null
        defaultBatchesShouldNotBeFound("colorK.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorKIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorK is greater than or equal to DEFAULT_COLOR_K
        defaultBatchesShouldBeFound("colorK.greaterThanOrEqual=" + DEFAULT_COLOR_K);

        // Get all the batchesList where colorK is greater than or equal to UPDATED_COLOR_K
        defaultBatchesShouldNotBeFound("colorK.greaterThanOrEqual=" + UPDATED_COLOR_K);
    }

    @Test
    @Transactional
    void getAllBatchesByColorKIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorK is less than or equal to DEFAULT_COLOR_K
        defaultBatchesShouldBeFound("colorK.lessThanOrEqual=" + DEFAULT_COLOR_K);

        // Get all the batchesList where colorK is less than or equal to SMALLER_COLOR_K
        defaultBatchesShouldNotBeFound("colorK.lessThanOrEqual=" + SMALLER_COLOR_K);
    }

    @Test
    @Transactional
    void getAllBatchesByColorKIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorK is less than DEFAULT_COLOR_K
        defaultBatchesShouldNotBeFound("colorK.lessThan=" + DEFAULT_COLOR_K);

        // Get all the batchesList where colorK is less than UPDATED_COLOR_K
        defaultBatchesShouldBeFound("colorK.lessThan=" + UPDATED_COLOR_K);
    }

    @Test
    @Transactional
    void getAllBatchesByColorKIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorK is greater than DEFAULT_COLOR_K
        defaultBatchesShouldNotBeFound("colorK.greaterThan=" + DEFAULT_COLOR_K);

        // Get all the batchesList where colorK is greater than SMALLER_COLOR_K
        defaultBatchesShouldBeFound("colorK.greaterThan=" + SMALLER_COLOR_K);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorL equals to DEFAULT_COLOR_L
        defaultBatchesShouldBeFound("colorL.equals=" + DEFAULT_COLOR_L);

        // Get all the batchesList where colorL equals to UPDATED_COLOR_L
        defaultBatchesShouldNotBeFound("colorL.equals=" + UPDATED_COLOR_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorL in DEFAULT_COLOR_L or UPDATED_COLOR_L
        defaultBatchesShouldBeFound("colorL.in=" + DEFAULT_COLOR_L + "," + UPDATED_COLOR_L);

        // Get all the batchesList where colorL equals to UPDATED_COLOR_L
        defaultBatchesShouldNotBeFound("colorL.in=" + UPDATED_COLOR_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorL is not null
        defaultBatchesShouldBeFound("colorL.specified=true");

        // Get all the batchesList where colorL is null
        defaultBatchesShouldNotBeFound("colorL.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorLIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorL is greater than or equal to DEFAULT_COLOR_L
        defaultBatchesShouldBeFound("colorL.greaterThanOrEqual=" + DEFAULT_COLOR_L);

        // Get all the batchesList where colorL is greater than or equal to UPDATED_COLOR_L
        defaultBatchesShouldNotBeFound("colorL.greaterThanOrEqual=" + UPDATED_COLOR_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorL is less than or equal to DEFAULT_COLOR_L
        defaultBatchesShouldBeFound("colorL.lessThanOrEqual=" + DEFAULT_COLOR_L);

        // Get all the batchesList where colorL is less than or equal to SMALLER_COLOR_L
        defaultBatchesShouldNotBeFound("colorL.lessThanOrEqual=" + SMALLER_COLOR_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorL is less than DEFAULT_COLOR_L
        defaultBatchesShouldNotBeFound("colorL.lessThan=" + DEFAULT_COLOR_L);

        // Get all the batchesList where colorL is less than UPDATED_COLOR_L
        defaultBatchesShouldBeFound("colorL.lessThan=" + UPDATED_COLOR_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorLIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorL is greater than DEFAULT_COLOR_L
        defaultBatchesShouldNotBeFound("colorL.greaterThan=" + DEFAULT_COLOR_L);

        // Get all the batchesList where colorL is greater than SMALLER_COLOR_L
        defaultBatchesShouldBeFound("colorL.greaterThan=" + SMALLER_COLOR_L);
    }

    @Test
    @Transactional
    void getAllBatchesByColorMIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorM equals to DEFAULT_COLOR_M
        defaultBatchesShouldBeFound("colorM.equals=" + DEFAULT_COLOR_M);

        // Get all the batchesList where colorM equals to UPDATED_COLOR_M
        defaultBatchesShouldNotBeFound("colorM.equals=" + UPDATED_COLOR_M);
    }

    @Test
    @Transactional
    void getAllBatchesByColorMIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorM in DEFAULT_COLOR_M or UPDATED_COLOR_M
        defaultBatchesShouldBeFound("colorM.in=" + DEFAULT_COLOR_M + "," + UPDATED_COLOR_M);

        // Get all the batchesList where colorM equals to UPDATED_COLOR_M
        defaultBatchesShouldNotBeFound("colorM.in=" + UPDATED_COLOR_M);
    }

    @Test
    @Transactional
    void getAllBatchesByColorMIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorM is not null
        defaultBatchesShouldBeFound("colorM.specified=true");

        // Get all the batchesList where colorM is null
        defaultBatchesShouldNotBeFound("colorM.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorMIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorM is greater than or equal to DEFAULT_COLOR_M
        defaultBatchesShouldBeFound("colorM.greaterThanOrEqual=" + DEFAULT_COLOR_M);

        // Get all the batchesList where colorM is greater than or equal to UPDATED_COLOR_M
        defaultBatchesShouldNotBeFound("colorM.greaterThanOrEqual=" + UPDATED_COLOR_M);
    }

    @Test
    @Transactional
    void getAllBatchesByColorMIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorM is less than or equal to DEFAULT_COLOR_M
        defaultBatchesShouldBeFound("colorM.lessThanOrEqual=" + DEFAULT_COLOR_M);

        // Get all the batchesList where colorM is less than or equal to SMALLER_COLOR_M
        defaultBatchesShouldNotBeFound("colorM.lessThanOrEqual=" + SMALLER_COLOR_M);
    }

    @Test
    @Transactional
    void getAllBatchesByColorMIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorM is less than DEFAULT_COLOR_M
        defaultBatchesShouldNotBeFound("colorM.lessThan=" + DEFAULT_COLOR_M);

        // Get all the batchesList where colorM is less than UPDATED_COLOR_M
        defaultBatchesShouldBeFound("colorM.lessThan=" + UPDATED_COLOR_M);
    }

    @Test
    @Transactional
    void getAllBatchesByColorMIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorM is greater than DEFAULT_COLOR_M
        defaultBatchesShouldNotBeFound("colorM.greaterThan=" + DEFAULT_COLOR_M);

        // Get all the batchesList where colorM is greater than SMALLER_COLOR_M
        defaultBatchesShouldBeFound("colorM.greaterThan=" + SMALLER_COLOR_M);
    }

    @Test
    @Transactional
    void getAllBatchesByColorNIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorN equals to DEFAULT_COLOR_N
        defaultBatchesShouldBeFound("colorN.equals=" + DEFAULT_COLOR_N);

        // Get all the batchesList where colorN equals to UPDATED_COLOR_N
        defaultBatchesShouldNotBeFound("colorN.equals=" + UPDATED_COLOR_N);
    }

    @Test
    @Transactional
    void getAllBatchesByColorNIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorN in DEFAULT_COLOR_N or UPDATED_COLOR_N
        defaultBatchesShouldBeFound("colorN.in=" + DEFAULT_COLOR_N + "," + UPDATED_COLOR_N);

        // Get all the batchesList where colorN equals to UPDATED_COLOR_N
        defaultBatchesShouldNotBeFound("colorN.in=" + UPDATED_COLOR_N);
    }

    @Test
    @Transactional
    void getAllBatchesByColorNIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorN is not null
        defaultBatchesShouldBeFound("colorN.specified=true");

        // Get all the batchesList where colorN is null
        defaultBatchesShouldNotBeFound("colorN.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorNIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorN is greater than or equal to DEFAULT_COLOR_N
        defaultBatchesShouldBeFound("colorN.greaterThanOrEqual=" + DEFAULT_COLOR_N);

        // Get all the batchesList where colorN is greater than or equal to UPDATED_COLOR_N
        defaultBatchesShouldNotBeFound("colorN.greaterThanOrEqual=" + UPDATED_COLOR_N);
    }

    @Test
    @Transactional
    void getAllBatchesByColorNIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorN is less than or equal to DEFAULT_COLOR_N
        defaultBatchesShouldBeFound("colorN.lessThanOrEqual=" + DEFAULT_COLOR_N);

        // Get all the batchesList where colorN is less than or equal to SMALLER_COLOR_N
        defaultBatchesShouldNotBeFound("colorN.lessThanOrEqual=" + SMALLER_COLOR_N);
    }

    @Test
    @Transactional
    void getAllBatchesByColorNIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorN is less than DEFAULT_COLOR_N
        defaultBatchesShouldNotBeFound("colorN.lessThan=" + DEFAULT_COLOR_N);

        // Get all the batchesList where colorN is less than UPDATED_COLOR_N
        defaultBatchesShouldBeFound("colorN.lessThan=" + UPDATED_COLOR_N);
    }

    @Test
    @Transactional
    void getAllBatchesByColorNIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorN is greater than DEFAULT_COLOR_N
        defaultBatchesShouldNotBeFound("colorN.greaterThan=" + DEFAULT_COLOR_N);

        // Get all the batchesList where colorN is greater than SMALLER_COLOR_N
        defaultBatchesShouldBeFound("colorN.greaterThan=" + SMALLER_COLOR_N);
    }

    @Test
    @Transactional
    void getAllBatchesByColorOIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorO equals to DEFAULT_COLOR_O
        defaultBatchesShouldBeFound("colorO.equals=" + DEFAULT_COLOR_O);

        // Get all the batchesList where colorO equals to UPDATED_COLOR_O
        defaultBatchesShouldNotBeFound("colorO.equals=" + UPDATED_COLOR_O);
    }

    @Test
    @Transactional
    void getAllBatchesByColorOIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorO in DEFAULT_COLOR_O or UPDATED_COLOR_O
        defaultBatchesShouldBeFound("colorO.in=" + DEFAULT_COLOR_O + "," + UPDATED_COLOR_O);

        // Get all the batchesList where colorO equals to UPDATED_COLOR_O
        defaultBatchesShouldNotBeFound("colorO.in=" + UPDATED_COLOR_O);
    }

    @Test
    @Transactional
    void getAllBatchesByColorOIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorO is not null
        defaultBatchesShouldBeFound("colorO.specified=true");

        // Get all the batchesList where colorO is null
        defaultBatchesShouldNotBeFound("colorO.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorOIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorO is greater than or equal to DEFAULT_COLOR_O
        defaultBatchesShouldBeFound("colorO.greaterThanOrEqual=" + DEFAULT_COLOR_O);

        // Get all the batchesList where colorO is greater than or equal to UPDATED_COLOR_O
        defaultBatchesShouldNotBeFound("colorO.greaterThanOrEqual=" + UPDATED_COLOR_O);
    }

    @Test
    @Transactional
    void getAllBatchesByColorOIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorO is less than or equal to DEFAULT_COLOR_O
        defaultBatchesShouldBeFound("colorO.lessThanOrEqual=" + DEFAULT_COLOR_O);

        // Get all the batchesList where colorO is less than or equal to SMALLER_COLOR_O
        defaultBatchesShouldNotBeFound("colorO.lessThanOrEqual=" + SMALLER_COLOR_O);
    }

    @Test
    @Transactional
    void getAllBatchesByColorOIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorO is less than DEFAULT_COLOR_O
        defaultBatchesShouldNotBeFound("colorO.lessThan=" + DEFAULT_COLOR_O);

        // Get all the batchesList where colorO is less than UPDATED_COLOR_O
        defaultBatchesShouldBeFound("colorO.lessThan=" + UPDATED_COLOR_O);
    }

    @Test
    @Transactional
    void getAllBatchesByColorOIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorO is greater than DEFAULT_COLOR_O
        defaultBatchesShouldNotBeFound("colorO.greaterThan=" + DEFAULT_COLOR_O);

        // Get all the batchesList where colorO is greater than SMALLER_COLOR_O
        defaultBatchesShouldBeFound("colorO.greaterThan=" + SMALLER_COLOR_O);
    }

    @Test
    @Transactional
    void getAllBatchesByColorPIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorP equals to DEFAULT_COLOR_P
        defaultBatchesShouldBeFound("colorP.equals=" + DEFAULT_COLOR_P);

        // Get all the batchesList where colorP equals to UPDATED_COLOR_P
        defaultBatchesShouldNotBeFound("colorP.equals=" + UPDATED_COLOR_P);
    }

    @Test
    @Transactional
    void getAllBatchesByColorPIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorP in DEFAULT_COLOR_P or UPDATED_COLOR_P
        defaultBatchesShouldBeFound("colorP.in=" + DEFAULT_COLOR_P + "," + UPDATED_COLOR_P);

        // Get all the batchesList where colorP equals to UPDATED_COLOR_P
        defaultBatchesShouldNotBeFound("colorP.in=" + UPDATED_COLOR_P);
    }

    @Test
    @Transactional
    void getAllBatchesByColorPIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorP is not null
        defaultBatchesShouldBeFound("colorP.specified=true");

        // Get all the batchesList where colorP is null
        defaultBatchesShouldNotBeFound("colorP.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorPIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorP is greater than or equal to DEFAULT_COLOR_P
        defaultBatchesShouldBeFound("colorP.greaterThanOrEqual=" + DEFAULT_COLOR_P);

        // Get all the batchesList where colorP is greater than or equal to UPDATED_COLOR_P
        defaultBatchesShouldNotBeFound("colorP.greaterThanOrEqual=" + UPDATED_COLOR_P);
    }

    @Test
    @Transactional
    void getAllBatchesByColorPIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorP is less than or equal to DEFAULT_COLOR_P
        defaultBatchesShouldBeFound("colorP.lessThanOrEqual=" + DEFAULT_COLOR_P);

        // Get all the batchesList where colorP is less than or equal to SMALLER_COLOR_P
        defaultBatchesShouldNotBeFound("colorP.lessThanOrEqual=" + SMALLER_COLOR_P);
    }

    @Test
    @Transactional
    void getAllBatchesByColorPIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorP is less than DEFAULT_COLOR_P
        defaultBatchesShouldNotBeFound("colorP.lessThan=" + DEFAULT_COLOR_P);

        // Get all the batchesList where colorP is less than UPDATED_COLOR_P
        defaultBatchesShouldBeFound("colorP.lessThan=" + UPDATED_COLOR_P);
    }

    @Test
    @Transactional
    void getAllBatchesByColorPIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorP is greater than DEFAULT_COLOR_P
        defaultBatchesShouldNotBeFound("colorP.greaterThan=" + DEFAULT_COLOR_P);

        // Get all the batchesList where colorP is greater than SMALLER_COLOR_P
        defaultBatchesShouldBeFound("colorP.greaterThan=" + SMALLER_COLOR_P);
    }

    @Test
    @Transactional
    void getAllBatchesByColorQIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorQ equals to DEFAULT_COLOR_Q
        defaultBatchesShouldBeFound("colorQ.equals=" + DEFAULT_COLOR_Q);

        // Get all the batchesList where colorQ equals to UPDATED_COLOR_Q
        defaultBatchesShouldNotBeFound("colorQ.equals=" + UPDATED_COLOR_Q);
    }

    @Test
    @Transactional
    void getAllBatchesByColorQIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorQ in DEFAULT_COLOR_Q or UPDATED_COLOR_Q
        defaultBatchesShouldBeFound("colorQ.in=" + DEFAULT_COLOR_Q + "," + UPDATED_COLOR_Q);

        // Get all the batchesList where colorQ equals to UPDATED_COLOR_Q
        defaultBatchesShouldNotBeFound("colorQ.in=" + UPDATED_COLOR_Q);
    }

    @Test
    @Transactional
    void getAllBatchesByColorQIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorQ is not null
        defaultBatchesShouldBeFound("colorQ.specified=true");

        // Get all the batchesList where colorQ is null
        defaultBatchesShouldNotBeFound("colorQ.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorQIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorQ is greater than or equal to DEFAULT_COLOR_Q
        defaultBatchesShouldBeFound("colorQ.greaterThanOrEqual=" + DEFAULT_COLOR_Q);

        // Get all the batchesList where colorQ is greater than or equal to UPDATED_COLOR_Q
        defaultBatchesShouldNotBeFound("colorQ.greaterThanOrEqual=" + UPDATED_COLOR_Q);
    }

    @Test
    @Transactional
    void getAllBatchesByColorQIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorQ is less than or equal to DEFAULT_COLOR_Q
        defaultBatchesShouldBeFound("colorQ.lessThanOrEqual=" + DEFAULT_COLOR_Q);

        // Get all the batchesList where colorQ is less than or equal to SMALLER_COLOR_Q
        defaultBatchesShouldNotBeFound("colorQ.lessThanOrEqual=" + SMALLER_COLOR_Q);
    }

    @Test
    @Transactional
    void getAllBatchesByColorQIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorQ is less than DEFAULT_COLOR_Q
        defaultBatchesShouldNotBeFound("colorQ.lessThan=" + DEFAULT_COLOR_Q);

        // Get all the batchesList where colorQ is less than UPDATED_COLOR_Q
        defaultBatchesShouldBeFound("colorQ.lessThan=" + UPDATED_COLOR_Q);
    }

    @Test
    @Transactional
    void getAllBatchesByColorQIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorQ is greater than DEFAULT_COLOR_Q
        defaultBatchesShouldNotBeFound("colorQ.greaterThan=" + DEFAULT_COLOR_Q);

        // Get all the batchesList where colorQ is greater than SMALLER_COLOR_Q
        defaultBatchesShouldBeFound("colorQ.greaterThan=" + SMALLER_COLOR_Q);
    }

    @Test
    @Transactional
    void getAllBatchesByColorRIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorR equals to DEFAULT_COLOR_R
        defaultBatchesShouldBeFound("colorR.equals=" + DEFAULT_COLOR_R);

        // Get all the batchesList where colorR equals to UPDATED_COLOR_R
        defaultBatchesShouldNotBeFound("colorR.equals=" + UPDATED_COLOR_R);
    }

    @Test
    @Transactional
    void getAllBatchesByColorRIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorR in DEFAULT_COLOR_R or UPDATED_COLOR_R
        defaultBatchesShouldBeFound("colorR.in=" + DEFAULT_COLOR_R + "," + UPDATED_COLOR_R);

        // Get all the batchesList where colorR equals to UPDATED_COLOR_R
        defaultBatchesShouldNotBeFound("colorR.in=" + UPDATED_COLOR_R);
    }

    @Test
    @Transactional
    void getAllBatchesByColorRIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorR is not null
        defaultBatchesShouldBeFound("colorR.specified=true");

        // Get all the batchesList where colorR is null
        defaultBatchesShouldNotBeFound("colorR.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorRIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorR is greater than or equal to DEFAULT_COLOR_R
        defaultBatchesShouldBeFound("colorR.greaterThanOrEqual=" + DEFAULT_COLOR_R);

        // Get all the batchesList where colorR is greater than or equal to UPDATED_COLOR_R
        defaultBatchesShouldNotBeFound("colorR.greaterThanOrEqual=" + UPDATED_COLOR_R);
    }

    @Test
    @Transactional
    void getAllBatchesByColorRIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorR is less than or equal to DEFAULT_COLOR_R
        defaultBatchesShouldBeFound("colorR.lessThanOrEqual=" + DEFAULT_COLOR_R);

        // Get all the batchesList where colorR is less than or equal to SMALLER_COLOR_R
        defaultBatchesShouldNotBeFound("colorR.lessThanOrEqual=" + SMALLER_COLOR_R);
    }

    @Test
    @Transactional
    void getAllBatchesByColorRIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorR is less than DEFAULT_COLOR_R
        defaultBatchesShouldNotBeFound("colorR.lessThan=" + DEFAULT_COLOR_R);

        // Get all the batchesList where colorR is less than UPDATED_COLOR_R
        defaultBatchesShouldBeFound("colorR.lessThan=" + UPDATED_COLOR_R);
    }

    @Test
    @Transactional
    void getAllBatchesByColorRIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorR is greater than DEFAULT_COLOR_R
        defaultBatchesShouldNotBeFound("colorR.greaterThan=" + DEFAULT_COLOR_R);

        // Get all the batchesList where colorR is greater than SMALLER_COLOR_R
        defaultBatchesShouldBeFound("colorR.greaterThan=" + SMALLER_COLOR_R);
    }

    @Test
    @Transactional
    void getAllBatchesByColorSIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorS equals to DEFAULT_COLOR_S
        defaultBatchesShouldBeFound("colorS.equals=" + DEFAULT_COLOR_S);

        // Get all the batchesList where colorS equals to UPDATED_COLOR_S
        defaultBatchesShouldNotBeFound("colorS.equals=" + UPDATED_COLOR_S);
    }

    @Test
    @Transactional
    void getAllBatchesByColorSIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorS in DEFAULT_COLOR_S or UPDATED_COLOR_S
        defaultBatchesShouldBeFound("colorS.in=" + DEFAULT_COLOR_S + "," + UPDATED_COLOR_S);

        // Get all the batchesList where colorS equals to UPDATED_COLOR_S
        defaultBatchesShouldNotBeFound("colorS.in=" + UPDATED_COLOR_S);
    }

    @Test
    @Transactional
    void getAllBatchesByColorSIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorS is not null
        defaultBatchesShouldBeFound("colorS.specified=true");

        // Get all the batchesList where colorS is null
        defaultBatchesShouldNotBeFound("colorS.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorSIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorS is greater than or equal to DEFAULT_COLOR_S
        defaultBatchesShouldBeFound("colorS.greaterThanOrEqual=" + DEFAULT_COLOR_S);

        // Get all the batchesList where colorS is greater than or equal to UPDATED_COLOR_S
        defaultBatchesShouldNotBeFound("colorS.greaterThanOrEqual=" + UPDATED_COLOR_S);
    }

    @Test
    @Transactional
    void getAllBatchesByColorSIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorS is less than or equal to DEFAULT_COLOR_S
        defaultBatchesShouldBeFound("colorS.lessThanOrEqual=" + DEFAULT_COLOR_S);

        // Get all the batchesList where colorS is less than or equal to SMALLER_COLOR_S
        defaultBatchesShouldNotBeFound("colorS.lessThanOrEqual=" + SMALLER_COLOR_S);
    }

    @Test
    @Transactional
    void getAllBatchesByColorSIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorS is less than DEFAULT_COLOR_S
        defaultBatchesShouldNotBeFound("colorS.lessThan=" + DEFAULT_COLOR_S);

        // Get all the batchesList where colorS is less than UPDATED_COLOR_S
        defaultBatchesShouldBeFound("colorS.lessThan=" + UPDATED_COLOR_S);
    }

    @Test
    @Transactional
    void getAllBatchesByColorSIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorS is greater than DEFAULT_COLOR_S
        defaultBatchesShouldNotBeFound("colorS.greaterThan=" + DEFAULT_COLOR_S);

        // Get all the batchesList where colorS is greater than SMALLER_COLOR_S
        defaultBatchesShouldBeFound("colorS.greaterThan=" + SMALLER_COLOR_S);
    }

    @Test
    @Transactional
    void getAllBatchesByColorTIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorT equals to DEFAULT_COLOR_T
        defaultBatchesShouldBeFound("colorT.equals=" + DEFAULT_COLOR_T);

        // Get all the batchesList where colorT equals to UPDATED_COLOR_T
        defaultBatchesShouldNotBeFound("colorT.equals=" + UPDATED_COLOR_T);
    }

    @Test
    @Transactional
    void getAllBatchesByColorTIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorT in DEFAULT_COLOR_T or UPDATED_COLOR_T
        defaultBatchesShouldBeFound("colorT.in=" + DEFAULT_COLOR_T + "," + UPDATED_COLOR_T);

        // Get all the batchesList where colorT equals to UPDATED_COLOR_T
        defaultBatchesShouldNotBeFound("colorT.in=" + UPDATED_COLOR_T);
    }

    @Test
    @Transactional
    void getAllBatchesByColorTIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorT is not null
        defaultBatchesShouldBeFound("colorT.specified=true");

        // Get all the batchesList where colorT is null
        defaultBatchesShouldNotBeFound("colorT.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorTIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorT is greater than or equal to DEFAULT_COLOR_T
        defaultBatchesShouldBeFound("colorT.greaterThanOrEqual=" + DEFAULT_COLOR_T);

        // Get all the batchesList where colorT is greater than or equal to UPDATED_COLOR_T
        defaultBatchesShouldNotBeFound("colorT.greaterThanOrEqual=" + UPDATED_COLOR_T);
    }

    @Test
    @Transactional
    void getAllBatchesByColorTIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorT is less than or equal to DEFAULT_COLOR_T
        defaultBatchesShouldBeFound("colorT.lessThanOrEqual=" + DEFAULT_COLOR_T);

        // Get all the batchesList where colorT is less than or equal to SMALLER_COLOR_T
        defaultBatchesShouldNotBeFound("colorT.lessThanOrEqual=" + SMALLER_COLOR_T);
    }

    @Test
    @Transactional
    void getAllBatchesByColorTIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorT is less than DEFAULT_COLOR_T
        defaultBatchesShouldNotBeFound("colorT.lessThan=" + DEFAULT_COLOR_T);

        // Get all the batchesList where colorT is less than UPDATED_COLOR_T
        defaultBatchesShouldBeFound("colorT.lessThan=" + UPDATED_COLOR_T);
    }

    @Test
    @Transactional
    void getAllBatchesByColorTIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorT is greater than DEFAULT_COLOR_T
        defaultBatchesShouldNotBeFound("colorT.greaterThan=" + DEFAULT_COLOR_T);

        // Get all the batchesList where colorT is greater than SMALLER_COLOR_T
        defaultBatchesShouldBeFound("colorT.greaterThan=" + SMALLER_COLOR_T);
    }

    @Test
    @Transactional
    void getAllBatchesByColorUIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorU equals to DEFAULT_COLOR_U
        defaultBatchesShouldBeFound("colorU.equals=" + DEFAULT_COLOR_U);

        // Get all the batchesList where colorU equals to UPDATED_COLOR_U
        defaultBatchesShouldNotBeFound("colorU.equals=" + UPDATED_COLOR_U);
    }

    @Test
    @Transactional
    void getAllBatchesByColorUIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorU in DEFAULT_COLOR_U or UPDATED_COLOR_U
        defaultBatchesShouldBeFound("colorU.in=" + DEFAULT_COLOR_U + "," + UPDATED_COLOR_U);

        // Get all the batchesList where colorU equals to UPDATED_COLOR_U
        defaultBatchesShouldNotBeFound("colorU.in=" + UPDATED_COLOR_U);
    }

    @Test
    @Transactional
    void getAllBatchesByColorUIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorU is not null
        defaultBatchesShouldBeFound("colorU.specified=true");

        // Get all the batchesList where colorU is null
        defaultBatchesShouldNotBeFound("colorU.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorUIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorU is greater than or equal to DEFAULT_COLOR_U
        defaultBatchesShouldBeFound("colorU.greaterThanOrEqual=" + DEFAULT_COLOR_U);

        // Get all the batchesList where colorU is greater than or equal to UPDATED_COLOR_U
        defaultBatchesShouldNotBeFound("colorU.greaterThanOrEqual=" + UPDATED_COLOR_U);
    }

    @Test
    @Transactional
    void getAllBatchesByColorUIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorU is less than or equal to DEFAULT_COLOR_U
        defaultBatchesShouldBeFound("colorU.lessThanOrEqual=" + DEFAULT_COLOR_U);

        // Get all the batchesList where colorU is less than or equal to SMALLER_COLOR_U
        defaultBatchesShouldNotBeFound("colorU.lessThanOrEqual=" + SMALLER_COLOR_U);
    }

    @Test
    @Transactional
    void getAllBatchesByColorUIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorU is less than DEFAULT_COLOR_U
        defaultBatchesShouldNotBeFound("colorU.lessThan=" + DEFAULT_COLOR_U);

        // Get all the batchesList where colorU is less than UPDATED_COLOR_U
        defaultBatchesShouldBeFound("colorU.lessThan=" + UPDATED_COLOR_U);
    }

    @Test
    @Transactional
    void getAllBatchesByColorUIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorU is greater than DEFAULT_COLOR_U
        defaultBatchesShouldNotBeFound("colorU.greaterThan=" + DEFAULT_COLOR_U);

        // Get all the batchesList where colorU is greater than SMALLER_COLOR_U
        defaultBatchesShouldBeFound("colorU.greaterThan=" + SMALLER_COLOR_U);
    }

    @Test
    @Transactional
    void getAllBatchesByColorVIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorV equals to DEFAULT_COLOR_V
        defaultBatchesShouldBeFound("colorV.equals=" + DEFAULT_COLOR_V);

        // Get all the batchesList where colorV equals to UPDATED_COLOR_V
        defaultBatchesShouldNotBeFound("colorV.equals=" + UPDATED_COLOR_V);
    }

    @Test
    @Transactional
    void getAllBatchesByColorVIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorV in DEFAULT_COLOR_V or UPDATED_COLOR_V
        defaultBatchesShouldBeFound("colorV.in=" + DEFAULT_COLOR_V + "," + UPDATED_COLOR_V);

        // Get all the batchesList where colorV equals to UPDATED_COLOR_V
        defaultBatchesShouldNotBeFound("colorV.in=" + UPDATED_COLOR_V);
    }

    @Test
    @Transactional
    void getAllBatchesByColorVIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorV is not null
        defaultBatchesShouldBeFound("colorV.specified=true");

        // Get all the batchesList where colorV is null
        defaultBatchesShouldNotBeFound("colorV.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorVIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorV is greater than or equal to DEFAULT_COLOR_V
        defaultBatchesShouldBeFound("colorV.greaterThanOrEqual=" + DEFAULT_COLOR_V);

        // Get all the batchesList where colorV is greater than or equal to UPDATED_COLOR_V
        defaultBatchesShouldNotBeFound("colorV.greaterThanOrEqual=" + UPDATED_COLOR_V);
    }

    @Test
    @Transactional
    void getAllBatchesByColorVIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorV is less than or equal to DEFAULT_COLOR_V
        defaultBatchesShouldBeFound("colorV.lessThanOrEqual=" + DEFAULT_COLOR_V);

        // Get all the batchesList where colorV is less than or equal to SMALLER_COLOR_V
        defaultBatchesShouldNotBeFound("colorV.lessThanOrEqual=" + SMALLER_COLOR_V);
    }

    @Test
    @Transactional
    void getAllBatchesByColorVIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorV is less than DEFAULT_COLOR_V
        defaultBatchesShouldNotBeFound("colorV.lessThan=" + DEFAULT_COLOR_V);

        // Get all the batchesList where colorV is less than UPDATED_COLOR_V
        defaultBatchesShouldBeFound("colorV.lessThan=" + UPDATED_COLOR_V);
    }

    @Test
    @Transactional
    void getAllBatchesByColorVIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorV is greater than DEFAULT_COLOR_V
        defaultBatchesShouldNotBeFound("colorV.greaterThan=" + DEFAULT_COLOR_V);

        // Get all the batchesList where colorV is greater than SMALLER_COLOR_V
        defaultBatchesShouldBeFound("colorV.greaterThan=" + SMALLER_COLOR_V);
    }

    @Test
    @Transactional
    void getAllBatchesByColorWIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorW equals to DEFAULT_COLOR_W
        defaultBatchesShouldBeFound("colorW.equals=" + DEFAULT_COLOR_W);

        // Get all the batchesList where colorW equals to UPDATED_COLOR_W
        defaultBatchesShouldNotBeFound("colorW.equals=" + UPDATED_COLOR_W);
    }

    @Test
    @Transactional
    void getAllBatchesByColorWIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorW in DEFAULT_COLOR_W or UPDATED_COLOR_W
        defaultBatchesShouldBeFound("colorW.in=" + DEFAULT_COLOR_W + "," + UPDATED_COLOR_W);

        // Get all the batchesList where colorW equals to UPDATED_COLOR_W
        defaultBatchesShouldNotBeFound("colorW.in=" + UPDATED_COLOR_W);
    }

    @Test
    @Transactional
    void getAllBatchesByColorWIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorW is not null
        defaultBatchesShouldBeFound("colorW.specified=true");

        // Get all the batchesList where colorW is null
        defaultBatchesShouldNotBeFound("colorW.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorWIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorW is greater than or equal to DEFAULT_COLOR_W
        defaultBatchesShouldBeFound("colorW.greaterThanOrEqual=" + DEFAULT_COLOR_W);

        // Get all the batchesList where colorW is greater than or equal to UPDATED_COLOR_W
        defaultBatchesShouldNotBeFound("colorW.greaterThanOrEqual=" + UPDATED_COLOR_W);
    }

    @Test
    @Transactional
    void getAllBatchesByColorWIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorW is less than or equal to DEFAULT_COLOR_W
        defaultBatchesShouldBeFound("colorW.lessThanOrEqual=" + DEFAULT_COLOR_W);

        // Get all the batchesList where colorW is less than or equal to SMALLER_COLOR_W
        defaultBatchesShouldNotBeFound("colorW.lessThanOrEqual=" + SMALLER_COLOR_W);
    }

    @Test
    @Transactional
    void getAllBatchesByColorWIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorW is less than DEFAULT_COLOR_W
        defaultBatchesShouldNotBeFound("colorW.lessThan=" + DEFAULT_COLOR_W);

        // Get all the batchesList where colorW is less than UPDATED_COLOR_W
        defaultBatchesShouldBeFound("colorW.lessThan=" + UPDATED_COLOR_W);
    }

    @Test
    @Transactional
    void getAllBatchesByColorWIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorW is greater than DEFAULT_COLOR_W
        defaultBatchesShouldNotBeFound("colorW.greaterThan=" + DEFAULT_COLOR_W);

        // Get all the batchesList where colorW is greater than SMALLER_COLOR_W
        defaultBatchesShouldBeFound("colorW.greaterThan=" + SMALLER_COLOR_W);
    }

    @Test
    @Transactional
    void getAllBatchesByColorXIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorX equals to DEFAULT_COLOR_X
        defaultBatchesShouldBeFound("colorX.equals=" + DEFAULT_COLOR_X);

        // Get all the batchesList where colorX equals to UPDATED_COLOR_X
        defaultBatchesShouldNotBeFound("colorX.equals=" + UPDATED_COLOR_X);
    }

    @Test
    @Transactional
    void getAllBatchesByColorXIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorX in DEFAULT_COLOR_X or UPDATED_COLOR_X
        defaultBatchesShouldBeFound("colorX.in=" + DEFAULT_COLOR_X + "," + UPDATED_COLOR_X);

        // Get all the batchesList where colorX equals to UPDATED_COLOR_X
        defaultBatchesShouldNotBeFound("colorX.in=" + UPDATED_COLOR_X);
    }

    @Test
    @Transactional
    void getAllBatchesByColorXIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorX is not null
        defaultBatchesShouldBeFound("colorX.specified=true");

        // Get all the batchesList where colorX is null
        defaultBatchesShouldNotBeFound("colorX.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorX is greater than or equal to DEFAULT_COLOR_X
        defaultBatchesShouldBeFound("colorX.greaterThanOrEqual=" + DEFAULT_COLOR_X);

        // Get all the batchesList where colorX is greater than or equal to UPDATED_COLOR_X
        defaultBatchesShouldNotBeFound("colorX.greaterThanOrEqual=" + UPDATED_COLOR_X);
    }

    @Test
    @Transactional
    void getAllBatchesByColorXIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorX is less than or equal to DEFAULT_COLOR_X
        defaultBatchesShouldBeFound("colorX.lessThanOrEqual=" + DEFAULT_COLOR_X);

        // Get all the batchesList where colorX is less than or equal to SMALLER_COLOR_X
        defaultBatchesShouldNotBeFound("colorX.lessThanOrEqual=" + SMALLER_COLOR_X);
    }

    @Test
    @Transactional
    void getAllBatchesByColorXIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorX is less than DEFAULT_COLOR_X
        defaultBatchesShouldNotBeFound("colorX.lessThan=" + DEFAULT_COLOR_X);

        // Get all the batchesList where colorX is less than UPDATED_COLOR_X
        defaultBatchesShouldBeFound("colorX.lessThan=" + UPDATED_COLOR_X);
    }

    @Test
    @Transactional
    void getAllBatchesByColorXIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorX is greater than DEFAULT_COLOR_X
        defaultBatchesShouldNotBeFound("colorX.greaterThan=" + DEFAULT_COLOR_X);

        // Get all the batchesList where colorX is greater than SMALLER_COLOR_X
        defaultBatchesShouldBeFound("colorX.greaterThan=" + SMALLER_COLOR_X);
    }

    @Test
    @Transactional
    void getAllBatchesByColorYIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorY equals to DEFAULT_COLOR_Y
        defaultBatchesShouldBeFound("colorY.equals=" + DEFAULT_COLOR_Y);

        // Get all the batchesList where colorY equals to UPDATED_COLOR_Y
        defaultBatchesShouldNotBeFound("colorY.equals=" + UPDATED_COLOR_Y);
    }

    @Test
    @Transactional
    void getAllBatchesByColorYIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorY in DEFAULT_COLOR_Y or UPDATED_COLOR_Y
        defaultBatchesShouldBeFound("colorY.in=" + DEFAULT_COLOR_Y + "," + UPDATED_COLOR_Y);

        // Get all the batchesList where colorY equals to UPDATED_COLOR_Y
        defaultBatchesShouldNotBeFound("colorY.in=" + UPDATED_COLOR_Y);
    }

    @Test
    @Transactional
    void getAllBatchesByColorYIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorY is not null
        defaultBatchesShouldBeFound("colorY.specified=true");

        // Get all the batchesList where colorY is null
        defaultBatchesShouldNotBeFound("colorY.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorY is greater than or equal to DEFAULT_COLOR_Y
        defaultBatchesShouldBeFound("colorY.greaterThanOrEqual=" + DEFAULT_COLOR_Y);

        // Get all the batchesList where colorY is greater than or equal to UPDATED_COLOR_Y
        defaultBatchesShouldNotBeFound("colorY.greaterThanOrEqual=" + UPDATED_COLOR_Y);
    }

    @Test
    @Transactional
    void getAllBatchesByColorYIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorY is less than or equal to DEFAULT_COLOR_Y
        defaultBatchesShouldBeFound("colorY.lessThanOrEqual=" + DEFAULT_COLOR_Y);

        // Get all the batchesList where colorY is less than or equal to SMALLER_COLOR_Y
        defaultBatchesShouldNotBeFound("colorY.lessThanOrEqual=" + SMALLER_COLOR_Y);
    }

    @Test
    @Transactional
    void getAllBatchesByColorYIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorY is less than DEFAULT_COLOR_Y
        defaultBatchesShouldNotBeFound("colorY.lessThan=" + DEFAULT_COLOR_Y);

        // Get all the batchesList where colorY is less than UPDATED_COLOR_Y
        defaultBatchesShouldBeFound("colorY.lessThan=" + UPDATED_COLOR_Y);
    }

    @Test
    @Transactional
    void getAllBatchesByColorYIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorY is greater than DEFAULT_COLOR_Y
        defaultBatchesShouldNotBeFound("colorY.greaterThan=" + DEFAULT_COLOR_Y);

        // Get all the batchesList where colorY is greater than SMALLER_COLOR_Y
        defaultBatchesShouldBeFound("colorY.greaterThan=" + SMALLER_COLOR_Y);
    }

    @Test
    @Transactional
    void getAllBatchesByColorZIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorZ equals to DEFAULT_COLOR_Z
        defaultBatchesShouldBeFound("colorZ.equals=" + DEFAULT_COLOR_Z);

        // Get all the batchesList where colorZ equals to UPDATED_COLOR_Z
        defaultBatchesShouldNotBeFound("colorZ.equals=" + UPDATED_COLOR_Z);
    }

    @Test
    @Transactional
    void getAllBatchesByColorZIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorZ in DEFAULT_COLOR_Z or UPDATED_COLOR_Z
        defaultBatchesShouldBeFound("colorZ.in=" + DEFAULT_COLOR_Z + "," + UPDATED_COLOR_Z);

        // Get all the batchesList where colorZ equals to UPDATED_COLOR_Z
        defaultBatchesShouldNotBeFound("colorZ.in=" + UPDATED_COLOR_Z);
    }

    @Test
    @Transactional
    void getAllBatchesByColorZIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorZ is not null
        defaultBatchesShouldBeFound("colorZ.specified=true");

        // Get all the batchesList where colorZ is null
        defaultBatchesShouldNotBeFound("colorZ.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorZIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorZ is greater than or equal to DEFAULT_COLOR_Z
        defaultBatchesShouldBeFound("colorZ.greaterThanOrEqual=" + DEFAULT_COLOR_Z);

        // Get all the batchesList where colorZ is greater than or equal to UPDATED_COLOR_Z
        defaultBatchesShouldNotBeFound("colorZ.greaterThanOrEqual=" + UPDATED_COLOR_Z);
    }

    @Test
    @Transactional
    void getAllBatchesByColorZIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorZ is less than or equal to DEFAULT_COLOR_Z
        defaultBatchesShouldBeFound("colorZ.lessThanOrEqual=" + DEFAULT_COLOR_Z);

        // Get all the batchesList where colorZ is less than or equal to SMALLER_COLOR_Z
        defaultBatchesShouldNotBeFound("colorZ.lessThanOrEqual=" + SMALLER_COLOR_Z);
    }

    @Test
    @Transactional
    void getAllBatchesByColorZIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorZ is less than DEFAULT_COLOR_Z
        defaultBatchesShouldNotBeFound("colorZ.lessThan=" + DEFAULT_COLOR_Z);

        // Get all the batchesList where colorZ is less than UPDATED_COLOR_Z
        defaultBatchesShouldBeFound("colorZ.lessThan=" + UPDATED_COLOR_Z);
    }

    @Test
    @Transactional
    void getAllBatchesByColorZIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where colorZ is greater than DEFAULT_COLOR_Z
        defaultBatchesShouldNotBeFound("colorZ.greaterThan=" + DEFAULT_COLOR_Z);

        // Get all the batchesList where colorZ is greater than SMALLER_COLOR_Z
        defaultBatchesShouldBeFound("colorZ.greaterThan=" + SMALLER_COLOR_Z);
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogIdIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogId equals to DEFAULT_CATALOG_ID
        defaultBatchesShouldBeFound("catalogId.equals=" + DEFAULT_CATALOG_ID);

        // Get all the batchesList where catalogId equals to UPDATED_CATALOG_ID
        defaultBatchesShouldNotBeFound("catalogId.equals=" + UPDATED_CATALOG_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogIdIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogId in DEFAULT_CATALOG_ID or UPDATED_CATALOG_ID
        defaultBatchesShouldBeFound("catalogId.in=" + DEFAULT_CATALOG_ID + "," + UPDATED_CATALOG_ID);

        // Get all the batchesList where catalogId equals to UPDATED_CATALOG_ID
        defaultBatchesShouldNotBeFound("catalogId.in=" + UPDATED_CATALOG_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogId is not null
        defaultBatchesShouldBeFound("catalogId.specified=true");

        // Get all the batchesList where catalogId is null
        defaultBatchesShouldNotBeFound("catalogId.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogIdContainsSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogId contains DEFAULT_CATALOG_ID
        defaultBatchesShouldBeFound("catalogId.contains=" + DEFAULT_CATALOG_ID);

        // Get all the batchesList where catalogId contains UPDATED_CATALOG_ID
        defaultBatchesShouldNotBeFound("catalogId.contains=" + UPDATED_CATALOG_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogIdNotContainsSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogId does not contain DEFAULT_CATALOG_ID
        defaultBatchesShouldNotBeFound("catalogId.doesNotContain=" + DEFAULT_CATALOG_ID);

        // Get all the batchesList where catalogId does not contain UPDATED_CATALOG_ID
        defaultBatchesShouldBeFound("catalogId.doesNotContain=" + UPDATED_CATALOG_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogIsactiveIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogIsactive equals to DEFAULT_CATALOG_ISACTIVE
        defaultBatchesShouldBeFound("catalogIsactive.equals=" + DEFAULT_CATALOG_ISACTIVE);

        // Get all the batchesList where catalogIsactive equals to UPDATED_CATALOG_ISACTIVE
        defaultBatchesShouldNotBeFound("catalogIsactive.equals=" + UPDATED_CATALOG_ISACTIVE);
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogIsactiveIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogIsactive in DEFAULT_CATALOG_ISACTIVE or UPDATED_CATALOG_ISACTIVE
        defaultBatchesShouldBeFound("catalogIsactive.in=" + DEFAULT_CATALOG_ISACTIVE + "," + UPDATED_CATALOG_ISACTIVE);

        // Get all the batchesList where catalogIsactive equals to UPDATED_CATALOG_ISACTIVE
        defaultBatchesShouldNotBeFound("catalogIsactive.in=" + UPDATED_CATALOG_ISACTIVE);
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogIsactiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogIsactive is not null
        defaultBatchesShouldBeFound("catalogIsactive.specified=true");

        // Get all the batchesList where catalogIsactive is null
        defaultBatchesShouldNotBeFound("catalogIsactive.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogCreatedtimeIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogCreatedtime equals to DEFAULT_CATALOG_CREATEDTIME
        defaultBatchesShouldBeFound("catalogCreatedtime.equals=" + DEFAULT_CATALOG_CREATEDTIME);

        // Get all the batchesList where catalogCreatedtime equals to UPDATED_CATALOG_CREATEDTIME
        defaultBatchesShouldNotBeFound("catalogCreatedtime.equals=" + UPDATED_CATALOG_CREATEDTIME);
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogCreatedtimeIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogCreatedtime in DEFAULT_CATALOG_CREATEDTIME or UPDATED_CATALOG_CREATEDTIME
        defaultBatchesShouldBeFound("catalogCreatedtime.in=" + DEFAULT_CATALOG_CREATEDTIME + "," + UPDATED_CATALOG_CREATEDTIME);

        // Get all the batchesList where catalogCreatedtime equals to UPDATED_CATALOG_CREATEDTIME
        defaultBatchesShouldNotBeFound("catalogCreatedtime.in=" + UPDATED_CATALOG_CREATEDTIME);
    }

    @Test
    @Transactional
    void getAllBatchesByCatalogCreatedtimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where catalogCreatedtime is not null
        defaultBatchesShouldBeFound("catalogCreatedtime.specified=true");

        // Get all the batchesList where catalogCreatedtime is null
        defaultBatchesShouldNotBeFound("catalogCreatedtime.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByBaseMaterialIdIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where baseMaterialId equals to DEFAULT_BASE_MATERIAL_ID
        defaultBatchesShouldBeFound("baseMaterialId.equals=" + DEFAULT_BASE_MATERIAL_ID);

        // Get all the batchesList where baseMaterialId equals to UPDATED_BASE_MATERIAL_ID
        defaultBatchesShouldNotBeFound("baseMaterialId.equals=" + UPDATED_BASE_MATERIAL_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByBaseMaterialIdIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where baseMaterialId in DEFAULT_BASE_MATERIAL_ID or UPDATED_BASE_MATERIAL_ID
        defaultBatchesShouldBeFound("baseMaterialId.in=" + DEFAULT_BASE_MATERIAL_ID + "," + UPDATED_BASE_MATERIAL_ID);

        // Get all the batchesList where baseMaterialId equals to UPDATED_BASE_MATERIAL_ID
        defaultBatchesShouldNotBeFound("baseMaterialId.in=" + UPDATED_BASE_MATERIAL_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByBaseMaterialIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where baseMaterialId is not null
        defaultBatchesShouldBeFound("baseMaterialId.specified=true");

        // Get all the batchesList where baseMaterialId is null
        defaultBatchesShouldNotBeFound("baseMaterialId.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByBaseMaterialIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where baseMaterialId is greater than or equal to DEFAULT_BASE_MATERIAL_ID
        defaultBatchesShouldBeFound("baseMaterialId.greaterThanOrEqual=" + DEFAULT_BASE_MATERIAL_ID);

        // Get all the batchesList where baseMaterialId is greater than or equal to UPDATED_BASE_MATERIAL_ID
        defaultBatchesShouldNotBeFound("baseMaterialId.greaterThanOrEqual=" + UPDATED_BASE_MATERIAL_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByBaseMaterialIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where baseMaterialId is less than or equal to DEFAULT_BASE_MATERIAL_ID
        defaultBatchesShouldBeFound("baseMaterialId.lessThanOrEqual=" + DEFAULT_BASE_MATERIAL_ID);

        // Get all the batchesList where baseMaterialId is less than or equal to SMALLER_BASE_MATERIAL_ID
        defaultBatchesShouldNotBeFound("baseMaterialId.lessThanOrEqual=" + SMALLER_BASE_MATERIAL_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByBaseMaterialIdIsLessThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where baseMaterialId is less than DEFAULT_BASE_MATERIAL_ID
        defaultBatchesShouldNotBeFound("baseMaterialId.lessThan=" + DEFAULT_BASE_MATERIAL_ID);

        // Get all the batchesList where baseMaterialId is less than UPDATED_BASE_MATERIAL_ID
        defaultBatchesShouldBeFound("baseMaterialId.lessThan=" + UPDATED_BASE_MATERIAL_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByBaseMaterialIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where baseMaterialId is greater than DEFAULT_BASE_MATERIAL_ID
        defaultBatchesShouldNotBeFound("baseMaterialId.greaterThan=" + DEFAULT_BASE_MATERIAL_ID);

        // Get all the batchesList where baseMaterialId is greater than SMALLER_BASE_MATERIAL_ID
        defaultBatchesShouldBeFound("baseMaterialId.greaterThan=" + SMALLER_BASE_MATERIAL_ID);
    }

    @Test
    @Transactional
    void getAllBatchesByOrderedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where orderedTime equals to DEFAULT_ORDERED_TIME
        defaultBatchesShouldBeFound("orderedTime.equals=" + DEFAULT_ORDERED_TIME);

        // Get all the batchesList where orderedTime equals to UPDATED_ORDERED_TIME
        defaultBatchesShouldNotBeFound("orderedTime.equals=" + UPDATED_ORDERED_TIME);
    }

    @Test
    @Transactional
    void getAllBatchesByOrderedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where orderedTime in DEFAULT_ORDERED_TIME or UPDATED_ORDERED_TIME
        defaultBatchesShouldBeFound("orderedTime.in=" + DEFAULT_ORDERED_TIME + "," + UPDATED_ORDERED_TIME);

        // Get all the batchesList where orderedTime equals to UPDATED_ORDERED_TIME
        defaultBatchesShouldNotBeFound("orderedTime.in=" + UPDATED_ORDERED_TIME);
    }

    @Test
    @Transactional
    void getAllBatchesByOrderedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where orderedTime is not null
        defaultBatchesShouldBeFound("orderedTime.specified=true");

        // Get all the batchesList where orderedTime is null
        defaultBatchesShouldNotBeFound("orderedTime.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByStartedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where startedTime equals to DEFAULT_STARTED_TIME
        defaultBatchesShouldBeFound("startedTime.equals=" + DEFAULT_STARTED_TIME);

        // Get all the batchesList where startedTime equals to UPDATED_STARTED_TIME
        defaultBatchesShouldNotBeFound("startedTime.equals=" + UPDATED_STARTED_TIME);
    }

    @Test
    @Transactional
    void getAllBatchesByStartedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where startedTime in DEFAULT_STARTED_TIME or UPDATED_STARTED_TIME
        defaultBatchesShouldBeFound("startedTime.in=" + DEFAULT_STARTED_TIME + "," + UPDATED_STARTED_TIME);

        // Get all the batchesList where startedTime equals to UPDATED_STARTED_TIME
        defaultBatchesShouldNotBeFound("startedTime.in=" + UPDATED_STARTED_TIME);
    }

    @Test
    @Transactional
    void getAllBatchesByStartedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where startedTime is not null
        defaultBatchesShouldBeFound("startedTime.specified=true");

        // Get all the batchesList where startedTime is null
        defaultBatchesShouldNotBeFound("startedTime.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByModifiedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where modifiedTime equals to DEFAULT_MODIFIED_TIME
        defaultBatchesShouldBeFound("modifiedTime.equals=" + DEFAULT_MODIFIED_TIME);

        // Get all the batchesList where modifiedTime equals to UPDATED_MODIFIED_TIME
        defaultBatchesShouldNotBeFound("modifiedTime.equals=" + UPDATED_MODIFIED_TIME);
    }

    @Test
    @Transactional
    void getAllBatchesByModifiedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where modifiedTime in DEFAULT_MODIFIED_TIME or UPDATED_MODIFIED_TIME
        defaultBatchesShouldBeFound("modifiedTime.in=" + DEFAULT_MODIFIED_TIME + "," + UPDATED_MODIFIED_TIME);

        // Get all the batchesList where modifiedTime equals to UPDATED_MODIFIED_TIME
        defaultBatchesShouldNotBeFound("modifiedTime.in=" + UPDATED_MODIFIED_TIME);
    }

    @Test
    @Transactional
    void getAllBatchesByModifiedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where modifiedTime is not null
        defaultBatchesShouldBeFound("modifiedTime.specified=true");

        // Get all the batchesList where modifiedTime is null
        defaultBatchesShouldNotBeFound("modifiedTime.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesBySuspendedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where suspendedTime equals to DEFAULT_SUSPENDED_TIME
        defaultBatchesShouldBeFound("suspendedTime.equals=" + DEFAULT_SUSPENDED_TIME);

        // Get all the batchesList where suspendedTime equals to UPDATED_SUSPENDED_TIME
        defaultBatchesShouldNotBeFound("suspendedTime.equals=" + UPDATED_SUSPENDED_TIME);
    }

    @Test
    @Transactional
    void getAllBatchesBySuspendedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where suspendedTime in DEFAULT_SUSPENDED_TIME or UPDATED_SUSPENDED_TIME
        defaultBatchesShouldBeFound("suspendedTime.in=" + DEFAULT_SUSPENDED_TIME + "," + UPDATED_SUSPENDED_TIME);

        // Get all the batchesList where suspendedTime equals to UPDATED_SUSPENDED_TIME
        defaultBatchesShouldNotBeFound("suspendedTime.in=" + UPDATED_SUSPENDED_TIME);
    }

    @Test
    @Transactional
    void getAllBatchesBySuspendedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where suspendedTime is not null
        defaultBatchesShouldBeFound("suspendedTime.specified=true");

        // Get all the batchesList where suspendedTime is null
        defaultBatchesShouldNotBeFound("suspendedTime.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByFinishedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where finishedTime equals to DEFAULT_FINISHED_TIME
        defaultBatchesShouldBeFound("finishedTime.equals=" + DEFAULT_FINISHED_TIME);

        // Get all the batchesList where finishedTime equals to UPDATED_FINISHED_TIME
        defaultBatchesShouldNotBeFound("finishedTime.equals=" + UPDATED_FINISHED_TIME);
    }

    @Test
    @Transactional
    void getAllBatchesByFinishedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where finishedTime in DEFAULT_FINISHED_TIME or UPDATED_FINISHED_TIME
        defaultBatchesShouldBeFound("finishedTime.in=" + DEFAULT_FINISHED_TIME + "," + UPDATED_FINISHED_TIME);

        // Get all the batchesList where finishedTime equals to UPDATED_FINISHED_TIME
        defaultBatchesShouldNotBeFound("finishedTime.in=" + UPDATED_FINISHED_TIME);
    }

    @Test
    @Transactional
    void getAllBatchesByFinishedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        batchesRepository.saveAndFlush(batches);

        // Get all the batchesList where finishedTime is not null
        defaultBatchesShouldBeFound("finishedTime.specified=true");

        // Get all the batchesList where finishedTime is null
        defaultBatchesShouldNotBeFound("finishedTime.specified=false");
    }

    @Test
    @Transactional
    void getAllBatchesByColorCatalogIsEqualToSomething() throws Exception {
        Catalogs colorCatalog;
        if (TestUtil.findAll(em, Catalogs.class).isEmpty()) {
            batchesRepository.saveAndFlush(batches);
            colorCatalog = CatalogsResourceIT.createEntity(em);
        } else {
            colorCatalog = TestUtil.findAll(em, Catalogs.class).get(0);
        }
        em.persist(colorCatalog);
        em.flush();
        batches.setColorCatalog(colorCatalog);
        batchesRepository.saveAndFlush(batches);
        String colorCatalogId = colorCatalog.getId();
        // Get all the batchesList where colorCatalog equals to colorCatalogId
        defaultBatchesShouldBeFound("colorCatalogId.equals=" + colorCatalogId);

        // Get all the batchesList where colorCatalog equals to "invalid-id"
        defaultBatchesShouldNotBeFound("colorCatalogId.equals=" + "invalid-id");
    }

    @Test
    @Transactional
    void getAllBatchesByIdIsEqualToSomething() throws Exception {
        Scans id;
        if (TestUtil.findAll(em, Scans.class).isEmpty()) {
            batchesRepository.saveAndFlush(batches);
            id = ScansResourceIT.createEntity(em);
        } else {
            id = TestUtil.findAll(em, Scans.class).get(0);
        }
        em.persist(id);
        em.flush();
        batches.addId(id);
        batchesRepository.saveAndFlush(batches);
        String idId = id.getId();
        // Get all the batchesList where id equals to idId
        defaultBatchesShouldBeFound("idId.equals=" + idId);

        // Get all the batchesList where id equals to "invalid-id"
        defaultBatchesShouldNotBeFound("idId.equals=" + "invalid-id");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBatchesShouldBeFound(String filter) throws Exception {
        restBatchesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batches.getId())))
            .andExpect(jsonPath("$.[*].poName").value(hasItem(DEFAULT_PO_NAME)))
            .andExpect(jsonPath("$.[*].sequenceInPo").value(hasItem(DEFAULT_SEQUENCE_IN_PO)))
            .andExpect(jsonPath("$.[*].scannerId").value(hasItem(DEFAULT_SCANNER_ID.toString())))
            .andExpect(jsonPath("$.[*].previousProductionBatchId").value(hasItem(DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].inspectionSequence").value(hasItem(DEFAULT_INSPECTION_SEQUENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].orderedQuantity").value(hasItem(DEFAULT_ORDERED_QUANTITY)))
            .andExpect(jsonPath("$.[*].producingQuantity").value(hasItem(DEFAULT_PRODUCING_QUANTITY)))
            .andExpect(jsonPath("$.[*].totalProducingQuantity").value(hasItem(DEFAULT_TOTAL_PRODUCING_QUANTITY)))
            .andExpect(jsonPath("$.[*].remainingQuantity").value(hasItem(DEFAULT_REMAINING_QUANTITY)))
            .andExpect(jsonPath("$.[*].totalremainingQuantity").value(hasItem(DEFAULT_TOTALREMAINING_QUANTITY)))
            .andExpect(jsonPath("$.[*].inspectedQuantity").value(hasItem(DEFAULT_INSPECTED_QUANTITY)))
            .andExpect(jsonPath("$.[*].totalInspectedQuantity").value(hasItem(DEFAULT_TOTAL_INSPECTED_QUANTITY)))
            .andExpect(jsonPath("$.[*].failedQuantity").value(hasItem(DEFAULT_FAILED_QUANTITY)))
            .andExpect(jsonPath("$.[*].totalFailedQuantity").value(hasItem(DEFAULT_TOTAL_FAILED_QUANTITY)))
            .andExpect(jsonPath("$.[*].colorId").value(hasItem(DEFAULT_COLOR_ID)))
            .andExpect(jsonPath("$.[*].colorCode").value(hasItem(DEFAULT_COLOR_CODE.toString())))
            .andExpect(jsonPath("$.[*].colorName").value(hasItem(DEFAULT_COLOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].colorBasematerial").value(hasItem(DEFAULT_COLOR_BASEMATERIAL.intValue())))
            .andExpect(jsonPath("$.[*].colorLabL").value(hasItem(DEFAULT_COLOR_LAB_L.doubleValue())))
            .andExpect(jsonPath("$.[*].colorLabA").value(hasItem(DEFAULT_COLOR_LAB_A.doubleValue())))
            .andExpect(jsonPath("$.[*].colorLabB").value(hasItem(DEFAULT_COLOR_LAB_B.doubleValue())))
            .andExpect(jsonPath("$.[*].colorA").value(hasItem(DEFAULT_COLOR_A.doubleValue())))
            .andExpect(jsonPath("$.[*].colorB").value(hasItem(DEFAULT_COLOR_B.doubleValue())))
            .andExpect(jsonPath("$.[*].colorC").value(hasItem(DEFAULT_COLOR_C.doubleValue())))
            .andExpect(jsonPath("$.[*].colorD").value(hasItem(DEFAULT_COLOR_D.doubleValue())))
            .andExpect(jsonPath("$.[*].colorE").value(hasItem(DEFAULT_COLOR_E.doubleValue())))
            .andExpect(jsonPath("$.[*].colorF").value(hasItem(DEFAULT_COLOR_F.doubleValue())))
            .andExpect(jsonPath("$.[*].colorG").value(hasItem(DEFAULT_COLOR_G.doubleValue())))
            .andExpect(jsonPath("$.[*].colorH").value(hasItem(DEFAULT_COLOR_H.doubleValue())))
            .andExpect(jsonPath("$.[*].colorI").value(hasItem(DEFAULT_COLOR_I.doubleValue())))
            .andExpect(jsonPath("$.[*].colorJ").value(hasItem(DEFAULT_COLOR_J.doubleValue())))
            .andExpect(jsonPath("$.[*].colorK").value(hasItem(DEFAULT_COLOR_K.doubleValue())))
            .andExpect(jsonPath("$.[*].colorL").value(hasItem(DEFAULT_COLOR_L.doubleValue())))
            .andExpect(jsonPath("$.[*].colorM").value(hasItem(DEFAULT_COLOR_M.doubleValue())))
            .andExpect(jsonPath("$.[*].colorN").value(hasItem(DEFAULT_COLOR_N.doubleValue())))
            .andExpect(jsonPath("$.[*].colorO").value(hasItem(DEFAULT_COLOR_O.doubleValue())))
            .andExpect(jsonPath("$.[*].colorP").value(hasItem(DEFAULT_COLOR_P.doubleValue())))
            .andExpect(jsonPath("$.[*].colorQ").value(hasItem(DEFAULT_COLOR_Q.doubleValue())))
            .andExpect(jsonPath("$.[*].colorR").value(hasItem(DEFAULT_COLOR_R.doubleValue())))
            .andExpect(jsonPath("$.[*].colorS").value(hasItem(DEFAULT_COLOR_S.doubleValue())))
            .andExpect(jsonPath("$.[*].colorT").value(hasItem(DEFAULT_COLOR_T.doubleValue())))
            .andExpect(jsonPath("$.[*].colorU").value(hasItem(DEFAULT_COLOR_U.doubleValue())))
            .andExpect(jsonPath("$.[*].colorV").value(hasItem(DEFAULT_COLOR_V.doubleValue())))
            .andExpect(jsonPath("$.[*].colorW").value(hasItem(DEFAULT_COLOR_W.doubleValue())))
            .andExpect(jsonPath("$.[*].colorX").value(hasItem(DEFAULT_COLOR_X.doubleValue())))
            .andExpect(jsonPath("$.[*].colorY").value(hasItem(DEFAULT_COLOR_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].colorZ").value(hasItem(DEFAULT_COLOR_Z.doubleValue())))
            .andExpect(jsonPath("$.[*].catalogId").value(hasItem(DEFAULT_CATALOG_ID)))
            .andExpect(jsonPath("$.[*].catalogExternalid").value(hasItem(DEFAULT_CATALOG_EXTERNALID.toString())))
            .andExpect(jsonPath("$.[*].catalogName").value(hasItem(DEFAULT_CATALOG_NAME.toString())))
            .andExpect(jsonPath("$.[*].catalogVersion").value(hasItem(DEFAULT_CATALOG_VERSION.toString())))
            .andExpect(jsonPath("$.[*].catalogIsactive").value(hasItem(DEFAULT_CATALOG_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].catalogCreatedtime").value(hasItem(DEFAULT_CATALOG_CREATEDTIME.toString())))
            .andExpect(jsonPath("$.[*].baseMaterialId").value(hasItem(DEFAULT_BASE_MATERIAL_ID.intValue())))
            .andExpect(jsonPath("$.[*].orderedTime").value(hasItem(DEFAULT_ORDERED_TIME.toString())))
            .andExpect(jsonPath("$.[*].startedTime").value(hasItem(DEFAULT_STARTED_TIME.toString())))
            .andExpect(jsonPath("$.[*].modifiedTime").value(hasItem(DEFAULT_MODIFIED_TIME.toString())))
            .andExpect(jsonPath("$.[*].suspendedTime").value(hasItem(DEFAULT_SUSPENDED_TIME.toString())))
            .andExpect(jsonPath("$.[*].finishedTime").value(hasItem(DEFAULT_FINISHED_TIME.toString())))
            .andExpect(jsonPath("$.[*].original").value(hasItem(DEFAULT_ORIGINAL.toString())));

        // Check, that the count call also returns 1
        restBatchesMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBatchesShouldNotBeFound(String filter) throws Exception {
        restBatchesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBatchesMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBatches() throws Exception {
        // Get the batches
        restBatchesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBatches() throws Exception {
        // Initialize the database
        batches.setId(UUID.randomUUID().toString());
        batchesRepository.saveAndFlush(batches);

        int databaseSizeBeforeUpdate = batchesRepository.findAll().size();

        // Update the batches
        Batches updatedBatches = batchesRepository.findById(batches.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBatches are not directly saved in db
        em.detach(updatedBatches);
        updatedBatches
            .poName(UPDATED_PO_NAME)
            .sequenceInPo(UPDATED_SEQUENCE_IN_PO)
            .scannerId(UPDATED_SCANNER_ID)
            .previousProductionBatchId(UPDATED_PREVIOUS_PRODUCTION_BATCH_ID)
            .state(UPDATED_STATE)
            .inspectionSequence(UPDATED_INSPECTION_SEQUENCE)
            .orderedQuantity(UPDATED_ORDERED_QUANTITY)
            .producingQuantity(UPDATED_PRODUCING_QUANTITY)
            .totalProducingQuantity(UPDATED_TOTAL_PRODUCING_QUANTITY)
            .remainingQuantity(UPDATED_REMAINING_QUANTITY)
            .totalremainingQuantity(UPDATED_TOTALREMAINING_QUANTITY)
            .inspectedQuantity(UPDATED_INSPECTED_QUANTITY)
            .totalInspectedQuantity(UPDATED_TOTAL_INSPECTED_QUANTITY)
            .failedQuantity(UPDATED_FAILED_QUANTITY)
            .totalFailedQuantity(UPDATED_TOTAL_FAILED_QUANTITY)
            .colorId(UPDATED_COLOR_ID)
            .colorCode(UPDATED_COLOR_CODE)
            .colorName(UPDATED_COLOR_NAME)
            .colorBasematerial(UPDATED_COLOR_BASEMATERIAL)
            .colorLabL(UPDATED_COLOR_LAB_L)
            .colorLabA(UPDATED_COLOR_LAB_A)
            .colorLabB(UPDATED_COLOR_LAB_B)
            .colorA(UPDATED_COLOR_A)
            .colorB(UPDATED_COLOR_B)
            .colorC(UPDATED_COLOR_C)
            .colorD(UPDATED_COLOR_D)
            .colorE(UPDATED_COLOR_E)
            .colorF(UPDATED_COLOR_F)
            .colorG(UPDATED_COLOR_G)
            .colorH(UPDATED_COLOR_H)
            .colorI(UPDATED_COLOR_I)
            .colorJ(UPDATED_COLOR_J)
            .colorK(UPDATED_COLOR_K)
            .colorL(UPDATED_COLOR_L)
            .colorM(UPDATED_COLOR_M)
            .colorN(UPDATED_COLOR_N)
            .colorO(UPDATED_COLOR_O)
            .colorP(UPDATED_COLOR_P)
            .colorQ(UPDATED_COLOR_Q)
            .colorR(UPDATED_COLOR_R)
            .colorS(UPDATED_COLOR_S)
            .colorT(UPDATED_COLOR_T)
            .colorU(UPDATED_COLOR_U)
            .colorV(UPDATED_COLOR_V)
            .colorW(UPDATED_COLOR_W)
            .colorX(UPDATED_COLOR_X)
            .colorY(UPDATED_COLOR_Y)
            .colorZ(UPDATED_COLOR_Z)
            .catalogId(UPDATED_CATALOG_ID)
            .catalogExternalid(UPDATED_CATALOG_EXTERNALID)
            .catalogName(UPDATED_CATALOG_NAME)
            .catalogVersion(UPDATED_CATALOG_VERSION)
            .catalogIsactive(UPDATED_CATALOG_ISACTIVE)
            .catalogCreatedtime(UPDATED_CATALOG_CREATEDTIME)
            .baseMaterialId(UPDATED_BASE_MATERIAL_ID)
            .orderedTime(UPDATED_ORDERED_TIME)
            .startedTime(UPDATED_STARTED_TIME)
            .modifiedTime(UPDATED_MODIFIED_TIME)
            .suspendedTime(UPDATED_SUSPENDED_TIME)
            .finishedTime(UPDATED_FINISHED_TIME)
            .original(UPDATED_ORIGINAL);
        BatchesDTO batchesDTO = batchesMapper.toDto(updatedBatches);

        restBatchesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, batchesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batchesDTO))
            )
            .andExpect(status().isOk());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeUpdate);
        Batches testBatches = batchesList.get(batchesList.size() - 1);
        assertThat(testBatches.getPoName()).isEqualTo(UPDATED_PO_NAME);
        assertThat(testBatches.getSequenceInPo()).isEqualTo(UPDATED_SEQUENCE_IN_PO);
        assertThat(testBatches.getScannerId()).isEqualTo(UPDATED_SCANNER_ID);
        assertThat(testBatches.getPreviousProductionBatchId()).isEqualTo(UPDATED_PREVIOUS_PRODUCTION_BATCH_ID);
        assertThat(testBatches.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testBatches.getInspectionSequence()).isEqualTo(UPDATED_INSPECTION_SEQUENCE);
        assertThat(testBatches.getOrderedQuantity()).isEqualTo(UPDATED_ORDERED_QUANTITY);
        assertThat(testBatches.getProducingQuantity()).isEqualTo(UPDATED_PRODUCING_QUANTITY);
        assertThat(testBatches.getTotalProducingQuantity()).isEqualTo(UPDATED_TOTAL_PRODUCING_QUANTITY);
        assertThat(testBatches.getRemainingQuantity()).isEqualTo(UPDATED_REMAINING_QUANTITY);
        assertThat(testBatches.getTotalremainingQuantity()).isEqualTo(UPDATED_TOTALREMAINING_QUANTITY);
        assertThat(testBatches.getInspectedQuantity()).isEqualTo(UPDATED_INSPECTED_QUANTITY);
        assertThat(testBatches.getTotalInspectedQuantity()).isEqualTo(UPDATED_TOTAL_INSPECTED_QUANTITY);
        assertThat(testBatches.getFailedQuantity()).isEqualTo(UPDATED_FAILED_QUANTITY);
        assertThat(testBatches.getTotalFailedQuantity()).isEqualTo(UPDATED_TOTAL_FAILED_QUANTITY);
        assertThat(testBatches.getColorId()).isEqualTo(UPDATED_COLOR_ID);
        assertThat(testBatches.getColorCode()).isEqualTo(UPDATED_COLOR_CODE);
        assertThat(testBatches.getColorName()).isEqualTo(UPDATED_COLOR_NAME);
        assertThat(testBatches.getColorBasematerial()).isEqualTo(UPDATED_COLOR_BASEMATERIAL);
        assertThat(testBatches.getColorLabL()).isEqualTo(UPDATED_COLOR_LAB_L);
        assertThat(testBatches.getColorLabA()).isEqualTo(UPDATED_COLOR_LAB_A);
        assertThat(testBatches.getColorLabB()).isEqualTo(UPDATED_COLOR_LAB_B);
        assertThat(testBatches.getColorA()).isEqualTo(UPDATED_COLOR_A);
        assertThat(testBatches.getColorB()).isEqualTo(UPDATED_COLOR_B);
        assertThat(testBatches.getColorC()).isEqualTo(UPDATED_COLOR_C);
        assertThat(testBatches.getColorD()).isEqualTo(UPDATED_COLOR_D);
        assertThat(testBatches.getColorE()).isEqualTo(UPDATED_COLOR_E);
        assertThat(testBatches.getColorF()).isEqualTo(UPDATED_COLOR_F);
        assertThat(testBatches.getColorG()).isEqualTo(UPDATED_COLOR_G);
        assertThat(testBatches.getColorH()).isEqualTo(UPDATED_COLOR_H);
        assertThat(testBatches.getColorI()).isEqualTo(UPDATED_COLOR_I);
        assertThat(testBatches.getColorJ()).isEqualTo(UPDATED_COLOR_J);
        assertThat(testBatches.getColorK()).isEqualTo(UPDATED_COLOR_K);
        assertThat(testBatches.getColorL()).isEqualTo(UPDATED_COLOR_L);
        assertThat(testBatches.getColorM()).isEqualTo(UPDATED_COLOR_M);
        assertThat(testBatches.getColorN()).isEqualTo(UPDATED_COLOR_N);
        assertThat(testBatches.getColorO()).isEqualTo(UPDATED_COLOR_O);
        assertThat(testBatches.getColorP()).isEqualTo(UPDATED_COLOR_P);
        assertThat(testBatches.getColorQ()).isEqualTo(UPDATED_COLOR_Q);
        assertThat(testBatches.getColorR()).isEqualTo(UPDATED_COLOR_R);
        assertThat(testBatches.getColorS()).isEqualTo(UPDATED_COLOR_S);
        assertThat(testBatches.getColorT()).isEqualTo(UPDATED_COLOR_T);
        assertThat(testBatches.getColorU()).isEqualTo(UPDATED_COLOR_U);
        assertThat(testBatches.getColorV()).isEqualTo(UPDATED_COLOR_V);
        assertThat(testBatches.getColorW()).isEqualTo(UPDATED_COLOR_W);
        assertThat(testBatches.getColorX()).isEqualTo(UPDATED_COLOR_X);
        assertThat(testBatches.getColorY()).isEqualTo(UPDATED_COLOR_Y);
        assertThat(testBatches.getColorZ()).isEqualTo(UPDATED_COLOR_Z);
        assertThat(testBatches.getCatalogId()).isEqualTo(UPDATED_CATALOG_ID);
        assertThat(testBatches.getCatalogExternalid()).isEqualTo(UPDATED_CATALOG_EXTERNALID);
        assertThat(testBatches.getCatalogName()).isEqualTo(UPDATED_CATALOG_NAME);
        assertThat(testBatches.getCatalogVersion()).isEqualTo(UPDATED_CATALOG_VERSION);
        assertThat(testBatches.getCatalogIsactive()).isEqualTo(UPDATED_CATALOG_ISACTIVE);
        assertThat(testBatches.getCatalogCreatedtime()).isEqualTo(UPDATED_CATALOG_CREATEDTIME);
        assertThat(testBatches.getBaseMaterialId()).isEqualTo(UPDATED_BASE_MATERIAL_ID);
        assertThat(testBatches.getOrderedTime()).isEqualTo(UPDATED_ORDERED_TIME);
        assertThat(testBatches.getStartedTime()).isEqualTo(UPDATED_STARTED_TIME);
        assertThat(testBatches.getModifiedTime()).isEqualTo(UPDATED_MODIFIED_TIME);
        assertThat(testBatches.getSuspendedTime()).isEqualTo(UPDATED_SUSPENDED_TIME);
        assertThat(testBatches.getFinishedTime()).isEqualTo(UPDATED_FINISHED_TIME);
        assertThat(testBatches.getOriginal()).isEqualTo(UPDATED_ORIGINAL);
    }

    @Test
    @Transactional
    void putNonExistingBatches() throws Exception {
        int databaseSizeBeforeUpdate = batchesRepository.findAll().size();
        batches.setId(UUID.randomUUID().toString());

        // Create the Batches
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBatchesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, batchesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batchesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBatches() throws Exception {
        int databaseSizeBeforeUpdate = batchesRepository.findAll().size();
        batches.setId(UUID.randomUUID().toString());

        // Create the Batches
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBatchesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(batchesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBatches() throws Exception {
        int databaseSizeBeforeUpdate = batchesRepository.findAll().size();
        batches.setId(UUID.randomUUID().toString());

        // Create the Batches
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBatchesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(batchesDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBatchesWithPatch() throws Exception {
        // Initialize the database
        batches.setId(UUID.randomUUID().toString());
        batchesRepository.saveAndFlush(batches);

        int databaseSizeBeforeUpdate = batchesRepository.findAll().size();

        // Update the batches using partial update
        Batches partialUpdatedBatches = new Batches();
        partialUpdatedBatches.setId(batches.getId());

        partialUpdatedBatches
            .state(UPDATED_STATE)
            .inspectionSequence(UPDATED_INSPECTION_SEQUENCE)
            .totalremainingQuantity(UPDATED_TOTALREMAINING_QUANTITY)
            .totalInspectedQuantity(UPDATED_TOTAL_INSPECTED_QUANTITY)
            .totalFailedQuantity(UPDATED_TOTAL_FAILED_QUANTITY)
            .colorId(UPDATED_COLOR_ID)
            .colorName(UPDATED_COLOR_NAME)
            .colorBasematerial(UPDATED_COLOR_BASEMATERIAL)
            .colorLabL(UPDATED_COLOR_LAB_L)
            .colorLabB(UPDATED_COLOR_LAB_B)
            .colorA(UPDATED_COLOR_A)
            .colorB(UPDATED_COLOR_B)
            .colorG(UPDATED_COLOR_G)
            .colorJ(UPDATED_COLOR_J)
            .colorL(UPDATED_COLOR_L)
            .colorM(UPDATED_COLOR_M)
            .colorN(UPDATED_COLOR_N)
            .colorO(UPDATED_COLOR_O)
            .colorP(UPDATED_COLOR_P)
            .colorQ(UPDATED_COLOR_Q)
            .colorT(UPDATED_COLOR_T)
            .colorV(UPDATED_COLOR_V)
            .colorW(UPDATED_COLOR_W)
            .catalogName(UPDATED_CATALOG_NAME)
            .catalogVersion(UPDATED_CATALOG_VERSION)
            .catalogCreatedtime(UPDATED_CATALOG_CREATEDTIME)
            .baseMaterialId(UPDATED_BASE_MATERIAL_ID)
            .modifiedTime(UPDATED_MODIFIED_TIME)
            .finishedTime(UPDATED_FINISHED_TIME)
            .original(UPDATED_ORIGINAL);

        restBatchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBatches.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBatches))
            )
            .andExpect(status().isOk());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeUpdate);
        Batches testBatches = batchesList.get(batchesList.size() - 1);
        assertThat(testBatches.getPoName()).isEqualTo(DEFAULT_PO_NAME);
        assertThat(testBatches.getSequenceInPo()).isEqualTo(DEFAULT_SEQUENCE_IN_PO);
        assertThat(testBatches.getScannerId()).isEqualTo(DEFAULT_SCANNER_ID);
        assertThat(testBatches.getPreviousProductionBatchId()).isEqualTo(DEFAULT_PREVIOUS_PRODUCTION_BATCH_ID);
        assertThat(testBatches.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testBatches.getInspectionSequence()).isEqualTo(UPDATED_INSPECTION_SEQUENCE);
        assertThat(testBatches.getOrderedQuantity()).isEqualTo(DEFAULT_ORDERED_QUANTITY);
        assertThat(testBatches.getProducingQuantity()).isEqualTo(DEFAULT_PRODUCING_QUANTITY);
        assertThat(testBatches.getTotalProducingQuantity()).isEqualTo(DEFAULT_TOTAL_PRODUCING_QUANTITY);
        assertThat(testBatches.getRemainingQuantity()).isEqualTo(DEFAULT_REMAINING_QUANTITY);
        assertThat(testBatches.getTotalremainingQuantity()).isEqualTo(UPDATED_TOTALREMAINING_QUANTITY);
        assertThat(testBatches.getInspectedQuantity()).isEqualTo(DEFAULT_INSPECTED_QUANTITY);
        assertThat(testBatches.getTotalInspectedQuantity()).isEqualTo(UPDATED_TOTAL_INSPECTED_QUANTITY);
        assertThat(testBatches.getFailedQuantity()).isEqualTo(DEFAULT_FAILED_QUANTITY);
        assertThat(testBatches.getTotalFailedQuantity()).isEqualTo(UPDATED_TOTAL_FAILED_QUANTITY);
        assertThat(testBatches.getColorId()).isEqualTo(UPDATED_COLOR_ID);
        assertThat(testBatches.getColorCode()).isEqualTo(DEFAULT_COLOR_CODE);
        assertThat(testBatches.getColorName()).isEqualTo(UPDATED_COLOR_NAME);
        assertThat(testBatches.getColorBasematerial()).isEqualTo(UPDATED_COLOR_BASEMATERIAL);
        assertThat(testBatches.getColorLabL()).isEqualTo(UPDATED_COLOR_LAB_L);
        assertThat(testBatches.getColorLabA()).isEqualTo(DEFAULT_COLOR_LAB_A);
        assertThat(testBatches.getColorLabB()).isEqualTo(UPDATED_COLOR_LAB_B);
        assertThat(testBatches.getColorA()).isEqualTo(UPDATED_COLOR_A);
        assertThat(testBatches.getColorB()).isEqualTo(UPDATED_COLOR_B);
        assertThat(testBatches.getColorC()).isEqualTo(DEFAULT_COLOR_C);
        assertThat(testBatches.getColorD()).isEqualTo(DEFAULT_COLOR_D);
        assertThat(testBatches.getColorE()).isEqualTo(DEFAULT_COLOR_E);
        assertThat(testBatches.getColorF()).isEqualTo(DEFAULT_COLOR_F);
        assertThat(testBatches.getColorG()).isEqualTo(UPDATED_COLOR_G);
        assertThat(testBatches.getColorH()).isEqualTo(DEFAULT_COLOR_H);
        assertThat(testBatches.getColorI()).isEqualTo(DEFAULT_COLOR_I);
        assertThat(testBatches.getColorJ()).isEqualTo(UPDATED_COLOR_J);
        assertThat(testBatches.getColorK()).isEqualTo(DEFAULT_COLOR_K);
        assertThat(testBatches.getColorL()).isEqualTo(UPDATED_COLOR_L);
        assertThat(testBatches.getColorM()).isEqualTo(UPDATED_COLOR_M);
        assertThat(testBatches.getColorN()).isEqualTo(UPDATED_COLOR_N);
        assertThat(testBatches.getColorO()).isEqualTo(UPDATED_COLOR_O);
        assertThat(testBatches.getColorP()).isEqualTo(UPDATED_COLOR_P);
        assertThat(testBatches.getColorQ()).isEqualTo(UPDATED_COLOR_Q);
        assertThat(testBatches.getColorR()).isEqualTo(DEFAULT_COLOR_R);
        assertThat(testBatches.getColorS()).isEqualTo(DEFAULT_COLOR_S);
        assertThat(testBatches.getColorT()).isEqualTo(UPDATED_COLOR_T);
        assertThat(testBatches.getColorU()).isEqualTo(DEFAULT_COLOR_U);
        assertThat(testBatches.getColorV()).isEqualTo(UPDATED_COLOR_V);
        assertThat(testBatches.getColorW()).isEqualTo(UPDATED_COLOR_W);
        assertThat(testBatches.getColorX()).isEqualTo(DEFAULT_COLOR_X);
        assertThat(testBatches.getColorY()).isEqualTo(DEFAULT_COLOR_Y);
        assertThat(testBatches.getColorZ()).isEqualTo(DEFAULT_COLOR_Z);
        assertThat(testBatches.getCatalogId()).isEqualTo(DEFAULT_CATALOG_ID);
        assertThat(testBatches.getCatalogExternalid()).isEqualTo(DEFAULT_CATALOG_EXTERNALID);
        assertThat(testBatches.getCatalogName()).isEqualTo(UPDATED_CATALOG_NAME);
        assertThat(testBatches.getCatalogVersion()).isEqualTo(UPDATED_CATALOG_VERSION);
        assertThat(testBatches.getCatalogIsactive()).isEqualTo(DEFAULT_CATALOG_ISACTIVE);
        assertThat(testBatches.getCatalogCreatedtime()).isEqualTo(UPDATED_CATALOG_CREATEDTIME);
        assertThat(testBatches.getBaseMaterialId()).isEqualTo(UPDATED_BASE_MATERIAL_ID);
        assertThat(testBatches.getOrderedTime()).isEqualTo(DEFAULT_ORDERED_TIME);
        assertThat(testBatches.getStartedTime()).isEqualTo(DEFAULT_STARTED_TIME);
        assertThat(testBatches.getModifiedTime()).isEqualTo(UPDATED_MODIFIED_TIME);
        assertThat(testBatches.getSuspendedTime()).isEqualTo(DEFAULT_SUSPENDED_TIME);
        assertThat(testBatches.getFinishedTime()).isEqualTo(UPDATED_FINISHED_TIME);
        assertThat(testBatches.getOriginal()).isEqualTo(UPDATED_ORIGINAL);
    }

    @Test
    @Transactional
    void fullUpdateBatchesWithPatch() throws Exception {
        // Initialize the database
        batches.setId(UUID.randomUUID().toString());
        batchesRepository.saveAndFlush(batches);

        int databaseSizeBeforeUpdate = batchesRepository.findAll().size();

        // Update the batches using partial update
        Batches partialUpdatedBatches = new Batches();
        partialUpdatedBatches.setId(batches.getId());

        partialUpdatedBatches
            .poName(UPDATED_PO_NAME)
            .sequenceInPo(UPDATED_SEQUENCE_IN_PO)
            .scannerId(UPDATED_SCANNER_ID)
            .previousProductionBatchId(UPDATED_PREVIOUS_PRODUCTION_BATCH_ID)
            .state(UPDATED_STATE)
            .inspectionSequence(UPDATED_INSPECTION_SEQUENCE)
            .orderedQuantity(UPDATED_ORDERED_QUANTITY)
            .producingQuantity(UPDATED_PRODUCING_QUANTITY)
            .totalProducingQuantity(UPDATED_TOTAL_PRODUCING_QUANTITY)
            .remainingQuantity(UPDATED_REMAINING_QUANTITY)
            .totalremainingQuantity(UPDATED_TOTALREMAINING_QUANTITY)
            .inspectedQuantity(UPDATED_INSPECTED_QUANTITY)
            .totalInspectedQuantity(UPDATED_TOTAL_INSPECTED_QUANTITY)
            .failedQuantity(UPDATED_FAILED_QUANTITY)
            .totalFailedQuantity(UPDATED_TOTAL_FAILED_QUANTITY)
            .colorId(UPDATED_COLOR_ID)
            .colorCode(UPDATED_COLOR_CODE)
            .colorName(UPDATED_COLOR_NAME)
            .colorBasematerial(UPDATED_COLOR_BASEMATERIAL)
            .colorLabL(UPDATED_COLOR_LAB_L)
            .colorLabA(UPDATED_COLOR_LAB_A)
            .colorLabB(UPDATED_COLOR_LAB_B)
            .colorA(UPDATED_COLOR_A)
            .colorB(UPDATED_COLOR_B)
            .colorC(UPDATED_COLOR_C)
            .colorD(UPDATED_COLOR_D)
            .colorE(UPDATED_COLOR_E)
            .colorF(UPDATED_COLOR_F)
            .colorG(UPDATED_COLOR_G)
            .colorH(UPDATED_COLOR_H)
            .colorI(UPDATED_COLOR_I)
            .colorJ(UPDATED_COLOR_J)
            .colorK(UPDATED_COLOR_K)
            .colorL(UPDATED_COLOR_L)
            .colorM(UPDATED_COLOR_M)
            .colorN(UPDATED_COLOR_N)
            .colorO(UPDATED_COLOR_O)
            .colorP(UPDATED_COLOR_P)
            .colorQ(UPDATED_COLOR_Q)
            .colorR(UPDATED_COLOR_R)
            .colorS(UPDATED_COLOR_S)
            .colorT(UPDATED_COLOR_T)
            .colorU(UPDATED_COLOR_U)
            .colorV(UPDATED_COLOR_V)
            .colorW(UPDATED_COLOR_W)
            .colorX(UPDATED_COLOR_X)
            .colorY(UPDATED_COLOR_Y)
            .colorZ(UPDATED_COLOR_Z)
            .catalogId(UPDATED_CATALOG_ID)
            .catalogExternalid(UPDATED_CATALOG_EXTERNALID)
            .catalogName(UPDATED_CATALOG_NAME)
            .catalogVersion(UPDATED_CATALOG_VERSION)
            .catalogIsactive(UPDATED_CATALOG_ISACTIVE)
            .catalogCreatedtime(UPDATED_CATALOG_CREATEDTIME)
            .baseMaterialId(UPDATED_BASE_MATERIAL_ID)
            .orderedTime(UPDATED_ORDERED_TIME)
            .startedTime(UPDATED_STARTED_TIME)
            .modifiedTime(UPDATED_MODIFIED_TIME)
            .suspendedTime(UPDATED_SUSPENDED_TIME)
            .finishedTime(UPDATED_FINISHED_TIME)
            .original(UPDATED_ORIGINAL);

        restBatchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBatches.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBatches))
            )
            .andExpect(status().isOk());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeUpdate);
        Batches testBatches = batchesList.get(batchesList.size() - 1);
        assertThat(testBatches.getPoName()).isEqualTo(UPDATED_PO_NAME);
        assertThat(testBatches.getSequenceInPo()).isEqualTo(UPDATED_SEQUENCE_IN_PO);
        assertThat(testBatches.getScannerId()).isEqualTo(UPDATED_SCANNER_ID);
        assertThat(testBatches.getPreviousProductionBatchId()).isEqualTo(UPDATED_PREVIOUS_PRODUCTION_BATCH_ID);
        assertThat(testBatches.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testBatches.getInspectionSequence()).isEqualTo(UPDATED_INSPECTION_SEQUENCE);
        assertThat(testBatches.getOrderedQuantity()).isEqualTo(UPDATED_ORDERED_QUANTITY);
        assertThat(testBatches.getProducingQuantity()).isEqualTo(UPDATED_PRODUCING_QUANTITY);
        assertThat(testBatches.getTotalProducingQuantity()).isEqualTo(UPDATED_TOTAL_PRODUCING_QUANTITY);
        assertThat(testBatches.getRemainingQuantity()).isEqualTo(UPDATED_REMAINING_QUANTITY);
        assertThat(testBatches.getTotalremainingQuantity()).isEqualTo(UPDATED_TOTALREMAINING_QUANTITY);
        assertThat(testBatches.getInspectedQuantity()).isEqualTo(UPDATED_INSPECTED_QUANTITY);
        assertThat(testBatches.getTotalInspectedQuantity()).isEqualTo(UPDATED_TOTAL_INSPECTED_QUANTITY);
        assertThat(testBatches.getFailedQuantity()).isEqualTo(UPDATED_FAILED_QUANTITY);
        assertThat(testBatches.getTotalFailedQuantity()).isEqualTo(UPDATED_TOTAL_FAILED_QUANTITY);
        assertThat(testBatches.getColorId()).isEqualTo(UPDATED_COLOR_ID);
        assertThat(testBatches.getColorCode()).isEqualTo(UPDATED_COLOR_CODE);
        assertThat(testBatches.getColorName()).isEqualTo(UPDATED_COLOR_NAME);
        assertThat(testBatches.getColorBasematerial()).isEqualTo(UPDATED_COLOR_BASEMATERIAL);
        assertThat(testBatches.getColorLabL()).isEqualTo(UPDATED_COLOR_LAB_L);
        assertThat(testBatches.getColorLabA()).isEqualTo(UPDATED_COLOR_LAB_A);
        assertThat(testBatches.getColorLabB()).isEqualTo(UPDATED_COLOR_LAB_B);
        assertThat(testBatches.getColorA()).isEqualTo(UPDATED_COLOR_A);
        assertThat(testBatches.getColorB()).isEqualTo(UPDATED_COLOR_B);
        assertThat(testBatches.getColorC()).isEqualTo(UPDATED_COLOR_C);
        assertThat(testBatches.getColorD()).isEqualTo(UPDATED_COLOR_D);
        assertThat(testBatches.getColorE()).isEqualTo(UPDATED_COLOR_E);
        assertThat(testBatches.getColorF()).isEqualTo(UPDATED_COLOR_F);
        assertThat(testBatches.getColorG()).isEqualTo(UPDATED_COLOR_G);
        assertThat(testBatches.getColorH()).isEqualTo(UPDATED_COLOR_H);
        assertThat(testBatches.getColorI()).isEqualTo(UPDATED_COLOR_I);
        assertThat(testBatches.getColorJ()).isEqualTo(UPDATED_COLOR_J);
        assertThat(testBatches.getColorK()).isEqualTo(UPDATED_COLOR_K);
        assertThat(testBatches.getColorL()).isEqualTo(UPDATED_COLOR_L);
        assertThat(testBatches.getColorM()).isEqualTo(UPDATED_COLOR_M);
        assertThat(testBatches.getColorN()).isEqualTo(UPDATED_COLOR_N);
        assertThat(testBatches.getColorO()).isEqualTo(UPDATED_COLOR_O);
        assertThat(testBatches.getColorP()).isEqualTo(UPDATED_COLOR_P);
        assertThat(testBatches.getColorQ()).isEqualTo(UPDATED_COLOR_Q);
        assertThat(testBatches.getColorR()).isEqualTo(UPDATED_COLOR_R);
        assertThat(testBatches.getColorS()).isEqualTo(UPDATED_COLOR_S);
        assertThat(testBatches.getColorT()).isEqualTo(UPDATED_COLOR_T);
        assertThat(testBatches.getColorU()).isEqualTo(UPDATED_COLOR_U);
        assertThat(testBatches.getColorV()).isEqualTo(UPDATED_COLOR_V);
        assertThat(testBatches.getColorW()).isEqualTo(UPDATED_COLOR_W);
        assertThat(testBatches.getColorX()).isEqualTo(UPDATED_COLOR_X);
        assertThat(testBatches.getColorY()).isEqualTo(UPDATED_COLOR_Y);
        assertThat(testBatches.getColorZ()).isEqualTo(UPDATED_COLOR_Z);
        assertThat(testBatches.getCatalogId()).isEqualTo(UPDATED_CATALOG_ID);
        assertThat(testBatches.getCatalogExternalid()).isEqualTo(UPDATED_CATALOG_EXTERNALID);
        assertThat(testBatches.getCatalogName()).isEqualTo(UPDATED_CATALOG_NAME);
        assertThat(testBatches.getCatalogVersion()).isEqualTo(UPDATED_CATALOG_VERSION);
        assertThat(testBatches.getCatalogIsactive()).isEqualTo(UPDATED_CATALOG_ISACTIVE);
        assertThat(testBatches.getCatalogCreatedtime()).isEqualTo(UPDATED_CATALOG_CREATEDTIME);
        assertThat(testBatches.getBaseMaterialId()).isEqualTo(UPDATED_BASE_MATERIAL_ID);
        assertThat(testBatches.getOrderedTime()).isEqualTo(UPDATED_ORDERED_TIME);
        assertThat(testBatches.getStartedTime()).isEqualTo(UPDATED_STARTED_TIME);
        assertThat(testBatches.getModifiedTime()).isEqualTo(UPDATED_MODIFIED_TIME);
        assertThat(testBatches.getSuspendedTime()).isEqualTo(UPDATED_SUSPENDED_TIME);
        assertThat(testBatches.getFinishedTime()).isEqualTo(UPDATED_FINISHED_TIME);
        assertThat(testBatches.getOriginal()).isEqualTo(UPDATED_ORIGINAL);
    }

    @Test
    @Transactional
    void patchNonExistingBatches() throws Exception {
        int databaseSizeBeforeUpdate = batchesRepository.findAll().size();
        batches.setId(UUID.randomUUID().toString());

        // Create the Batches
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBatchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, batchesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(batchesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBatches() throws Exception {
        int databaseSizeBeforeUpdate = batchesRepository.findAll().size();
        batches.setId(UUID.randomUUID().toString());

        // Create the Batches
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBatchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(batchesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBatches() throws Exception {
        int databaseSizeBeforeUpdate = batchesRepository.findAll().size();
        batches.setId(UUID.randomUUID().toString());

        // Create the Batches
        BatchesDTO batchesDTO = batchesMapper.toDto(batches);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBatchesMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(batchesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Batches in the database
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBatches() throws Exception {
        // Initialize the database
        batches.setId(UUID.randomUUID().toString());
        batchesRepository.saveAndFlush(batches);

        int databaseSizeBeforeDelete = batchesRepository.findAll().size();

        // Delete the batches
        restBatchesMockMvc
            .perform(delete(ENTITY_API_URL_ID, batches.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Batches> batchesList = batchesRepository.findAll();
        assertThat(batchesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
