package Reparatodo.Repa.Persistence.dao;

import Reparatodo.Repa.Bussines.dto.CrearOrdenDto;
import Reparatodo.Repa.Bussines.dto.OrdenDTO;
import Reparatodo.Repa.Persistence.Repository.OrdenRepository;
import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import Reparatodo.Repa.Persistence.mapper.OrdenMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrdenDao {

    private final OrdenMapper ordenMapper;
    private final OrdenRepository ordenRepository;

    public OrdenDTO save(CrearOrdenDto crearOrdenDto){
        OrdenEntity entity=ordenMapper.toEntity(crearOrdenDto);

        OrdenEntity guardarEntity=ordenRepository.save(entity);
        return ordenMapper.toDTO(guardarEntity);
    }

    public List<OrdenDTO>getAllOrdenes(){
        List<OrdenEntity>ordenEntities=ordenRepository.findAll();
        return ordenMapper.toDTOList(ordenEntities);
    }

    public Optional<OrdenDTO>findById(Long id){
        return ordenRepository.findById(id).map(ordenMapper::toDTO);
    }


}
