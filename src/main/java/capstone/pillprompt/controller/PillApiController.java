package capstone.pillprompt.controller;

import capstone.pillprompt.service.PillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PillApiController {

    private final PillService pillService;

    
}
