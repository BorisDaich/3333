package biz.daich.tambour.shlicht.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import biz.daich.tambour.shlicht.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CatalogsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatalogsDTO.class);
        CatalogsDTO catalogsDTO1 = new CatalogsDTO();
        catalogsDTO1.setId("id1");
        CatalogsDTO catalogsDTO2 = new CatalogsDTO();
        assertThat(catalogsDTO1).isNotEqualTo(catalogsDTO2);
        catalogsDTO2.setId(catalogsDTO1.getId());
        assertThat(catalogsDTO1).isEqualTo(catalogsDTO2);
        catalogsDTO2.setId("id2");
        assertThat(catalogsDTO1).isNotEqualTo(catalogsDTO2);
        catalogsDTO1.setId(null);
        assertThat(catalogsDTO1).isNotEqualTo(catalogsDTO2);
    }
}
