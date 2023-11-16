package capstone.pillprompt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String fcmToken;

    @Column(nullable = false)
    private boolean isOutModeActivate;

    public String updateToken(String fcmToken) {
        this.fcmToken = fcmToken;
        return this.fcmToken;
    }

    public boolean switchOutMode() {
        isOutModeActivate = !isOutModeActivate;
        return isOutModeActivate;
    }
}
