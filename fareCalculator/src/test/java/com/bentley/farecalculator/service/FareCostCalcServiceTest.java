package com.bentley.farecalculator.Service;

import com.bentley.farecalculator.model.Tap;
import com.bentley.farecalculator.model.TapType;
import com.bentley.farecalculator.model.Commute;
import com.bentley.farecalculator.model.CommuteStatus;
import com.bentley.farecalculator.configurations.CsvConfig;
import com.bentley.farecalculator.configurations.JacksonConfiguration;
import com.bentley.farecalculator.io.InputReader;
import com.bentley.farecalculator.io.OutputWriter;
import com.bentley.farecalculator.fareCalculator.CostCalculator;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
class FareCostCalcServiceTest {

    private static final String INPUT = "tapsTest.csv";
    private static final String OUTPUT = "tripsTest.csv";

    @Mock
    private OutputWriter outputWriter;

    @Mock
    private InputReader inputReader;

    @Mock
    private CsvConfig csvConfig;

    @Mock
    private JacksonConfiguration jacksonConfiguration;

    @Mock
    private CostCalculator costCalculator;

    private FareCostCalcService fareCostCalcService;

    @BeforeEach
    void setup() {
        when(CsvConfig.getInputFileName()).thenReturn(INPUT);
        when(CsvConfig.getOutputFileName()).thenReturn(OUTPUT);

        FareCostCalcService = new FareCostCalcService(csvConfig, jacksonConfiguration, inputReader, outputWriter, costCalculator);
    }

    @Test
    void calculateTripCostandWriteToFile() throws IOException{
        String stop1 = "Stop1";
        String stop2 = "Stop2";
        String companyId = "Company1";
        String busId = "Bus37";
        String pan = "122000000000003";

        Instant startTime = Instant.now();

        Tap onTap = new Tap(1, Date.from(startTime), TapType.ON, stop1, companyId, busId, pan);
        Tap offTap = new Tap(2, Date.from(startTime.plusSeconds(600)), TapType.OFF, stop2, companyId, busId, pan);

        when(inputReader.parse(INPUT, Tap.class)).thenReturn(List.of(onTap, offTap).iterator());
        when(costCalculator.CalculateTrip(onTap, offTap).thenReturn(Double));

        fareCostCalcService.calculateTrips();
        ArgumentCaptor<List<Trip>> tripArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(outputWriter).createFile(eq(OUTPUT), tripArgumentCaptor.capture(), eq(Commute.class));
        verify(tripCostCalculator).calculateChargeAmount(tapOn, tapOff);
        assertEquals(1, tripArgumentCaptor.getValue().size());
        Commute writtenTrip = tripArgumentCaptor.getValue().get(0);
        assertEquals(stop1, writtenTrip.getFromStopId());
        assertEquals(stop2, writtenTrip.getToStopId());
        assertEquals(companyId, writtenTrip.getCompanyId());
        assertEquals(busId, writtenTrip.getBusId());
        assertEquals(pan, writtenTrip.getPrimaryAccountNumber());
        assertEquals(tripDuration, writtenTrip.getDurationSeconds());
        assertEquals(Date.from(startTime), writtenTrip.getStartedTime());
        assertEquals(Date.from(startTime.plusSeconds(tripDuration)), writtenTrip.getFinishedTime());
        assertEquals(CommuteStatus.COMPLETED, writtenTrip.getTripStatus());
        assertEquals(CURRENCY + Double, writtenTrip.getChargeAmount());


    }


}