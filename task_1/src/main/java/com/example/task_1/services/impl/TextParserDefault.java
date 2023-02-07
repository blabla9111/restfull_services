package com.example.task_1.services.impl;

import com.example.task_1.services.TextParser;

public class TextParserDefault implements TextParser {
    String innerText = "NONE";
    @Override
    public String makeText(String text) {
        setInnerText(text);
        return text;
    }

    private void setInnerText(String text){
        this.innerText = text;
    }

    public String getInnerText(){
        return this.innerText;
    }
}
