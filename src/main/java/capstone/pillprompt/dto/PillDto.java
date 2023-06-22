package capstone.pillprompt.dto;

import capstone.pillprompt.domain.Times;
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
    private List<Times> times = new ArrayList<>();
    private boolean isTaken;
}
