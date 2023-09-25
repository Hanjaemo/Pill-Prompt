package capstone.pillprompt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import capstone.pillprompt.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByFcmToken(String fcmToken);
}
