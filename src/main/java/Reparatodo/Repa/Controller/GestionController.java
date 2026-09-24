package Reparatodo.Repa.Controller;


import Reparatodo.Repa.Bussines.Service.OrdenService;
import Reparatodo.Repa.Bussines.dto.CrearOrdenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class GestionController {

    private final OrdenService ordenService;

    @GetMapping("/gestion")
    public String mostrarPantalla(){
        return "home/gestionar";
    }

    @GetMapping("/crear")
    public String orden(){
        return "home/orden";

    }









}
