package ua.demitt.devopscourseapp.api;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.demitt.devopscourseapp.dto.HelloResponseDto;
import ua.demitt.devopscourseapp.service.HelloService;

import javax.validation.constraints.NotEmpty;

@RestController
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class MainController {
    private final HelloService helloService;

    @GetMapping("/api/v1/hello")
    public HelloResponseDto sayHello(@NotEmpty @RequestParam("name") String name) {
        String hello = helloService.sayHello(name);
        return new HelloResponseDto(hello);
    }
}
