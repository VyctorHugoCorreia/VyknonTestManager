package io.github.vyctorhugocorreia.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TeamEntity idTeam;

    @ManyToOne
    @JoinColumn(name = "id_product")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductEntity idProduct;

    @ManyToOne
    @JoinColumn(name = "id_testplan")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TestPlanEntity idTestPlan;

    @JoinColumn(name = "id_user")
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserEntity user;

    @Transient
    private int scenarioQuantity;

    public String getNameTeam() {
        if (idTeam != null) {
            return idTeam.getNameTeam();
        } else {
            return null;
        }
    }

    public String getDescProduct() {
        if (idProduct != null) {
            return idProduct.getDescProduct();
        } else {
            return null;
        }
    }

    public String getDescTesPlan() {
        if (idTestPlan != null) {
            return idTestPlan.getDescTestPlan();
        } else {
            return null;
        }
    }

}
