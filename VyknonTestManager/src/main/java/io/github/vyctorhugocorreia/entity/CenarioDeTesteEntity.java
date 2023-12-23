package io.github.vyctorhugocorreia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private PlanoDeTesteEntity IdPlano;

    @ManyToOne
    @JoinColumn(name = "id_suite")
    private SuiteDeTesteEntity idSuite;

    @ManyToOne
    @JoinColumn(name = "id_tproduto")
    private ProdutoEntity idTproduto;

    @ManyToOne
    @JoinColumn(name = "id_funcionalidade")
    private FuncionalidadeEntity idFuncionalidade;

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


}
