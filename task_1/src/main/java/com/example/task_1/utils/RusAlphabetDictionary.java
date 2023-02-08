package com.example.task_1.utils;

import java.util.HashMap;

public class RusAlphabetDictionary {
    private HashMap<Character, Integer> dictionary = new HashMap<>();

    public RusAlphabetDictionary() {
        char[] alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
        for (int i = 0; i < 33; i++) {
            dictionary.put(alphabet[i], i + 1);
        }
    }

    public Integer getCharNum(Character c) {
        if (dictionary.containsKey(c)) {
            return dictionary.get(c);
        }
        return 0;
    }
}
