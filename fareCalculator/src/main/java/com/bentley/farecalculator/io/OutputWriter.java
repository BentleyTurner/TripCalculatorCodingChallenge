package com.bentley.farecalculator.io;

import java.io.IOException;
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class OutputWriter{
    private final CsvMapper csvMapper;

    public <T> void createOutputFile(String filename, List<T> data, Class<T> pClass) throws IOException{
        File outputFile = new File(filename);
        outputFile.getParentFile().mkdirs();
        FileOutputStream outStream = new FileOutputStream(outputFile);
        BufferedOutputStream bufferedOutStream = new BufferedOutputStream(outStream);
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(bufferedOutStream, StandardCharsets.UTF_8);

        CsvSchema pClassSchema = csvMapper.schemaFor(pClass).withHeader().withoutQuoteChar();
        ObjectWriter pClassObjectWriter = csvMapper.writer(pClassSchema);
        pClassObjectWriter.writeValue(outStreamWriter, data);

    }

}