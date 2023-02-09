package com.example.task_1.controllers;

import com.example.task_1.services.TextParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task_1")
public class firstController {
    private final TextParser textParser;

    @GetMapping
    public String get() {
        return textParser.getFinalText();
    }

    @PostMapping
    public String post(@RequestBody String text){
        textParser.makeText(text);
        return textParser.getFinalText();
    }

}
