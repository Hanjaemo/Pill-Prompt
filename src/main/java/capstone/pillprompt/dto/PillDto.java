package capstone.pillprompt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PillDto {

    private String name;
    private int quantity;
    private boolean isTaken;
}
