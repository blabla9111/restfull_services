package com.example.task_1.services.impl;

import com.example.task_1.services.TextParser;
import com.example.task_1.utils.RusAlphabetDictionary;
import org.springframework.stereotype.Service;

@Service
public class TextParserDefault implements TextParser {
    private String innerText = "NONE";
    private String outText = "NONE";

    @Override
    public void makeText(String text) {
        RusAlphabetDictionary rusAlphabetDictionary = new RusAlphabetDictionary();
        System.out.println(text);
        char[] textToChar = text.toCharArray();
        text = "";
        String parseText = "";
        for (char c : textToChar) {
            int num = rusAlphabetDictionary.getCharNum(Character.toLowerCase(c));
            System.out.println(c);
            if (c == '\n') {
                continue;
            } else if (c == '\r') {
                text += c;
                parseText += c;
                continue;
            }
            text += c + " ";
            if (num == 0) {
                parseText += c;
            } else {
                parseText += num;
                if (num >= 10) {
                    text += " ";
                }
            }
            parseText += " ";
        }
        setInnerText(text + "\n");
        setOutText(parseText);
    }

    private void setInnerText(String text) {
        this.innerText = text;
    }

    private void setOutText(String text) {
        this.outText = text;
    }

    @Override
    public String getFinalText() {
        return this.innerText + this.outText;
    }
}
