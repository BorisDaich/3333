package biz.daich.tambour.shlicht.domain;

import static org.assertj.core.api.Assertions.assertThat;

import biz.daich.tambour.shlicht.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ImagesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Images.class);
        Images images1 = new Images();
        images1.setId("id1");
        Images images2 = new Images();
        images2.setId(images1.getId());
        assertThat(images1).isEqualTo(images2);
        images2.setId("id2");
        assertThat(images1).isNotEqualTo(images2);
        images1.setId(null);
        assertThat(images1).isNotEqualTo(images2);
    }
}
