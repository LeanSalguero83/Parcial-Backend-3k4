package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.SupplierDto;
import com.example.ParcialSabado28.model.Supplier;
import com.example.ParcialSabado28.repository.IdentifierRepository;
import com.example.ParcialSabado28.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SupplierServiceImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SupplierDtoMapper supplierDtoMapper;

    @Mock
    private SupplierMapper supplierMapper;

    @Mock
    private IdentifierRepository identifierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSuppliers() {
        Supplier supplier1 = new Supplier();
        Supplier supplier2 = new Supplier();
        SupplierDto supplierDto1 = new SupplierDto();
        SupplierDto supplierDto2 = new SupplierDto();

        when(supplierRepository.findAll()).thenReturn(Arrays.asList(supplier1, supplier2));
        when(supplierDtoMapper.apply(supplier1)).thenReturn(supplierDto1);
        when(supplierDtoMapper.apply(supplier2)).thenReturn(supplierDto2);

        List<SupplierDto> result = supplierService.getAllSuppliers();

        assertEquals(2, result.size());
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    void createSupplier() {
        SupplierDto supplierDto = new SupplierDto();
        Supplier supplier = new Supplier();

        when(supplierMapper.apply(supplierDto)).thenReturn(supplier);
        when(supplierRepository.save(supplier)).thenReturn(supplier); // Asumiendo que el ID se genera automáticamente o previamente.
        when(supplierDtoMapper.apply(supplier)).thenReturn(supplierDto);

        SupplierDto result = supplierService.createSupplier(supplierDto);

        assertEquals(supplierDto, result);
        verify(supplierRepository, times(1)).save(supplier);
    }


    @Test
    void updateSupplier() {
        SupplierDto supplierDto = new SupplierDto();
        Supplier supplier = new Supplier();

        when(supplierMapper.apply(supplierDto)).thenReturn(supplier);
        when(supplierDtoMapper.apply(supplier)).thenReturn(supplierDto);

        SupplierDto result = supplierService.updateSupplier(supplierDto);

        assertEquals(supplierDto, result);
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    void deleteSupplierById() {
        Supplier supplier = new Supplier();
        SupplierDto supplierDto = new SupplierDto();
        Integer supplierId = 1;

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        when(supplierDtoMapper.apply(supplier)).thenReturn(supplierDto);

        SupplierDto result = supplierService.deleteSupplierById(supplierId);

        assertEquals(supplierDto, result);
        verify(supplierRepository, times(1)).delete(supplier);
    }

    @Test
    void findSupplierById() {
        Supplier supplier = new Supplier();
        SupplierDto supplierDto = new SupplierDto();
        Integer supplierId = 1;

        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
        when(supplierDtoMapper.apply(supplier)).thenReturn(supplierDto);

        SupplierDto result = supplierService.findSupplierById(supplierId);

        assertEquals(supplierDto, result);
    }
}
