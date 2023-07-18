package biz.daich.tambour.shlicht.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import biz.daich.tambour.shlicht.IntegrationTest;
import biz.daich.tambour.shlicht.domain.Catalogcolors;
import biz.daich.tambour.shlicht.domain.Catalogs;
import biz.daich.tambour.shlicht.repository.CatalogcolorsRepository;
import biz.daich.tambour.shlicht.service.dto.CatalogcolorsDTO;
import biz.daich.tambour.shlicht.service.mapper.CatalogcolorsMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link CatalogcolorsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CatalogcolorsResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_BASE_MATERIAL = 1L;
    private static final Long UPDATED_BASE_MATERIAL = 2L;
    private static final Long SMALLER_BASE_MATERIAL = 1L - 1L;

    private static final Double DEFAULT_LAB_L = 1D;
    private static final Double UPDATED_LAB_L = 2D;
    private static final Double SMALLER_LAB_L = 1D - 1D;

    private static final Double DEFAULT_LAB_A = 1D;
    private static final Double UPDATED_LAB_A = 2D;
    private static final Double SMALLER_LAB_A = 1D - 1D;

    private static final Double DEFAULT_LAB_B = 1D;
    private static final Double UPDATED_LAB_B = 2D;
    private static final Double SMALLER_LAB_B = 1D - 1D;

    private static final Double DEFAULT_A = 1D;
    private static final Double UPDATED_A = 2D;
    private static final Double SMALLER_A = 1D - 1D;

    private static final Double DEFAULT_B = 1D;
    private static final Double UPDATED_B = 2D;
    private static final Double SMALLER_B = 1D - 1D;

    private static final Double DEFAULT_C = 1D;
    private static final Double UPDATED_C = 2D;
    private static final Double SMALLER_C = 1D - 1D;

    private static final Double DEFAULT_D = 1D;
    private static final Double UPDATED_D = 2D;
    private static final Double SMALLER_D = 1D - 1D;

    private static final Double DEFAULT_E = 1D;
    private static final Double UPDATED_E = 2D;
    private static final Double SMALLER_E = 1D - 1D;

    private static final Double DEFAULT_F = 1D;
    private static final Double UPDATED_F = 2D;
    private static final Double SMALLER_F = 1D - 1D;

    private static final Double DEFAULT_G = 1D;
    private static final Double UPDATED_G = 2D;
    private static final Double SMALLER_G = 1D - 1D;

    private static final Double DEFAULT_H = 1D;
    private static final Double UPDATED_H = 2D;
    private static final Double SMALLER_H = 1D - 1D;

    private static final Double DEFAULT_I = 1D;
    private static final Double UPDATED_I = 2D;
    private static final Double SMALLER_I = 1D - 1D;

    private static final Double DEFAULT_J = 1D;
    private static final Double UPDATED_J = 2D;
    private static final Double SMALLER_J = 1D - 1D;

    private static final Double DEFAULT_K = 1D;
    private static final Double UPDATED_K = 2D;
    private static final Double SMALLER_K = 1D - 1D;

    private static final Double DEFAULT_L = 1D;
    private static final Double UPDATED_L = 2D;
    private static final Double SMALLER_L = 1D - 1D;

    private static final Double DEFAULT_M = 1D;
    private static final Double UPDATED_M = 2D;
    private static final Double SMALLER_M = 1D - 1D;

    private static final Double DEFAULT_N = 1D;
    private static final Double UPDATED_N = 2D;
    private static final Double SMALLER_N = 1D - 1D;

    private static final Double DEFAULT_O = 1D;
    private static final Double UPDATED_O = 2D;
    private static final Double SMALLER_O = 1D - 1D;

    private static final Double DEFAULT_P = 1D;
    private static final Double UPDATED_P = 2D;
    private static final Double SMALLER_P = 1D - 1D;

    private static final Double DEFAULT_Q = 1D;
    private static final Double UPDATED_Q = 2D;
    private static final Double SMALLER_Q = 1D - 1D;

    private static final Double DEFAULT_R = 1D;
    private static final Double UPDATED_R = 2D;
    private static final Double SMALLER_R = 1D - 1D;

    private static final Double DEFAULT_S = 1D;
    private static final Double UPDATED_S = 2D;
    private static final Double SMALLER_S = 1D - 1D;

    private static final Double DEFAULT_T = 1D;
    private static final Double UPDATED_T = 2D;
    private static final Double SMALLER_T = 1D - 1D;

    private static final Double DEFAULT_U = 1D;
    private static final Double UPDATED_U = 2D;
    private static final Double SMALLER_U = 1D - 1D;

    private static final Double DEFAULT_V = 1D;
    private static final Double UPDATED_V = 2D;
    private static final Double SMALLER_V = 1D - 1D;

    private static final Double DEFAULT_W = 1D;
    private static final Double UPDATED_W = 2D;
    private static final Double SMALLER_W = 1D - 1D;

    private static final Double DEFAULT_X = 1D;
    private static final Double UPDATED_X = 2D;
    private static final Double SMALLER_X = 1D - 1D;

    private static final Double DEFAULT_Y = 1D;
    private static final Double UPDATED_Y = 2D;
    private static final Double SMALLER_Y = 1D - 1D;

    private static final Double DEFAULT_Z = 1D;
    private static final Double UPDATED_Z = 2D;
    private static final Double SMALLER_Z = 1D - 1D;

    private static final String ENTITY_API_URL = "/api/catalogcolors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CatalogcolorsRepository catalogcolorsRepository;

    @Autowired
    private CatalogcolorsMapper catalogcolorsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCatalogcolorsMockMvc;

    private Catalogcolors catalogcolors;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Catalogcolors createEntity(EntityManager em) {
        Catalogcolors catalogcolors = new Catalogcolors()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .baseMaterial(DEFAULT_BASE_MATERIAL)
            .labL(DEFAULT_LAB_L)
            .labA(DEFAULT_LAB_A)
            .labB(DEFAULT_LAB_B)
            .a(DEFAULT_A)
            .b(DEFAULT_B)
            .c(DEFAULT_C)
            .d(DEFAULT_D)
            .e(DEFAULT_E)
            .f(DEFAULT_F)
            .g(DEFAULT_G)
            .h(DEFAULT_H)
            .i(DEFAULT_I)
            .j(DEFAULT_J)
            .k(DEFAULT_K)
            .l(DEFAULT_L)
            .m(DEFAULT_M)
            .n(DEFAULT_N)
            .o(DEFAULT_O)
            .p(DEFAULT_P)
            .q(DEFAULT_Q)
            .r(DEFAULT_R)
            .s(DEFAULT_S)
            .t(DEFAULT_T)
            .u(DEFAULT_U)
            .v(DEFAULT_V)
            .w(DEFAULT_W)
            .x(DEFAULT_X)
            .y(DEFAULT_Y)
            .z(DEFAULT_Z);
        return catalogcolors;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Catalogcolors createUpdatedEntity(EntityManager em) {
        Catalogcolors catalogcolors = new Catalogcolors()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .baseMaterial(UPDATED_BASE_MATERIAL)
            .labL(UPDATED_LAB_L)
            .labA(UPDATED_LAB_A)
            .labB(UPDATED_LAB_B)
            .a(UPDATED_A)
            .b(UPDATED_B)
            .c(UPDATED_C)
            .d(UPDATED_D)
            .e(UPDATED_E)
            .f(UPDATED_F)
            .g(UPDATED_G)
            .h(UPDATED_H)
            .i(UPDATED_I)
            .j(UPDATED_J)
            .k(UPDATED_K)
            .l(UPDATED_L)
            .m(UPDATED_M)
            .n(UPDATED_N)
            .o(UPDATED_O)
            .p(UPDATED_P)
            .q(UPDATED_Q)
            .r(UPDATED_R)
            .s(UPDATED_S)
            .t(UPDATED_T)
            .u(UPDATED_U)
            .v(UPDATED_V)
            .w(UPDATED_W)
            .x(UPDATED_X)
            .y(UPDATED_Y)
            .z(UPDATED_Z);
        return catalogcolors;
    }

    @BeforeEach
    public void initTest() {
        catalogcolors = createEntity(em);
    }

    @Test
    @Transactional
    void createCatalogcolors() throws Exception {
        int databaseSizeBeforeCreate = catalogcolorsRepository.findAll().size();
        // Create the Catalogcolors
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);
        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeCreate + 1);
        Catalogcolors testCatalogcolors = catalogcolorsList.get(catalogcolorsList.size() - 1);
        assertThat(testCatalogcolors.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCatalogcolors.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCatalogcolors.getBaseMaterial()).isEqualTo(DEFAULT_BASE_MATERIAL);
        assertThat(testCatalogcolors.getLabL()).isEqualTo(DEFAULT_LAB_L);
        assertThat(testCatalogcolors.getLabA()).isEqualTo(DEFAULT_LAB_A);
        assertThat(testCatalogcolors.getLabB()).isEqualTo(DEFAULT_LAB_B);
        assertThat(testCatalogcolors.getA()).isEqualTo(DEFAULT_A);
        assertThat(testCatalogcolors.getB()).isEqualTo(DEFAULT_B);
        assertThat(testCatalogcolors.getC()).isEqualTo(DEFAULT_C);
        assertThat(testCatalogcolors.getD()).isEqualTo(DEFAULT_D);
        assertThat(testCatalogcolors.getE()).isEqualTo(DEFAULT_E);
        assertThat(testCatalogcolors.getF()).isEqualTo(DEFAULT_F);
        assertThat(testCatalogcolors.getG()).isEqualTo(DEFAULT_G);
        assertThat(testCatalogcolors.getH()).isEqualTo(DEFAULT_H);
        assertThat(testCatalogcolors.getI()).isEqualTo(DEFAULT_I);
        assertThat(testCatalogcolors.getJ()).isEqualTo(DEFAULT_J);
        assertThat(testCatalogcolors.getK()).isEqualTo(DEFAULT_K);
        assertThat(testCatalogcolors.getL()).isEqualTo(DEFAULT_L);
        assertThat(testCatalogcolors.getM()).isEqualTo(DEFAULT_M);
        assertThat(testCatalogcolors.getN()).isEqualTo(DEFAULT_N);
        assertThat(testCatalogcolors.getO()).isEqualTo(DEFAULT_O);
        assertThat(testCatalogcolors.getP()).isEqualTo(DEFAULT_P);
        assertThat(testCatalogcolors.getQ()).isEqualTo(DEFAULT_Q);
        assertThat(testCatalogcolors.getR()).isEqualTo(DEFAULT_R);
        assertThat(testCatalogcolors.getS()).isEqualTo(DEFAULT_S);
        assertThat(testCatalogcolors.getT()).isEqualTo(DEFAULT_T);
        assertThat(testCatalogcolors.getU()).isEqualTo(DEFAULT_U);
        assertThat(testCatalogcolors.getV()).isEqualTo(DEFAULT_V);
        assertThat(testCatalogcolors.getW()).isEqualTo(DEFAULT_W);
        assertThat(testCatalogcolors.getX()).isEqualTo(DEFAULT_X);
        assertThat(testCatalogcolors.getY()).isEqualTo(DEFAULT_Y);
        assertThat(testCatalogcolors.getZ()).isEqualTo(DEFAULT_Z);
    }

    @Test
    @Transactional
    void createCatalogcolorsWithExistingId() throws Exception {
        // Create the Catalogcolors with an existing ID
        catalogcolors.setId("existing_id");
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        int databaseSizeBeforeCreate = catalogcolorsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkAIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setA(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setB(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setC(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setD(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setE(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setF(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setG(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkHIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setH(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setI(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkJIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setJ(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkKIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setK(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setL(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setM(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setN(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setO(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setP(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setQ(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setR(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setS(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setT(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setU(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setV(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkWIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setW(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkXIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setX(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkYIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setY(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkZIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogcolorsRepository.findAll().size();
        // set the field null
        catalogcolors.setZ(null);

        // Create the Catalogcolors, which fails.
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCatalogcolors() throws Exception {
        // Initialize the database
        catalogcolors.setId(UUID.randomUUID().toString());
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList
        restCatalogcolorsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catalogcolors.getId())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].baseMaterial").value(hasItem(DEFAULT_BASE_MATERIAL.intValue())))
            .andExpect(jsonPath("$.[*].labL").value(hasItem(DEFAULT_LAB_L.doubleValue())))
            .andExpect(jsonPath("$.[*].labA").value(hasItem(DEFAULT_LAB_A.doubleValue())))
            .andExpect(jsonPath("$.[*].labB").value(hasItem(DEFAULT_LAB_B.doubleValue())))
            .andExpect(jsonPath("$.[*].a").value(hasItem(DEFAULT_A.doubleValue())))
            .andExpect(jsonPath("$.[*].b").value(hasItem(DEFAULT_B.doubleValue())))
            .andExpect(jsonPath("$.[*].c").value(hasItem(DEFAULT_C.doubleValue())))
            .andExpect(jsonPath("$.[*].d").value(hasItem(DEFAULT_D.doubleValue())))
            .andExpect(jsonPath("$.[*].e").value(hasItem(DEFAULT_E.doubleValue())))
            .andExpect(jsonPath("$.[*].f").value(hasItem(DEFAULT_F.doubleValue())))
            .andExpect(jsonPath("$.[*].g").value(hasItem(DEFAULT_G.doubleValue())))
            .andExpect(jsonPath("$.[*].h").value(hasItem(DEFAULT_H.doubleValue())))
            .andExpect(jsonPath("$.[*].i").value(hasItem(DEFAULT_I.doubleValue())))
            .andExpect(jsonPath("$.[*].j").value(hasItem(DEFAULT_J.doubleValue())))
            .andExpect(jsonPath("$.[*].k").value(hasItem(DEFAULT_K.doubleValue())))
            .andExpect(jsonPath("$.[*].l").value(hasItem(DEFAULT_L.doubleValue())))
            .andExpect(jsonPath("$.[*].m").value(hasItem(DEFAULT_M.doubleValue())))
            .andExpect(jsonPath("$.[*].n").value(hasItem(DEFAULT_N.doubleValue())))
            .andExpect(jsonPath("$.[*].o").value(hasItem(DEFAULT_O.doubleValue())))
            .andExpect(jsonPath("$.[*].p").value(hasItem(DEFAULT_P.doubleValue())))
            .andExpect(jsonPath("$.[*].q").value(hasItem(DEFAULT_Q.doubleValue())))
            .andExpect(jsonPath("$.[*].r").value(hasItem(DEFAULT_R.doubleValue())))
            .andExpect(jsonPath("$.[*].s").value(hasItem(DEFAULT_S.doubleValue())))
            .andExpect(jsonPath("$.[*].t").value(hasItem(DEFAULT_T.doubleValue())))
            .andExpect(jsonPath("$.[*].u").value(hasItem(DEFAULT_U.doubleValue())))
            .andExpect(jsonPath("$.[*].v").value(hasItem(DEFAULT_V.doubleValue())))
            .andExpect(jsonPath("$.[*].w").value(hasItem(DEFAULT_W.doubleValue())))
            .andExpect(jsonPath("$.[*].x").value(hasItem(DEFAULT_X.doubleValue())))
            .andExpect(jsonPath("$.[*].y").value(hasItem(DEFAULT_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].z").value(hasItem(DEFAULT_Z.doubleValue())));
    }

    @Test
    @Transactional
    void getCatalogcolors() throws Exception {
        // Initialize the database
        catalogcolors.setId(UUID.randomUUID().toString());
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get the catalogcolors
        restCatalogcolorsMockMvc
            .perform(get(ENTITY_API_URL_ID, catalogcolors.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(catalogcolors.getId()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.baseMaterial").value(DEFAULT_BASE_MATERIAL.intValue()))
            .andExpect(jsonPath("$.labL").value(DEFAULT_LAB_L.doubleValue()))
            .andExpect(jsonPath("$.labA").value(DEFAULT_LAB_A.doubleValue()))
            .andExpect(jsonPath("$.labB").value(DEFAULT_LAB_B.doubleValue()))
            .andExpect(jsonPath("$.a").value(DEFAULT_A.doubleValue()))
            .andExpect(jsonPath("$.b").value(DEFAULT_B.doubleValue()))
            .andExpect(jsonPath("$.c").value(DEFAULT_C.doubleValue()))
            .andExpect(jsonPath("$.d").value(DEFAULT_D.doubleValue()))
            .andExpect(jsonPath("$.e").value(DEFAULT_E.doubleValue()))
            .andExpect(jsonPath("$.f").value(DEFAULT_F.doubleValue()))
            .andExpect(jsonPath("$.g").value(DEFAULT_G.doubleValue()))
            .andExpect(jsonPath("$.h").value(DEFAULT_H.doubleValue()))
            .andExpect(jsonPath("$.i").value(DEFAULT_I.doubleValue()))
            .andExpect(jsonPath("$.j").value(DEFAULT_J.doubleValue()))
            .andExpect(jsonPath("$.k").value(DEFAULT_K.doubleValue()))
            .andExpect(jsonPath("$.l").value(DEFAULT_L.doubleValue()))
            .andExpect(jsonPath("$.m").value(DEFAULT_M.doubleValue()))
            .andExpect(jsonPath("$.n").value(DEFAULT_N.doubleValue()))
            .andExpect(jsonPath("$.o").value(DEFAULT_O.doubleValue()))
            .andExpect(jsonPath("$.p").value(DEFAULT_P.doubleValue()))
            .andExpect(jsonPath("$.q").value(DEFAULT_Q.doubleValue()))
            .andExpect(jsonPath("$.r").value(DEFAULT_R.doubleValue()))
            .andExpect(jsonPath("$.s").value(DEFAULT_S.doubleValue()))
            .andExpect(jsonPath("$.t").value(DEFAULT_T.doubleValue()))
            .andExpect(jsonPath("$.u").value(DEFAULT_U.doubleValue()))
            .andExpect(jsonPath("$.v").value(DEFAULT_V.doubleValue()))
            .andExpect(jsonPath("$.w").value(DEFAULT_W.doubleValue()))
            .andExpect(jsonPath("$.x").value(DEFAULT_X.doubleValue()))
            .andExpect(jsonPath("$.y").value(DEFAULT_Y.doubleValue()))
            .andExpect(jsonPath("$.z").value(DEFAULT_Z.doubleValue()));
    }

    @Test
    @Transactional
    void getCatalogcolorsByIdFiltering() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        String id = catalogcolors.getId();

        defaultCatalogcolorsShouldBeFound("id.equals=" + id);
        defaultCatalogcolorsShouldNotBeFound("id.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBaseMaterialIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where baseMaterial equals to DEFAULT_BASE_MATERIAL
        defaultCatalogcolorsShouldBeFound("baseMaterial.equals=" + DEFAULT_BASE_MATERIAL);

        // Get all the catalogcolorsList where baseMaterial equals to UPDATED_BASE_MATERIAL
        defaultCatalogcolorsShouldNotBeFound("baseMaterial.equals=" + UPDATED_BASE_MATERIAL);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBaseMaterialIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where baseMaterial in DEFAULT_BASE_MATERIAL or UPDATED_BASE_MATERIAL
        defaultCatalogcolorsShouldBeFound("baseMaterial.in=" + DEFAULT_BASE_MATERIAL + "," + UPDATED_BASE_MATERIAL);

        // Get all the catalogcolorsList where baseMaterial equals to UPDATED_BASE_MATERIAL
        defaultCatalogcolorsShouldNotBeFound("baseMaterial.in=" + UPDATED_BASE_MATERIAL);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBaseMaterialIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where baseMaterial is not null
        defaultCatalogcolorsShouldBeFound("baseMaterial.specified=true");

        // Get all the catalogcolorsList where baseMaterial is null
        defaultCatalogcolorsShouldNotBeFound("baseMaterial.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBaseMaterialIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where baseMaterial is greater than or equal to DEFAULT_BASE_MATERIAL
        defaultCatalogcolorsShouldBeFound("baseMaterial.greaterThanOrEqual=" + DEFAULT_BASE_MATERIAL);

        // Get all the catalogcolorsList where baseMaterial is greater than or equal to UPDATED_BASE_MATERIAL
        defaultCatalogcolorsShouldNotBeFound("baseMaterial.greaterThanOrEqual=" + UPDATED_BASE_MATERIAL);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBaseMaterialIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where baseMaterial is less than or equal to DEFAULT_BASE_MATERIAL
        defaultCatalogcolorsShouldBeFound("baseMaterial.lessThanOrEqual=" + DEFAULT_BASE_MATERIAL);

        // Get all the catalogcolorsList where baseMaterial is less than or equal to SMALLER_BASE_MATERIAL
        defaultCatalogcolorsShouldNotBeFound("baseMaterial.lessThanOrEqual=" + SMALLER_BASE_MATERIAL);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBaseMaterialIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where baseMaterial is less than DEFAULT_BASE_MATERIAL
        defaultCatalogcolorsShouldNotBeFound("baseMaterial.lessThan=" + DEFAULT_BASE_MATERIAL);

        // Get all the catalogcolorsList where baseMaterial is less than UPDATED_BASE_MATERIAL
        defaultCatalogcolorsShouldBeFound("baseMaterial.lessThan=" + UPDATED_BASE_MATERIAL);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBaseMaterialIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where baseMaterial is greater than DEFAULT_BASE_MATERIAL
        defaultCatalogcolorsShouldNotBeFound("baseMaterial.greaterThan=" + DEFAULT_BASE_MATERIAL);

        // Get all the catalogcolorsList where baseMaterial is greater than SMALLER_BASE_MATERIAL
        defaultCatalogcolorsShouldBeFound("baseMaterial.greaterThan=" + SMALLER_BASE_MATERIAL);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabLIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labL equals to DEFAULT_LAB_L
        defaultCatalogcolorsShouldBeFound("labL.equals=" + DEFAULT_LAB_L);

        // Get all the catalogcolorsList where labL equals to UPDATED_LAB_L
        defaultCatalogcolorsShouldNotBeFound("labL.equals=" + UPDATED_LAB_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabLIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labL in DEFAULT_LAB_L or UPDATED_LAB_L
        defaultCatalogcolorsShouldBeFound("labL.in=" + DEFAULT_LAB_L + "," + UPDATED_LAB_L);

        // Get all the catalogcolorsList where labL equals to UPDATED_LAB_L
        defaultCatalogcolorsShouldNotBeFound("labL.in=" + UPDATED_LAB_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabLIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labL is not null
        defaultCatalogcolorsShouldBeFound("labL.specified=true");

        // Get all the catalogcolorsList where labL is null
        defaultCatalogcolorsShouldNotBeFound("labL.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabLIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labL is greater than or equal to DEFAULT_LAB_L
        defaultCatalogcolorsShouldBeFound("labL.greaterThanOrEqual=" + DEFAULT_LAB_L);

        // Get all the catalogcolorsList where labL is greater than or equal to UPDATED_LAB_L
        defaultCatalogcolorsShouldNotBeFound("labL.greaterThanOrEqual=" + UPDATED_LAB_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabLIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labL is less than or equal to DEFAULT_LAB_L
        defaultCatalogcolorsShouldBeFound("labL.lessThanOrEqual=" + DEFAULT_LAB_L);

        // Get all the catalogcolorsList where labL is less than or equal to SMALLER_LAB_L
        defaultCatalogcolorsShouldNotBeFound("labL.lessThanOrEqual=" + SMALLER_LAB_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabLIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labL is less than DEFAULT_LAB_L
        defaultCatalogcolorsShouldNotBeFound("labL.lessThan=" + DEFAULT_LAB_L);

        // Get all the catalogcolorsList where labL is less than UPDATED_LAB_L
        defaultCatalogcolorsShouldBeFound("labL.lessThan=" + UPDATED_LAB_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabLIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labL is greater than DEFAULT_LAB_L
        defaultCatalogcolorsShouldNotBeFound("labL.greaterThan=" + DEFAULT_LAB_L);

        // Get all the catalogcolorsList where labL is greater than SMALLER_LAB_L
        defaultCatalogcolorsShouldBeFound("labL.greaterThan=" + SMALLER_LAB_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabAIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labA equals to DEFAULT_LAB_A
        defaultCatalogcolorsShouldBeFound("labA.equals=" + DEFAULT_LAB_A);

        // Get all the catalogcolorsList where labA equals to UPDATED_LAB_A
        defaultCatalogcolorsShouldNotBeFound("labA.equals=" + UPDATED_LAB_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabAIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labA in DEFAULT_LAB_A or UPDATED_LAB_A
        defaultCatalogcolorsShouldBeFound("labA.in=" + DEFAULT_LAB_A + "," + UPDATED_LAB_A);

        // Get all the catalogcolorsList where labA equals to UPDATED_LAB_A
        defaultCatalogcolorsShouldNotBeFound("labA.in=" + UPDATED_LAB_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabAIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labA is not null
        defaultCatalogcolorsShouldBeFound("labA.specified=true");

        // Get all the catalogcolorsList where labA is null
        defaultCatalogcolorsShouldNotBeFound("labA.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabAIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labA is greater than or equal to DEFAULT_LAB_A
        defaultCatalogcolorsShouldBeFound("labA.greaterThanOrEqual=" + DEFAULT_LAB_A);

        // Get all the catalogcolorsList where labA is greater than or equal to UPDATED_LAB_A
        defaultCatalogcolorsShouldNotBeFound("labA.greaterThanOrEqual=" + UPDATED_LAB_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabAIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labA is less than or equal to DEFAULT_LAB_A
        defaultCatalogcolorsShouldBeFound("labA.lessThanOrEqual=" + DEFAULT_LAB_A);

        // Get all the catalogcolorsList where labA is less than or equal to SMALLER_LAB_A
        defaultCatalogcolorsShouldNotBeFound("labA.lessThanOrEqual=" + SMALLER_LAB_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabAIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labA is less than DEFAULT_LAB_A
        defaultCatalogcolorsShouldNotBeFound("labA.lessThan=" + DEFAULT_LAB_A);

        // Get all the catalogcolorsList where labA is less than UPDATED_LAB_A
        defaultCatalogcolorsShouldBeFound("labA.lessThan=" + UPDATED_LAB_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabAIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labA is greater than DEFAULT_LAB_A
        defaultCatalogcolorsShouldNotBeFound("labA.greaterThan=" + DEFAULT_LAB_A);

        // Get all the catalogcolorsList where labA is greater than SMALLER_LAB_A
        defaultCatalogcolorsShouldBeFound("labA.greaterThan=" + SMALLER_LAB_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabBIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labB equals to DEFAULT_LAB_B
        defaultCatalogcolorsShouldBeFound("labB.equals=" + DEFAULT_LAB_B);

        // Get all the catalogcolorsList where labB equals to UPDATED_LAB_B
        defaultCatalogcolorsShouldNotBeFound("labB.equals=" + UPDATED_LAB_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabBIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labB in DEFAULT_LAB_B or UPDATED_LAB_B
        defaultCatalogcolorsShouldBeFound("labB.in=" + DEFAULT_LAB_B + "," + UPDATED_LAB_B);

        // Get all the catalogcolorsList where labB equals to UPDATED_LAB_B
        defaultCatalogcolorsShouldNotBeFound("labB.in=" + UPDATED_LAB_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabBIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labB is not null
        defaultCatalogcolorsShouldBeFound("labB.specified=true");

        // Get all the catalogcolorsList where labB is null
        defaultCatalogcolorsShouldNotBeFound("labB.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabBIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labB is greater than or equal to DEFAULT_LAB_B
        defaultCatalogcolorsShouldBeFound("labB.greaterThanOrEqual=" + DEFAULT_LAB_B);

        // Get all the catalogcolorsList where labB is greater than or equal to UPDATED_LAB_B
        defaultCatalogcolorsShouldNotBeFound("labB.greaterThanOrEqual=" + UPDATED_LAB_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabBIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labB is less than or equal to DEFAULT_LAB_B
        defaultCatalogcolorsShouldBeFound("labB.lessThanOrEqual=" + DEFAULT_LAB_B);

        // Get all the catalogcolorsList where labB is less than or equal to SMALLER_LAB_B
        defaultCatalogcolorsShouldNotBeFound("labB.lessThanOrEqual=" + SMALLER_LAB_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabBIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labB is less than DEFAULT_LAB_B
        defaultCatalogcolorsShouldNotBeFound("labB.lessThan=" + DEFAULT_LAB_B);

        // Get all the catalogcolorsList where labB is less than UPDATED_LAB_B
        defaultCatalogcolorsShouldBeFound("labB.lessThan=" + UPDATED_LAB_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLabBIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where labB is greater than DEFAULT_LAB_B
        defaultCatalogcolorsShouldNotBeFound("labB.greaterThan=" + DEFAULT_LAB_B);

        // Get all the catalogcolorsList where labB is greater than SMALLER_LAB_B
        defaultCatalogcolorsShouldBeFound("labB.greaterThan=" + SMALLER_LAB_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByAIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where a equals to DEFAULT_A
        defaultCatalogcolorsShouldBeFound("a.equals=" + DEFAULT_A);

        // Get all the catalogcolorsList where a equals to UPDATED_A
        defaultCatalogcolorsShouldNotBeFound("a.equals=" + UPDATED_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByAIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where a in DEFAULT_A or UPDATED_A
        defaultCatalogcolorsShouldBeFound("a.in=" + DEFAULT_A + "," + UPDATED_A);

        // Get all the catalogcolorsList where a equals to UPDATED_A
        defaultCatalogcolorsShouldNotBeFound("a.in=" + UPDATED_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByAIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where a is not null
        defaultCatalogcolorsShouldBeFound("a.specified=true");

        // Get all the catalogcolorsList where a is null
        defaultCatalogcolorsShouldNotBeFound("a.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByAIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where a is greater than or equal to DEFAULT_A
        defaultCatalogcolorsShouldBeFound("a.greaterThanOrEqual=" + DEFAULT_A);

        // Get all the catalogcolorsList where a is greater than or equal to UPDATED_A
        defaultCatalogcolorsShouldNotBeFound("a.greaterThanOrEqual=" + UPDATED_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByAIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where a is less than or equal to DEFAULT_A
        defaultCatalogcolorsShouldBeFound("a.lessThanOrEqual=" + DEFAULT_A);

        // Get all the catalogcolorsList where a is less than or equal to SMALLER_A
        defaultCatalogcolorsShouldNotBeFound("a.lessThanOrEqual=" + SMALLER_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByAIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where a is less than DEFAULT_A
        defaultCatalogcolorsShouldNotBeFound("a.lessThan=" + DEFAULT_A);

        // Get all the catalogcolorsList where a is less than UPDATED_A
        defaultCatalogcolorsShouldBeFound("a.lessThan=" + UPDATED_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByAIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where a is greater than DEFAULT_A
        defaultCatalogcolorsShouldNotBeFound("a.greaterThan=" + DEFAULT_A);

        // Get all the catalogcolorsList where a is greater than SMALLER_A
        defaultCatalogcolorsShouldBeFound("a.greaterThan=" + SMALLER_A);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where b equals to DEFAULT_B
        defaultCatalogcolorsShouldBeFound("b.equals=" + DEFAULT_B);

        // Get all the catalogcolorsList where b equals to UPDATED_B
        defaultCatalogcolorsShouldNotBeFound("b.equals=" + UPDATED_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where b in DEFAULT_B or UPDATED_B
        defaultCatalogcolorsShouldBeFound("b.in=" + DEFAULT_B + "," + UPDATED_B);

        // Get all the catalogcolorsList where b equals to UPDATED_B
        defaultCatalogcolorsShouldNotBeFound("b.in=" + UPDATED_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where b is not null
        defaultCatalogcolorsShouldBeFound("b.specified=true");

        // Get all the catalogcolorsList where b is null
        defaultCatalogcolorsShouldNotBeFound("b.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where b is greater than or equal to DEFAULT_B
        defaultCatalogcolorsShouldBeFound("b.greaterThanOrEqual=" + DEFAULT_B);

        // Get all the catalogcolorsList where b is greater than or equal to UPDATED_B
        defaultCatalogcolorsShouldNotBeFound("b.greaterThanOrEqual=" + UPDATED_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where b is less than or equal to DEFAULT_B
        defaultCatalogcolorsShouldBeFound("b.lessThanOrEqual=" + DEFAULT_B);

        // Get all the catalogcolorsList where b is less than or equal to SMALLER_B
        defaultCatalogcolorsShouldNotBeFound("b.lessThanOrEqual=" + SMALLER_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where b is less than DEFAULT_B
        defaultCatalogcolorsShouldNotBeFound("b.lessThan=" + DEFAULT_B);

        // Get all the catalogcolorsList where b is less than UPDATED_B
        defaultCatalogcolorsShouldBeFound("b.lessThan=" + UPDATED_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByBIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where b is greater than DEFAULT_B
        defaultCatalogcolorsShouldNotBeFound("b.greaterThan=" + DEFAULT_B);

        // Get all the catalogcolorsList where b is greater than SMALLER_B
        defaultCatalogcolorsShouldBeFound("b.greaterThan=" + SMALLER_B);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByCIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where c equals to DEFAULT_C
        defaultCatalogcolorsShouldBeFound("c.equals=" + DEFAULT_C);

        // Get all the catalogcolorsList where c equals to UPDATED_C
        defaultCatalogcolorsShouldNotBeFound("c.equals=" + UPDATED_C);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByCIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where c in DEFAULT_C or UPDATED_C
        defaultCatalogcolorsShouldBeFound("c.in=" + DEFAULT_C + "," + UPDATED_C);

        // Get all the catalogcolorsList where c equals to UPDATED_C
        defaultCatalogcolorsShouldNotBeFound("c.in=" + UPDATED_C);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByCIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where c is not null
        defaultCatalogcolorsShouldBeFound("c.specified=true");

        // Get all the catalogcolorsList where c is null
        defaultCatalogcolorsShouldNotBeFound("c.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByCIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where c is greater than or equal to DEFAULT_C
        defaultCatalogcolorsShouldBeFound("c.greaterThanOrEqual=" + DEFAULT_C);

        // Get all the catalogcolorsList where c is greater than or equal to UPDATED_C
        defaultCatalogcolorsShouldNotBeFound("c.greaterThanOrEqual=" + UPDATED_C);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByCIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where c is less than or equal to DEFAULT_C
        defaultCatalogcolorsShouldBeFound("c.lessThanOrEqual=" + DEFAULT_C);

        // Get all the catalogcolorsList where c is less than or equal to SMALLER_C
        defaultCatalogcolorsShouldNotBeFound("c.lessThanOrEqual=" + SMALLER_C);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByCIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where c is less than DEFAULT_C
        defaultCatalogcolorsShouldNotBeFound("c.lessThan=" + DEFAULT_C);

        // Get all the catalogcolorsList where c is less than UPDATED_C
        defaultCatalogcolorsShouldBeFound("c.lessThan=" + UPDATED_C);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByCIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where c is greater than DEFAULT_C
        defaultCatalogcolorsShouldNotBeFound("c.greaterThan=" + DEFAULT_C);

        // Get all the catalogcolorsList where c is greater than SMALLER_C
        defaultCatalogcolorsShouldBeFound("c.greaterThan=" + SMALLER_C);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByDIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where d equals to DEFAULT_D
        defaultCatalogcolorsShouldBeFound("d.equals=" + DEFAULT_D);

        // Get all the catalogcolorsList where d equals to UPDATED_D
        defaultCatalogcolorsShouldNotBeFound("d.equals=" + UPDATED_D);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByDIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where d in DEFAULT_D or UPDATED_D
        defaultCatalogcolorsShouldBeFound("d.in=" + DEFAULT_D + "," + UPDATED_D);

        // Get all the catalogcolorsList where d equals to UPDATED_D
        defaultCatalogcolorsShouldNotBeFound("d.in=" + UPDATED_D);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByDIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where d is not null
        defaultCatalogcolorsShouldBeFound("d.specified=true");

        // Get all the catalogcolorsList where d is null
        defaultCatalogcolorsShouldNotBeFound("d.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByDIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where d is greater than or equal to DEFAULT_D
        defaultCatalogcolorsShouldBeFound("d.greaterThanOrEqual=" + DEFAULT_D);

        // Get all the catalogcolorsList where d is greater than or equal to UPDATED_D
        defaultCatalogcolorsShouldNotBeFound("d.greaterThanOrEqual=" + UPDATED_D);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByDIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where d is less than or equal to DEFAULT_D
        defaultCatalogcolorsShouldBeFound("d.lessThanOrEqual=" + DEFAULT_D);

        // Get all the catalogcolorsList where d is less than or equal to SMALLER_D
        defaultCatalogcolorsShouldNotBeFound("d.lessThanOrEqual=" + SMALLER_D);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByDIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where d is less than DEFAULT_D
        defaultCatalogcolorsShouldNotBeFound("d.lessThan=" + DEFAULT_D);

        // Get all the catalogcolorsList where d is less than UPDATED_D
        defaultCatalogcolorsShouldBeFound("d.lessThan=" + UPDATED_D);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByDIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where d is greater than DEFAULT_D
        defaultCatalogcolorsShouldNotBeFound("d.greaterThan=" + DEFAULT_D);

        // Get all the catalogcolorsList where d is greater than SMALLER_D
        defaultCatalogcolorsShouldBeFound("d.greaterThan=" + SMALLER_D);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByEIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where e equals to DEFAULT_E
        defaultCatalogcolorsShouldBeFound("e.equals=" + DEFAULT_E);

        // Get all the catalogcolorsList where e equals to UPDATED_E
        defaultCatalogcolorsShouldNotBeFound("e.equals=" + UPDATED_E);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByEIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where e in DEFAULT_E or UPDATED_E
        defaultCatalogcolorsShouldBeFound("e.in=" + DEFAULT_E + "," + UPDATED_E);

        // Get all the catalogcolorsList where e equals to UPDATED_E
        defaultCatalogcolorsShouldNotBeFound("e.in=" + UPDATED_E);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByEIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where e is not null
        defaultCatalogcolorsShouldBeFound("e.specified=true");

        // Get all the catalogcolorsList where e is null
        defaultCatalogcolorsShouldNotBeFound("e.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByEIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where e is greater than or equal to DEFAULT_E
        defaultCatalogcolorsShouldBeFound("e.greaterThanOrEqual=" + DEFAULT_E);

        // Get all the catalogcolorsList where e is greater than or equal to UPDATED_E
        defaultCatalogcolorsShouldNotBeFound("e.greaterThanOrEqual=" + UPDATED_E);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByEIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where e is less than or equal to DEFAULT_E
        defaultCatalogcolorsShouldBeFound("e.lessThanOrEqual=" + DEFAULT_E);

        // Get all the catalogcolorsList where e is less than or equal to SMALLER_E
        defaultCatalogcolorsShouldNotBeFound("e.lessThanOrEqual=" + SMALLER_E);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByEIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where e is less than DEFAULT_E
        defaultCatalogcolorsShouldNotBeFound("e.lessThan=" + DEFAULT_E);

        // Get all the catalogcolorsList where e is less than UPDATED_E
        defaultCatalogcolorsShouldBeFound("e.lessThan=" + UPDATED_E);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByEIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where e is greater than DEFAULT_E
        defaultCatalogcolorsShouldNotBeFound("e.greaterThan=" + DEFAULT_E);

        // Get all the catalogcolorsList where e is greater than SMALLER_E
        defaultCatalogcolorsShouldBeFound("e.greaterThan=" + SMALLER_E);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByFIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where f equals to DEFAULT_F
        defaultCatalogcolorsShouldBeFound("f.equals=" + DEFAULT_F);

        // Get all the catalogcolorsList where f equals to UPDATED_F
        defaultCatalogcolorsShouldNotBeFound("f.equals=" + UPDATED_F);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByFIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where f in DEFAULT_F or UPDATED_F
        defaultCatalogcolorsShouldBeFound("f.in=" + DEFAULT_F + "," + UPDATED_F);

        // Get all the catalogcolorsList where f equals to UPDATED_F
        defaultCatalogcolorsShouldNotBeFound("f.in=" + UPDATED_F);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByFIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where f is not null
        defaultCatalogcolorsShouldBeFound("f.specified=true");

        // Get all the catalogcolorsList where f is null
        defaultCatalogcolorsShouldNotBeFound("f.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByFIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where f is greater than or equal to DEFAULT_F
        defaultCatalogcolorsShouldBeFound("f.greaterThanOrEqual=" + DEFAULT_F);

        // Get all the catalogcolorsList where f is greater than or equal to UPDATED_F
        defaultCatalogcolorsShouldNotBeFound("f.greaterThanOrEqual=" + UPDATED_F);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByFIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where f is less than or equal to DEFAULT_F
        defaultCatalogcolorsShouldBeFound("f.lessThanOrEqual=" + DEFAULT_F);

        // Get all the catalogcolorsList where f is less than or equal to SMALLER_F
        defaultCatalogcolorsShouldNotBeFound("f.lessThanOrEqual=" + SMALLER_F);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByFIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where f is less than DEFAULT_F
        defaultCatalogcolorsShouldNotBeFound("f.lessThan=" + DEFAULT_F);

        // Get all the catalogcolorsList where f is less than UPDATED_F
        defaultCatalogcolorsShouldBeFound("f.lessThan=" + UPDATED_F);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByFIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where f is greater than DEFAULT_F
        defaultCatalogcolorsShouldNotBeFound("f.greaterThan=" + DEFAULT_F);

        // Get all the catalogcolorsList where f is greater than SMALLER_F
        defaultCatalogcolorsShouldBeFound("f.greaterThan=" + SMALLER_F);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByGIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where g equals to DEFAULT_G
        defaultCatalogcolorsShouldBeFound("g.equals=" + DEFAULT_G);

        // Get all the catalogcolorsList where g equals to UPDATED_G
        defaultCatalogcolorsShouldNotBeFound("g.equals=" + UPDATED_G);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByGIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where g in DEFAULT_G or UPDATED_G
        defaultCatalogcolorsShouldBeFound("g.in=" + DEFAULT_G + "," + UPDATED_G);

        // Get all the catalogcolorsList where g equals to UPDATED_G
        defaultCatalogcolorsShouldNotBeFound("g.in=" + UPDATED_G);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByGIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where g is not null
        defaultCatalogcolorsShouldBeFound("g.specified=true");

        // Get all the catalogcolorsList where g is null
        defaultCatalogcolorsShouldNotBeFound("g.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByGIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where g is greater than or equal to DEFAULT_G
        defaultCatalogcolorsShouldBeFound("g.greaterThanOrEqual=" + DEFAULT_G);

        // Get all the catalogcolorsList where g is greater than or equal to UPDATED_G
        defaultCatalogcolorsShouldNotBeFound("g.greaterThanOrEqual=" + UPDATED_G);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByGIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where g is less than or equal to DEFAULT_G
        defaultCatalogcolorsShouldBeFound("g.lessThanOrEqual=" + DEFAULT_G);

        // Get all the catalogcolorsList where g is less than or equal to SMALLER_G
        defaultCatalogcolorsShouldNotBeFound("g.lessThanOrEqual=" + SMALLER_G);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByGIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where g is less than DEFAULT_G
        defaultCatalogcolorsShouldNotBeFound("g.lessThan=" + DEFAULT_G);

        // Get all the catalogcolorsList where g is less than UPDATED_G
        defaultCatalogcolorsShouldBeFound("g.lessThan=" + UPDATED_G);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByGIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where g is greater than DEFAULT_G
        defaultCatalogcolorsShouldNotBeFound("g.greaterThan=" + DEFAULT_G);

        // Get all the catalogcolorsList where g is greater than SMALLER_G
        defaultCatalogcolorsShouldBeFound("g.greaterThan=" + SMALLER_G);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByHIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where h equals to DEFAULT_H
        defaultCatalogcolorsShouldBeFound("h.equals=" + DEFAULT_H);

        // Get all the catalogcolorsList where h equals to UPDATED_H
        defaultCatalogcolorsShouldNotBeFound("h.equals=" + UPDATED_H);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByHIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where h in DEFAULT_H or UPDATED_H
        defaultCatalogcolorsShouldBeFound("h.in=" + DEFAULT_H + "," + UPDATED_H);

        // Get all the catalogcolorsList where h equals to UPDATED_H
        defaultCatalogcolorsShouldNotBeFound("h.in=" + UPDATED_H);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByHIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where h is not null
        defaultCatalogcolorsShouldBeFound("h.specified=true");

        // Get all the catalogcolorsList where h is null
        defaultCatalogcolorsShouldNotBeFound("h.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByHIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where h is greater than or equal to DEFAULT_H
        defaultCatalogcolorsShouldBeFound("h.greaterThanOrEqual=" + DEFAULT_H);

        // Get all the catalogcolorsList where h is greater than or equal to UPDATED_H
        defaultCatalogcolorsShouldNotBeFound("h.greaterThanOrEqual=" + UPDATED_H);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByHIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where h is less than or equal to DEFAULT_H
        defaultCatalogcolorsShouldBeFound("h.lessThanOrEqual=" + DEFAULT_H);

        // Get all the catalogcolorsList where h is less than or equal to SMALLER_H
        defaultCatalogcolorsShouldNotBeFound("h.lessThanOrEqual=" + SMALLER_H);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByHIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where h is less than DEFAULT_H
        defaultCatalogcolorsShouldNotBeFound("h.lessThan=" + DEFAULT_H);

        // Get all the catalogcolorsList where h is less than UPDATED_H
        defaultCatalogcolorsShouldBeFound("h.lessThan=" + UPDATED_H);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByHIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where h is greater than DEFAULT_H
        defaultCatalogcolorsShouldNotBeFound("h.greaterThan=" + DEFAULT_H);

        // Get all the catalogcolorsList where h is greater than SMALLER_H
        defaultCatalogcolorsShouldBeFound("h.greaterThan=" + SMALLER_H);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByIIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where i equals to DEFAULT_I
        defaultCatalogcolorsShouldBeFound("i.equals=" + DEFAULT_I);

        // Get all the catalogcolorsList where i equals to UPDATED_I
        defaultCatalogcolorsShouldNotBeFound("i.equals=" + UPDATED_I);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByIIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where i in DEFAULT_I or UPDATED_I
        defaultCatalogcolorsShouldBeFound("i.in=" + DEFAULT_I + "," + UPDATED_I);

        // Get all the catalogcolorsList where i equals to UPDATED_I
        defaultCatalogcolorsShouldNotBeFound("i.in=" + UPDATED_I);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByIIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where i is not null
        defaultCatalogcolorsShouldBeFound("i.specified=true");

        // Get all the catalogcolorsList where i is null
        defaultCatalogcolorsShouldNotBeFound("i.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByIIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where i is greater than or equal to DEFAULT_I
        defaultCatalogcolorsShouldBeFound("i.greaterThanOrEqual=" + DEFAULT_I);

        // Get all the catalogcolorsList where i is greater than or equal to UPDATED_I
        defaultCatalogcolorsShouldNotBeFound("i.greaterThanOrEqual=" + UPDATED_I);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByIIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where i is less than or equal to DEFAULT_I
        defaultCatalogcolorsShouldBeFound("i.lessThanOrEqual=" + DEFAULT_I);

        // Get all the catalogcolorsList where i is less than or equal to SMALLER_I
        defaultCatalogcolorsShouldNotBeFound("i.lessThanOrEqual=" + SMALLER_I);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByIIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where i is less than DEFAULT_I
        defaultCatalogcolorsShouldNotBeFound("i.lessThan=" + DEFAULT_I);

        // Get all the catalogcolorsList where i is less than UPDATED_I
        defaultCatalogcolorsShouldBeFound("i.lessThan=" + UPDATED_I);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByIIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where i is greater than DEFAULT_I
        defaultCatalogcolorsShouldNotBeFound("i.greaterThan=" + DEFAULT_I);

        // Get all the catalogcolorsList where i is greater than SMALLER_I
        defaultCatalogcolorsShouldBeFound("i.greaterThan=" + SMALLER_I);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByJIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where j equals to DEFAULT_J
        defaultCatalogcolorsShouldBeFound("j.equals=" + DEFAULT_J);

        // Get all the catalogcolorsList where j equals to UPDATED_J
        defaultCatalogcolorsShouldNotBeFound("j.equals=" + UPDATED_J);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByJIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where j in DEFAULT_J or UPDATED_J
        defaultCatalogcolorsShouldBeFound("j.in=" + DEFAULT_J + "," + UPDATED_J);

        // Get all the catalogcolorsList where j equals to UPDATED_J
        defaultCatalogcolorsShouldNotBeFound("j.in=" + UPDATED_J);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByJIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where j is not null
        defaultCatalogcolorsShouldBeFound("j.specified=true");

        // Get all the catalogcolorsList where j is null
        defaultCatalogcolorsShouldNotBeFound("j.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByJIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where j is greater than or equal to DEFAULT_J
        defaultCatalogcolorsShouldBeFound("j.greaterThanOrEqual=" + DEFAULT_J);

        // Get all the catalogcolorsList where j is greater than or equal to UPDATED_J
        defaultCatalogcolorsShouldNotBeFound("j.greaterThanOrEqual=" + UPDATED_J);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByJIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where j is less than or equal to DEFAULT_J
        defaultCatalogcolorsShouldBeFound("j.lessThanOrEqual=" + DEFAULT_J);

        // Get all the catalogcolorsList where j is less than or equal to SMALLER_J
        defaultCatalogcolorsShouldNotBeFound("j.lessThanOrEqual=" + SMALLER_J);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByJIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where j is less than DEFAULT_J
        defaultCatalogcolorsShouldNotBeFound("j.lessThan=" + DEFAULT_J);

        // Get all the catalogcolorsList where j is less than UPDATED_J
        defaultCatalogcolorsShouldBeFound("j.lessThan=" + UPDATED_J);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByJIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where j is greater than DEFAULT_J
        defaultCatalogcolorsShouldNotBeFound("j.greaterThan=" + DEFAULT_J);

        // Get all the catalogcolorsList where j is greater than SMALLER_J
        defaultCatalogcolorsShouldBeFound("j.greaterThan=" + SMALLER_J);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByKIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where k equals to DEFAULT_K
        defaultCatalogcolorsShouldBeFound("k.equals=" + DEFAULT_K);

        // Get all the catalogcolorsList where k equals to UPDATED_K
        defaultCatalogcolorsShouldNotBeFound("k.equals=" + UPDATED_K);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByKIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where k in DEFAULT_K or UPDATED_K
        defaultCatalogcolorsShouldBeFound("k.in=" + DEFAULT_K + "," + UPDATED_K);

        // Get all the catalogcolorsList where k equals to UPDATED_K
        defaultCatalogcolorsShouldNotBeFound("k.in=" + UPDATED_K);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByKIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where k is not null
        defaultCatalogcolorsShouldBeFound("k.specified=true");

        // Get all the catalogcolorsList where k is null
        defaultCatalogcolorsShouldNotBeFound("k.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByKIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where k is greater than or equal to DEFAULT_K
        defaultCatalogcolorsShouldBeFound("k.greaterThanOrEqual=" + DEFAULT_K);

        // Get all the catalogcolorsList where k is greater than or equal to UPDATED_K
        defaultCatalogcolorsShouldNotBeFound("k.greaterThanOrEqual=" + UPDATED_K);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByKIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where k is less than or equal to DEFAULT_K
        defaultCatalogcolorsShouldBeFound("k.lessThanOrEqual=" + DEFAULT_K);

        // Get all the catalogcolorsList where k is less than or equal to SMALLER_K
        defaultCatalogcolorsShouldNotBeFound("k.lessThanOrEqual=" + SMALLER_K);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByKIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where k is less than DEFAULT_K
        defaultCatalogcolorsShouldNotBeFound("k.lessThan=" + DEFAULT_K);

        // Get all the catalogcolorsList where k is less than UPDATED_K
        defaultCatalogcolorsShouldBeFound("k.lessThan=" + UPDATED_K);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByKIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where k is greater than DEFAULT_K
        defaultCatalogcolorsShouldNotBeFound("k.greaterThan=" + DEFAULT_K);

        // Get all the catalogcolorsList where k is greater than SMALLER_K
        defaultCatalogcolorsShouldBeFound("k.greaterThan=" + SMALLER_K);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where l equals to DEFAULT_L
        defaultCatalogcolorsShouldBeFound("l.equals=" + DEFAULT_L);

        // Get all the catalogcolorsList where l equals to UPDATED_L
        defaultCatalogcolorsShouldNotBeFound("l.equals=" + UPDATED_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where l in DEFAULT_L or UPDATED_L
        defaultCatalogcolorsShouldBeFound("l.in=" + DEFAULT_L + "," + UPDATED_L);

        // Get all the catalogcolorsList where l equals to UPDATED_L
        defaultCatalogcolorsShouldNotBeFound("l.in=" + UPDATED_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where l is not null
        defaultCatalogcolorsShouldBeFound("l.specified=true");

        // Get all the catalogcolorsList where l is null
        defaultCatalogcolorsShouldNotBeFound("l.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where l is greater than or equal to DEFAULT_L
        defaultCatalogcolorsShouldBeFound("l.greaterThanOrEqual=" + DEFAULT_L);

        // Get all the catalogcolorsList where l is greater than or equal to UPDATED_L
        defaultCatalogcolorsShouldNotBeFound("l.greaterThanOrEqual=" + UPDATED_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where l is less than or equal to DEFAULT_L
        defaultCatalogcolorsShouldBeFound("l.lessThanOrEqual=" + DEFAULT_L);

        // Get all the catalogcolorsList where l is less than or equal to SMALLER_L
        defaultCatalogcolorsShouldNotBeFound("l.lessThanOrEqual=" + SMALLER_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where l is less than DEFAULT_L
        defaultCatalogcolorsShouldNotBeFound("l.lessThan=" + DEFAULT_L);

        // Get all the catalogcolorsList where l is less than UPDATED_L
        defaultCatalogcolorsShouldBeFound("l.lessThan=" + UPDATED_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByLIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where l is greater than DEFAULT_L
        defaultCatalogcolorsShouldNotBeFound("l.greaterThan=" + DEFAULT_L);

        // Get all the catalogcolorsList where l is greater than SMALLER_L
        defaultCatalogcolorsShouldBeFound("l.greaterThan=" + SMALLER_L);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByMIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where m equals to DEFAULT_M
        defaultCatalogcolorsShouldBeFound("m.equals=" + DEFAULT_M);

        // Get all the catalogcolorsList where m equals to UPDATED_M
        defaultCatalogcolorsShouldNotBeFound("m.equals=" + UPDATED_M);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByMIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where m in DEFAULT_M or UPDATED_M
        defaultCatalogcolorsShouldBeFound("m.in=" + DEFAULT_M + "," + UPDATED_M);

        // Get all the catalogcolorsList where m equals to UPDATED_M
        defaultCatalogcolorsShouldNotBeFound("m.in=" + UPDATED_M);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByMIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where m is not null
        defaultCatalogcolorsShouldBeFound("m.specified=true");

        // Get all the catalogcolorsList where m is null
        defaultCatalogcolorsShouldNotBeFound("m.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByMIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where m is greater than or equal to DEFAULT_M
        defaultCatalogcolorsShouldBeFound("m.greaterThanOrEqual=" + DEFAULT_M);

        // Get all the catalogcolorsList where m is greater than or equal to UPDATED_M
        defaultCatalogcolorsShouldNotBeFound("m.greaterThanOrEqual=" + UPDATED_M);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByMIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where m is less than or equal to DEFAULT_M
        defaultCatalogcolorsShouldBeFound("m.lessThanOrEqual=" + DEFAULT_M);

        // Get all the catalogcolorsList where m is less than or equal to SMALLER_M
        defaultCatalogcolorsShouldNotBeFound("m.lessThanOrEqual=" + SMALLER_M);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByMIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where m is less than DEFAULT_M
        defaultCatalogcolorsShouldNotBeFound("m.lessThan=" + DEFAULT_M);

        // Get all the catalogcolorsList where m is less than UPDATED_M
        defaultCatalogcolorsShouldBeFound("m.lessThan=" + UPDATED_M);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByMIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where m is greater than DEFAULT_M
        defaultCatalogcolorsShouldNotBeFound("m.greaterThan=" + DEFAULT_M);

        // Get all the catalogcolorsList where m is greater than SMALLER_M
        defaultCatalogcolorsShouldBeFound("m.greaterThan=" + SMALLER_M);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByNIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where n equals to DEFAULT_N
        defaultCatalogcolorsShouldBeFound("n.equals=" + DEFAULT_N);

        // Get all the catalogcolorsList where n equals to UPDATED_N
        defaultCatalogcolorsShouldNotBeFound("n.equals=" + UPDATED_N);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByNIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where n in DEFAULT_N or UPDATED_N
        defaultCatalogcolorsShouldBeFound("n.in=" + DEFAULT_N + "," + UPDATED_N);

        // Get all the catalogcolorsList where n equals to UPDATED_N
        defaultCatalogcolorsShouldNotBeFound("n.in=" + UPDATED_N);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByNIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where n is not null
        defaultCatalogcolorsShouldBeFound("n.specified=true");

        // Get all the catalogcolorsList where n is null
        defaultCatalogcolorsShouldNotBeFound("n.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByNIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where n is greater than or equal to DEFAULT_N
        defaultCatalogcolorsShouldBeFound("n.greaterThanOrEqual=" + DEFAULT_N);

        // Get all the catalogcolorsList where n is greater than or equal to UPDATED_N
        defaultCatalogcolorsShouldNotBeFound("n.greaterThanOrEqual=" + UPDATED_N);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByNIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where n is less than or equal to DEFAULT_N
        defaultCatalogcolorsShouldBeFound("n.lessThanOrEqual=" + DEFAULT_N);

        // Get all the catalogcolorsList where n is less than or equal to SMALLER_N
        defaultCatalogcolorsShouldNotBeFound("n.lessThanOrEqual=" + SMALLER_N);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByNIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where n is less than DEFAULT_N
        defaultCatalogcolorsShouldNotBeFound("n.lessThan=" + DEFAULT_N);

        // Get all the catalogcolorsList where n is less than UPDATED_N
        defaultCatalogcolorsShouldBeFound("n.lessThan=" + UPDATED_N);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByNIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where n is greater than DEFAULT_N
        defaultCatalogcolorsShouldNotBeFound("n.greaterThan=" + DEFAULT_N);

        // Get all the catalogcolorsList where n is greater than SMALLER_N
        defaultCatalogcolorsShouldBeFound("n.greaterThan=" + SMALLER_N);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByOIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where o equals to DEFAULT_O
        defaultCatalogcolorsShouldBeFound("o.equals=" + DEFAULT_O);

        // Get all the catalogcolorsList where o equals to UPDATED_O
        defaultCatalogcolorsShouldNotBeFound("o.equals=" + UPDATED_O);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByOIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where o in DEFAULT_O or UPDATED_O
        defaultCatalogcolorsShouldBeFound("o.in=" + DEFAULT_O + "," + UPDATED_O);

        // Get all the catalogcolorsList where o equals to UPDATED_O
        defaultCatalogcolorsShouldNotBeFound("o.in=" + UPDATED_O);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByOIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where o is not null
        defaultCatalogcolorsShouldBeFound("o.specified=true");

        // Get all the catalogcolorsList where o is null
        defaultCatalogcolorsShouldNotBeFound("o.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByOIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where o is greater than or equal to DEFAULT_O
        defaultCatalogcolorsShouldBeFound("o.greaterThanOrEqual=" + DEFAULT_O);

        // Get all the catalogcolorsList where o is greater than or equal to UPDATED_O
        defaultCatalogcolorsShouldNotBeFound("o.greaterThanOrEqual=" + UPDATED_O);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByOIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where o is less than or equal to DEFAULT_O
        defaultCatalogcolorsShouldBeFound("o.lessThanOrEqual=" + DEFAULT_O);

        // Get all the catalogcolorsList where o is less than or equal to SMALLER_O
        defaultCatalogcolorsShouldNotBeFound("o.lessThanOrEqual=" + SMALLER_O);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByOIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where o is less than DEFAULT_O
        defaultCatalogcolorsShouldNotBeFound("o.lessThan=" + DEFAULT_O);

        // Get all the catalogcolorsList where o is less than UPDATED_O
        defaultCatalogcolorsShouldBeFound("o.lessThan=" + UPDATED_O);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByOIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where o is greater than DEFAULT_O
        defaultCatalogcolorsShouldNotBeFound("o.greaterThan=" + DEFAULT_O);

        // Get all the catalogcolorsList where o is greater than SMALLER_O
        defaultCatalogcolorsShouldBeFound("o.greaterThan=" + SMALLER_O);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByPIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where p equals to DEFAULT_P
        defaultCatalogcolorsShouldBeFound("p.equals=" + DEFAULT_P);

        // Get all the catalogcolorsList where p equals to UPDATED_P
        defaultCatalogcolorsShouldNotBeFound("p.equals=" + UPDATED_P);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByPIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where p in DEFAULT_P or UPDATED_P
        defaultCatalogcolorsShouldBeFound("p.in=" + DEFAULT_P + "," + UPDATED_P);

        // Get all the catalogcolorsList where p equals to UPDATED_P
        defaultCatalogcolorsShouldNotBeFound("p.in=" + UPDATED_P);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByPIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where p is not null
        defaultCatalogcolorsShouldBeFound("p.specified=true");

        // Get all the catalogcolorsList where p is null
        defaultCatalogcolorsShouldNotBeFound("p.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByPIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where p is greater than or equal to DEFAULT_P
        defaultCatalogcolorsShouldBeFound("p.greaterThanOrEqual=" + DEFAULT_P);

        // Get all the catalogcolorsList where p is greater than or equal to UPDATED_P
        defaultCatalogcolorsShouldNotBeFound("p.greaterThanOrEqual=" + UPDATED_P);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByPIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where p is less than or equal to DEFAULT_P
        defaultCatalogcolorsShouldBeFound("p.lessThanOrEqual=" + DEFAULT_P);

        // Get all the catalogcolorsList where p is less than or equal to SMALLER_P
        defaultCatalogcolorsShouldNotBeFound("p.lessThanOrEqual=" + SMALLER_P);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByPIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where p is less than DEFAULT_P
        defaultCatalogcolorsShouldNotBeFound("p.lessThan=" + DEFAULT_P);

        // Get all the catalogcolorsList where p is less than UPDATED_P
        defaultCatalogcolorsShouldBeFound("p.lessThan=" + UPDATED_P);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByPIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where p is greater than DEFAULT_P
        defaultCatalogcolorsShouldNotBeFound("p.greaterThan=" + DEFAULT_P);

        // Get all the catalogcolorsList where p is greater than SMALLER_P
        defaultCatalogcolorsShouldBeFound("p.greaterThan=" + SMALLER_P);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByQIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where q equals to DEFAULT_Q
        defaultCatalogcolorsShouldBeFound("q.equals=" + DEFAULT_Q);

        // Get all the catalogcolorsList where q equals to UPDATED_Q
        defaultCatalogcolorsShouldNotBeFound("q.equals=" + UPDATED_Q);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByQIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where q in DEFAULT_Q or UPDATED_Q
        defaultCatalogcolorsShouldBeFound("q.in=" + DEFAULT_Q + "," + UPDATED_Q);

        // Get all the catalogcolorsList where q equals to UPDATED_Q
        defaultCatalogcolorsShouldNotBeFound("q.in=" + UPDATED_Q);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByQIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where q is not null
        defaultCatalogcolorsShouldBeFound("q.specified=true");

        // Get all the catalogcolorsList where q is null
        defaultCatalogcolorsShouldNotBeFound("q.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByQIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where q is greater than or equal to DEFAULT_Q
        defaultCatalogcolorsShouldBeFound("q.greaterThanOrEqual=" + DEFAULT_Q);

        // Get all the catalogcolorsList where q is greater than or equal to UPDATED_Q
        defaultCatalogcolorsShouldNotBeFound("q.greaterThanOrEqual=" + UPDATED_Q);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByQIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where q is less than or equal to DEFAULT_Q
        defaultCatalogcolorsShouldBeFound("q.lessThanOrEqual=" + DEFAULT_Q);

        // Get all the catalogcolorsList where q is less than or equal to SMALLER_Q
        defaultCatalogcolorsShouldNotBeFound("q.lessThanOrEqual=" + SMALLER_Q);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByQIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where q is less than DEFAULT_Q
        defaultCatalogcolorsShouldNotBeFound("q.lessThan=" + DEFAULT_Q);

        // Get all the catalogcolorsList where q is less than UPDATED_Q
        defaultCatalogcolorsShouldBeFound("q.lessThan=" + UPDATED_Q);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByQIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where q is greater than DEFAULT_Q
        defaultCatalogcolorsShouldNotBeFound("q.greaterThan=" + DEFAULT_Q);

        // Get all the catalogcolorsList where q is greater than SMALLER_Q
        defaultCatalogcolorsShouldBeFound("q.greaterThan=" + SMALLER_Q);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByRIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where r equals to DEFAULT_R
        defaultCatalogcolorsShouldBeFound("r.equals=" + DEFAULT_R);

        // Get all the catalogcolorsList where r equals to UPDATED_R
        defaultCatalogcolorsShouldNotBeFound("r.equals=" + UPDATED_R);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByRIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where r in DEFAULT_R or UPDATED_R
        defaultCatalogcolorsShouldBeFound("r.in=" + DEFAULT_R + "," + UPDATED_R);

        // Get all the catalogcolorsList where r equals to UPDATED_R
        defaultCatalogcolorsShouldNotBeFound("r.in=" + UPDATED_R);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByRIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where r is not null
        defaultCatalogcolorsShouldBeFound("r.specified=true");

        // Get all the catalogcolorsList where r is null
        defaultCatalogcolorsShouldNotBeFound("r.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByRIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where r is greater than or equal to DEFAULT_R
        defaultCatalogcolorsShouldBeFound("r.greaterThanOrEqual=" + DEFAULT_R);

        // Get all the catalogcolorsList where r is greater than or equal to UPDATED_R
        defaultCatalogcolorsShouldNotBeFound("r.greaterThanOrEqual=" + UPDATED_R);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByRIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where r is less than or equal to DEFAULT_R
        defaultCatalogcolorsShouldBeFound("r.lessThanOrEqual=" + DEFAULT_R);

        // Get all the catalogcolorsList where r is less than or equal to SMALLER_R
        defaultCatalogcolorsShouldNotBeFound("r.lessThanOrEqual=" + SMALLER_R);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByRIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where r is less than DEFAULT_R
        defaultCatalogcolorsShouldNotBeFound("r.lessThan=" + DEFAULT_R);

        // Get all the catalogcolorsList where r is less than UPDATED_R
        defaultCatalogcolorsShouldBeFound("r.lessThan=" + UPDATED_R);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByRIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where r is greater than DEFAULT_R
        defaultCatalogcolorsShouldNotBeFound("r.greaterThan=" + DEFAULT_R);

        // Get all the catalogcolorsList where r is greater than SMALLER_R
        defaultCatalogcolorsShouldBeFound("r.greaterThan=" + SMALLER_R);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsBySIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where s equals to DEFAULT_S
        defaultCatalogcolorsShouldBeFound("s.equals=" + DEFAULT_S);

        // Get all the catalogcolorsList where s equals to UPDATED_S
        defaultCatalogcolorsShouldNotBeFound("s.equals=" + UPDATED_S);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsBySIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where s in DEFAULT_S or UPDATED_S
        defaultCatalogcolorsShouldBeFound("s.in=" + DEFAULT_S + "," + UPDATED_S);

        // Get all the catalogcolorsList where s equals to UPDATED_S
        defaultCatalogcolorsShouldNotBeFound("s.in=" + UPDATED_S);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsBySIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where s is not null
        defaultCatalogcolorsShouldBeFound("s.specified=true");

        // Get all the catalogcolorsList where s is null
        defaultCatalogcolorsShouldNotBeFound("s.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsBySIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where s is greater than or equal to DEFAULT_S
        defaultCatalogcolorsShouldBeFound("s.greaterThanOrEqual=" + DEFAULT_S);

        // Get all the catalogcolorsList where s is greater than or equal to UPDATED_S
        defaultCatalogcolorsShouldNotBeFound("s.greaterThanOrEqual=" + UPDATED_S);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsBySIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where s is less than or equal to DEFAULT_S
        defaultCatalogcolorsShouldBeFound("s.lessThanOrEqual=" + DEFAULT_S);

        // Get all the catalogcolorsList where s is less than or equal to SMALLER_S
        defaultCatalogcolorsShouldNotBeFound("s.lessThanOrEqual=" + SMALLER_S);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsBySIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where s is less than DEFAULT_S
        defaultCatalogcolorsShouldNotBeFound("s.lessThan=" + DEFAULT_S);

        // Get all the catalogcolorsList where s is less than UPDATED_S
        defaultCatalogcolorsShouldBeFound("s.lessThan=" + UPDATED_S);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsBySIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where s is greater than DEFAULT_S
        defaultCatalogcolorsShouldNotBeFound("s.greaterThan=" + DEFAULT_S);

        // Get all the catalogcolorsList where s is greater than SMALLER_S
        defaultCatalogcolorsShouldBeFound("s.greaterThan=" + SMALLER_S);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByTIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where t equals to DEFAULT_T
        defaultCatalogcolorsShouldBeFound("t.equals=" + DEFAULT_T);

        // Get all the catalogcolorsList where t equals to UPDATED_T
        defaultCatalogcolorsShouldNotBeFound("t.equals=" + UPDATED_T);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByTIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where t in DEFAULT_T or UPDATED_T
        defaultCatalogcolorsShouldBeFound("t.in=" + DEFAULT_T + "," + UPDATED_T);

        // Get all the catalogcolorsList where t equals to UPDATED_T
        defaultCatalogcolorsShouldNotBeFound("t.in=" + UPDATED_T);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByTIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where t is not null
        defaultCatalogcolorsShouldBeFound("t.specified=true");

        // Get all the catalogcolorsList where t is null
        defaultCatalogcolorsShouldNotBeFound("t.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByTIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where t is greater than or equal to DEFAULT_T
        defaultCatalogcolorsShouldBeFound("t.greaterThanOrEqual=" + DEFAULT_T);

        // Get all the catalogcolorsList where t is greater than or equal to UPDATED_T
        defaultCatalogcolorsShouldNotBeFound("t.greaterThanOrEqual=" + UPDATED_T);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByTIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where t is less than or equal to DEFAULT_T
        defaultCatalogcolorsShouldBeFound("t.lessThanOrEqual=" + DEFAULT_T);

        // Get all the catalogcolorsList where t is less than or equal to SMALLER_T
        defaultCatalogcolorsShouldNotBeFound("t.lessThanOrEqual=" + SMALLER_T);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByTIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where t is less than DEFAULT_T
        defaultCatalogcolorsShouldNotBeFound("t.lessThan=" + DEFAULT_T);

        // Get all the catalogcolorsList where t is less than UPDATED_T
        defaultCatalogcolorsShouldBeFound("t.lessThan=" + UPDATED_T);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByTIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where t is greater than DEFAULT_T
        defaultCatalogcolorsShouldNotBeFound("t.greaterThan=" + DEFAULT_T);

        // Get all the catalogcolorsList where t is greater than SMALLER_T
        defaultCatalogcolorsShouldBeFound("t.greaterThan=" + SMALLER_T);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByUIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where u equals to DEFAULT_U
        defaultCatalogcolorsShouldBeFound("u.equals=" + DEFAULT_U);

        // Get all the catalogcolorsList where u equals to UPDATED_U
        defaultCatalogcolorsShouldNotBeFound("u.equals=" + UPDATED_U);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByUIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where u in DEFAULT_U or UPDATED_U
        defaultCatalogcolorsShouldBeFound("u.in=" + DEFAULT_U + "," + UPDATED_U);

        // Get all the catalogcolorsList where u equals to UPDATED_U
        defaultCatalogcolorsShouldNotBeFound("u.in=" + UPDATED_U);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByUIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where u is not null
        defaultCatalogcolorsShouldBeFound("u.specified=true");

        // Get all the catalogcolorsList where u is null
        defaultCatalogcolorsShouldNotBeFound("u.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByUIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where u is greater than or equal to DEFAULT_U
        defaultCatalogcolorsShouldBeFound("u.greaterThanOrEqual=" + DEFAULT_U);

        // Get all the catalogcolorsList where u is greater than or equal to UPDATED_U
        defaultCatalogcolorsShouldNotBeFound("u.greaterThanOrEqual=" + UPDATED_U);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByUIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where u is less than or equal to DEFAULT_U
        defaultCatalogcolorsShouldBeFound("u.lessThanOrEqual=" + DEFAULT_U);

        // Get all the catalogcolorsList where u is less than or equal to SMALLER_U
        defaultCatalogcolorsShouldNotBeFound("u.lessThanOrEqual=" + SMALLER_U);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByUIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where u is less than DEFAULT_U
        defaultCatalogcolorsShouldNotBeFound("u.lessThan=" + DEFAULT_U);

        // Get all the catalogcolorsList where u is less than UPDATED_U
        defaultCatalogcolorsShouldBeFound("u.lessThan=" + UPDATED_U);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByUIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where u is greater than DEFAULT_U
        defaultCatalogcolorsShouldNotBeFound("u.greaterThan=" + DEFAULT_U);

        // Get all the catalogcolorsList where u is greater than SMALLER_U
        defaultCatalogcolorsShouldBeFound("u.greaterThan=" + SMALLER_U);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByVIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where v equals to DEFAULT_V
        defaultCatalogcolorsShouldBeFound("v.equals=" + DEFAULT_V);

        // Get all the catalogcolorsList where v equals to UPDATED_V
        defaultCatalogcolorsShouldNotBeFound("v.equals=" + UPDATED_V);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByVIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where v in DEFAULT_V or UPDATED_V
        defaultCatalogcolorsShouldBeFound("v.in=" + DEFAULT_V + "," + UPDATED_V);

        // Get all the catalogcolorsList where v equals to UPDATED_V
        defaultCatalogcolorsShouldNotBeFound("v.in=" + UPDATED_V);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByVIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where v is not null
        defaultCatalogcolorsShouldBeFound("v.specified=true");

        // Get all the catalogcolorsList where v is null
        defaultCatalogcolorsShouldNotBeFound("v.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByVIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where v is greater than or equal to DEFAULT_V
        defaultCatalogcolorsShouldBeFound("v.greaterThanOrEqual=" + DEFAULT_V);

        // Get all the catalogcolorsList where v is greater than or equal to UPDATED_V
        defaultCatalogcolorsShouldNotBeFound("v.greaterThanOrEqual=" + UPDATED_V);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByVIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where v is less than or equal to DEFAULT_V
        defaultCatalogcolorsShouldBeFound("v.lessThanOrEqual=" + DEFAULT_V);

        // Get all the catalogcolorsList where v is less than or equal to SMALLER_V
        defaultCatalogcolorsShouldNotBeFound("v.lessThanOrEqual=" + SMALLER_V);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByVIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where v is less than DEFAULT_V
        defaultCatalogcolorsShouldNotBeFound("v.lessThan=" + DEFAULT_V);

        // Get all the catalogcolorsList where v is less than UPDATED_V
        defaultCatalogcolorsShouldBeFound("v.lessThan=" + UPDATED_V);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByVIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where v is greater than DEFAULT_V
        defaultCatalogcolorsShouldNotBeFound("v.greaterThan=" + DEFAULT_V);

        // Get all the catalogcolorsList where v is greater than SMALLER_V
        defaultCatalogcolorsShouldBeFound("v.greaterThan=" + SMALLER_V);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByWIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where w equals to DEFAULT_W
        defaultCatalogcolorsShouldBeFound("w.equals=" + DEFAULT_W);

        // Get all the catalogcolorsList where w equals to UPDATED_W
        defaultCatalogcolorsShouldNotBeFound("w.equals=" + UPDATED_W);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByWIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where w in DEFAULT_W or UPDATED_W
        defaultCatalogcolorsShouldBeFound("w.in=" + DEFAULT_W + "," + UPDATED_W);

        // Get all the catalogcolorsList where w equals to UPDATED_W
        defaultCatalogcolorsShouldNotBeFound("w.in=" + UPDATED_W);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByWIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where w is not null
        defaultCatalogcolorsShouldBeFound("w.specified=true");

        // Get all the catalogcolorsList where w is null
        defaultCatalogcolorsShouldNotBeFound("w.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByWIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where w is greater than or equal to DEFAULT_W
        defaultCatalogcolorsShouldBeFound("w.greaterThanOrEqual=" + DEFAULT_W);

        // Get all the catalogcolorsList where w is greater than or equal to UPDATED_W
        defaultCatalogcolorsShouldNotBeFound("w.greaterThanOrEqual=" + UPDATED_W);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByWIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where w is less than or equal to DEFAULT_W
        defaultCatalogcolorsShouldBeFound("w.lessThanOrEqual=" + DEFAULT_W);

        // Get all the catalogcolorsList where w is less than or equal to SMALLER_W
        defaultCatalogcolorsShouldNotBeFound("w.lessThanOrEqual=" + SMALLER_W);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByWIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where w is less than DEFAULT_W
        defaultCatalogcolorsShouldNotBeFound("w.lessThan=" + DEFAULT_W);

        // Get all the catalogcolorsList where w is less than UPDATED_W
        defaultCatalogcolorsShouldBeFound("w.lessThan=" + UPDATED_W);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByWIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where w is greater than DEFAULT_W
        defaultCatalogcolorsShouldNotBeFound("w.greaterThan=" + DEFAULT_W);

        // Get all the catalogcolorsList where w is greater than SMALLER_W
        defaultCatalogcolorsShouldBeFound("w.greaterThan=" + SMALLER_W);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByXIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where x equals to DEFAULT_X
        defaultCatalogcolorsShouldBeFound("x.equals=" + DEFAULT_X);

        // Get all the catalogcolorsList where x equals to UPDATED_X
        defaultCatalogcolorsShouldNotBeFound("x.equals=" + UPDATED_X);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByXIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where x in DEFAULT_X or UPDATED_X
        defaultCatalogcolorsShouldBeFound("x.in=" + DEFAULT_X + "," + UPDATED_X);

        // Get all the catalogcolorsList where x equals to UPDATED_X
        defaultCatalogcolorsShouldNotBeFound("x.in=" + UPDATED_X);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByXIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where x is not null
        defaultCatalogcolorsShouldBeFound("x.specified=true");

        // Get all the catalogcolorsList where x is null
        defaultCatalogcolorsShouldNotBeFound("x.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByXIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where x is greater than or equal to DEFAULT_X
        defaultCatalogcolorsShouldBeFound("x.greaterThanOrEqual=" + DEFAULT_X);

        // Get all the catalogcolorsList where x is greater than or equal to UPDATED_X
        defaultCatalogcolorsShouldNotBeFound("x.greaterThanOrEqual=" + UPDATED_X);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByXIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where x is less than or equal to DEFAULT_X
        defaultCatalogcolorsShouldBeFound("x.lessThanOrEqual=" + DEFAULT_X);

        // Get all the catalogcolorsList where x is less than or equal to SMALLER_X
        defaultCatalogcolorsShouldNotBeFound("x.lessThanOrEqual=" + SMALLER_X);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByXIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where x is less than DEFAULT_X
        defaultCatalogcolorsShouldNotBeFound("x.lessThan=" + DEFAULT_X);

        // Get all the catalogcolorsList where x is less than UPDATED_X
        defaultCatalogcolorsShouldBeFound("x.lessThan=" + UPDATED_X);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByXIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where x is greater than DEFAULT_X
        defaultCatalogcolorsShouldNotBeFound("x.greaterThan=" + DEFAULT_X);

        // Get all the catalogcolorsList where x is greater than SMALLER_X
        defaultCatalogcolorsShouldBeFound("x.greaterThan=" + SMALLER_X);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByYIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where y equals to DEFAULT_Y
        defaultCatalogcolorsShouldBeFound("y.equals=" + DEFAULT_Y);

        // Get all the catalogcolorsList where y equals to UPDATED_Y
        defaultCatalogcolorsShouldNotBeFound("y.equals=" + UPDATED_Y);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByYIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where y in DEFAULT_Y or UPDATED_Y
        defaultCatalogcolorsShouldBeFound("y.in=" + DEFAULT_Y + "," + UPDATED_Y);

        // Get all the catalogcolorsList where y equals to UPDATED_Y
        defaultCatalogcolorsShouldNotBeFound("y.in=" + UPDATED_Y);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByYIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where y is not null
        defaultCatalogcolorsShouldBeFound("y.specified=true");

        // Get all the catalogcolorsList where y is null
        defaultCatalogcolorsShouldNotBeFound("y.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByYIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where y is greater than or equal to DEFAULT_Y
        defaultCatalogcolorsShouldBeFound("y.greaterThanOrEqual=" + DEFAULT_Y);

        // Get all the catalogcolorsList where y is greater than or equal to UPDATED_Y
        defaultCatalogcolorsShouldNotBeFound("y.greaterThanOrEqual=" + UPDATED_Y);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByYIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where y is less than or equal to DEFAULT_Y
        defaultCatalogcolorsShouldBeFound("y.lessThanOrEqual=" + DEFAULT_Y);

        // Get all the catalogcolorsList where y is less than or equal to SMALLER_Y
        defaultCatalogcolorsShouldNotBeFound("y.lessThanOrEqual=" + SMALLER_Y);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByYIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where y is less than DEFAULT_Y
        defaultCatalogcolorsShouldNotBeFound("y.lessThan=" + DEFAULT_Y);

        // Get all the catalogcolorsList where y is less than UPDATED_Y
        defaultCatalogcolorsShouldBeFound("y.lessThan=" + UPDATED_Y);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByYIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where y is greater than DEFAULT_Y
        defaultCatalogcolorsShouldNotBeFound("y.greaterThan=" + DEFAULT_Y);

        // Get all the catalogcolorsList where y is greater than SMALLER_Y
        defaultCatalogcolorsShouldBeFound("y.greaterThan=" + SMALLER_Y);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByZIsEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where z equals to DEFAULT_Z
        defaultCatalogcolorsShouldBeFound("z.equals=" + DEFAULT_Z);

        // Get all the catalogcolorsList where z equals to UPDATED_Z
        defaultCatalogcolorsShouldNotBeFound("z.equals=" + UPDATED_Z);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByZIsInShouldWork() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where z in DEFAULT_Z or UPDATED_Z
        defaultCatalogcolorsShouldBeFound("z.in=" + DEFAULT_Z + "," + UPDATED_Z);

        // Get all the catalogcolorsList where z equals to UPDATED_Z
        defaultCatalogcolorsShouldNotBeFound("z.in=" + UPDATED_Z);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByZIsNullOrNotNull() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where z is not null
        defaultCatalogcolorsShouldBeFound("z.specified=true");

        // Get all the catalogcolorsList where z is null
        defaultCatalogcolorsShouldNotBeFound("z.specified=false");
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByZIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where z is greater than or equal to DEFAULT_Z
        defaultCatalogcolorsShouldBeFound("z.greaterThanOrEqual=" + DEFAULT_Z);

        // Get all the catalogcolorsList where z is greater than or equal to UPDATED_Z
        defaultCatalogcolorsShouldNotBeFound("z.greaterThanOrEqual=" + UPDATED_Z);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByZIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where z is less than or equal to DEFAULT_Z
        defaultCatalogcolorsShouldBeFound("z.lessThanOrEqual=" + DEFAULT_Z);

        // Get all the catalogcolorsList where z is less than or equal to SMALLER_Z
        defaultCatalogcolorsShouldNotBeFound("z.lessThanOrEqual=" + SMALLER_Z);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByZIsLessThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where z is less than DEFAULT_Z
        defaultCatalogcolorsShouldNotBeFound("z.lessThan=" + DEFAULT_Z);

        // Get all the catalogcolorsList where z is less than UPDATED_Z
        defaultCatalogcolorsShouldBeFound("z.lessThan=" + UPDATED_Z);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByZIsGreaterThanSomething() throws Exception {
        // Initialize the database
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        // Get all the catalogcolorsList where z is greater than DEFAULT_Z
        defaultCatalogcolorsShouldNotBeFound("z.greaterThan=" + DEFAULT_Z);

        // Get all the catalogcolorsList where z is greater than SMALLER_Z
        defaultCatalogcolorsShouldBeFound("z.greaterThan=" + SMALLER_Z);
    }

    @Test
    @Transactional
    void getAllCatalogcolorsByCatalogIsEqualToSomething() throws Exception {
        Catalogs catalog;
        if (TestUtil.findAll(em, Catalogs.class).isEmpty()) {
            catalogcolorsRepository.saveAndFlush(catalogcolors);
            catalog = CatalogsResourceIT.createEntity(em);
        } else {
            catalog = TestUtil.findAll(em, Catalogs.class).get(0);
        }
        em.persist(catalog);
        em.flush();
        catalogcolors.setCatalog(catalog);
        catalogcolorsRepository.saveAndFlush(catalogcolors);
        String catalogId = catalog.getId();
        // Get all the catalogcolorsList where catalog equals to catalogId
        defaultCatalogcolorsShouldBeFound("catalogId.equals=" + catalogId);

        // Get all the catalogcolorsList where catalog equals to "invalid-id"
        defaultCatalogcolorsShouldNotBeFound("catalogId.equals=" + "invalid-id");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCatalogcolorsShouldBeFound(String filter) throws Exception {
        restCatalogcolorsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catalogcolors.getId())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].baseMaterial").value(hasItem(DEFAULT_BASE_MATERIAL.intValue())))
            .andExpect(jsonPath("$.[*].labL").value(hasItem(DEFAULT_LAB_L.doubleValue())))
            .andExpect(jsonPath("$.[*].labA").value(hasItem(DEFAULT_LAB_A.doubleValue())))
            .andExpect(jsonPath("$.[*].labB").value(hasItem(DEFAULT_LAB_B.doubleValue())))
            .andExpect(jsonPath("$.[*].a").value(hasItem(DEFAULT_A.doubleValue())))
            .andExpect(jsonPath("$.[*].b").value(hasItem(DEFAULT_B.doubleValue())))
            .andExpect(jsonPath("$.[*].c").value(hasItem(DEFAULT_C.doubleValue())))
            .andExpect(jsonPath("$.[*].d").value(hasItem(DEFAULT_D.doubleValue())))
            .andExpect(jsonPath("$.[*].e").value(hasItem(DEFAULT_E.doubleValue())))
            .andExpect(jsonPath("$.[*].f").value(hasItem(DEFAULT_F.doubleValue())))
            .andExpect(jsonPath("$.[*].g").value(hasItem(DEFAULT_G.doubleValue())))
            .andExpect(jsonPath("$.[*].h").value(hasItem(DEFAULT_H.doubleValue())))
            .andExpect(jsonPath("$.[*].i").value(hasItem(DEFAULT_I.doubleValue())))
            .andExpect(jsonPath("$.[*].j").value(hasItem(DEFAULT_J.doubleValue())))
            .andExpect(jsonPath("$.[*].k").value(hasItem(DEFAULT_K.doubleValue())))
            .andExpect(jsonPath("$.[*].l").value(hasItem(DEFAULT_L.doubleValue())))
            .andExpect(jsonPath("$.[*].m").value(hasItem(DEFAULT_M.doubleValue())))
            .andExpect(jsonPath("$.[*].n").value(hasItem(DEFAULT_N.doubleValue())))
            .andExpect(jsonPath("$.[*].o").value(hasItem(DEFAULT_O.doubleValue())))
            .andExpect(jsonPath("$.[*].p").value(hasItem(DEFAULT_P.doubleValue())))
            .andExpect(jsonPath("$.[*].q").value(hasItem(DEFAULT_Q.doubleValue())))
            .andExpect(jsonPath("$.[*].r").value(hasItem(DEFAULT_R.doubleValue())))
            .andExpect(jsonPath("$.[*].s").value(hasItem(DEFAULT_S.doubleValue())))
            .andExpect(jsonPath("$.[*].t").value(hasItem(DEFAULT_T.doubleValue())))
            .andExpect(jsonPath("$.[*].u").value(hasItem(DEFAULT_U.doubleValue())))
            .andExpect(jsonPath("$.[*].v").value(hasItem(DEFAULT_V.doubleValue())))
            .andExpect(jsonPath("$.[*].w").value(hasItem(DEFAULT_W.doubleValue())))
            .andExpect(jsonPath("$.[*].x").value(hasItem(DEFAULT_X.doubleValue())))
            .andExpect(jsonPath("$.[*].y").value(hasItem(DEFAULT_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].z").value(hasItem(DEFAULT_Z.doubleValue())));

        // Check, that the count call also returns 1
        restCatalogcolorsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCatalogcolorsShouldNotBeFound(String filter) throws Exception {
        restCatalogcolorsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCatalogcolorsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCatalogcolors() throws Exception {
        // Get the catalogcolors
        restCatalogcolorsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCatalogcolors() throws Exception {
        // Initialize the database
        catalogcolors.setId(UUID.randomUUID().toString());
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        int databaseSizeBeforeUpdate = catalogcolorsRepository.findAll().size();

        // Update the catalogcolors
        Catalogcolors updatedCatalogcolors = catalogcolorsRepository.findById(catalogcolors.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCatalogcolors are not directly saved in db
        em.detach(updatedCatalogcolors);
        updatedCatalogcolors
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .baseMaterial(UPDATED_BASE_MATERIAL)
            .labL(UPDATED_LAB_L)
            .labA(UPDATED_LAB_A)
            .labB(UPDATED_LAB_B)
            .a(UPDATED_A)
            .b(UPDATED_B)
            .c(UPDATED_C)
            .d(UPDATED_D)
            .e(UPDATED_E)
            .f(UPDATED_F)
            .g(UPDATED_G)
            .h(UPDATED_H)
            .i(UPDATED_I)
            .j(UPDATED_J)
            .k(UPDATED_K)
            .l(UPDATED_L)
            .m(UPDATED_M)
            .n(UPDATED_N)
            .o(UPDATED_O)
            .p(UPDATED_P)
            .q(UPDATED_Q)
            .r(UPDATED_R)
            .s(UPDATED_S)
            .t(UPDATED_T)
            .u(UPDATED_U)
            .v(UPDATED_V)
            .w(UPDATED_W)
            .x(UPDATED_X)
            .y(UPDATED_Y)
            .z(UPDATED_Z);
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(updatedCatalogcolors);

        restCatalogcolorsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, catalogcolorsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isOk());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeUpdate);
        Catalogcolors testCatalogcolors = catalogcolorsList.get(catalogcolorsList.size() - 1);
        assertThat(testCatalogcolors.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCatalogcolors.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCatalogcolors.getBaseMaterial()).isEqualTo(UPDATED_BASE_MATERIAL);
        assertThat(testCatalogcolors.getLabL()).isEqualTo(UPDATED_LAB_L);
        assertThat(testCatalogcolors.getLabA()).isEqualTo(UPDATED_LAB_A);
        assertThat(testCatalogcolors.getLabB()).isEqualTo(UPDATED_LAB_B);
        assertThat(testCatalogcolors.getA()).isEqualTo(UPDATED_A);
        assertThat(testCatalogcolors.getB()).isEqualTo(UPDATED_B);
        assertThat(testCatalogcolors.getC()).isEqualTo(UPDATED_C);
        assertThat(testCatalogcolors.getD()).isEqualTo(UPDATED_D);
        assertThat(testCatalogcolors.getE()).isEqualTo(UPDATED_E);
        assertThat(testCatalogcolors.getF()).isEqualTo(UPDATED_F);
        assertThat(testCatalogcolors.getG()).isEqualTo(UPDATED_G);
        assertThat(testCatalogcolors.getH()).isEqualTo(UPDATED_H);
        assertThat(testCatalogcolors.getI()).isEqualTo(UPDATED_I);
        assertThat(testCatalogcolors.getJ()).isEqualTo(UPDATED_J);
        assertThat(testCatalogcolors.getK()).isEqualTo(UPDATED_K);
        assertThat(testCatalogcolors.getL()).isEqualTo(UPDATED_L);
        assertThat(testCatalogcolors.getM()).isEqualTo(UPDATED_M);
        assertThat(testCatalogcolors.getN()).isEqualTo(UPDATED_N);
        assertThat(testCatalogcolors.getO()).isEqualTo(UPDATED_O);
        assertThat(testCatalogcolors.getP()).isEqualTo(UPDATED_P);
        assertThat(testCatalogcolors.getQ()).isEqualTo(UPDATED_Q);
        assertThat(testCatalogcolors.getR()).isEqualTo(UPDATED_R);
        assertThat(testCatalogcolors.getS()).isEqualTo(UPDATED_S);
        assertThat(testCatalogcolors.getT()).isEqualTo(UPDATED_T);
        assertThat(testCatalogcolors.getU()).isEqualTo(UPDATED_U);
        assertThat(testCatalogcolors.getV()).isEqualTo(UPDATED_V);
        assertThat(testCatalogcolors.getW()).isEqualTo(UPDATED_W);
        assertThat(testCatalogcolors.getX()).isEqualTo(UPDATED_X);
        assertThat(testCatalogcolors.getY()).isEqualTo(UPDATED_Y);
        assertThat(testCatalogcolors.getZ()).isEqualTo(UPDATED_Z);
    }

    @Test
    @Transactional
    void putNonExistingCatalogcolors() throws Exception {
        int databaseSizeBeforeUpdate = catalogcolorsRepository.findAll().size();
        catalogcolors.setId(UUID.randomUUID().toString());

        // Create the Catalogcolors
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatalogcolorsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, catalogcolorsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCatalogcolors() throws Exception {
        int databaseSizeBeforeUpdate = catalogcolorsRepository.findAll().size();
        catalogcolors.setId(UUID.randomUUID().toString());

        // Create the Catalogcolors
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogcolorsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCatalogcolors() throws Exception {
        int databaseSizeBeforeUpdate = catalogcolorsRepository.findAll().size();
        catalogcolors.setId(UUID.randomUUID().toString());

        // Create the Catalogcolors
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogcolorsMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCatalogcolorsWithPatch() throws Exception {
        // Initialize the database
        catalogcolors.setId(UUID.randomUUID().toString());
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        int databaseSizeBeforeUpdate = catalogcolorsRepository.findAll().size();

        // Update the catalogcolors using partial update
        Catalogcolors partialUpdatedCatalogcolors = new Catalogcolors();
        partialUpdatedCatalogcolors.setId(catalogcolors.getId());

        partialUpdatedCatalogcolors
            .name(UPDATED_NAME)
            .a(UPDATED_A)
            .b(UPDATED_B)
            .g(UPDATED_G)
            .h(UPDATED_H)
            .o(UPDATED_O)
            .s(UPDATED_S)
            .w(UPDATED_W)
            .x(UPDATED_X)
            .y(UPDATED_Y);

        restCatalogcolorsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCatalogcolors.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCatalogcolors))
            )
            .andExpect(status().isOk());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeUpdate);
        Catalogcolors testCatalogcolors = catalogcolorsList.get(catalogcolorsList.size() - 1);
        assertThat(testCatalogcolors.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCatalogcolors.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCatalogcolors.getBaseMaterial()).isEqualTo(DEFAULT_BASE_MATERIAL);
        assertThat(testCatalogcolors.getLabL()).isEqualTo(DEFAULT_LAB_L);
        assertThat(testCatalogcolors.getLabA()).isEqualTo(DEFAULT_LAB_A);
        assertThat(testCatalogcolors.getLabB()).isEqualTo(DEFAULT_LAB_B);
        assertThat(testCatalogcolors.getA()).isEqualTo(UPDATED_A);
        assertThat(testCatalogcolors.getB()).isEqualTo(UPDATED_B);
        assertThat(testCatalogcolors.getC()).isEqualTo(DEFAULT_C);
        assertThat(testCatalogcolors.getD()).isEqualTo(DEFAULT_D);
        assertThat(testCatalogcolors.getE()).isEqualTo(DEFAULT_E);
        assertThat(testCatalogcolors.getF()).isEqualTo(DEFAULT_F);
        assertThat(testCatalogcolors.getG()).isEqualTo(UPDATED_G);
        assertThat(testCatalogcolors.getH()).isEqualTo(UPDATED_H);
        assertThat(testCatalogcolors.getI()).isEqualTo(DEFAULT_I);
        assertThat(testCatalogcolors.getJ()).isEqualTo(DEFAULT_J);
        assertThat(testCatalogcolors.getK()).isEqualTo(DEFAULT_K);
        assertThat(testCatalogcolors.getL()).isEqualTo(DEFAULT_L);
        assertThat(testCatalogcolors.getM()).isEqualTo(DEFAULT_M);
        assertThat(testCatalogcolors.getN()).isEqualTo(DEFAULT_N);
        assertThat(testCatalogcolors.getO()).isEqualTo(UPDATED_O);
        assertThat(testCatalogcolors.getP()).isEqualTo(DEFAULT_P);
        assertThat(testCatalogcolors.getQ()).isEqualTo(DEFAULT_Q);
        assertThat(testCatalogcolors.getR()).isEqualTo(DEFAULT_R);
        assertThat(testCatalogcolors.getS()).isEqualTo(UPDATED_S);
        assertThat(testCatalogcolors.getT()).isEqualTo(DEFAULT_T);
        assertThat(testCatalogcolors.getU()).isEqualTo(DEFAULT_U);
        assertThat(testCatalogcolors.getV()).isEqualTo(DEFAULT_V);
        assertThat(testCatalogcolors.getW()).isEqualTo(UPDATED_W);
        assertThat(testCatalogcolors.getX()).isEqualTo(UPDATED_X);
        assertThat(testCatalogcolors.getY()).isEqualTo(UPDATED_Y);
        assertThat(testCatalogcolors.getZ()).isEqualTo(DEFAULT_Z);
    }

    @Test
    @Transactional
    void fullUpdateCatalogcolorsWithPatch() throws Exception {
        // Initialize the database
        catalogcolors.setId(UUID.randomUUID().toString());
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        int databaseSizeBeforeUpdate = catalogcolorsRepository.findAll().size();

        // Update the catalogcolors using partial update
        Catalogcolors partialUpdatedCatalogcolors = new Catalogcolors();
        partialUpdatedCatalogcolors.setId(catalogcolors.getId());

        partialUpdatedCatalogcolors
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .baseMaterial(UPDATED_BASE_MATERIAL)
            .labL(UPDATED_LAB_L)
            .labA(UPDATED_LAB_A)
            .labB(UPDATED_LAB_B)
            .a(UPDATED_A)
            .b(UPDATED_B)
            .c(UPDATED_C)
            .d(UPDATED_D)
            .e(UPDATED_E)
            .f(UPDATED_F)
            .g(UPDATED_G)
            .h(UPDATED_H)
            .i(UPDATED_I)
            .j(UPDATED_J)
            .k(UPDATED_K)
            .l(UPDATED_L)
            .m(UPDATED_M)
            .n(UPDATED_N)
            .o(UPDATED_O)
            .p(UPDATED_P)
            .q(UPDATED_Q)
            .r(UPDATED_R)
            .s(UPDATED_S)
            .t(UPDATED_T)
            .u(UPDATED_U)
            .v(UPDATED_V)
            .w(UPDATED_W)
            .x(UPDATED_X)
            .y(UPDATED_Y)
            .z(UPDATED_Z);

        restCatalogcolorsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCatalogcolors.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCatalogcolors))
            )
            .andExpect(status().isOk());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeUpdate);
        Catalogcolors testCatalogcolors = catalogcolorsList.get(catalogcolorsList.size() - 1);
        assertThat(testCatalogcolors.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCatalogcolors.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCatalogcolors.getBaseMaterial()).isEqualTo(UPDATED_BASE_MATERIAL);
        assertThat(testCatalogcolors.getLabL()).isEqualTo(UPDATED_LAB_L);
        assertThat(testCatalogcolors.getLabA()).isEqualTo(UPDATED_LAB_A);
        assertThat(testCatalogcolors.getLabB()).isEqualTo(UPDATED_LAB_B);
        assertThat(testCatalogcolors.getA()).isEqualTo(UPDATED_A);
        assertThat(testCatalogcolors.getB()).isEqualTo(UPDATED_B);
        assertThat(testCatalogcolors.getC()).isEqualTo(UPDATED_C);
        assertThat(testCatalogcolors.getD()).isEqualTo(UPDATED_D);
        assertThat(testCatalogcolors.getE()).isEqualTo(UPDATED_E);
        assertThat(testCatalogcolors.getF()).isEqualTo(UPDATED_F);
        assertThat(testCatalogcolors.getG()).isEqualTo(UPDATED_G);
        assertThat(testCatalogcolors.getH()).isEqualTo(UPDATED_H);
        assertThat(testCatalogcolors.getI()).isEqualTo(UPDATED_I);
        assertThat(testCatalogcolors.getJ()).isEqualTo(UPDATED_J);
        assertThat(testCatalogcolors.getK()).isEqualTo(UPDATED_K);
        assertThat(testCatalogcolors.getL()).isEqualTo(UPDATED_L);
        assertThat(testCatalogcolors.getM()).isEqualTo(UPDATED_M);
        assertThat(testCatalogcolors.getN()).isEqualTo(UPDATED_N);
        assertThat(testCatalogcolors.getO()).isEqualTo(UPDATED_O);
        assertThat(testCatalogcolors.getP()).isEqualTo(UPDATED_P);
        assertThat(testCatalogcolors.getQ()).isEqualTo(UPDATED_Q);
        assertThat(testCatalogcolors.getR()).isEqualTo(UPDATED_R);
        assertThat(testCatalogcolors.getS()).isEqualTo(UPDATED_S);
        assertThat(testCatalogcolors.getT()).isEqualTo(UPDATED_T);
        assertThat(testCatalogcolors.getU()).isEqualTo(UPDATED_U);
        assertThat(testCatalogcolors.getV()).isEqualTo(UPDATED_V);
        assertThat(testCatalogcolors.getW()).isEqualTo(UPDATED_W);
        assertThat(testCatalogcolors.getX()).isEqualTo(UPDATED_X);
        assertThat(testCatalogcolors.getY()).isEqualTo(UPDATED_Y);
        assertThat(testCatalogcolors.getZ()).isEqualTo(UPDATED_Z);
    }

    @Test
    @Transactional
    void patchNonExistingCatalogcolors() throws Exception {
        int databaseSizeBeforeUpdate = catalogcolorsRepository.findAll().size();
        catalogcolors.setId(UUID.randomUUID().toString());

        // Create the Catalogcolors
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatalogcolorsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, catalogcolorsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCatalogcolors() throws Exception {
        int databaseSizeBeforeUpdate = catalogcolorsRepository.findAll().size();
        catalogcolors.setId(UUID.randomUUID().toString());

        // Create the Catalogcolors
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogcolorsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCatalogcolors() throws Exception {
        int databaseSizeBeforeUpdate = catalogcolorsRepository.findAll().size();
        catalogcolors.setId(UUID.randomUUID().toString());

        // Create the Catalogcolors
        CatalogcolorsDTO catalogcolorsDTO = catalogcolorsMapper.toDto(catalogcolors);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCatalogcolorsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(catalogcolorsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Catalogcolors in the database
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCatalogcolors() throws Exception {
        // Initialize the database
        catalogcolors.setId(UUID.randomUUID().toString());
        catalogcolorsRepository.saveAndFlush(catalogcolors);

        int databaseSizeBeforeDelete = catalogcolorsRepository.findAll().size();

        // Delete the catalogcolors
        restCatalogcolorsMockMvc
            .perform(delete(ENTITY_API_URL_ID, catalogcolors.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Catalogcolors> catalogcolorsList = catalogcolorsRepository.findAll();
        assertThat(catalogcolorsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
