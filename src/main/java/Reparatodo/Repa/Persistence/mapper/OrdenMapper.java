package Reparatodo.Repa.Persistence.mapper;


import Reparatodo.Repa.Bussines.dto.ActualizarOrdenDTO;
import Reparatodo.Repa.Bussines.dto.CrearOrdenDto;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;
import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import Reparatodo.Repa.Persistence.entity.DispositivoEntity;
import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedSourcePolicy = ReportingPolicy.WARN)
public interface OrdenMapper {




    @Mapping(target = "idCliente",source = "clienteEntity.cedula")
    @Mapping(target = "idDispositivo",source = "dispositivoEntity.codigo")
    OrdenDTO toDTO(OrdenEntity entity);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "observaciones",ignore = true)
    @Mapping(target ="clienteEntity",source = "cedula",qualifiedByName = "createClienteEntityFromId")
    @Mapping(target = "dispositivoEntity",source = "codigo",qualifiedByName = "createDispositivoEntityFromId")
    OrdenEntity toEntity(CrearOrdenDto crearOrdenDto);

    @Named("createClienteEntityFromId")
    default ClienteEntity createClienteEntityFromId(Long idCliente){
        if(idCliente==null){
            return null;
        }
       ClienteEntity clienteEntity =new ClienteEntity();
        clienteEntity.setCedula(idCliente);
        return clienteEntity;
    }

    @Named("createDispositivoEntityFromId")
    default DispositivoEntity createDispositivoEntityFromId(Long codigo){
        if(codigo==null){
            return null;
        }
        DispositivoEntity dispositivoEntity=new DispositivoEntity();
        dispositivoEntity.setCodigo(codigo);
        return dispositivoEntity;
    }

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "fecha",ignore = true)
    @Mapping(target = "clienteEntity",ignore = true)
    @Mapping(target = "dispositivoEntity",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void actualuzarEntityFromDTO(ActualizarOrdenDTO actualizarOrdenDTO,@MappingTarget OrdenEntity ordenEntity);



    List<OrdenDTO>toDTOList(List<OrdenEntity>entities);





}
