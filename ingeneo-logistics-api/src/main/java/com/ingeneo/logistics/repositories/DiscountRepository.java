package com.ingeneo.logistics.repositories;

import com.ingeneo.logistics.entities.Discount;
import com.ingeneo.logistics.enumeration.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findByState(State state);
}
