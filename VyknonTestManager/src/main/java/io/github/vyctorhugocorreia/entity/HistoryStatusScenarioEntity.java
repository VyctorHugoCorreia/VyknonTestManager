package io.github.vyctorhugocorreia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "thistorystatusscenario")
public class HistoryStatusScenarioEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history")
    private Long idHistory;

    @ManyToOne
    @JoinColumn(name = "id_cenario")
    private CenarioDeTesteEntity idCenario;

    @ManyToOne
    @JoinColumn(name = "status_before")
    private StatusCenarioEntity statusBefore;

    @ManyToOne
    @JoinColumn(name = "status_after")
    private StatusCenarioEntity statusAfter;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

}
