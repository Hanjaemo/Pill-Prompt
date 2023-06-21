package capstone.pillprompt.dto;

import capstone.pillprompt.domain.Taking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PillDto {

    private String name;
    private int quantity;
    private List<Taking> takings = new ArrayList<>();
    private boolean isTaken;
}
