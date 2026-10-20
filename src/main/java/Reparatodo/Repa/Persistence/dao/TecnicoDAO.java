package Reparatodo.Repa.Persistence.dao;


import Reparatodo.Repa.Bussines.dto.ActualizarTecnico;
import Reparatodo.Repa.Bussines.dto.TecnicoDTO;
import Reparatodo.Repa.Persistence.Repository.TecnicoRepository;
import Reparatodo.Repa.Persistence.entity.TecnicoEntity;
import Reparatodo.Repa.Persistence.mapper.TecnicoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TecnicoDAO {

    private final TecnicoRepository tecnicoRepository;
    private final TecnicoMapper tecnicoMapper;


    public Optional<TecnicoDTO>actulizarTecnico(Long id,ActualizarTecnico actualizarTecnico){
        return tecnicoRepository.findById(id).
                map(existingEntity->{
                    tecnicoMapper.actulizarEntityFromDTO(actualizarTecnico,existingEntity);
                    TecnicoEntity actualizarEntity=tecnicoRepository.save(existingEntity);
                    return tecnicoMapper.toDTO(actualizarEntity);
                });
    }

    public Optional<TecnicoDTO>findById(Long id){
        return tecnicoRepository.findById(id).map(tecnicoMapper::toDTO);
    }


   public Optional<TecnicoDTO>findByOrdenEntity_Id(Long id){
        return tecnicoRepository.findByOrdenEntity_Id(id).map(tecnicoMapper::toDTO);
   }

   public boolean existsByOrdenEntity(Long id){
        return tecnicoRepository.existsByCedulaTecnicoAndOrdenEntityIsNotNull(id);
   }




}
