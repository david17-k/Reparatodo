package Reparatodo.Repa.Bussines.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CrearDispositivoDTO {

    private Long codigo;
    private String dispositivo;
    private String dano;
    private String observaciones;

}
