package com.myapp.service.mapper;


import com.myapp.domain.*;
import com.myapp.service.dto.DDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link D} and its DTO {@link DDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DMapper extends EntityMapper<DDTO, D> {


    @Mapping(target = "d", ignore = true)
    D toEntity(DDTO dDTO);

    default D fromId(Long id) {
        if (id == null) {
            return null;
        }
        D d = new D();
        d.setId(id);
        return d;
    }
}
