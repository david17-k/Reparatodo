package Reparatodo.Repa.Bussines.Service;

import Reparatodo.Repa.Bussines.dto.ActualizarTecnico;
import Reparatodo.Repa.Bussines.dto.TecnicoDTO;

public interface TecnicoService {

    TecnicoDTO getTecnicoByid(Long id);

    TecnicoDTO actualizarTecnico(Long id, ActualizarTecnico actualizarTecnico);
}
