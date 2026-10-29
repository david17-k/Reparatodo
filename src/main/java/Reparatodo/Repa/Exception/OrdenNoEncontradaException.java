package Reparatodo.Repa.Exception;

public class OrdenNoEncontradaException extends RuntimeException{

    public OrdenNoEncontradaException(Long id){
        super("La orden no esta registrda");
    }
}
