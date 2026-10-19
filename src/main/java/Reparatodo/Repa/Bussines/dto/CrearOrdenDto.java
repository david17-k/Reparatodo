package Reparatodo.Repa.Bussines.dto;

import Reparatodo.Repa.Bussines.Estado.Estado;

import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import Reparatodo.Repa.Persistence.entity.DispositivoEntity;
import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CrearOrdenDto {

    // Datos del cliente
    private Long cedula;
    private String nombre;
    private String apellido;
    private String celular;


    // Datos de la orden
    private Long codigo;
    private String tipoEquipo;
    private String dano;
    private String observaciones;
    private LocalDate fecha;
    private Estado estado;


}
