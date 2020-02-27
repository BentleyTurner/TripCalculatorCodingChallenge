package com.bentley.farecalculator.model;

import lombok.Getter;
import lombok.Setter;

public enum CommuteStatus{
    COMPLETED("COMPLETED"), INCOMPLETE("INCOMPLETE"), CANCELLED("CANCELLED");

    @Getter
    private final String status;

    CommuteStatus(String status){ this.status = status;}
}