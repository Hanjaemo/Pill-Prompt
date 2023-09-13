package capstone.pillprompt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {

    private String token;
    private String title;
    private String body;

    @Builder
    public FCMNotificationRequestDto(String token, String title, String body) {
        this.token = token;
        this.title = title;
        this.body = body;
    }
}
