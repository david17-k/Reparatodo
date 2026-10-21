package Reparatodo.Repa.Bussines.Service.impl;

import Reparatodo.Repa.Bussines.Service.OrdenService;
import Reparatodo.Repa.Bussines.Service.TecnicoService;
import Reparatodo.Repa.Bussines.dto.ActualizarTecnico;
import Reparatodo.Repa.Bussines.dto.TecnicoDTO;
import Reparatodo.Repa.Exception.OrdenConTecnicoAsignadoException;
import Reparatodo.Repa.Exception.TecnicoNoEncontradoException;
import Reparatodo.Repa.Exception.TecnicoOrdenAsignada;
import Reparatodo.Repa.Persistence.dao.TecnicoDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoDAO tecnicoDAO;
    private final OrdenService ordenService;



    @Override
    public TecnicoDTO getTecnicoByid(Long id) {
        return tecnicoDAO.findById(id).orElseThrow(()->{
            return new TecnicoNoEncontradoException(id);
        });
    }

    @Override
    public TecnicoDTO actualizarTecnico(Long id, ActualizarTecnico actualizarTecnico) {
        if (!tecnicoDAO.findById(id).isPresent()) {
            throw new TecnicoNoEncontradoException(id);
        }
      if(tecnicoDAO.existsByOrdenEntity(id)){
         throw new TecnicoOrdenAsignada(id);
      }
      if(ordenService.tecnicoAsignado(actualizarTecnico.getIdOrden())){
        throw new OrdenConTecnicoAsignadoException(id);
      }
        validarDatos(actualizarTecnico);
        TecnicoDTO actualizarTecnicos = tecnicoDAO.actulizarTecnico(id, actualizarTecnico).orElseThrow(() ->
                new RuntimeException("Error al actualizar el tecnico"));
        return actualizarTecnicos;
    }


    private void validarDatos(ActualizarTecnico actualizarTecnico) {
        if(actualizarTecnico.getIdOrden()==null || actualizarTecnico.getIdOrden()<=0){
            throw new RuntimeException("Id no valido");
        }
    }

}
