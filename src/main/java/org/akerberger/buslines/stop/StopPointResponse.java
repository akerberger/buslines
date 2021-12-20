package org.akerberger.buslines.stop;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class StopPointResponse {
    private String StatusCode;
    private String Message;
    private String ExecutionTime;
    private ResponseData ResponseData;

    public List<Result> getResults() {
        return ResponseData.getResults();
    }

    @NoArgsConstructor
    public static class ResponseData {
        private String Version;
        private String Type;
        private List<Result> Result;

        public List<StopPointResponse.Result> getResults() {
            return Collections.unmodifiableList(Result);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Result {
        private String StopPointNumber;
        private String StopPointName;
    }
}
