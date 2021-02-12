package com.myapp.web.rest;

import com.myapp.MyApp2App;
import com.myapp.domain.D;
import com.myapp.domain.C;
import com.myapp.repository.DRepository;
import com.myapp.service.DService;
import com.myapp.service.dto.DDTO;
import com.myapp.service.mapper.DMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.myapp.domain.enumeration.Type;
/**
 * Integration tests for the {@link DResource} REST controller.
 */
@SpringBootTest(classes = MyApp2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class DResourceIT {

    private static final Type DEFAULT_NAME = Type.KEY;
    private static final Type UPDATED_NAME = Type.JUSTKEY;

    @Autowired
    private DRepository dRepository;

    @Autowired
    private DMapper dMapper;

    @Autowired
    private DService dService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDMockMvc;

    private D d;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static D createEntity(EntityManager em) {
        D d = new D()
            .name(DEFAULT_NAME);
        // Add required entity
        C c;
        if (TestUtil.findAll(em, C.class).isEmpty()) {
            c = CResourceIT.createEntity(em);
            em.persist(c);
            em.flush();
        } else {
            c = TestUtil.findAll(em, C.class).get(0);
        }
        d.setD(c);
        return d;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static D createUpdatedEntity(EntityManager em) {
        D d = new D()
            .name(UPDATED_NAME);
        // Add required entity
        C c;
        if (TestUtil.findAll(em, C.class).isEmpty()) {
            c = CResourceIT.createUpdatedEntity(em);
            em.persist(c);
            em.flush();
        } else {
            c = TestUtil.findAll(em, C.class).get(0);
        }
        d.setD(c);
        return d;
    }

    @BeforeEach
    public void initTest() {
        d = createEntity(em);
    }

    @Test
    @Transactional
    public void createD() throws Exception {
        int databaseSizeBeforeCreate = dRepository.findAll().size();
        // Create the D
        DDTO dDTO = dMapper.toDto(d);
        restDMockMvc.perform(post("/api/ds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dDTO)))
            .andExpect(status().isCreated());

        // Validate the D in the database
        List<D> dList = dRepository.findAll();
        assertThat(dList).hasSize(databaseSizeBeforeCreate + 1);
        D testD = dList.get(dList.size() - 1);
        assertThat(testD.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createDWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dRepository.findAll().size();

        // Create the D with an existing ID
        d.setId(1L);
        DDTO dDTO = dMapper.toDto(d);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDMockMvc.perform(post("/api/ds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dDTO)))
            .andExpect(status().isBadRequest());

        // Validate the D in the database
        List<D> dList = dRepository.findAll();
        assertThat(dList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDS() throws Exception {
        // Initialize the database
        dRepository.saveAndFlush(d);

        // Get all the dList
        restDMockMvc.perform(get("/api/ds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(d.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getD() throws Exception {
        // Initialize the database
        dRepository.saveAndFlush(d);

        // Get the d
        restDMockMvc.perform(get("/api/ds/{id}", d.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(d.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingD() throws Exception {
        // Get the d
        restDMockMvc.perform(get("/api/ds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateD() throws Exception {
        // Initialize the database
        dRepository.saveAndFlush(d);

        int databaseSizeBeforeUpdate = dRepository.findAll().size();

        // Update the d
        D updatedD = dRepository.findById(d.getId()).get();
        // Disconnect from session so that the updates on updatedD are not directly saved in db
        em.detach(updatedD);
        updatedD
            .name(UPDATED_NAME);
        DDTO dDTO = dMapper.toDto(updatedD);

        restDMockMvc.perform(put("/api/ds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dDTO)))
            .andExpect(status().isOk());

        // Validate the D in the database
        List<D> dList = dRepository.findAll();
        assertThat(dList).hasSize(databaseSizeBeforeUpdate);
        D testD = dList.get(dList.size() - 1);
        assertThat(testD.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingD() throws Exception {
        int databaseSizeBeforeUpdate = dRepository.findAll().size();

        // Create the D
        DDTO dDTO = dMapper.toDto(d);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDMockMvc.perform(put("/api/ds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dDTO)))
            .andExpect(status().isBadRequest());

        // Validate the D in the database
        List<D> dList = dRepository.findAll();
        assertThat(dList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteD() throws Exception {
        // Initialize the database
        dRepository.saveAndFlush(d);

        int databaseSizeBeforeDelete = dRepository.findAll().size();

        // Delete the d
        restDMockMvc.perform(delete("/api/ds/{id}", d.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<D> dList = dRepository.findAll();
        assertThat(dList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
