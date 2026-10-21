package Reparatodo.Repa.Persistence.Repository;

import Reparatodo.Repa.Persistence.entity.OrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrdenRepository extends JpaRepository<OrdenEntity,Long> {


    Optional<OrdenEntity> findById(Long id);

    boolean existsById(Long id);

    boolean existsByIdAndTecnicoEntityIsNotNull(Long id);

    @Query("SELECT i FROM OrdenEntity i JOIN FETCH i.clienteEntity WHERE i.id= :id")
    Optional<OrdenEntity>findByWithCliente(@Param("id")Long id);





}
