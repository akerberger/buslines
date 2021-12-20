package org.akerberger.buslines.line;

import org.akerberger.buslines.stop.StopName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/buslines")
public class LineController {

    final static Logger log = LoggerFactory.getLogger(LineController.class);

    private LineService lineService;

    public LineController(LineService lineService) {
        this.lineService = lineService;
    }

    @GetMapping(path = "/most-stops")
    public String getLinesWithMostStops() {

        final int nrOfLines = 10;

        List<Line> linesWithMostStops = lineService.linesWithMostStops(nrOfLines);

        List<StopName> stopNames = lineService.busStopNames(linesWithMostStops.get(0));

        return buildBrowserOutputString(linesWithMostStops, stopNames);

    }

    private String buildBrowserOutputString(List<Line> mostStops, List<StopName> stopNames) {
        StringBuilder sb = new StringBuilder();

        sb.append("Bus lines with most stops: ");
        sb.append(mostStops.toString());
        sb.append("<br><br>");
        sb.append("Stop names, line nr " + mostStops.get(0).getLineNumber() + ": ");
        sb.append(stopNames.toString());

        return sb.toString();
    }
}
