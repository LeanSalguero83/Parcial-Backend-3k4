package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.SupplierDto;
import com.example.ParcialSabado28.model.Supplier;
import com.example.ParcialSabado28.repository.IdentifierRepository;
import com.example.ParcialSabado28.repository.SupplierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SupplierServiceImpl implements ISupplierService {
    SupplierRepository supplierRepository;
    SupplierDtoMapper supplierDtoMapper;
    SupplierMapper supplierMapper;
    IdentifierRepository identifierRepository;

    @Override
    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> values = supplierRepository.findAll();
        return values
                .stream()
                .map(supplierDtoMapper)
                .toList();
    }

    @Override
    public SupplierDto createSupplier(SupplierDto supplierDto) {
        // Convertimos el DTO a la entidad Supplier.
        Supplier supplier = Optional.of(supplierDto).map(supplierMapper).orElseThrow();

        // Obtenemos un nuevo identificador para la tabla Supplier.
        int supplierId = identifierRepository.nextValue(Supplier.TABLE_NAME);

        // Asignamos el nuevo identificador al Supplier.
        supplier.setSupplierId(supplierId);

        // Guardamos el nuevo Supplier en la base de datos.
        Supplier savedSupplier = supplierRepository.save(supplier);

        // Convertimos el Supplier guardado a un DTO para devolverlo.
        return supplierDtoMapper.apply(savedSupplier);
    }

    @Override
    public SupplierDto updateSupplier(SupplierDto supplierDto) {
        Supplier supplier = Optional.of(supplierDto).map(supplierMapper).orElseThrow();
        supplierRepository.save(supplier);
        return supplierDtoMapper.apply(supplier);
    }

    @Override
    public SupplierDto deleteSupplierById(Integer supplierId) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        SupplierDto deleted = optionalSupplier.map(supplierDtoMapper).orElseThrow();
        optionalSupplier.ifPresent(supplierRepository::delete);
        return deleted;

    }

    @Override
    public SupplierDto findSupplierById(Integer supplierId) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        return optionalSupplier.map(supplierDtoMapper).orElseThrow();
    }
}
