package capstone.pillprompt.dto.request;

import capstone.pillprompt.domain.NameOfTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Schema(description = "약 정보 요청 DTO")
public class PillRequest {

    @Schema(description = "이름", example = "감기약")
    private String name;

    @Schema(description = "수량", example = "1")
    private int quantity;

    @Schema(description = "복용 시간")
    private List<NameOfTime> times = new ArrayList<>();
}
