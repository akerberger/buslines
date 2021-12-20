package org.akerberger.buslines.line;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class LineResponse {
    private String StatusCode;
    private String Message;
    private String ExecutionTime;
    private ResponseData ResponseData;

    @NoArgsConstructor
    public static class ResponseData {
        private String Version;
        private String Type;
        private List<Result> Result;

    }

    @Getter
    @NoArgsConstructor
    public static class Result {

        private String LineNumber;
        private String LineDesignation;
        private String DefaultTransportMode;
        private String DefaultTransportModeCode;
        private String LastModifiedUtcDateTime;
        private String ExistsFromDate;
    }
}
