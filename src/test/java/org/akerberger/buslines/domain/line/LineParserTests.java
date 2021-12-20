package org.akerberger.buslines.domain.line;

import org.akerberger.buslines.stop.StopPoint;
import org.akerberger.buslines.line.Line;
import org.akerberger.buslines.line.LineParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineParserTests {

    LineParser instanceToTest = new LineParser();

    List<Line> lines = new ArrayList<>();
    int lineWithMostElements = 3;

    @BeforeEach
    void populateBusLinesList(){
            Random random = new Random();

            Line withFiveStops = new Line(1);
            for(int i = 0; i < 5; i++){
                withFiveStops.addBusStop(new StopPoint(random.nextInt(500)+1, random.nextInt(2)+1));
            }
            lines.add(withFiveStops);

            Line withTenStops = new Line(2);
            for(int i = 0; i < 10; i++){
                withTenStops.addBusStop(new StopPoint(random.nextInt(500)+1, random.nextInt(2)+1));
            }
            lines.add(withTenStops);

            Line withFifteenStops = new Line(lineWithMostElements);
            for(int i = 0; i < 15; i++){
                withFifteenStops.addBusStop(new StopPoint(random.nextInt(500)+1, random.nextInt(2)+1));
            }
            lines.add(withFifteenStops);

            Line withEightStops = new Line(4);
            for(int i = 0; i < 8; i++){
                withEightStops.addBusStop(new StopPoint(random.nextInt(500)+1, random.nextInt(2)+1));
            }
            lines.add(withEightStops);
    }

    @Test
    void lineWithMostStopsIsFirstInResultList(){
        int topListSize = 4;
        List<Line> linesWithMostStop = instanceToTest.linesWithMostStops(lines, topListSize);
        Line firstItem = linesWithMostStop.get(0);

        assertEquals(firstItem.getLineNumber(), lineWithMostElements);
        }

}