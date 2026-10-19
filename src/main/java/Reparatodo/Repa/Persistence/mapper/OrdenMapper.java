package Reparatodo.Repa.Persistence.mapper;


import Reparatodo.Repa.Bussines.dto.CrearOrdenDto;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;
import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import Reparatodo.Repa.Persistence.entity.DispositivoEntity;
import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedSourcePolicy = ReportingPolicy.WARN)
public interface OrdenMapper {



    @Mapping(target = "idCliente",source = "clienteEntity.cedula")
    @Mapping(target = "idDispositivo",source = "dispositivoEntity.codigo")
    OrdenDTO toDTO(OrdenEntity entity);

    @Mapping(target = "id",ignore = true)
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



    List<OrdenDTO>toDTOList(List<OrdenEntity>entities);





}
