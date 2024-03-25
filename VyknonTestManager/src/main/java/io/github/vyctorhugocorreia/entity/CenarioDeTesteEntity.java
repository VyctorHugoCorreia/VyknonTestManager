package io.github.vyctorhugocorreia.entity;

import io.github.vyctorhugocorreia.ListConverter;
import io.github.vyctorhugocorreia.dto.StepDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tcenario")


public class CenarioDeTesteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cenario")
    private Long idCenario;

    @Column(name = "titulo_cenario")
    private String tituloCenario;

    @Column(name = "desc_cenario")
    private String descCenario;

    @Column(name = "link_cenario")
    private String linkCenario;

    @ManyToOne
    @JoinColumn(name = "id_Time")
    private TimeEntity idTime;

    @ManyToOne
    @JoinColumn(name = "id_plano")
    private PlanoDeTesteEntity idPlano;

    @ManyToOne
    @JoinColumn(name = "id_suite")
    private SuiteDeTesteEntity idSuite;

    @ManyToOne
    @JoinColumn(name = "id_tproduto")
    private ProdutoEntity idTproduto;


    @ManyToOne
    @JoinColumn(name = "id_tpcenario")
    private TipoCenarioEntity idTpcenario;

    @ManyToOne
    @JoinColumn(name = "id_plataforma")
    private TipoPlataformaEntity idPlataforma;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private StatusCenarioEntity idStatus;

    @ManyToOne
    @JoinColumn(name = "id_automatizado")
    private StatusAutomatizadoEntity idAutomatizado;


    @Column(name = "steps")
    @Convert(converter = ListConverter.class)
    private List<StepDTO> steps;

    @Column(name = "tags")
    @Convert(converter = ListConverter.class)
    private List<String> tags;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @JoinColumn(name = "usuario")
    @ManyToOne
    private UsuarioEntity usuario;
}
