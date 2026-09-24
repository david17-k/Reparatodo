package Reparatodo.Repa.Bussines.Service.impl;

import Reparatodo.Repa.Bussines.Service.ClienteService;
import Reparatodo.Repa.Bussines.dto.ClienteDTO;
import Reparatodo.Repa.Bussines.dto.CrearClienteDTO;
import Reparatodo.Repa.Persistence.dao.ClienteDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDao clienteDao;



    @Override
    public ClienteDTO crearCliente(CrearClienteDTO crearClienteDTO) {
        ClienteDTO crearCliente=clienteDao.save(crearClienteDTO);
        return crearCliente;
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO getClienteById(Long id) {
        validarFomato(id);
        return clienteDao.findById(id).orElseThrow(()->{
            return new RuntimeException("Cliente no encontrado");
        });
    }

    @Override
    public boolean existsByCedula(String cedula) {
        return clienteDao.existsByCedula(cedula);
    }


    private void validarFomato(Long id){
        if(id<=0 || id==null){
            throw new RuntimeException("El id no cumple con el formato");
        }
    }




}
