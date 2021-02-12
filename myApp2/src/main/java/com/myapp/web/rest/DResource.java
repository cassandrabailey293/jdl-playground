package com.myapp.web.rest;

import com.myapp.service.DService;
import com.myapp.web.rest.errors.BadRequestAlertException;
import com.myapp.service.dto.DDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.myapp.domain.D}.
 */
@RestController
@RequestMapping("/api")
public class DResource {

    private final Logger log = LoggerFactory.getLogger(DResource.class);

    private static final String ENTITY_NAME = "myApp2D";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DService dService;

    public DResource(DService dService) {
        this.dService = dService;
    }

    /**
     * {@code POST  /ds} : Create a new d.
     *
     * @param dDTO the dDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dDTO, or with status {@code 400 (Bad Request)} if the d has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ds")
    public ResponseEntity<DDTO> createD(@Valid @RequestBody DDTO dDTO) throws URISyntaxException {
        log.debug("REST request to save D : {}", dDTO);
        if (dDTO.getId() != null) {
            throw new BadRequestAlertException("A new d cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DDTO result = dService.save(dDTO);
        return ResponseEntity.created(new URI("/api/ds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ds} : Updates an existing d.
     *
     * @param dDTO the dDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dDTO,
     * or with status {@code 400 (Bad Request)} if the dDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ds")
    public ResponseEntity<DDTO> updateD(@Valid @RequestBody DDTO dDTO) throws URISyntaxException {
        log.debug("REST request to update D : {}", dDTO);
        if (dDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DDTO result = dService.save(dDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ds} : get all the ds.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ds in body.
     */
    @GetMapping("/ds")
    public List<DDTO> getAllDS(@RequestParam(required = false) String filter) {
        if ("d-is-null".equals(filter)) {
            log.debug("REST request to get all Ds where d is null");
            return dService.findAllWhereDIsNull();
        }
        log.debug("REST request to get all DS");
        return dService.findAll();
    }

    /**
     * {@code GET  /ds/:id} : get the "id" d.
     *
     * @param id the id of the dDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ds/{id}")
    public ResponseEntity<DDTO> getD(@PathVariable Long id) {
        log.debug("REST request to get D : {}", id);
        Optional<DDTO> dDTO = dService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dDTO);
    }

    /**
     * {@code DELETE  /ds/:id} : delete the "id" d.
     *
     * @param id the id of the dDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ds/{id}")
    public ResponseEntity<Void> deleteD(@PathVariable Long id) {
        log.debug("REST request to delete D : {}", id);
        dService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
