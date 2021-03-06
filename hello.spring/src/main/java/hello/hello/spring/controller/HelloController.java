package hello.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 컨트롤러임을 알려주는 어노테이션
public class HelloController {

    @GetMapping("hello") // 웹 어플리케이션에서 '/hello'로 들어오면 이 메서드를 호출한다.
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // model의 값을 hello 화면을 실행시키도록 한다.
    }

    @GetMapping("hello-mvc")
    // 웹 URL을 통해 파라미터를 전달 받는다.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // static으로 만들면 클래스 안에서 클래스를 사용할 수 있다.
    static class Hello { // 반환할 객체
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}