package team03.secondhand.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.test.domain.TestDomain;
import team03.secondhand.test.dto.request.TestRequest;
import team03.secondhand.test.dto.response.TestResponse;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public ResponseEntity<List<TestDomain>> getTest() {
        return ResponseEntity.ok(testService.getAllTests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDomain> getTestById(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getById(id));
    }

    @PostMapping
    @ResponseBody
    public String joinTest(@RequestBody TestRequest testRequest) {
        testService.joinTest(testRequest);
        return "join ok";
    }

    @PatchMapping("/{id}")
    public String updateById(@PathVariable Long id, @RequestBody TestRequest testRequest) {
        testService.updateTest(id, testRequest);
        return "update ok";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        testService.deleteTest(id);
        return "delete ok";
    }




}
