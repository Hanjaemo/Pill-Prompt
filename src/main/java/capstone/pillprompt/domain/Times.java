package capstone.pillprompt.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Times {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "times_id")
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private NameOfTimes name;

    @Temporal(TemporalType.TIME)
    private LocalTime time;

    @Builder
    public Times(NameOfTimes name, LocalTime time) {
        this.name = name;
        this.time = time;
    }
}
