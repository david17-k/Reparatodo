package Reparatodo.Repa.Controller;


import Reparatodo.Repa.Bussines.Service.DispositivoService;
import Reparatodo.Repa.Bussines.Service.OrdenService;
import Reparatodo.Repa.Bussines.dto.DispositivoDTO;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GestionController {

    private final OrdenService ordenService;
    private final DispositivoService dispositivoService;

    @GetMapping("/gestion")
    public String mostrarPantalla(Model model){
        log.debug("Obteniendo orderliness");
        List<OrdenDTO> orderliness =ordenService.getAllOrden();
        model.addAttribute(
                "orderliness",
                orderliness
        );
        return "home/gestionar";
    }

    @GetMapping("/crear")
    public String orden(){
        return "home/orden";
    }

    @GetMapping("/ordenes/{id}")
    public String verDetalles(@PathVariable Long id,
                              Model model){
        OrdenDTO orden=ordenService.getOrdenById(id);
        model.addAttribute("orden",orden);
        return "home/detalleOrden";
    }

    @GetMapping("/orden/{id}")
    public String tomarOrden()
    {
        return "home/tomarOrden";
    }














}
