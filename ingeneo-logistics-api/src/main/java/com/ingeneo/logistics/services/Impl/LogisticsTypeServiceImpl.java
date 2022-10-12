package com.ingeneo.logistics.services.Impl;

import com.ingeneo.logistics.entities.LogisticsType;
import com.ingeneo.logistics.exceptions.NotFoundException;
import com.ingeneo.logistics.repositories.LogisticsTypeRepository;
import com.ingeneo.logistics.services.LogisticsTypeService;
import com.ingeneo.logistics.enumeration.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogisticsTypeServiceImpl implements LogisticsTypeService {
    private final LogisticsTypeRepository logisticsTypeRepository;
    @Override
    public List<LogisticsType> findAll(String state) {
        State filter = null != state ? State.valueOf(state) : State.ACTIVE;
        return logisticsTypeRepository.findAllByState(filter);
    }

    @Override
    public LogisticsType findById(Long id) {
        return logisticsTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de logistica no existe"));
    }

    @Override
    public LogisticsType create(LogisticsType o) {
        return logisticsTypeRepository.save(o);
    }

    @Override
    public LogisticsType update(LogisticsType o) {
        Optional<LogisticsType> optionalLogisticsType = logisticsTypeRepository.findById(o.getId());
        if(optionalLogisticsType.isPresent()) {
            return logisticsTypeRepository.save(o);
        }else {
            throw new NotFoundException("El tipo de logistica con el id "+o.getId()+" no existe");
        }
    }
}
