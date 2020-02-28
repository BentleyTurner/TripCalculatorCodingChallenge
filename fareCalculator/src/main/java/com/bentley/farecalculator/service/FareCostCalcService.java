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
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class FareCostCalcService{

    private final String inputFileName;
    private final String outputFileName;

    private InputReader inputReader;
    private OutputWriter outputWriter;

    public FareCostCalcService(CsvConfig csvConfig, InputReader inputReader, OutputWriter outputWriter){
       this.inputReader = inputReader;
       this.outputWriter = outputWriter;

       this.inputFileName = csvConfig.getInputFileName();
       this.outputFileName = csvConfig.getOutputFileName();
    }

    public void calculateTrips() throws IOException{
        Iterator<Tap> tapIterator = inputReader.parse(inputFileName, Tap.class);

        Map<String, Tap> tappedOnCommuters = new HashMap<>();
        List<Commute> calculatedTrips = new ArrayList<>();
        List<Tap> incompleteTripTaps = new ArrayList<>();

        tapIterator.forEachRemaining(tap -> {
            String primaryAccNumber = tap.getPrimaryAccNumber();
            switch(tap.getTapType()){
                case ON:
                    if(tappedOnCommuters.containsKey(primaryAccNumber)){
                        incompleteTripTaps.add(tappedOnCommuters.get(primaryAccNumber));
                    }
                    tappedOnCommuters.put(primaryAccNumber, tap);
                    break;
                case OFF:
                    Tap tapOn = tappedOnCommuters.get(primaryAccNumber);
                    Commute trip = completeTripFromTapPair(tapOn, tap);
                    calculatedTrips.add(trip);
                    tappedOnCommuters.remove(primaryAccNumber);
                    break;
            }
        });
        incompleteTripTaps.addAll(tappedOnCommuters.values());
        incompleteTripTaps.forEach(tap -> calculatedTrips.add(completeTripFromTapPair(tap, null, CommuteStatus.INCOMPLETE)));
        outputWriter.createOutputFile(outputFileName, calculatedTrips, Commute.class);

    }

    private Commute completeTripFromTapPair(Tap onTap, @Nullable Tap offTap, CommuteStatus commuteStatus){
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

    private Commute completeTripFromTapPair(Tap onTap, Tap offTap){
        return completeTripFromTapPair(onTap,offTap, getTripStatus(onTap,offTap));
    }

    private static long calcTripSeconds(Date onTap, Date offTap){
        return TimeUnit.MILLISECONDS.toSeconds(onTap.getTime() - offTap.getTime());
    }
    private static CommuteStatus getTripStatus(Tap onTap, Tap offTap){
        if(offTap.getStopId().equals(onTap.getStopId())){
            return CommuteStatus.CANCELLED;
        }
        return CommuteStatus.COMPLETED;
    }
}