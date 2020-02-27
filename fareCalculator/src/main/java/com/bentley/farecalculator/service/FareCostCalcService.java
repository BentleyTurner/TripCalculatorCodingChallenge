package com.bentley.farecalculator.Service;

import java.io.IOException;
import com.bentley.farecalculator.model.Tap;
import com.bentley.farecalculator.model.TapType;
import com.bentley.farecalculator.model.Commute;
import com.bentley.farecalculator.model.CommuteStatus;
import com.bentley.farecalculator.config.CsvConfig;
import com.bentley.farecalculator.io.InputReader;
import com.bentley.farecalculator.io.OutputWriter;

import org.springframework.stereotype.Service;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class FareCostCalcService{

    private final String inputPath = "";
    private final String outputPath = "";

    private InputReader inputReader;
    private OutputWriter outputWriter;

    public FareCostCalcService(CsvConfig csvConfig, InputReader inputReader, OutputWriter outputWriter){
       this.inputReader = inputReader;
       this.outputWriter = outputWriter;
    }

    public void calculateTrips() throws IOException{

    }

    public Commute completeTripFromTapPair(Tap onTap, @Nullable Tap offTap, CommuteStatus commuteStatus){
        Commute Trip = new Commute();

        Date CommuteStartTime = onTap.getTapTime();
        Trip.setTimeStarted(CommuteStartTime);
        if(offTap != null){
            Date CommuteEndTime = offTap.getTapTime();
            Trip.setTimeFinished(CommuteEndTime);
            Trip.setToStopId(offTap.getStopId());
            Trip.setDurationSeconds(calcTripSeconds(CommuteStartTime, CommuteEndTime));

        }

        Trip.setBusId(onTap.getBusId());
        Trip.setFromStopId(onTap.getStopId());
        Trip.setCompanyId(onTap.getCompanyId());
        Trip.setPrimaryAccNumber(onTap.getPrimaryAccNumber());
        //TODO CALC's HERE
        return Trip;
    }

    public static long calcTripSeconds(Date onTap, Date offTap){
        return TimeUnit.MILLISECONDS.toSeconds(onTap.getTime() - offTap.getTime());
    }
}