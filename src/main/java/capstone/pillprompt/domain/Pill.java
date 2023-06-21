package capstone.pillprompt.domain;

import capstone.pillprompt.dto.PillDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Pill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pill_id")
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "pill", cascade = CascadeType.ALL)
    private List<Taking> takings = new ArrayList<>();

    @Column(nullable = false)
    private boolean isTaken;

    @Builder
    public Pill(String name, int quantity, List<Taking> takings, boolean isTaken) {
        this.name = name;
        this.quantity = quantity;
        this.takings = takings;
        this.isTaken = isTaken;
    }

    public Pill update(Pill newPill) {
        this.name = newPill.getName();
        this.quantity = newPill.getQuantity();
        this.takings = newPill.getTakings();
        return this;
    }

    public static Pill of(PillDto pillDto) {
        return Pill.builder()
                .name(pillDto.getName())
                .quantity(pillDto.getQuantity())
                .isTaken(pillDto.isTaken())
                .build();
    }
}
