package Reparatodo.Repa.Bussines.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DispositivoDTO {

    private Long codigo;
    private String dispositivo;
    private String dano;
    private String observaciones;
}