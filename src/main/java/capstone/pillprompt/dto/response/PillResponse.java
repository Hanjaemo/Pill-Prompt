package capstone.pillprompt.dto.response;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.Pill;
import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class PillResponse {

    private Long id;
    private String name;
    private int quantity;
    private List<NameOfTime> times = new ArrayList<>();
    private boolean isTaken;

    public static PillResponse of(Pill pill) {
        return PillResponse.builder()
                .id(pill.getId())
                .name(pill.getName())
                .quantity(pill.getQuantity())
                .times(pill.getTimes())
                .isTaken(pill.isTaken())
                .build();
    }
}