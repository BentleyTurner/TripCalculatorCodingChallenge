package com.bentley.farecalculator.config;

public class CsvConfig{
    private String inputPath;
    private String outputPath;

    public CsvConfig(String inputPath, String outputPath){
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }
}