package com.example.task_1.controllers;

import com.example.task_1.services.impl.TextParserDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task_1")
public class firstController {
    TextParserDefault myText = new TextParserDefault();

    @GetMapping
    public String get() {
        return myText.getFinalText();
    }

    @PostMapping
    public void post(@RequestBody String text) {
        myText.makeText(text);
    }

}
