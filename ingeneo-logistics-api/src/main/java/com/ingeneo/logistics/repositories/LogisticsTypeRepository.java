package com.ingeneo.logistics.repositories;

import com.ingeneo.logistics.entities.LogisticsType;
import com.ingeneo.logistics.enumeration.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticsTypeRepository extends JpaRepository<LogisticsType, Long> {
    List<LogisticsType> findAllByState(State state);
}
