package capstone.pillprompt.service;

import capstone.pillprompt.dto.FCMNotificationRequestDto;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FCMNotificationService {

    private final FirebaseMessaging firebaseMessaging;

    public String sendNotificationByToken(FCMNotificationRequestDto requestDto) {

        Notification notification = Notification.builder()
                .setTitle(requestDto.getTitle())
                .setBody(requestDto.getBody())
                .build();

        Message message = Message.builder()
                .setToken(requestDto.getToken())
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
            return "Success sending notification";
        } catch (FirebaseMessagingException e) {
            return "Error sending notification";
        }
    }
}
