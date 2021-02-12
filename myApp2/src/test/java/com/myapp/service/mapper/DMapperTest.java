package com.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DMapperTest {

    private DMapper dMapper;

    @BeforeEach
    public void setUp() {
        dMapper = new DMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dMapper.fromId(null)).isNull();
    }
}
