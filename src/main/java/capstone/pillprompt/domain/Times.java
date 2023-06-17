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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "times_id")
    private Long id;

    @Column(nullable = false)
    private NameOfTimes nameOfTimes;

    @Temporal(TemporalType.TIME)
    private LocalTime time;

    @Builder
    public Times(NameOfTimes nameOfTimes, LocalTime time) {
        this.nameOfTimes = nameOfTimes;
        this.time = time;
    }
}
