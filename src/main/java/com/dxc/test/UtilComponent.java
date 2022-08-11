package com.dxc.test;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UtilComponent {

    private final String vowelRegex = "[^aeiouAEIOU]";

    public int getVowelsCount(String word){
        return word.replaceAll("[^aeiouAEIOU]","").length();
    }

    public Character[] getVowels(String word) {

        List<Character> vowelsList = word.replaceAll("[^aeiouAEIOU]", "").chars().mapToObj(
                c -> (char) c).distinct().collect(Collectors.toList());
        Character[] vowels = new Character[vowelsList.size()];
        return vowelsList.toArray(vowels) ;

    }
}
