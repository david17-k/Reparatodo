package Reparatodo.Repa.Persistence.Repository;


import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import Reparatodo.Repa.Persistence.entity.TecnicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface TecnicoRepository extends JpaRepository<TecnicoEntity,Long> {


    Optional<TecnicoEntity>findById(Long id);



}
