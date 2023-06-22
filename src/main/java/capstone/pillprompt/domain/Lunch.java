package capstone.pillprompt.domain;

import jakarta.persistence.Entity;

import java.time.LocalTime;

@Entity
public class Lunch extends TakingTime {

    public Lunch() {
        super();
    }

    public Lunch(String name, LocalTime time) {
        super(name, time);
    }
}
