package com.bentley.farecalculator.fareCalculator;


import com.bentley.farecalculator.model.Tap;
import com.bentley.farecalculator.model.TapType;
import com.bentley.farecalculator.model.Commute;
import com.bentley.farecalculator.model.CommuteStatus;

import org.springframework.lang.Nullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CostCalculatorTest{

    private CostCalculator costCalculator;

    @Test
    private Double calculateCharge(String stop1, String stop2){
        Tap onTap = mock(Tap.class);
        when(onTap.getStopId()).thenReturn(stop1);
        Tap offTap = mock(Tap.class);
        when(offTap.getStopId()).thenReturn(stop2);
        return.costCalculator.CalculateTrip(onTap,offTap);
    }
}
