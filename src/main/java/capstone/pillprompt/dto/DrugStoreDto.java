package capstone.pillprompt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DrugStoreDto {

    private String id;
    private String name;
    private String address;
    private String telephone;
    // 영업 시간 추가해야 함
    private Double lon; // 경도
    private Double lat; // 위도

}
