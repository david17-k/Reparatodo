package Reparatodo.Repa.Persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dispositivo")
public class DispositivoEntity {

    @Id
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "dispositivo")
    private String dispositivo;

    @Column(name = "dano")
    private String dano;

    @Column(name = "observaciones")
    private String observaciones;


}
