package Reparatodo.Repa.Bussines.dto;


import Reparatodo.Repa.Bussines.Estado.EstadoTecnico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TecnicoDTO {

   private Long cedulaTecnico;
   private EstadoTecnico estadoTecnico;
   private Long idOrden;
   private String nombre;
   private String telefono;
}
