package capstone.pillprompt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class FCMMessage {

    private boolean validate_only;
    private Message message;

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Message {
        private Notification notification;
        private String token;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }
}