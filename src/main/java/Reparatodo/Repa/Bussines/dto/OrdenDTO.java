package Reparatodo.Repa.Bussines.dto;


import Reparatodo.Repa.Bussines.Estado.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdenDTO {


    private Long id;
    private Long idCliente;
    private LocalDate fecha;
    private Estado estado;
    private Long idDispositivo;


}
