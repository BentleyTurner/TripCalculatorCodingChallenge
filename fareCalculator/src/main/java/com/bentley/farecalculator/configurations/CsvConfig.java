package com.bentley.farecalculator.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Configration
@ConfigurationProperties(prefix = "fareCalculatorApplication")
public class CsvConfig{
    private String inputFileName;
    private String outputFileName;

    public CsvConfig(String inputFileName, String outputFileName){
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
}