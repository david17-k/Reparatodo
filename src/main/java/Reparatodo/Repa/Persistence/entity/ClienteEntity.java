package Reparatodo.Repa.Persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class ClienteEntity {


    @Id
    Long cedula;

    @Column(name = "nombreC")
    private String nombre;
    @Column(name = "apellidoC")
    private String apellido;
    @Column(name = "celularC")
    private String celular;

    @OneToMany(mappedBy = "clienteEntity",fetch = FetchType.LAZY)
    private List<OrdenEntity> orden;


}
