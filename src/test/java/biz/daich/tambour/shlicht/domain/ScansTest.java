package biz.daich.tambour.shlicht.domain;

import static org.assertj.core.api.Assertions.assertThat;

import biz.daich.tambour.shlicht.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ScansTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Scans.class);
        Scans scans1 = new Scans();
        scans1.setId("id1");
        Scans scans2 = new Scans();
        scans2.setId(scans1.getId());
        assertThat(scans1).isEqualTo(scans2);
        scans2.setId("id2");
        assertThat(scans1).isNotEqualTo(scans2);
        scans1.setId(null);
        assertThat(scans1).isNotEqualTo(scans2);
    }
}
