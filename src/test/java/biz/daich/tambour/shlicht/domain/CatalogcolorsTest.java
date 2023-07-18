package biz.daich.tambour.shlicht.domain;

import static org.assertj.core.api.Assertions.assertThat;

import biz.daich.tambour.shlicht.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CatalogcolorsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Catalogcolors.class);
        Catalogcolors catalogcolors1 = new Catalogcolors();
        catalogcolors1.setId("id1");
        Catalogcolors catalogcolors2 = new Catalogcolors();
        catalogcolors2.setId(catalogcolors1.getId());
        assertThat(catalogcolors1).isEqualTo(catalogcolors2);
        catalogcolors2.setId("id2");
        assertThat(catalogcolors1).isNotEqualTo(catalogcolors2);
        catalogcolors1.setId(null);
        assertThat(catalogcolors1).isNotEqualTo(catalogcolors2);
    }
}
