package Reparatodo.Repa.Persistence.mapper;


import Reparatodo.Repa.Bussines.dto.CrearOrdenDto;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;
import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
unmappedSourcePolicy = ReportingPolicy.WARN)
public interface OrdenMapper {


    OrdenDTO toDTO(OrdenEntity entity);

    @Mapping(target = "id",ignore = true)
    @Mapping(target ="clienteEntity",source = "cedula",qualifiedByName = "createClienteEntityFromId")
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





}
