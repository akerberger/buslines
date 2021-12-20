package org.akerberger.buslines.line;

import org.akerberger.buslines.stop.StopPoint;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private int lineNumber;
    private List<StopPoint> stopPoints = new ArrayList<>();

    public Line(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void addBusStop(StopPoint stopPoint) {
        stopPoints.add(stopPoint);
    }

    public int getNumberOfStops() {
        return stopPoints.size();
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean containsStop(int stopPointNumber) {
        return stopPoints.stream()
                .filter(stop -> stop.getPointNumber() == stopPointNumber)
                .findFirst()
                .isPresent();
    }

    @Override
    public String toString() {
        return String.valueOf(lineNumber);
    }
}
