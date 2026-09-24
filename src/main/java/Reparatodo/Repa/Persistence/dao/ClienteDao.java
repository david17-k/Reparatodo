package Reparatodo.Repa.Persistence.dao;


import Reparatodo.Repa.Bussines.dto.ClienteDTO;
import Reparatodo.Repa.Bussines.dto.CrearClienteDTO;
import Reparatodo.Repa.Persistence.Repository.ClienteRepository;
import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import Reparatodo.Repa.Persistence.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteDao {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    public ClienteDTO save(CrearClienteDTO crearClienteDTO){
        ClienteEntity entity=clienteMapper.toEntity(crearClienteDTO);
        ClienteEntity guardarEntity=clienteRepository.save(entity);
        return clienteMapper.toDTO(guardarEntity);
    }


    public boolean existsByCedula(String cedula){
        return clienteRepository.existsByCedula(cedula);
    }

    public Optional<ClienteDTO>findById(Long id){
        return clienteRepository.findById(id).map(clienteMapper::toDTO);
    }


}
