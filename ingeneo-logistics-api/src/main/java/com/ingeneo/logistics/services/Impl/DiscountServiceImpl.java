package com.ingeneo.logistics.services.Impl;

import com.ingeneo.logistics.entities.Discount;
import com.ingeneo.logistics.enumeration.State;
import com.ingeneo.logistics.exceptions.BadRequestException;
import com.ingeneo.logistics.exceptions.NotFoundException;
import com.ingeneo.logistics.repositories.DiscountRepository;
import com.ingeneo.logistics.services.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    @Override
    public List<Discount> findAll(String state) {
        log.info("Fetching all discounts");
        State filter = null != state ? State.valueOf(state) : State.ACTIVE;
        return discountRepository.findByState(filter);
    }

    @Override
    public Discount findById(Long id) {
        log.info("fetching discount by id");
        return discountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El descuento con el id "+id+" no existe"));
    }

    @Override
    public Discount create(Discount o) {
        log.info("saving a discount");
        return discountRepository.save(o);
    }

    @Override
    public Discount update(Discount o) {
        log.info("updating a discount");
        if(null == o.getId()) {
            throw new BadRequestException("Para actualizar el descuento debe tener el id que le corresponde");
        }
        Optional<Discount> optionalDiscount = discountRepository.findById(o.getId());
        if(optionalDiscount.isPresent()) {
            return discountRepository.save(o);
        } else {
            throw new NotFoundException("El descuento con el id "+ o.getId() + " no existe");
        }
    }
}
