package capstone.pillprompt.dto.request;

import capstone.pillprompt.dto.Message;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGptRequest {

    private String model;
    private List<Message> messages = new ArrayList<>();

    public ChatGptRequest(String model, String prompt) {
        this.model = model;
        this.messages.add(new Message("user", prompt));
    }
}
