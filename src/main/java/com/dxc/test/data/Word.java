package com.dxc.test.data;

public class Word {

    private String word;

    private int vowelsCount;

    public Word(String word) {
        this.word = word;
    }

    public void setVowelsCount(int vowelsCount){
        this.vowelsCount = vowelsCount;
    }

    public int getVowelsCount(){
        return vowelsCount;
    }

    public int getWordLength(){
        return this.word.length();
    }

    @Override
    public String toString(){
        return this.word;
    }
}
