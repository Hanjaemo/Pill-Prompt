package capstone.pillprompt.dto.pill.response;

import capstone.pillprompt.domain.NameOfTime;
import capstone.pillprompt.domain.Pill;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "약 정보 응답 DTO")
public class PillResponse {

    @Schema(description = "식별값", example = "1")
    private Long id;

    @Schema(description = "이름", example = "감기약")
    private String name;

    @Schema(description = "아침 수량", example = "1")
    private int morningQuantity;

    @Schema(description = "점심 수량", example = "1")
    private int lunchQuantity;

    @Schema(description = "저녁 수량", example = "1")
    private int dinnerQuantity;

    @Schema(description = "복용 시간")
    private List<NameOfTime> times;

    @Schema(description = "복용 여부(아침)", example = "true")
    private boolean taken_morning;

    @Schema(description = "복용 여부(아침)", example = "false")
    private boolean taken_lunch;

    @Schema(description = "복용 여부(아침)", example = "false")
    private boolean taken_dinner;

    public static PillResponse of(Pill pill) {
        return PillResponse.builder()
                .id(pill.getId())
                .name(pill.getName())
                .morningQuantity(pill.getMorningQuantity())
                .lunchQuantity(pill.getLunchQuantity())
                .dinnerQuantity(pill.getDinnerQuantity())
                .times(pill.getTimes())
                .taken_morning(pill.isTaken_morning())
                .taken_lunch(pill.isTaken_lunch())
                .taken_dinner(pill.isTaken_dinner())
                .build();
    }
}