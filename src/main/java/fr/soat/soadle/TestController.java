package fr.soat.soadle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class TestController {
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello world!";
    }
}
