package team03.secondhand.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team03.secondhand.test.domain.TestDomain;

@Repository
public interface TestRepository extends JpaRepository<TestDomain, Long> {

}
