package biz.daich.tambour.shlicht.domain;

import static org.assertj.core.api.Assertions.assertThat;

import biz.daich.tambour.shlicht.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CatalogsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Catalogs.class);
        Catalogs catalogs1 = new Catalogs();
        catalogs1.setId("id1");
        Catalogs catalogs2 = new Catalogs();
        catalogs2.setId(catalogs1.getId());
        assertThat(catalogs1).isEqualTo(catalogs2);
        catalogs2.setId("id2");
        assertThat(catalogs1).isNotEqualTo(catalogs2);
        catalogs1.setId(null);
        assertThat(catalogs1).isNotEqualTo(catalogs2);
    }
}
