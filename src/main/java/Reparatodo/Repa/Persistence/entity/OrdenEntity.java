package Reparatodo.Repa.Persistence.entity;




import Reparatodo.Repa.Bussines.Estado.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orden")
public class OrdenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO",nullable = false)
    private Estado estado;

    @Column(name = "fechaorden")
    private LocalDate fecha;

    @Column(name = "observaciones")
    private String observaciones;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico")
    private TecnicoEntity tecnicoEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clientecedula")
    private ClienteEntity clienteEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddispositivo")
    private DispositivoEntity dispositivoEntity;


}
