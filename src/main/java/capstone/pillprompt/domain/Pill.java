package capstone.pillprompt.domain;

import capstone.pillprompt.dto.pill.PillDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(value = 0, message = "수량은 최소 0개 이상이어야 합니다.")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Pill(String name, int quantity, List<NameOfTime> times) {
        this.name = name;
        this.quantity = quantity;
        this.times = times;
    }

    public Pill update(PillDto newPill) {
        this.name = newPill.getName();
        this.quantity = newPill.getQuantity();
        this.times.clear();
        this.times.addAll(newPill.getTimes());
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

    public Pill takeCancel(NameOfTime time) {
        switch (time) {
            case MORNING:
                this.taken_morning = false;
                break;
            case LUNCH:
                this.taken_lunch = false;
                break;
            case DINNER:
                this.taken_dinner = false;
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
