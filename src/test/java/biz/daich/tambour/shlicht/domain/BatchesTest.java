package biz.daich.tambour.shlicht.domain;

import static org.assertj.core.api.Assertions.assertThat;

import biz.daich.tambour.shlicht.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BatchesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Batches.class);
        Batches batches1 = new Batches();
        batches1.setId("id1");
        Batches batches2 = new Batches();
        batches2.setId(batches1.getId());
        assertThat(batches1).isEqualTo(batches2);
        batches2.setId("id2");
        assertThat(batches1).isNotEqualTo(batches2);
        batches1.setId(null);
        assertThat(batches1).isNotEqualTo(batches2);
    }
}
