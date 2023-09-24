package capstone.pillprompt.controller;

import capstone.pillprompt.dto.FCMRequest;
import capstone.pillprompt.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class FCMController {

    private final FCMService fcmService;

    @PostMapping
    public String sendNotificationByToken(@RequestBody FCMRequest requestDto) {
        return fcmService.sendNotificationByToken(requestDto);
    }
}