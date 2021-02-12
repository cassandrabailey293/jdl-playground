package com.myapp.web.rest;

import com.myapp.service.CService;
import com.myapp.web.rest.errors.BadRequestAlertException;
import com.myapp.service.dto.CDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.myapp.domain.C}.
 */
@RestController
@RequestMapping("/api")
public class CResource {

    private final Logger log = LoggerFactory.getLogger(CResource.class);

    private static final String ENTITY_NAME = "myApp2C";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CService cService;

    public CResource(CService cService) {
        this.cService = cService;
    }

    /**
     * {@code POST  /cs} : Create a new c.
     *
     * @param cDTO the cDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cDTO, or with status {@code 400 (Bad Request)} if the c has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cs")
    public ResponseEntity<CDTO> createC(@Valid @RequestBody CDTO cDTO) throws URISyntaxException {
        log.debug("REST request to save C : {}", cDTO);
        if (cDTO.getId() != null) {
            throw new BadRequestAlertException("A new c cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CDTO result = cService.save(cDTO);
        return ResponseEntity.created(new URI("/api/cs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cs} : Updates an existing c.
     *
     * @param cDTO the cDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cDTO,
     * or with status {@code 400 (Bad Request)} if the cDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cs")
    public ResponseEntity<CDTO> updateC(@Valid @RequestBody CDTO cDTO) throws URISyntaxException {
        log.debug("REST request to update C : {}", cDTO);
        if (cDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CDTO result = cService.save(cDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cs} : get all the cs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cs in body.
     */
    @GetMapping("/cs")
    public List<CDTO> getAllCS() {
        log.debug("REST request to get all CS");
        return cService.findAll();
    }

    /**
     * {@code GET  /cs/:id} : get the "id" c.
     *
     * @param id the id of the cDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cs/{id}")
    public ResponseEntity<CDTO> getC(@PathVariable Long id) {
        log.debug("REST request to get C : {}", id);
        Optional<CDTO> cDTO = cService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cDTO);
    }

    /**
     * {@code DELETE  /cs/:id} : delete the "id" c.
     *
     * @param id the id of the cDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cs/{id}")
    public ResponseEntity<Void> deleteC(@PathVariable Long id) {
        log.debug("REST request to delete C : {}", id);
        cService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
