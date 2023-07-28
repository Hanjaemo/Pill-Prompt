package capstone.pillprompt.dto.pill.request;

import capstone.pillprompt.domain.NameOfTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Schema(description = "약 정보 요청 DTO")
public class PillRequest {

    @Schema(description = "이름", example = "감기약")
    @NotEmpty(message = "약 이름을 입력해주세요.")
    @Size(min = 1, max = 20, message = "약 이름의 글자수는 1 ~ 20 이어야 합니다.")
    private String name;

    @Schema(description = "수량", example = "1")
    @Min(value = 0, message = "수량은 최소 0개 이상이어야 합니다.")
    private int quantity;

    @Schema(description = "복용 시간")
    private List<NameOfTime> times = new ArrayList<>();
}
