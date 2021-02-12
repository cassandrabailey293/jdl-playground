package com.myapp.service;

import com.myapp.service.dto.CDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.myapp.domain.C}.
 */
public interface CService {

    /**
     * Save a c.
     *
     * @param cDTO the entity to save.
     * @return the persisted entity.
     */
    CDTO save(CDTO cDTO);

    /**
     * Get all the cs.
     *
     * @return the list of entities.
     */
    List<CDTO> findAll();


    /**
     * Get the "id" c.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CDTO> findOne(Long id);

    /**
     * Delete the "id" c.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
