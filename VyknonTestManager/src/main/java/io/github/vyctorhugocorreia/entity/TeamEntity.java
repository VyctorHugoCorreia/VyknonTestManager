package io.github.vyctorhugocorreia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team")
public class TeamEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_team")
    private Long idTeam;

    @Column(name = "name_team")
    @NotBlank(message = "Preencha um nome válido")
    private String nameTeam;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private UserEntity user;

    @Transient
    private int scenarioQuantity;

}
