package Reparatodo.Repa.Controller;

import Reparatodo.Repa.Bussines.Estado.EstadoTecnico;
import Reparatodo.Repa.Bussines.Service.DispositivoService;
import Reparatodo.Repa.Bussines.Service.OrdenService;
import Reparatodo.Repa.Bussines.Service.TecnicoService;
import Reparatodo.Repa.Bussines.dto.ActualizarTecnico;
import Reparatodo.Repa.Bussines.dto.DispositivoDTO;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;
import Reparatodo.Repa.Bussines.dto.TecnicoDTO;
import Reparatodo.Repa.Exception.OrdenConTecnicoAsignadoException;
import Reparatodo.Repa.Exception.TecnicoNoEncontradoException;
import Reparatodo.Repa.Exception.TecnicoOrdenAsignada;
import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String actualizarTecnico(
            @PathVariable Long id,
            @RequestParam Long cedulaTecnico,
            RedirectAttributes redirectAttributes) {

        try {
            ActualizarTecnico actualizarTecnico =
                    new ActualizarTecnico(EstadoTecnico.OCUPADO, id);
            tecnicoService.actualizarTecnico(
                    cedulaTecnico, actualizarTecnico);
            redirectAttributes.addFlashAttribute(
                    "mensajeExito",
                    "La orden fue asignada correctamente al técnico."+ cedulaTecnico
            );

        } catch (TecnicoNoEncontradoException e) {
            redirectAttributes.addFlashAttribute(
                    "mensajeError",
                    "El técnico ingresado no existe. Verifica la cédula."
            );

        }catch (TecnicoOrdenAsignada e) {
            redirectAttributes.addFlashAttribute(
                    "mensajeError",
                    "El técnico ya posee una orden asignada."
            );
        }
        catch (OrdenConTecnicoAsignadoException e){
            redirectAttributes.addFlashAttribute(
                    "mensajeError",
                    "La orden ya cuenta con tecnico asignado"
            );

        }
           catch (Exception e) {
                redirectAttributes.addFlashAttribute(
                        "mensajeError",
                        "No fue posible asignar la orden. Intenta nuevamente."
                );
            }
        return "redirect:/tomar/" + id;
    }

    @GetMapping("/validaciontecnico")
    public String validar(){
        return "home/validarTecnico";
    }

    @PostMapping("/verificar")
    public String verificarTecnico(@RequestParam Long cedulaTecnico,
                                   RedirectAttributes redirectAttributes ){
        try {
            tecnicoService.getTecnicoByid(cedulaTecnico);
            return "redirect:/reparacion/"+cedulaTecnico;
        }catch (RuntimeException e){
            if (e.getMessage() != null &&
                    e.getMessage().contains("no encontrado")) {
                redirectAttributes.addFlashAttribute(
                        "mensajeError",
                        "El técnico ingresado no existe. Verifica la cédula."
                );
        }else {
                redirectAttributes.addFlashAttribute(
                        "mensajeError",
                        "No fue posible asignar la orden. Intenta nuevamente."
                );
            }
        }
        return "redirect:/validaciontecnico";
    }
}
