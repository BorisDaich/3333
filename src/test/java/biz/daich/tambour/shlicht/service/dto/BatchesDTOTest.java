package biz.daich.tambour.shlicht.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import biz.daich.tambour.shlicht.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BatchesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BatchesDTO.class);
        BatchesDTO batchesDTO1 = new BatchesDTO();
        batchesDTO1.setId("id1");
        BatchesDTO batchesDTO2 = new BatchesDTO();
        assertThat(batchesDTO1).isNotEqualTo(batchesDTO2);
        batchesDTO2.setId(batchesDTO1.getId());
        assertThat(batchesDTO1).isEqualTo(batchesDTO2);
        batchesDTO2.setId("id2");
        assertThat(batchesDTO1).isNotEqualTo(batchesDTO2);
        batchesDTO1.setId(null);
        assertThat(batchesDTO1).isNotEqualTo(batchesDTO2);
    }
}
