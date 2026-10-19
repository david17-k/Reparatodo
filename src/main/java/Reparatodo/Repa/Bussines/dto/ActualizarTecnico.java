package Reparatodo.Repa.Bussines.dto;

import Reparatodo.Repa.Bussines.Estado.EstadoTecnico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarTecnico {

    private EstadoTecnico estadoTecnico;
    private Long idOrden;


}
