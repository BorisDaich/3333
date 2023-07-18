package biz.daich.tambour.shlicht.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import biz.daich.tambour.shlicht.IntegrationTest;
import biz.daich.tambour.shlicht.domain.Images;
import biz.daich.tambour.shlicht.domain.Scans;
import biz.daich.tambour.shlicht.repository.ImagesRepository;
import biz.daich.tambour.shlicht.service.dto.ImagesDTO;
import biz.daich.tambour.shlicht.service.mapper.ImagesMapper;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link ImagesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ImagesResourceIT {

    private static final byte[] DEFAULT_PNG_CONTENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PNG_CONTENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PNG_CONTENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PNG_CONTENT_CONTENT_TYPE = "image/png";

    private static final Integer DEFAULT_RAW_WIDTH = 1;
    private static final Integer UPDATED_RAW_WIDTH = 2;
    private static final Integer SMALLER_RAW_WIDTH = 1 - 1;

    private static final Integer DEFAULT_RAW_HEIGHT = 1;
    private static final Integer UPDATED_RAW_HEIGHT = 2;
    private static final Integer SMALLER_RAW_HEIGHT = 1 - 1;

    private static final String DEFAULT_RAW_FORMAT = "AAAAAAAAAA";
    private static final String UPDATED_RAW_FORMAT = "BBBBBBBBBB";

    private static final byte[] DEFAULT_RAW_CONTENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_RAW_CONTENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_RAW_CONTENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_RAW_CONTENT_CONTENT_TYPE = "image/png";

    private static final String ENTITY_API_URL = "/api/images";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ImagesRepository imagesRepository;

    @Autowired
    private ImagesMapper imagesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restImagesMockMvc;

    private Images images;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Images createEntity(EntityManager em) {
        Images images = new Images()
            .pngContent(DEFAULT_PNG_CONTENT)
            .pngContentContentType(DEFAULT_PNG_CONTENT_CONTENT_TYPE)
            .rawWidth(DEFAULT_RAW_WIDTH)
            .rawHeight(DEFAULT_RAW_HEIGHT)
            .rawFormat(DEFAULT_RAW_FORMAT)
            .rawContent(DEFAULT_RAW_CONTENT)
            .rawContentContentType(DEFAULT_RAW_CONTENT_CONTENT_TYPE);
        return images;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Images createUpdatedEntity(EntityManager em) {
        Images images = new Images()
            .pngContent(UPDATED_PNG_CONTENT)
            .pngContentContentType(UPDATED_PNG_CONTENT_CONTENT_TYPE)
            .rawWidth(UPDATED_RAW_WIDTH)
            .rawHeight(UPDATED_RAW_HEIGHT)
            .rawFormat(UPDATED_RAW_FORMAT)
            .rawContent(UPDATED_RAW_CONTENT)
            .rawContentContentType(UPDATED_RAW_CONTENT_CONTENT_TYPE);
        return images;
    }

    @BeforeEach
    public void initTest() {
        images = createEntity(em);
    }

    @Test
    @Transactional
    void createImages() throws Exception {
        int databaseSizeBeforeCreate = imagesRepository.findAll().size();
        // Create the Images
        ImagesDTO imagesDTO = imagesMapper.toDto(images);
        restImagesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imagesDTO)))
            .andExpect(status().isCreated());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeCreate + 1);
        Images testImages = imagesList.get(imagesList.size() - 1);
        assertThat(testImages.getPngContent()).isEqualTo(DEFAULT_PNG_CONTENT);
        assertThat(testImages.getPngContentContentType()).isEqualTo(DEFAULT_PNG_CONTENT_CONTENT_TYPE);
        assertThat(testImages.getRawWidth()).isEqualTo(DEFAULT_RAW_WIDTH);
        assertThat(testImages.getRawHeight()).isEqualTo(DEFAULT_RAW_HEIGHT);
        assertThat(testImages.getRawFormat()).isEqualTo(DEFAULT_RAW_FORMAT);
        assertThat(testImages.getRawContent()).isEqualTo(DEFAULT_RAW_CONTENT);
        assertThat(testImages.getRawContentContentType()).isEqualTo(DEFAULT_RAW_CONTENT_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void createImagesWithExistingId() throws Exception {
        // Create the Images with an existing ID
        images.setId("existing_id");
        ImagesDTO imagesDTO = imagesMapper.toDto(images);

        int databaseSizeBeforeCreate = imagesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restImagesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imagesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllImages() throws Exception {
        // Initialize the database
        images.setId(UUID.randomUUID().toString());
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList
        restImagesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(images.getId())))
            .andExpect(jsonPath("$.[*].pngContentContentType").value(hasItem(DEFAULT_PNG_CONTENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].pngContent").value(hasItem(Base64Utils.encodeToString(DEFAULT_PNG_CONTENT))))
            .andExpect(jsonPath("$.[*].rawWidth").value(hasItem(DEFAULT_RAW_WIDTH)))
            .andExpect(jsonPath("$.[*].rawHeight").value(hasItem(DEFAULT_RAW_HEIGHT)))
            .andExpect(jsonPath("$.[*].rawFormat").value(hasItem(DEFAULT_RAW_FORMAT.toString())))
            .andExpect(jsonPath("$.[*].rawContentContentType").value(hasItem(DEFAULT_RAW_CONTENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].rawContent").value(hasItem(Base64Utils.encodeToString(DEFAULT_RAW_CONTENT))));
    }

    @Test
    @Transactional
    void getImages() throws Exception {
        // Initialize the database
        images.setId(UUID.randomUUID().toString());
        imagesRepository.saveAndFlush(images);

        // Get the images
        restImagesMockMvc
            .perform(get(ENTITY_API_URL_ID, images.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(images.getId()))
            .andExpect(jsonPath("$.pngContentContentType").value(DEFAULT_PNG_CONTENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.pngContent").value(Base64Utils.encodeToString(DEFAULT_PNG_CONTENT)))
            .andExpect(jsonPath("$.rawWidth").value(DEFAULT_RAW_WIDTH))
            .andExpect(jsonPath("$.rawHeight").value(DEFAULT_RAW_HEIGHT))
            .andExpect(jsonPath("$.rawFormat").value(DEFAULT_RAW_FORMAT.toString()))
            .andExpect(jsonPath("$.rawContentContentType").value(DEFAULT_RAW_CONTENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.rawContent").value(Base64Utils.encodeToString(DEFAULT_RAW_CONTENT)));
    }

    @Test
    @Transactional
    void getImagesByIdFiltering() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        String id = images.getId();

        defaultImagesShouldBeFound("id.equals=" + id);
        defaultImagesShouldNotBeFound("id.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllImagesByRawWidthIsEqualToSomething() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawWidth equals to DEFAULT_RAW_WIDTH
        defaultImagesShouldBeFound("rawWidth.equals=" + DEFAULT_RAW_WIDTH);

        // Get all the imagesList where rawWidth equals to UPDATED_RAW_WIDTH
        defaultImagesShouldNotBeFound("rawWidth.equals=" + UPDATED_RAW_WIDTH);
    }

    @Test
    @Transactional
    void getAllImagesByRawWidthIsInShouldWork() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawWidth in DEFAULT_RAW_WIDTH or UPDATED_RAW_WIDTH
        defaultImagesShouldBeFound("rawWidth.in=" + DEFAULT_RAW_WIDTH + "," + UPDATED_RAW_WIDTH);

        // Get all the imagesList where rawWidth equals to UPDATED_RAW_WIDTH
        defaultImagesShouldNotBeFound("rawWidth.in=" + UPDATED_RAW_WIDTH);
    }

    @Test
    @Transactional
    void getAllImagesByRawWidthIsNullOrNotNull() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawWidth is not null
        defaultImagesShouldBeFound("rawWidth.specified=true");

        // Get all the imagesList where rawWidth is null
        defaultImagesShouldNotBeFound("rawWidth.specified=false");
    }

    @Test
    @Transactional
    void getAllImagesByRawWidthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawWidth is greater than or equal to DEFAULT_RAW_WIDTH
        defaultImagesShouldBeFound("rawWidth.greaterThanOrEqual=" + DEFAULT_RAW_WIDTH);

        // Get all the imagesList where rawWidth is greater than or equal to UPDATED_RAW_WIDTH
        defaultImagesShouldNotBeFound("rawWidth.greaterThanOrEqual=" + UPDATED_RAW_WIDTH);
    }

    @Test
    @Transactional
    void getAllImagesByRawWidthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawWidth is less than or equal to DEFAULT_RAW_WIDTH
        defaultImagesShouldBeFound("rawWidth.lessThanOrEqual=" + DEFAULT_RAW_WIDTH);

        // Get all the imagesList where rawWidth is less than or equal to SMALLER_RAW_WIDTH
        defaultImagesShouldNotBeFound("rawWidth.lessThanOrEqual=" + SMALLER_RAW_WIDTH);
    }

    @Test
    @Transactional
    void getAllImagesByRawWidthIsLessThanSomething() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawWidth is less than DEFAULT_RAW_WIDTH
        defaultImagesShouldNotBeFound("rawWidth.lessThan=" + DEFAULT_RAW_WIDTH);

        // Get all the imagesList where rawWidth is less than UPDATED_RAW_WIDTH
        defaultImagesShouldBeFound("rawWidth.lessThan=" + UPDATED_RAW_WIDTH);
    }

    @Test
    @Transactional
    void getAllImagesByRawWidthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawWidth is greater than DEFAULT_RAW_WIDTH
        defaultImagesShouldNotBeFound("rawWidth.greaterThan=" + DEFAULT_RAW_WIDTH);

        // Get all the imagesList where rawWidth is greater than SMALLER_RAW_WIDTH
        defaultImagesShouldBeFound("rawWidth.greaterThan=" + SMALLER_RAW_WIDTH);
    }

    @Test
    @Transactional
    void getAllImagesByRawHeightIsEqualToSomething() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawHeight equals to DEFAULT_RAW_HEIGHT
        defaultImagesShouldBeFound("rawHeight.equals=" + DEFAULT_RAW_HEIGHT);

        // Get all the imagesList where rawHeight equals to UPDATED_RAW_HEIGHT
        defaultImagesShouldNotBeFound("rawHeight.equals=" + UPDATED_RAW_HEIGHT);
    }

    @Test
    @Transactional
    void getAllImagesByRawHeightIsInShouldWork() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawHeight in DEFAULT_RAW_HEIGHT or UPDATED_RAW_HEIGHT
        defaultImagesShouldBeFound("rawHeight.in=" + DEFAULT_RAW_HEIGHT + "," + UPDATED_RAW_HEIGHT);

        // Get all the imagesList where rawHeight equals to UPDATED_RAW_HEIGHT
        defaultImagesShouldNotBeFound("rawHeight.in=" + UPDATED_RAW_HEIGHT);
    }

    @Test
    @Transactional
    void getAllImagesByRawHeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawHeight is not null
        defaultImagesShouldBeFound("rawHeight.specified=true");

        // Get all the imagesList where rawHeight is null
        defaultImagesShouldNotBeFound("rawHeight.specified=false");
    }

    @Test
    @Transactional
    void getAllImagesByRawHeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawHeight is greater than or equal to DEFAULT_RAW_HEIGHT
        defaultImagesShouldBeFound("rawHeight.greaterThanOrEqual=" + DEFAULT_RAW_HEIGHT);

        // Get all the imagesList where rawHeight is greater than or equal to UPDATED_RAW_HEIGHT
        defaultImagesShouldNotBeFound("rawHeight.greaterThanOrEqual=" + UPDATED_RAW_HEIGHT);
    }

    @Test
    @Transactional
    void getAllImagesByRawHeightIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawHeight is less than or equal to DEFAULT_RAW_HEIGHT
        defaultImagesShouldBeFound("rawHeight.lessThanOrEqual=" + DEFAULT_RAW_HEIGHT);

        // Get all the imagesList where rawHeight is less than or equal to SMALLER_RAW_HEIGHT
        defaultImagesShouldNotBeFound("rawHeight.lessThanOrEqual=" + SMALLER_RAW_HEIGHT);
    }

    @Test
    @Transactional
    void getAllImagesByRawHeightIsLessThanSomething() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawHeight is less than DEFAULT_RAW_HEIGHT
        defaultImagesShouldNotBeFound("rawHeight.lessThan=" + DEFAULT_RAW_HEIGHT);

        // Get all the imagesList where rawHeight is less than UPDATED_RAW_HEIGHT
        defaultImagesShouldBeFound("rawHeight.lessThan=" + UPDATED_RAW_HEIGHT);
    }

    @Test
    @Transactional
    void getAllImagesByRawHeightIsGreaterThanSomething() throws Exception {
        // Initialize the database
        imagesRepository.saveAndFlush(images);

        // Get all the imagesList where rawHeight is greater than DEFAULT_RAW_HEIGHT
        defaultImagesShouldNotBeFound("rawHeight.greaterThan=" + DEFAULT_RAW_HEIGHT);

        // Get all the imagesList where rawHeight is greater than SMALLER_RAW_HEIGHT
        defaultImagesShouldBeFound("rawHeight.greaterThan=" + SMALLER_RAW_HEIGHT);
    }

    @Test
    @Transactional
    void getAllImagesByScansIsEqualToSomething() throws Exception {
        Scans scans;
        if (TestUtil.findAll(em, Scans.class).isEmpty()) {
            imagesRepository.saveAndFlush(images);
            scans = ScansResourceIT.createEntity(em);
        } else {
            scans = TestUtil.findAll(em, Scans.class).get(0);
        }
        em.persist(scans);
        em.flush();
        images.setScans(scans);
        scans.setImage(images);
        imagesRepository.saveAndFlush(images);
        String scansId = scans.getId();
        // Get all the imagesList where scans equals to scansId
        defaultImagesShouldBeFound("scansId.equals=" + scansId);

        // Get all the imagesList where scans equals to "invalid-id"
        defaultImagesShouldNotBeFound("scansId.equals=" + "invalid-id");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultImagesShouldBeFound(String filter) throws Exception {
        restImagesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(images.getId())))
            .andExpect(jsonPath("$.[*].pngContentContentType").value(hasItem(DEFAULT_PNG_CONTENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].pngContent").value(hasItem(Base64Utils.encodeToString(DEFAULT_PNG_CONTENT))))
            .andExpect(jsonPath("$.[*].rawWidth").value(hasItem(DEFAULT_RAW_WIDTH)))
            .andExpect(jsonPath("$.[*].rawHeight").value(hasItem(DEFAULT_RAW_HEIGHT)))
            .andExpect(jsonPath("$.[*].rawFormat").value(hasItem(DEFAULT_RAW_FORMAT.toString())))
            .andExpect(jsonPath("$.[*].rawContentContentType").value(hasItem(DEFAULT_RAW_CONTENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].rawContent").value(hasItem(Base64Utils.encodeToString(DEFAULT_RAW_CONTENT))));

        // Check, that the count call also returns 1
        restImagesMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultImagesShouldNotBeFound(String filter) throws Exception {
        restImagesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restImagesMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingImages() throws Exception {
        // Get the images
        restImagesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingImages() throws Exception {
        // Initialize the database
        images.setId(UUID.randomUUID().toString());
        imagesRepository.saveAndFlush(images);

        int databaseSizeBeforeUpdate = imagesRepository.findAll().size();

        // Update the images
        Images updatedImages = imagesRepository.findById(images.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedImages are not directly saved in db
        em.detach(updatedImages);
        updatedImages
            .pngContent(UPDATED_PNG_CONTENT)
            .pngContentContentType(UPDATED_PNG_CONTENT_CONTENT_TYPE)
            .rawWidth(UPDATED_RAW_WIDTH)
            .rawHeight(UPDATED_RAW_HEIGHT)
            .rawFormat(UPDATED_RAW_FORMAT)
            .rawContent(UPDATED_RAW_CONTENT)
            .rawContentContentType(UPDATED_RAW_CONTENT_CONTENT_TYPE);
        ImagesDTO imagesDTO = imagesMapper.toDto(updatedImages);

        restImagesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, imagesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(imagesDTO))
            )
            .andExpect(status().isOk());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeUpdate);
        Images testImages = imagesList.get(imagesList.size() - 1);
        assertThat(testImages.getPngContent()).isEqualTo(UPDATED_PNG_CONTENT);
        assertThat(testImages.getPngContentContentType()).isEqualTo(UPDATED_PNG_CONTENT_CONTENT_TYPE);
        assertThat(testImages.getRawWidth()).isEqualTo(UPDATED_RAW_WIDTH);
        assertThat(testImages.getRawHeight()).isEqualTo(UPDATED_RAW_HEIGHT);
        assertThat(testImages.getRawFormat()).isEqualTo(UPDATED_RAW_FORMAT);
        assertThat(testImages.getRawContent()).isEqualTo(UPDATED_RAW_CONTENT);
        assertThat(testImages.getRawContentContentType()).isEqualTo(UPDATED_RAW_CONTENT_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingImages() throws Exception {
        int databaseSizeBeforeUpdate = imagesRepository.findAll().size();
        images.setId(UUID.randomUUID().toString());

        // Create the Images
        ImagesDTO imagesDTO = imagesMapper.toDto(images);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImagesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, imagesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(imagesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchImages() throws Exception {
        int databaseSizeBeforeUpdate = imagesRepository.findAll().size();
        images.setId(UUID.randomUUID().toString());

        // Create the Images
        ImagesDTO imagesDTO = imagesMapper.toDto(images);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restImagesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(imagesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamImages() throws Exception {
        int databaseSizeBeforeUpdate = imagesRepository.findAll().size();
        images.setId(UUID.randomUUID().toString());

        // Create the Images
        ImagesDTO imagesDTO = imagesMapper.toDto(images);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restImagesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imagesDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateImagesWithPatch() throws Exception {
        // Initialize the database
        images.setId(UUID.randomUUID().toString());
        imagesRepository.saveAndFlush(images);

        int databaseSizeBeforeUpdate = imagesRepository.findAll().size();

        // Update the images using partial update
        Images partialUpdatedImages = new Images();
        partialUpdatedImages.setId(images.getId());

        partialUpdatedImages.rawHeight(UPDATED_RAW_HEIGHT);

        restImagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedImages.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedImages))
            )
            .andExpect(status().isOk());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeUpdate);
        Images testImages = imagesList.get(imagesList.size() - 1);
        assertThat(testImages.getPngContent()).isEqualTo(DEFAULT_PNG_CONTENT);
        assertThat(testImages.getPngContentContentType()).isEqualTo(DEFAULT_PNG_CONTENT_CONTENT_TYPE);
        assertThat(testImages.getRawWidth()).isEqualTo(DEFAULT_RAW_WIDTH);
        assertThat(testImages.getRawHeight()).isEqualTo(UPDATED_RAW_HEIGHT);
        assertThat(testImages.getRawFormat()).isEqualTo(DEFAULT_RAW_FORMAT);
        assertThat(testImages.getRawContent()).isEqualTo(DEFAULT_RAW_CONTENT);
        assertThat(testImages.getRawContentContentType()).isEqualTo(DEFAULT_RAW_CONTENT_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateImagesWithPatch() throws Exception {
        // Initialize the database
        images.setId(UUID.randomUUID().toString());
        imagesRepository.saveAndFlush(images);

        int databaseSizeBeforeUpdate = imagesRepository.findAll().size();

        // Update the images using partial update
        Images partialUpdatedImages = new Images();
        partialUpdatedImages.setId(images.getId());

        partialUpdatedImages
            .pngContent(UPDATED_PNG_CONTENT)
            .pngContentContentType(UPDATED_PNG_CONTENT_CONTENT_TYPE)
            .rawWidth(UPDATED_RAW_WIDTH)
            .rawHeight(UPDATED_RAW_HEIGHT)
            .rawFormat(UPDATED_RAW_FORMAT)
            .rawContent(UPDATED_RAW_CONTENT)
            .rawContentContentType(UPDATED_RAW_CONTENT_CONTENT_TYPE);

        restImagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedImages.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedImages))
            )
            .andExpect(status().isOk());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeUpdate);
        Images testImages = imagesList.get(imagesList.size() - 1);
        assertThat(testImages.getPngContent()).isEqualTo(UPDATED_PNG_CONTENT);
        assertThat(testImages.getPngContentContentType()).isEqualTo(UPDATED_PNG_CONTENT_CONTENT_TYPE);
        assertThat(testImages.getRawWidth()).isEqualTo(UPDATED_RAW_WIDTH);
        assertThat(testImages.getRawHeight()).isEqualTo(UPDATED_RAW_HEIGHT);
        assertThat(testImages.getRawFormat()).isEqualTo(UPDATED_RAW_FORMAT);
        assertThat(testImages.getRawContent()).isEqualTo(UPDATED_RAW_CONTENT);
        assertThat(testImages.getRawContentContentType()).isEqualTo(UPDATED_RAW_CONTENT_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingImages() throws Exception {
        int databaseSizeBeforeUpdate = imagesRepository.findAll().size();
        images.setId(UUID.randomUUID().toString());

        // Create the Images
        ImagesDTO imagesDTO = imagesMapper.toDto(images);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, imagesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(imagesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchImages() throws Exception {
        int databaseSizeBeforeUpdate = imagesRepository.findAll().size();
        images.setId(UUID.randomUUID().toString());

        // Create the Images
        ImagesDTO imagesDTO = imagesMapper.toDto(images);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restImagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(imagesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamImages() throws Exception {
        int databaseSizeBeforeUpdate = imagesRepository.findAll().size();
        images.setId(UUID.randomUUID().toString());

        // Create the Images
        ImagesDTO imagesDTO = imagesMapper.toDto(images);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restImagesMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(imagesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Images in the database
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteImages() throws Exception {
        // Initialize the database
        images.setId(UUID.randomUUID().toString());
        imagesRepository.saveAndFlush(images);

        int databaseSizeBeforeDelete = imagesRepository.findAll().size();

        // Delete the images
        restImagesMockMvc
            .perform(delete(ENTITY_API_URL_ID, images.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Images> imagesList = imagesRepository.findAll();
        assertThat(imagesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
