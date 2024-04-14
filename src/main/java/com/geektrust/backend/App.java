package com.geektrust.backend;

import com.geektrust.backend.appConfig.ApplicationConfig;
import com.geektrust.backend.command.CommandInvoker;
import com.geektrust.backend.exception.ServiceException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class App {
	// To run the application 	./gradlew run --args="sample_input/input1.txt"
	public static void main(String[] args) {

//		args = new String[]{"sample_input/input2.txt"};
		List<String> commandArgs = new LinkedList<>(Arrays.asList(args));

		String filePath = commandArgs.stream()
				.findFirst()
				.orElseThrow(() -> new ServiceException("No args provided"));

		run(filePath);
	}

	public static void run(String filePath) {
		ApplicationConfig appConfig = new ApplicationConfig();
		CommandInvoker commandInvoker = appConfig.getCommandInvoker();

		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			String line = bufferedReader.readLine();

			while (Objects.nonNull(line)) {

				List<String> args = Arrays.asList(line.split(" "));

				commandInvoker.executeCommand(args.get(0), args);

				line = bufferedReader.readLine();
			}

			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
