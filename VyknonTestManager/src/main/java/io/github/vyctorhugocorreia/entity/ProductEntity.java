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
@Table(name = "product")

public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idProduct;

    @Column(name = "desc_product")
    private String descProduct;

    @ManyToOne
    @JoinColumn(name = "id_team")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TeamEntity idTeam;

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

}
