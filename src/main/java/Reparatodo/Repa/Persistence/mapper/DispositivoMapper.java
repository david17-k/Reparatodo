package Reparatodo.Repa.Persistence.mapper;


import Reparatodo.Repa.Bussines.dto.CrearDispositivoDTO;
import Reparatodo.Repa.Bussines.dto.DispositivoDTO;
import Reparatodo.Repa.Persistence.entity.DispositivoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface DispositivoMapper {


    List<DispositivoDTO>toDTOlist(List<DispositivoEntity>dispositivoEntities);

    DispositivoDTO toDTO(DispositivoEntity entity);


    DispositivoEntity toEntity(CrearDispositivoDTO dispositivoDTO);
}
