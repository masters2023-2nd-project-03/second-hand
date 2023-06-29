package team03.secondhand.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByOauthId(String oauthId);

    Optional<Member> findByMemberId(Long memberId);

    Boolean existsByOauthId(String oauthId);

}
