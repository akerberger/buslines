package org.akerberger.buslines.stop;

import lombok.Getter;

@Getter
public class StopPoint {
    private int pointNumber;
    private int directionCode;

    public StopPoint(int pointNumber, int directionCode) {
        this.pointNumber = pointNumber;
        this.directionCode = directionCode;
    }
}
