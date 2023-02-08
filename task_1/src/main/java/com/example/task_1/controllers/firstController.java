package com.example.task_1.controllers;

import com.example.task_1.services.impl.TextParserDefault;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/task_1")
public class firstController {
    TextParserDefault myText = new TextParserDefault();

    @GetMapping
    public String get() {
        System.out.println("!!!!!");
        return myText.getFinalText();
    }

    @PostMapping
    public String post(@RequestBody String text) throws IOException, URISyntaxException {
        myText.makeText(text);
        return myText.getFinalText();
    }

}
