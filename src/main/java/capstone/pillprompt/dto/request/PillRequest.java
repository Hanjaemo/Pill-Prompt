package capstone.pillprompt.dto.request;

import capstone.pillprompt.domain.NameOfTime;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PillRequest {

    private String name;
    private int quantity;
    private List<NameOfTime> times = new ArrayList<>();
}
