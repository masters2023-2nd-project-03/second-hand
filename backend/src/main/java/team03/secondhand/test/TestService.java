package team03.secondhand.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.secondhand.test.domain.TestDomain;
import team03.secondhand.test.dto.request.TestRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final TestRepository testRepository;

    public List<TestDomain> getAllTests() {
        return testRepository.findAll();
    }

    public TestDomain getById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    public TestDomain joinTest(TestRequest testRequest) {
        TestDomain testDomain = new TestDomain(null, testRequest.getName());
        return testRepository.save(testDomain);
    }

    public TestDomain updateTest(Long id, TestRequest testRequest) {
        TestDomain testDomain = getById(id);
         testDomain.setName(testRequest.getName());
        return testDomain;
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }

}
