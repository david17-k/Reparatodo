package Reparatodo.Repa.Bussines.dto;

import Reparatodo.Repa.Bussines.Estado.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarOrdenDTO {

    private Estado estado;
    private String observaciones;

}
