package Reparatodo.Repa.Bussines.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DispositivoDTO {

    Long id;
    private String dispositivo;
    private String codigo;
    private String dano;
    private String observaciones;
}
