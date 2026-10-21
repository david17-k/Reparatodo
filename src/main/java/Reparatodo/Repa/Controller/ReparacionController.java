package Reparatodo.Repa.Controller;

import Reparatodo.Repa.Bussines.Service.DispositivoService;
import Reparatodo.Repa.Bussines.Service.OrdenService;
import Reparatodo.Repa.Bussines.Service.TecnicoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReparacionController {

    private final TecnicoService tecnicoService;


    @GetMapping("/reparacion/{id}")
    public String reparacion(Model model){


        return "home/reparacion";
    }
}
