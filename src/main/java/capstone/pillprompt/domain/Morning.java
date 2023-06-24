package capstone.pillprompt.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

import java.time.LocalTime;

@Entity
public class Morning extends TakingTime {

    public Morning() {
        super();
    }

    public Morning(String name, LocalTime time) {
        super(name, time);
    }
}
