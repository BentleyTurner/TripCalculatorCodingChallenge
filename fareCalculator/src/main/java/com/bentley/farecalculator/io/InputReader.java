package com.bentley.farecalculator.io;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;



@Component
@AllArgsConstructor
public class InputReader{

    private final CsvMapper csvMapper;

    public <T> Iterator<T> parse(String file, Class<T> pClass) throws IOException{

        File inputFile = new File(file);
        return csvMapper.readerFor(pClass).with(CsvSchema.emptySchema().withHeader()).readValues(file);
    }

}