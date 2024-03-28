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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TeamEntity idTeam;

    @ManyToOne
    @JoinColumn(name = "id_product")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductEntity idProduct;

    @JoinColumn(name = "id_user")
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserEntity user;

    @Transient
    private int scenarioQuantity;

    @Transient
    private int testSuiteQuantity;

    public String getNameTeam() {
        if (idTeam != null) {
            return idTeam.getNameTeam();
        } else {
            return null;
        }
    }

    public String getNameProduct() {
        if (idProduct != null) {
            return idProduct.getDescProduct();
        } else {
            return null;
        }
    }

}
