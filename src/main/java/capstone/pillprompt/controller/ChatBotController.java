package capstone.pillprompt.controller;

import capstone.pillprompt.dto.chatbot.ChatGptRequest;
import capstone.pillprompt.dto.chatbot.ChatGptResponse;
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

    @GetMapping
    public String chat(@RequestParam("prompt") String prompt) {
        // create a request
        ChatGptRequest chatGptRequest = new ChatGptRequest(model, prompt);

        // call the api
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, chatGptRequest, ChatGptResponse.class);

        if (chatGptResponse == null || chatGptResponse.getChoices() == null || chatGptResponse.getChoices().isEmpty()) {
            return "No response";
        }

        // return the first response
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}
