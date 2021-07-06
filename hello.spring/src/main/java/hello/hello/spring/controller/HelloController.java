package hello.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러임을 알려주는 어노테이션
public class HelloController {

    @GetMapping("hello") // 웹 어플리케이션에서 '/hello'로 들어오면 이 메서드를 호출한다.
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // model의 값을 hello 화면을 실행시키도록 한다.
    }
}