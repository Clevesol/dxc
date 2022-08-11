package com.dxc.test.data;

public class OutputWrapper {

    private String word;
    private Character[] vowels;
    private int wordLength;
    private double vowelAverage;

    public OutputWrapper(Character[] vowels, int wordLength, double vowelAverage,String word) {
        this.vowels = vowels;
        this.wordLength = wordLength;
        this.vowelAverage = vowelAverage;
        this.word = word;
    }

    @Override
    public String toString() {
        String vowels = "";
        int loopLength = this.vowels.length;
        for(int idx =0; idx <  loopLength; idx++){
            char c = this.vowels[idx];
            vowels = vowels.concat(c+"").concat(loopLength - idx > 1 ? ",":"");
        }

        String vowelAverageStr =  String.format( this.vowelAverage % 1 == 0 ?  "%.0f" : "%.2f", vowelAverage);
        return "({"+vowels+"}, "+this.wordLength+") -> " + vowelAverageStr;
    }
}
