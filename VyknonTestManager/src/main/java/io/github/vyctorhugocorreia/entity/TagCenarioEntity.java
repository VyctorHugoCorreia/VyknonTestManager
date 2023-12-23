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
@Table(name = "Ttagcenario")
public class TagCenarioEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    private Long idTag;

    @ManyToOne
    @JoinColumn(name = "id_cenario")
    private CenarioDeTesteEntity idCenario;

    @Column(name = "desc_tag")
    private String descTag;



}
