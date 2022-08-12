package com.dxc.test;

import com.dxc.test.components.Solution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class TestApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(TestApplication.class);

	@Autowired
	private Solution solution;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.solution.processAndOutput(args);
	}
}
