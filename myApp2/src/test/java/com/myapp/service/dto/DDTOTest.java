package com.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.myapp.web.rest.TestUtil;

public class DDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DDTO.class);
        DDTO dDTO1 = new DDTO();
        dDTO1.setId(1L);
        DDTO dDTO2 = new DDTO();
        assertThat(dDTO1).isNotEqualTo(dDTO2);
        dDTO2.setId(dDTO1.getId());
        assertThat(dDTO1).isEqualTo(dDTO2);
        dDTO2.setId(2L);
        assertThat(dDTO1).isNotEqualTo(dDTO2);
        dDTO1.setId(null);
        assertThat(dDTO1).isNotEqualTo(dDTO2);
    }
}
