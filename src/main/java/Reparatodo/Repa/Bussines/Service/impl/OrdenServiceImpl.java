package Reparatodo.Repa.Bussines.Service.impl;

import Reparatodo.Repa.Bussines.Service.ClienteService;
import Reparatodo.Repa.Bussines.Service.DispositivoService;
import Reparatodo.Repa.Bussines.Service.OrdenService;
import Reparatodo.Repa.Bussines.dto.*;


import Reparatodo.Repa.Exception.OrdenNoEncontradaException;
import Reparatodo.Repa.Persistence.Repository.ClienteRepository;
import Reparatodo.Repa.Persistence.dao.ClienteDao;
import Reparatodo.Repa.Persistence.dao.OrdenDao;
import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenDao ordenDao;
    private final ClienteService clienteService;
    private final DispositivoService dispositivoService;



    @Override
    public OrdenDTO crearOrden(CrearOrdenDto crearOrdenDto) {
        validarDatosOrden(crearOrdenDto);
            CrearClienteDTO cliente=new CrearClienteDTO(
                    crearOrdenDto.getCedula(),
                    crearOrdenDto.getNombre(),
                            crearOrdenDto.getApellido(),
                            crearOrdenDto.getCelular());
            clienteService.crearCliente(cliente);

            CrearDispositivoDTO dispositivo=new CrearDispositivoDTO(
                    crearOrdenDto.getCodigo(),
                    crearOrdenDto.getTipoEquipo(),
                    crearOrdenDto.getDano(),
                    crearOrdenDto.getObservaciones());
            dispositivoService.crearDispositivo(dispositivo);
        OrdenDTO creaOrden=ordenDao.save(crearOrdenDto);
        return creaOrden;
    }

    @Override
    public List<OrdenDTO> getAllOrden() {
        return ordenDao.getAllOrdenes() ;
    }

    @Override
    @Transactional(readOnly=true)
    public OrdenDTO getOrdenById(Long id) {
        validarFormato(id);
        return ordenDao.findById(id).orElseThrow(()->{
            return new RuntimeException("Orden no encontrada");
        });
    }

    @Override
    public OrdenDTO actualizarOrden(Long id, ActualizarOrdenDTO actualizarOrdenDTO) {
        if(!ordenDao.findById(id).isPresent()){
            throw new OrdenNoEncontradaException(id);
        }
        OrdenDTO actualizarDTO=ordenDao.actualizarOrden(id, actualizarOrdenDTO).orElseThrow(()->
            new RuntimeException("Error al actulizar los datos"));
        return actualizarDTO;
    }

    @Override
    public boolean tecnicoAsignado(Long id) {
        return ordenDao.existsByTecnicoEntity(id);
    }

    private void validarFormato(Long id) {
        if(id==null || id<=0){
            throw new RuntimeException("Id no valido");
        }
    }

    private void validarDatosOrden(CrearOrdenDto orden) {
        if(orden.getNombre()==null || orden.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("Nombre del cliente es obligatorio");
        }
    }


}
