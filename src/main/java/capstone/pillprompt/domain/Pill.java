package capstone.pillprompt.domain;

import capstone.pillprompt.dto.PillDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pill_id")
    private List<Times> times = new ArrayList<>();


    @Column(nullable = false)
    private boolean isTaken;

    @Builder
    public Pill(String name, int quantity, List<Times> times, boolean isTaken) {
        this.name = name;
        this.quantity = quantity;
        this.times = times;
        this.isTaken = isTaken;
    }

    public Pill update(PillDto newPill) {
        this.name = newPill.getName();
        this.quantity = newPill.getQuantity();
        this.times = newPill.getTimes();
        return this;
    }

    public static Pill of(PillDto pillDto) {
        return Pill.builder()
                .name(pillDto.getName())
                .quantity(pillDto.getQuantity())
                .times(pillDto.getTimes())
                .isTaken(pillDto.isTaken())
                .build();
    }
}
