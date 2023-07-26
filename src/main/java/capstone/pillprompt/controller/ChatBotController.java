package capstone.pillprompt.controller;

import capstone.pillprompt.dto.request.ChatGptRequest;
import capstone.pillprompt.dto.response.ChatGptResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/chat")
public class ChatBotController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @PostMapping
    public String chat(@RequestParam("prompt") String prompt) {
        ChatGptRequest chatGptRequest = new ChatGptRequest("gpt-3.5-turbo", prompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, chatGptRequest, ChatGptResponse.class);
        String result = chatGptResponse.getChoices().get(0).getMessage().getContent();
        log.info("result = {}", result);
        return result;
    }
}
