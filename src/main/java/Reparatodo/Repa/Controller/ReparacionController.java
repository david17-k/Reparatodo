package Reparatodo.Repa.Controller;

import Reparatodo.Repa.Bussines.Estado.Estado;
import Reparatodo.Repa.Bussines.Service.DispositivoService;
import Reparatodo.Repa.Bussines.Service.OrdenService;
import Reparatodo.Repa.Bussines.Service.TecnicoService;
import Reparatodo.Repa.Bussines.dto.ActualizarOrdenDTO;
import Reparatodo.Repa.Bussines.dto.DispositivoDTO;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;
import Reparatodo.Repa.Bussines.dto.TecnicoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReparacionController {

    private final TecnicoService tecnicoService;
    private final OrdenService ordenService;
    private final DispositivoService dispositivoService;


    @GetMapping("/reparacion/{id}")
    public String reparacion(@PathVariable Long id,
            Model model){
            OrdenDTO orden=ordenService.getOrdenById(tecnicoService.idOrden(id));
        DispositivoDTO dispositivo=dispositivoService.getDispositivoById(orden.getIdDispositivo());
            model.addAttribute("orden", orden);
            model.addAttribute("dispositivo",dispositivo);
        return "home/reparacion";
    }

    @PostMapping("/actualizarorden/{id}")
    public ResponseEntity<OrdenDTO> actualizar(@PathVariable Long id,
            @ModelAttribute ActualizarOrdenDTO actualizarOrdenDTO){
        OrdenDTO actualizarOrden=ordenService.actualizarOrden(id,actualizarOrdenDTO);
        return ResponseEntity.status(HttpStatus.OK).body(actualizarOrden);
    }


}
