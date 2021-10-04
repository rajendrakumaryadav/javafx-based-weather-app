package in.co.dreamsdoor.learningspring;

import in.co.dreamsdoor.learningspring.Pojo.Students;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloMessage {
    @GetMapping("/")
    public String index() {
        return "Hello, World!";
    }

    @PostMapping("/")
    public Students getData() {
        return new Students(1, "Rajendra", 20.3f);
    }
}

