package io.github.vyctorhugocorreia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryStatusScenarioDTO {
    private Long idHistory;
    private Long idCenario;
    private Long statusBefore;
    private Long statusAfter;
    private LocalDateTime dateUpdate;
}
