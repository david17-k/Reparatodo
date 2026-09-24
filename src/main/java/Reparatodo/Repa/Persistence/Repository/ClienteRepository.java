package Reparatodo.Repa.Persistence.Repository;


import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {

    Optional<ClienteEntity>findById(Long id);
    Optional<ClienteEntity>findByCedula(String email);

    boolean existsById(Long id);

    boolean existsByCedula(String cedula);
}
