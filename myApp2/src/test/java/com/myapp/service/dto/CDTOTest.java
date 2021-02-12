package com.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.myapp.web.rest.TestUtil;

public class CDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CDTO.class);
        CDTO cDTO1 = new CDTO();
        cDTO1.setId(1L);
        CDTO cDTO2 = new CDTO();
        assertThat(cDTO1).isNotEqualTo(cDTO2);
        cDTO2.setId(cDTO1.getId());
        assertThat(cDTO1).isEqualTo(cDTO2);
        cDTO2.setId(2L);
        assertThat(cDTO1).isNotEqualTo(cDTO2);
        cDTO1.setId(null);
        assertThat(cDTO1).isNotEqualTo(cDTO2);
    }
}
