package Reparatodo.Repa.Persistence.dao;


import Reparatodo.Repa.Bussines.dto.CrearDispositivoDTO;
import Reparatodo.Repa.Bussines.dto.DispositivoDTO;
import Reparatodo.Repa.Persistence.Repository.DispositivoRepository;
import Reparatodo.Repa.Persistence.entity.DispositivoEntity;
import Reparatodo.Repa.Persistence.mapper.DispositivoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DispositivoDao {

    private final DispositivoRepository dispositivoRepository;
    private final DispositivoMapper dispositivoMapper;

    public DispositivoDTO save(CrearDispositivoDTO crearDispositivoDTO){
        DispositivoEntity entity=dispositivoMapper.toEntity(crearDispositivoDTO);
        DispositivoEntity guardar=dispositivoRepository.save(entity);
        return dispositivoMapper.toDTO(guardar);
    }


    public Optional<DispositivoDTO>findById(Long id){
        return dispositivoRepository.findById(id).map(dispositivoMapper::toDTO);
    }

    public List<DispositivoDTO>lista(){
        List<DispositivoEntity>dispositivoEntities=dispositivoRepository.findAll();
        return dispositivoMapper.toDTOlist(dispositivoEntities);
    }




}
