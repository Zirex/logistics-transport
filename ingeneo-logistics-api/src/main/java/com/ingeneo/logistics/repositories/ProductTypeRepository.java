package com.ingeneo.logistics.repositories;

import com.ingeneo.logistics.entities.ProductType;
import com.ingeneo.logistics.enumeration.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    List<ProductType> findAllByState(State state);
}
