package io.github.vyctorhugocorreia.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepDTO {

    private int step;

    private String description;

    private String status;

}
