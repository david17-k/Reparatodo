package Reparatodo.Repa.Bussines.Service.impl;

import Reparatodo.Repa.Bussines.Service.TecnicoService;
import Reparatodo.Repa.Bussines.dto.ActualizarTecnico;
import Reparatodo.Repa.Bussines.dto.TecnicoDTO;
import Reparatodo.Repa.Persistence.dao.TecnicoDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
@Transactional
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoDAO tecnicoDAO;



    @Override
    public TecnicoDTO getTecnicoByid(Long id) {
        return tecnicoDAO.findById(id).orElseThrow(()->{
            return new RuntimeException("Tecnico no encontrado");
        });
    }

    @Override
    public TecnicoDTO actualizarTecnico(Long id, ActualizarTecnico actualizarTecnico) {
        if (!tecnicoDAO.findById(id).isPresent()) {
            throw new RuntimeException("Tecnico no registrado");
        }
        TecnicoDTO actualizarTecnicos = tecnicoDAO.actulizarTecnico(id, actualizarTecnico).orElseThrow(() ->
                new RuntimeException("Error al actualizar el tecnico"));
        return actualizarTecnicos;
    }

}
