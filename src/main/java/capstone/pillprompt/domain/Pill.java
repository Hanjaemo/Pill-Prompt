package capstone.pillprompt.domain;

import jakarta.persistence.*;
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
}
