package Reparatodo.Repa.Bussines.Service;

import Reparatodo.Repa.Bussines.dto.ActualizarOrdenDTO;
import Reparatodo.Repa.Bussines.dto.CrearOrdenDto;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;

import java.util.List;


public interface OrdenService {

    OrdenDTO crearOrden(CrearOrdenDto crearOrdenDto);

    List<OrdenDTO>getAllOrden();

    OrdenDTO getOrdenById(Long id);

    OrdenDTO actualizarOrden(Long id, ActualizarOrdenDTO actualizarOrdenDTO);

    boolean tecnicoAsignado(Long id);

}
