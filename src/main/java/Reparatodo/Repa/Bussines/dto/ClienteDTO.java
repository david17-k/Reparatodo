package Reparatodo.Repa.Bussines.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO {

    Long id;
    private String nombre;
    private String apellido;
    private String celular;


}
