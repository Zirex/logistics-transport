package com.ingeneo.logistics.services.Impl;

import com.ingeneo.logistics.entities.ProductType;
import com.ingeneo.logistics.enumeration.State;
import com.ingeneo.logistics.exceptions.NotFoundException;
import com.ingeneo.logistics.repositories.ProductTypeRepository;
import com.ingeneo.logistics.services.ProductTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> findAll(String state) {
        log.info("fetching all products type by state {}", state);
        State filter = null != state && !state.trim().isEmpty() ? State.valueOf(state) : State.ACTIVE;
        return productTypeRepository.findAllByState(filter);
    }

    @Override
    public ProductType findById(Long id) {
        log.info("fetching product type by id {}", id);
        return productTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Tipo de producto no existe"));
    }

    @Override
    public ProductType create(ProductType o) {
        log.info("saving a product Type with data {}", o.toString());
        return productTypeRepository.save(o);
    }

    @Override
    public ProductType update(ProductType o) {
        log.info("update a product type with data {}", o.toString());
        Optional<ProductType> optionalProductType = productTypeRepository.findById(o.getId());
        if (optionalProductType.isPresent()){
            return productTypeRepository.save(o);
        } else {
            throw new NotFoundException("El tipo de producto con el id " + o.getId() + " no existe.");
        }
    }
}
