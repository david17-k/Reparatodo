package Reparatodo.Repa.Persistence.mapper;


import Reparatodo.Repa.Bussines.dto.ActualizarTecnico;
import Reparatodo.Repa.Bussines.dto.TecnicoDTO;
import Reparatodo.Repa.Persistence.entity.TecnicoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
unmappedSourcePolicy = ReportingPolicy.WARN)
public interface TecnicoMapper {

    TecnicoDTO toDTO(TecnicoEntity entity);

    @Mapping(target = "cedulaTecnico",ignore = true)
    @Mapping(target = "nombre",ignore = true)
    @Mapping(target = "telefono",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actulizarEntityFromDTO(ActualizarTecnico actualizarTecnico, @MappingTarget TecnicoEntity entity);
}
