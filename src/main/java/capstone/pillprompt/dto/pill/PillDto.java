package capstone.pillprompt.dto.pill;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.dto.pill.request.PillRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PillDto {

    private String name;
    private int morningQuantity;
    private int lunchQuantity;
    private int dinnerQuantity;
    private List<NameOfTime> times;

    public static PillDto of(PillRequest request) {
        return PillDto.builder()
                .name(request.getName())
                .morningQuantity(request.getMorningQuantity())
                .lunchQuantity(request.getLunchQuantity())
                .dinnerQuantity(request.getDinnerQuantity())
                .times(request.getTimes())
                .build();
    }
}
