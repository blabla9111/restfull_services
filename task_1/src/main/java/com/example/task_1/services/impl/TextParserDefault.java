package com.example.task_1.services.impl;

import com.example.task_1.services.TextParser;
import com.example.task_1.utils.RusAlphabetDictionary;

public class TextParserDefault implements TextParser {
    private String innerText = "<p>NONE</p>";
    private String outText = "<p>NONE</p>";

    @Override
    public String makeText(String text) {
        RusAlphabetDictionary rusAlphabetDictionary = new RusAlphabetDictionary();
        char[] textToChar = text.toCharArray();
        text = "<p>";
        String parseText = "<p>";
        for (char c : textToChar) {
            int num = rusAlphabetDictionary.getCharNum(c);
            text += c + "&nbsp";
            if (num == 0) {
                parseText += c;
            } else {
                parseText += num;
                if (num >= 10) {
                    text += "&nbsp&nbsp";
                }
            }
            parseText += "&nbsp";
        }
        text += "</p>";
        parseText += "</p>";
        setInnerText(text);
        setOutText(parseText);
        return text;
    }

    private void setInnerText(String text) {
        this.innerText = text;
    }

    private void setOutText(String text) {
        this.outText = text;
    }

    public String getFinalText() {
        return this.innerText + this.outText;
    }
}
