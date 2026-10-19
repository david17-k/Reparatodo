package Reparatodo.Repa.Bussines.Service;

import Reparatodo.Repa.Bussines.dto.CrearDispositivoDTO;
import Reparatodo.Repa.Bussines.dto.DispositivoDTO;

import java.util.List;

public interface DispositivoService {

    DispositivoDTO crearDispositivo(CrearDispositivoDTO crearDispositivoDTO);

    DispositivoDTO getDispositivoById(Long id);

    List<DispositivoDTO>getAll();
}
