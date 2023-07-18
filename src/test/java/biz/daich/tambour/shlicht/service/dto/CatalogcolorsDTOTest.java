package biz.daich.tambour.shlicht.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import biz.daich.tambour.shlicht.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CatalogcolorsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatalogcolorsDTO.class);
        CatalogcolorsDTO catalogcolorsDTO1 = new CatalogcolorsDTO();
        catalogcolorsDTO1.setId("id1");
        CatalogcolorsDTO catalogcolorsDTO2 = new CatalogcolorsDTO();
        assertThat(catalogcolorsDTO1).isNotEqualTo(catalogcolorsDTO2);
        catalogcolorsDTO2.setId(catalogcolorsDTO1.getId());
        assertThat(catalogcolorsDTO1).isEqualTo(catalogcolorsDTO2);
        catalogcolorsDTO2.setId("id2");
        assertThat(catalogcolorsDTO1).isNotEqualTo(catalogcolorsDTO2);
        catalogcolorsDTO1.setId(null);
        assertThat(catalogcolorsDTO1).isNotEqualTo(catalogcolorsDTO2);
    }
}
