package com.ingeneo.logistics.services;

import com.ingeneo.logistics.entities.LogisticsType;
import com.ingeneo.logistics.repositories.LogisticsTypeRepository;
import com.ingeneo.logistics.services.Impl.LogisticsTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogisticsTypeServiceImplTest {
    private LogisticsTypeRepository logisticsTypeRepository;
    private LogisticsTypeService logisticsTypeService;

    @BeforeEach
    public void init() {
        logisticsTypeRepository = Mockito.mock(LogisticsTypeRepository.class);
        logisticsTypeService = new LogisticsTypeServiceImpl(logisticsTypeRepository);
    }

    @DisplayName("Should save logistics type and return ok")
    @Test
    public void shouldSaveLogisticsTypeAndReturnOk() {
        //A: arrange
        var logisticsTypeId = 2;
        LogisticsType logisticsType = new LogisticsType();
        logisticsType.setId(2L);

        Mockito.when(logisticsTypeService.create(ArgumentMatchers.any()))
                .thenReturn(logisticsType);

        //A: act
        LogisticsType newLogisticsType = logisticsTypeService.create(logisticsType);

        //A: assert
        assertEquals(logisticsTypeId, newLogisticsType.getId());
    }
}
