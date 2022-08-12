package com.dxc.test;

import com.dxc.test.components.Solution;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@SpringBootTest
class TestApplicationTests {

    @MockBean
    private Solution solution;

    @Disabled
    @Test
    public void testFileCreatedForInput() throws Exception{


        File inputFile = new File(System.getProperty("java.io.tmpdir").concat("/input.txt"));
        File outputFile = new File(System.getProperty("java.io.tmpdir").concat("/output.txt"));
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputFile))){

            bufferedWriter.write("Platon made bamboo boats.");
            bufferedWriter.flush();
            String args[] = {inputFile.getPath(), outputFile.getPath()};
            this.solution.processAndOutput(args);
            if(!outputFile.exists()) throw new Exception("input processing test failed");
            inputFile.delete();
            outputFile.delete();
        }
    }
}
