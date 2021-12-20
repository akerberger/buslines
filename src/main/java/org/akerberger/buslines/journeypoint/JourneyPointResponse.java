package org.akerberger.buslines.journeypoint;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class JourneyPointResponse {

    private String StatusCode;
    private String ExecutionTime;
    private ResponseData ResponseData;

    public List<JourneyPointResult> getResults() {
        return ResponseData.getResults();
    }

    @NoArgsConstructor
    public class ResponseData {

        private String Version;
        private String Type;
        private List<JourneyPointResult> Result;

        public List<JourneyPointResult> getResults() {
            return Collections.unmodifiableList(Result);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class JourneyPointResult {

        private String LineNumber;
        private String DirectionCode;
        private String JourneyPatternPointNumber;
        private String LastModifiedUtcDateTime;
        private String ExistsFromDate;

    }


}
