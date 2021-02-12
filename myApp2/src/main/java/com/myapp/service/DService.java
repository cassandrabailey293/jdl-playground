package com.myapp.service;

import com.myapp.service.dto.DDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.myapp.domain.D}.
 */
public interface DService {

    /**
     * Save a d.
     *
     * @param dDTO the entity to save.
     * @return the persisted entity.
     */
    DDTO save(DDTO dDTO);

    /**
     * Get all the ds.
     *
     * @return the list of entities.
     */
    List<DDTO> findAll();
    /**
     * Get all the DDTO where D is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<DDTO> findAllWhereDIsNull();


    /**
     * Get the "id" d.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DDTO> findOne(Long id);

    /**
     * Delete the "id" d.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
