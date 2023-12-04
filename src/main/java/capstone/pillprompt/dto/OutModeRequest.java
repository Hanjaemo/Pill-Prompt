package capstone.pillprompt.dto;

import java.util.List;

import capstone.pillprompt.domain.NameOfTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OutModeRequest {

    private List<NameOfTime> times;
    private List<Integer> quantities;
}
