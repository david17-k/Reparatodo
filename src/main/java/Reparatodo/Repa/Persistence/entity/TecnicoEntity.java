package Reparatodo.Repa.Persistence.entity;


import Reparatodo.Repa.Bussines.Estado.EstadoTecnico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@Table(name = "Tecnicos")
public class TecnicoEntity {

    @Id
    Long cedulaTecnico;

    @Enumerated(EnumType.STRING)
    @Column(name = "estadoTecnico",nullable = false)
    private EstadoTecnico estadoTecnico;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "telefono")
    private String telefono;

    @OneToOne
    @JoinColumn(name = "ordenId")
    private OrdenEntity orden;

}
