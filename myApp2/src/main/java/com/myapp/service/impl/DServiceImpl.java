package com.myapp.service.impl;

import com.myapp.service.DService;
import com.myapp.domain.D;
import com.myapp.repository.DRepository;
import com.myapp.service.dto.DDTO;
import com.myapp.service.mapper.DMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link D}.
 */
@Service
@Transactional
public class DServiceImpl implements DService {

    private final Logger log = LoggerFactory.getLogger(DServiceImpl.class);

    private final DRepository dRepository;

    private final DMapper dMapper;

    public DServiceImpl(DRepository dRepository, DMapper dMapper) {
        this.dRepository = dRepository;
        this.dMapper = dMapper;
    }

    @Override
    public DDTO save(DDTO dDTO) {
        log.debug("Request to save D : {}", dDTO);
        D d = dMapper.toEntity(dDTO);
        d = dRepository.save(d);
        return dMapper.toDto(d);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DDTO> findAll() {
        log.debug("Request to get all DS");
        return dRepository.findAll().stream()
            .map(dMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the ds where D is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<DDTO> findAllWhereDIsNull() {
        log.debug("Request to get all ds where D is null");
        return StreamSupport
            .stream(dRepository.findAll().spliterator(), false)
            .filter(d -> d.getD() == null)
            .map(dMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DDTO> findOne(Long id) {
        log.debug("Request to get D : {}", id);
        return dRepository.findById(id)
            .map(dMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete D : {}", id);
        dRepository.deleteById(id);
    }
}
