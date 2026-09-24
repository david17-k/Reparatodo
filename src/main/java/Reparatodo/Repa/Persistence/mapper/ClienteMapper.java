package Reparatodo.Repa.Persistence.mapper;


import Reparatodo.Repa.Bussines.dto.ClienteDTO;
import Reparatodo.Repa.Bussines.dto.CrearClienteDTO;
import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
unmappedSourcePolicy = ReportingPolicy.WARN)
public interface ClienteMapper {
    ClienteDTO toDTO(ClienteEntity clienteEntity);

    @Mapping(target = "orden",ignore = true)
    ClienteEntity toEntity(CrearClienteDTO crearClienteDTO);
}
