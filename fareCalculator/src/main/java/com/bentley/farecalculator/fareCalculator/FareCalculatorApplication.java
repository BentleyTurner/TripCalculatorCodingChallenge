package com.bentley.farecalculator.fareCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bentley.farecalculator.Service.FareCostCalcService;
import com.bentley.farecalculator.io.InputReader;
import com.bentley.farecalculator.io.OutputWriter;
import com.bentley.farecalculator.config.CsvConfig;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import java.io.IOException;

@AllArgsConstructor
@SpringBootApplication
public class FareCalculatorApplication implements CommandLineRunner{

	private final FareCostCalcService fareCostCalcService;

	public static void main(String[] args) {
		SpringApplication.run(FareCalculatorApplication.class, args);
	}

	public void run(String[] args) throws IOException{
		fareCostCalcService.calculateTrips();
		System.exit(0);
	}

}
