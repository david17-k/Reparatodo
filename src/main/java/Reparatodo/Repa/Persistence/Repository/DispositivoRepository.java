package Reparatodo.Repa.Persistence.Repository;

import Reparatodo.Repa.Bussines.dto.DispositivoDTO;
import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import Reparatodo.Repa.Persistence.entity.DispositivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DispositivoRepository extends JpaRepository<DispositivoEntity,Long> {

    Optional<DispositivoEntity> findById(Long id);
}
