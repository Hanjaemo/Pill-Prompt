package capstone.pillprompt.service;


import java.util.Optional;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import capstone.pillprompt.domain.Member;
import capstone.pillprompt.dto.FCMRequest;
import capstone.pillprompt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FCMService {

    private final FirebaseMessaging firebaseMessaging;
    private final MemberRepository memberRepository;

    public String sendNotificationByToken(FCMRequest requestDto) {

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
            log.info("token={}, title={}, body={}", requestDto.getToken(), requestDto.getTitle(), requestDto.getBody());
            return "Success sending notification";
        } catch (FirebaseMessagingException e) {
            log.error("error={}", e);
            return "Error sending notification";
        }
    }

    public String saveToken(String token) {
        Optional<Member> member = memberRepository.findById(1L);
        if (member.isPresent()) {
            log.info("member={}", member.get().getFcmToken());
            memberRepository.delete(member.get());
        }
        Member newMember = Member.builder()
                .id(1L)
                .fcmToken(token)
                .build();
        memberRepository.save(newMember);
        log.info("newMember={}", newMember.getFcmToken());
        return newMember.getFcmToken();
    }

    public String findTokenByMemberId(Long id) {
        return memberRepository.findById(id)
                .orElseThrow()
                .getFcmToken();
    }
}