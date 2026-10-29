package Reparatodo.Repa.Persistence.mapper;


import Reparatodo.Repa.Bussines.dto.ActualizarTecnico;
import Reparatodo.Repa.Bussines.dto.TecnicoDTO;
import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import Reparatodo.Repa.Persistence.entity.TecnicoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
unmappedSourcePolicy = ReportingPolicy.WARN)
public interface TecnicoMapper {



    @Mapping(target = "ordenEntity",source = "idOrden",qualifiedByName = "createOrdenEntityFromId")
    TecnicoEntity toEntity(TecnicoDTO tecnicoDTO);

    @Named("createOrdenEntityFromId")
    default OrdenEntity createOrdenEntityFromId(Long idOrden){
        if(idOrden==null){
            return null;
        }
        OrdenEntity ordenEntity=new OrdenEntity();
        ordenEntity.setId(idOrden);
        return ordenEntity;
    }

    @Mapping(target = "idOrden",source = "ordenEntity.id")
    TecnicoDTO toDTO(TecnicoEntity entity);

    @Mapping(target = "cedulaTecnico",ignore = true)
    @Mapping(target = "nombre",ignore = true)
    @Mapping(target = "telefono",ignore = true)
    @Mapping(target = "ordenEntity",source = "idOrden",qualifiedByName = "createOrdenEntityFromId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actulizarEntityFromDTO(ActualizarTecnico actualizarTecnico, @MappingTarget TecnicoEntity entity);


}


