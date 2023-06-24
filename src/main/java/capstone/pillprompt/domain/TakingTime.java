package capstone.pillprompt.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Entity
@Getter
public class TakingTime {

    @Id
    @Enumerated(EnumType.STRING)
    private NameOfTime name;

    @Temporal(TemporalType.TIME)
    private LocalTime time;

    public TakingTime update(LocalTime newTime) {
        this.time = newTime;
        return this;
    }
}
