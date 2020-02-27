package com.bentley.farecalculator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Commute{
    @JsonProperty("Started")
    private Date timeStarted;
    @JsonProperty("Finished")
    private Date timeFinished;
    @JsonProperty("DurationSecs")
    private Long durationSeconds;
    @JsonProperty("FromStopId")
    private String fromStopId;
    @JsonProperty("ToStopId")
    private String toStopId;
    @JsonProperty("ChargeAmount")
    private String chargeAmount;
    @JsonProperty("CompanyId")
    private String companyId;
    @JsonProperty("BusID")
    private String busId;
    @JsonProperty("PAN")
    private String primaryAccNumber;
    @JsonProperty("Status")
    private CommuteStatus commuteStatus;

    @JsonCreator
    public Commute(@JsonProperty("Started") Date timeStarted,
    @JsonProperty("Finished") Date timeFinished,
    @JsonProperty("DurationSecs") Long durationSeconds,
    @JsonProperty("FromStopId") String fromStopId,
    @JsonProperty("ToStopId") String toStopId,
    @JsonProperty("ChargeAmount") String chargeAmount,
    @JsonProperty("CompanyId") String companyId,
    @JsonProperty("BusID") String busId,
    @JsonProperty("PAN") String primaryAccNumber,
    @JsonProperty("Status") CommuteStatus commuteStatus){
        this.timeStarted = timeStarted;
        this.timeFinished = timeFinished;
        this.durationSeconds = durationSeconds;
        this.fromStopId = fromStopId;
        this.toStopId = toStopId;
        this.chargeAmount = chargeAmount;
        this.companyId = companyId;
        this.busId = busId;
        this.primaryAccNumber = primaryAccNumber;
        this.commuteStatus = commuteStatus;
    }
}