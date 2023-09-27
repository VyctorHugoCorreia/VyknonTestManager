package io.github.vyctorhugocorreia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@Table(name = "Ttime")

public class TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_time")
    private Long idTime;

    @Column(name = "nome_time")
    private String nomeTime;



}
