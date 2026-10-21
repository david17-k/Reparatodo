package Reparatodo.Repa.Exception;

public class TecnicoNoEncontradoException extends RuntimeException {

    public TecnicoNoEncontradoException(Long cedulaTecnico){
        super("Tecnico con cedula"+cedulaTecnico+"no encontrado");
    }





}
