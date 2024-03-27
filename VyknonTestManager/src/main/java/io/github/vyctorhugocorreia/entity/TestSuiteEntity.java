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
@Table(name = "testsuite")

public class TestSuiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_testsuite")
    private Long idTestSuite ;

    @Column(name = "desc_testsuite")
    private String descTestSuite;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private TeamEntity idTeam;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity idProduct;

    @ManyToOne
    @JoinColumn(name = "id_testPlan")
    private TestPlanEntity idTestPlan;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private UserEntity user;

    @Transient
    private int scenarioQuantity;

}
