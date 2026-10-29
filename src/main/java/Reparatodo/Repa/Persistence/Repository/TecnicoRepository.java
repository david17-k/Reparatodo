package Reparatodo.Repa.Persistence.Repository;


import Reparatodo.Repa.Persistence.entity.ClienteEntity;
import Reparatodo.Repa.Persistence.entity.TecnicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface TecnicoRepository extends JpaRepository<TecnicoEntity,Long> {


    Optional<TecnicoEntity>findById(Long id);

    boolean existsByCedulaTecnicoAndOrdenEntityIsNotNull(Long cedulaTecnico);

    Optional<TecnicoEntity>findByOrdenEntity_Id(Long id);



    @Query("SELECT t.ordenEntity.id FROM TecnicoEntity t WHERE t.cedulaTecnico=:cedula")
    Optional<Long>buscarOrden(@Param("cedula")Long cedula);







}
