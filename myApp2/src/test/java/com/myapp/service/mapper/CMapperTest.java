package com.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CMapperTest {

    private CMapper cMapper;

    @BeforeEach
    public void setUp() {
        cMapper = new CMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cMapper.fromId(null)).isNull();
    }
}
