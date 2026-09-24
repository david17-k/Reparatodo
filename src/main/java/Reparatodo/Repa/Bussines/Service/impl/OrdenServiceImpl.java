package Reparatodo.Repa.Bussines.Service.impl;

import Reparatodo.Repa.Bussines.Service.ClienteService;
import Reparatodo.Repa.Bussines.Service.OrdenService;
import Reparatodo.Repa.Bussines.dto.ClienteDTO;
import Reparatodo.Repa.Bussines.dto.CrearClienteDTO;
import Reparatodo.Repa.Bussines.dto.CrearOrdenDto;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;


import Reparatodo.Repa.Persistence.Repository.ClienteRepository;
import Reparatodo.Repa.Persistence.dao.ClienteDao;
import Reparatodo.Repa.Persistence.dao.OrdenDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenDao ordenDao;
    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;
    private final ClienteDao clienteDao;


    @Override
    public OrdenDTO crearOrden(CrearOrdenDto crearOrdenDto) {
        log.info("Creando orden{} ",crearOrdenDto.getNombre());
        validarDatosOrden(crearOrdenDto);
        log.info("Orden crea con exito{} ",crearOrdenDto.getCedula());
        CrearClienteDTO crearClienteDTO=new CrearClienteDTO(crearOrdenDto.getCedula(),
                crearOrdenDto.getNombre(),crearOrdenDto.getApellido(), crearOrdenDto.getCelular());
        clienteService.crearCliente(crearClienteDTO);
        OrdenDTO creaOrden=ordenDao.save(crearOrdenDto);
        log.info("Orden creada con ID{}",creaOrden.getId());
        return creaOrden;
    }

    private void validarDatosOrden(CrearOrdenDto orden) {
        if(orden.getNombre()==null || orden.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("Nombre del cliente es obligatorio");
        }
    }


}
