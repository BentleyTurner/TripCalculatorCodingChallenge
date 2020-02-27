package com.bentley.farecalculator.model;

import lombok.Getter;
import lombok.Setter;

public enum TapType{

    ON("ON"), OFF("OFF");

    @Getter
    private final String type;

    TapType(String tapType){type = tapType;}
}