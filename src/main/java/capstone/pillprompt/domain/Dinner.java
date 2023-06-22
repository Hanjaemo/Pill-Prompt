package capstone.pillprompt.domain;

import jakarta.persistence.Entity;

import java.time.LocalTime;

@Entity
public class Dinner extends TakingTime {

    public Dinner() {
        super();
    }

    public Dinner(String name, LocalTime time) {
        super(name, time);
    }
}
