package com.myapp.service.mapper;


import com.myapp.domain.*;
import com.myapp.service.dto.CDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link C} and its DTO {@link CDTO}.
 */
@Mapper(componentModel = "spring", uses = {DMapper.class})
public interface CMapper extends EntityMapper<CDTO, C> {

    @Mapping(source = "a.id", target = "aId")
    CDTO toDto(C c);

    @Mapping(source = "aId", target = "a")
    C toEntity(CDTO cDTO);

    default C fromId(Long id) {
        if (id == null) {
            return null;
        }
        C c = new C();
        c.setId(id);
        return c;
    }
}
