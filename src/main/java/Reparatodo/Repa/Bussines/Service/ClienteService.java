package Reparatodo.Repa.Bussines.Service;

import Reparatodo.Repa.Bussines.dto.ClienteDTO;
import Reparatodo.Repa.Bussines.dto.CrearClienteDTO;

public interface ClienteService  {

    ClienteDTO crearCliente(CrearClienteDTO crearClienteDTO);

    ClienteDTO getClienteById(Long id);


    boolean existsByCedula(String cedula);


}
