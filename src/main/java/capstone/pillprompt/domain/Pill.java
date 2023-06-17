package capstone.pillprompt.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Times> times;

    @Column(nullable = false)
    private boolean isTaken;
}
