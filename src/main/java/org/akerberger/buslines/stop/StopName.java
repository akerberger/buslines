package org.akerberger.buslines.stop;

import lombok.Getter;

@Getter
public class StopName {

    private int stopPointNumber;
    private String stopPointName;

    public StopName(int stopPointNumber, String stopPointName) {
        this.stopPointNumber = stopPointNumber;
        this.stopPointName = stopPointName;
    }

    @Override
    public String toString() {
        return stopPointName;
    }
}
