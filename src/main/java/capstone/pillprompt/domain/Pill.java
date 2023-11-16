package capstone.pillprompt.domain;

import capstone.pillprompt.dto.pill.request.PillRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Pill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pill_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(value = 0, message = "수량은 최소 0개 이상이어야 합니다.")
    private int morningQuantity;

    @Column(nullable = false)
    @Min(value = 0, message = "수량은 최소 0개 이상이어야 합니다.")
    private int lunchQuantity;

    @Column(nullable = false)
    @Min(value = 0, message = "수량은 최소 0개 이상이어야 합니다.")
    private int dinnerQuantity;

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

    public Pill update(Pill newPill) {
        this.name = newPill.getName();
        this.morningQuantity = newPill.getMorningQuantity();
        this.lunchQuantity = newPill.getLunchQuantity();
        this.dinnerQuantity = newPill.getDinnerQuantity();
        this.times.clear();
        this.times.addAll(newPill.getTimes());
        return this;
    }

    public void disposed(NameOfTime time, int quantityForDispose) {
        if (time == NameOfTime.MORNING) {
            if (this.morningQuantity == 0) {
                return;
            }
            this.morningQuantity -= quantityForDispose;
            return;
        }
        if (time == NameOfTime.LUNCH) {
            if (this.lunchQuantity == 0) {
                return;
            }
            this.lunchQuantity -= quantityForDispose;
            return;
        }
        if (time == NameOfTime.DINNER) {
            if (this.dinnerQuantity == 0) {
                return;
            }
            this.dinnerQuantity -= quantityForDispose;
        }
    }

    public void taken(NameOfTime time) {
        switch (time) {
            case MORNING -> this.taken_morning = true;
            case LUNCH -> this.taken_lunch = true;
            case DINNER -> this.taken_dinner = true;
        }
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

    public static Pill of(PillRequest pillRequest) {
        return Pill.builder()
                .name(pillRequest.getName())
                .morningQuantity(pillRequest.getMorningQuantity())
                .lunchQuantity(pillRequest.getLunchQuantity())
                .dinnerQuantity(pillRequest.getDinnerQuantity())
                .times(pillRequest.getTimes())
                .build();
    }
}
