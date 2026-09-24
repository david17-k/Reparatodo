package Reparatodo.Repa.Bussines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CrearClienteDTO {


    private Long cedula;

    private String nombre;
    private String apellido;
    private String celular;

}
