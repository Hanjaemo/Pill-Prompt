package capstone.pillprompt.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalTime;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class TakingTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taking_time_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Temporal(TemporalType.TIME)
    private LocalTime time;

    public TakingTime() {
    }

    public TakingTime(String name, LocalTime time) {
        this.name = name;
        this.time = time;
    }
}
