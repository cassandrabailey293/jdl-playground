package com.myapp.service.impl;

import com.myapp.service.CService;
import com.myapp.domain.C;
import com.myapp.repository.CRepository;
import com.myapp.service.dto.CDTO;
import com.myapp.service.mapper.CMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link C}.
 */
@Service
@Transactional
public class CServiceImpl implements CService {

    private final Logger log = LoggerFactory.getLogger(CServiceImpl.class);

    private final CRepository cRepository;

    private final CMapper cMapper;

    public CServiceImpl(CRepository cRepository, CMapper cMapper) {
        this.cRepository = cRepository;
        this.cMapper = cMapper;
    }

    @Override
    public CDTO save(CDTO cDTO) {
        log.debug("Request to save C : {}", cDTO);
        C c = cMapper.toEntity(cDTO);
        c = cRepository.save(c);
        return cMapper.toDto(c);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CDTO> findAll() {
        log.debug("Request to get all CS");
        return cRepository.findAll().stream()
            .map(cMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CDTO> findOne(Long id) {
        log.debug("Request to get C : {}", id);
        return cRepository.findById(id)
            .map(cMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete C : {}", id);
        cRepository.deleteById(id);
    }
}
