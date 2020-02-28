package com.bentley.farecalculator.config;

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
}