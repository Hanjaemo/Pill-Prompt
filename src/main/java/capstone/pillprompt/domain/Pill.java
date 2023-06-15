package capstone.pillprompt.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;
    private List<Times> times;
    private boolean isTaken;
}
