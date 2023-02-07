package com.example.task_1.services.impl;

import com.example.task_1.services.TextParser;
import com.example.task_1.utils.RusAlphabetDictionary;

public class TextParserDefault implements TextParser {
    String innerText = "NONE";
    String outText = "NONE";
    @Override
    public String makeText(String text) {
        RusAlphabetDictionary rusAlphabetDictionary = new RusAlphabetDictionary();
        char[] textToChar = text.toCharArray();
        text="<p>";
        String parseText = "<p>";
        for(int i = 0; i< textToChar.length;i++){
            int num = rusAlphabetDictionary.getCharNum(textToChar[i]);
            if(num == 0){
                parseText+=textToChar[i]+"&nbsp";
                text +=textToChar[i]+"&nbsp";
            }
            else{
                if (num<10){
                    parseText+=num+"&nbsp";
                    text +=textToChar[i]+"&nbsp";
                }
                else{
                    parseText+=num+"&nbsp";
                    text +=textToChar[i]+"&nbsp&nbsp&nbsp";
                }
            }
        }
        text+="</p>";
        setInnerText(text);
        setOutText(parseText);
        return text;
    }

    private void setInnerText(String text){
        this.innerText = text;
    }

    private void setOutText(String text){
        this.outText = text;
    }

    public String getFinalText(){
        return this.innerText+this.outText;
    }
}
