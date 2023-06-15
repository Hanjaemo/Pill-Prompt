package capstone.pillprompt.domain;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Times {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "times_id")
    private Long id;

    @Column(nullable = false)
    private NameOfTimes nameOfTimes;

    @Temporal(TemporalType.TIME)
    private LocalTime time;
}
