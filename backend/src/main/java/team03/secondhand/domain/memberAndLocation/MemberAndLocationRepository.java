package team03.secondhand.domain.memberAndLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAndLocationRepository extends JpaRepository<MemberAndLocation, Long> {
}
