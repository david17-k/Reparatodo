package Reparatodo.Repa.Controller;

import Reparatodo.Repa.Bussines.Estado.EstadoTecnico;
import Reparatodo.Repa.Bussines.Service.DispositivoService;
import Reparatodo.Repa.Bussines.Service.OrdenService;
import Reparatodo.Repa.Bussines.Service.TecnicoService;
import Reparatodo.Repa.Bussines.dto.ActualizarTecnico;
import Reparatodo.Repa.Bussines.dto.DispositivoDTO;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;
import Reparatodo.Repa.Bussines.dto.TecnicoDTO;
import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TomarOrdenController {
    private final TecnicoService tecnicoService;
    private final OrdenService ordenService;
    private final DispositivoService dispositivoService;


    @GetMapping("/tomar/{id}")
    public String formularioTecnico(  @PathVariable Long id,
                                      Model model) {
        OrdenDTO orden = ordenService.getOrdenById(id);
        DispositivoDTO dispositivo = dispositivoService.getDispositivoById(orden.getIdDispositivo());
        model.addAttribute("dispositivo", dispositivo);
        model.addAttribute("orden", orden);
        return "home/tomarOrden";
    }

    @PostMapping("/tomarOrden/{id}")
    public ResponseEntity<TecnicoDTO> actualizarTecnico(
            @PathVariable Long id,
            @RequestParam Long cedulaTecnico) {
        try {
            ActualizarTecnico actualizarTecnico=new ActualizarTecnico(EstadoTecnico.OCUPADO,id);
            TecnicoDTO actualizarTecnicos = tecnicoService.actualizarTecnico(cedulaTecnico, actualizarTecnico);
            return ResponseEntity.ok(actualizarTecnicos);
        } catch (RuntimeException e) {
            if(e.getMessage().contains("no registrado")){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }






}
