package capstone.pillprompt.dto;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.dto.request.PillRequest;
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
    private List<NameOfTime> times = new ArrayList<>();

    public static PillDto of(PillRequest request) {
        return PillDto.builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .times(request.getTimes())
                .build();
    }
}
