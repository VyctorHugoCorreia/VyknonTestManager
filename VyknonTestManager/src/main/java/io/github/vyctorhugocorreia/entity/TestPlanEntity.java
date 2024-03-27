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
@Table(name = "testplan")

public class TestPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_testplan")
    private Long idTestPlan;

    @Column(name = "desc_testplan")
    private String descTestPlan;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private TeamEntity idTeam;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity idProduct;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private UserEntity user;

    @Transient
    private int scenarioQuantity;

    @Transient
    private int testSuiteQuantity;

}
