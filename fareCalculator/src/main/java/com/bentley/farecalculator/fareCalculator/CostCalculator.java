package com.bentley.farecalculator.fareCalculator;


import com.bentley.farecalculator.model.Tap;
import com.bentley.farecalculator.model.TapType;
import com.bentley.farecalculator.model.Commute;
import com.bentley.farecalculator.model.CommuteStatus;
import java.math.BigDecimal;

import org.springframework.lang.Nullable;

public class CostCalculator {

    public Double CalculateTrip(Tap onTap, @Nullable Tap offTap, CommuteStatus commuteStatus){
        String firstTapID = onTap.getStopId();
        String secondTapID = offTap.getStopId();
        switch (commuteStatus){
            case COMPLETED:
                if (firstTapID.equals("stop1") && secondTapID.equals("stop2")){ return 3.25;}
                if (firstTapID.equals("stop1") && secondTapID.equals("stop3")){ return 7.30;}
                if (firstTapID.equals("stop2") && secondTapID.equals("stop3")){ return 5.50;}
                if (firstTapID.equals("stop2") && secondTapID.equals("stop1")){ return 3.25;}
                if (firstTapID.equals("stop3") && secondTapID.equals("stop1")){ return 7.30;}
                if (firstTapID.equals("stop3") && secondTapID.equals("stop2")){ return 5.50;}
                break;
            case INCOMPLETE:
                if (firstTapID.equals("stop1")){return 7.30;}
                if (firstTapID.equals("stop2")){return 5.50;}
                if (firstTapID.equals("stop3")){return 7.30;}
                break;
            case CANCELLED:
                return 0.00;
        }
        return 0.00;
    }



}