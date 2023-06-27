package capstone.pillprompt.dto.response;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.Pill;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Schema(description = "약 정보 응답 DTO")
public class PillResponse {

    @Schema(description = "식별값", example = "1")
    private Long id;

    @Schema(description = "이름", example = "감기약")
    private String name;

    @Schema(description = "수량", example = "1")
    private int quantity;

    @Schema(description = "복용 시간")
    private List<NameOfTime> times = new ArrayList<>();

    @Schema(description = "복용 여부", defaultValue = "false")
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