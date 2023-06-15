package capstone.pillprompt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class Times {

    @Id @GeneratedValue
    private Long id;
    private Meal meal;
    private LocalTime time;
}
