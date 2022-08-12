package com.dxc.test.components;

import com.dxc.test.data.OutputWrapper;
import com.dxc.test.data.Word;
import com.dxc.test.exceptions.NoValidInputFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Component
public class Solution {

    @Autowired
    private UtilComponent utilComponent;

    private static Logger logger = LoggerFactory.getLogger(Solution.class);

    public void  processAndOutput(String... args) throws IOException {
        String inputFilePath = args.length == 2 ? args[0] : "input.txt";
        logger.info("Using file : "+inputFilePath);

        File inputFile = new File(inputFilePath);
        if(!inputFile.exists()) throw new NoValidInputFoundException();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));){
            String sentence = null;
            List<List<Word>> sentences = new ArrayList<>();
            while ((sentence = bufferedReader.readLine()) != null) {
                String[] words = sentence.split(" ");
                List<Word> wordsTemp = new ArrayList<>();
                for (String word : words) {
                    wordsTemp.add(new Word(word));
                }
                sentences.add(wordsTemp);
            }

            List<OutputWrapper> outputWrapperList = this.sentencesToOutputWrapper(sentences);
            String outPutPath = args.length == 2 ? args[1] : "output.txt";
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outPutPath))){

                bufferedWriter.write(
                        outputWrapperList.stream().map( wrapper -> {
                                    return wrapper.toString();
                                })
                                .collect(joining("\n"))
                                + "\n"
                );

            }


        }catch(Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }

    }

    public List<OutputWrapper> sentencesToOutputWrapper(List<List<Word>> sentences){


        List<OutputWrapper> outputWrapperList = new ArrayList<>();

        sentences.stream().forEach( senetence -> {



            senetence.stream().forEach(wordElement -> {
                String word = wordElement.toString();
                int wordLength = word.length();
                double vowelsCount = this.utilComponent.getVowelsCount(word);
                Character[] vowels = this.utilComponent.getVowels(word);
                double vowelAverage = vowelsCount/wordLength;
                outputWrapperList.add(new OutputWrapper(vowels,wordLength,vowelAverage,word));
            });
        });

        return  outputWrapperList;
    }
}
