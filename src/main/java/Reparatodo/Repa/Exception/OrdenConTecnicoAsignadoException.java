package Reparatodo.Repa.Exception;

public class OrdenConTecnicoAsignadoException extends RuntimeException{

    public OrdenConTecnicoAsignadoException(Long id){
        super("La orden ya tiene un tecnico asignado"+id);
    }
}
