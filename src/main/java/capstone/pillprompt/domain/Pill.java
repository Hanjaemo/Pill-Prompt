package capstone.pillprompt.domain;

import capstone.pillprompt.dto.PillDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pill_id")
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @ElementCollection
    @CollectionTable(name = "name_of_time",
            joinColumns = @JoinColumn(name = "pill_id"))
    @Column(name = "time_name")
    @Enumerated(EnumType.STRING)
    private List<NameOfTime> times = new ArrayList<>();

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean taken_morning;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean taken_lunch;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean taken_dinner;

    @Builder
    public Pill(String name, int quantity, List<NameOfTime> times) {
        this.name = name;
        this.quantity = quantity;
        this.times = times;
    }

    public Pill update(PillDto newPill) {
        this.name = newPill.getName();
        this.quantity = newPill.getQuantity();
        this.times = newPill.getTimes();
        return this;
    }

    public Pill disposed() {
        if (this.quantity == 0) {
            return this;
        }
        this.quantity--;
        return this;
    }

    public Pill taken(NameOfTime time) {
        switch (time) {
            case MORNING:
                this.taken_morning = true;
                break;
            case LUNCH:
                this.taken_lunch = true;
                break;
            case DINNER:
                this.taken_dinner = true;
                break;
        }
        return this;
    }

    public static Pill of(PillDto pillDto) {
        return Pill.builder()
                .name(pillDto.getName())
                .quantity(pillDto.getQuantity())
                .times(pillDto.getTimes())
                .build();
    }
}
