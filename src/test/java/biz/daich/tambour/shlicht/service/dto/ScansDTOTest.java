package biz.daich.tambour.shlicht.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import biz.daich.tambour.shlicht.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ScansDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ScansDTO.class);
        ScansDTO scansDTO1 = new ScansDTO();
        scansDTO1.setId("id1");
        ScansDTO scansDTO2 = new ScansDTO();
        assertThat(scansDTO1).isNotEqualTo(scansDTO2);
        scansDTO2.setId(scansDTO1.getId());
        assertThat(scansDTO1).isEqualTo(scansDTO2);
        scansDTO2.setId("id2");
        assertThat(scansDTO1).isNotEqualTo(scansDTO2);
        scansDTO1.setId(null);
        assertThat(scansDTO1).isNotEqualTo(scansDTO2);
    }
}
