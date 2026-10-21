package Reparatodo.Repa.Exception;

public class TecnicoOrdenAsignada extends RuntimeException{

    public TecnicoOrdenAsignada(Long cedula){
        super("El tecnico ya tiene una orden asignada"+cedula);
    }
}
