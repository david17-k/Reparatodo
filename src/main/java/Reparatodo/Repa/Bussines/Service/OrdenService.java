package Reparatodo.Repa.Bussines.Service;

import Reparatodo.Repa.Bussines.dto.CrearOrdenDto;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;

import java.util.List;


public interface OrdenService {

    OrdenDTO crearOrden(CrearOrdenDto crearOrdenDto);

    List<OrdenDTO>getAllOrden();

    OrdenDTO getOrdenById(Long id);

    boolean tecnicoAsignado(Long id);

}
