package Reparatodo.Repa.Controller;

import Reparatodo.Repa.Bussines.Service.OrdenService;
import Reparatodo.Repa.Bussines.dto.CrearOrdenDto;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@Slf4j
public class CrearOrdenController {

    private final OrdenService ordenService;

    @GetMapping("/reparatodo")
    public String mostrarOrden() {
        log.info("Cargando html {}","reparatodo");
        return "home/orden";
    }

    @PostMapping("/crearorden")
    public ResponseEntity<OrdenDTO>crearOrden(CrearOrdenDto crearOrdenDto){
        log.debug("POST api/v1/orden - crear orden{}",crearOrdenDto.getFecha().getYear());
        OrdenDTO crearOrden=ordenService.crearOrden(crearOrdenDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(crearOrden);
    }

}

