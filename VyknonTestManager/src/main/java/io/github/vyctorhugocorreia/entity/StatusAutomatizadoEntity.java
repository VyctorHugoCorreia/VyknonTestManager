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
@Table(name = "Tautomatizado")
public class StatusAutomatizadoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_automatizado")
    private Long idAutomatizado;

    @Column(name = "desc_automatizado")
    private String descAutomatizado;



}
