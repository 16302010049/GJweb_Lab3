package com.example.hello.controller;

import com.example.hello.response.GreetingResponse;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin(origins = "*")
public class HelloController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello, World!";
    }

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public @ResponseBody
    GreetingResponse greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new GreetingResponse(counter.incrementAndGet(), "Hello, " + name + "!");
    }

}

