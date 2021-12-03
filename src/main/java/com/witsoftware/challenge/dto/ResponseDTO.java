package com.witsoftware.challenge.dto;

public class ResponseDTO {

    private Double result;
    private boolean hasError;
    private String errorMessage;

    public Double getResult() {
        return result;
    }

    public boolean isHasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static class Builder {
        private Double result;
        private boolean hasError;
        private String errorMessage;

        public Builder with(double result) {
            this.result = result;
            return this;
        }

        public Builder with(boolean hasError) {
            this.hasError = hasError;
            return this;
        }

        public Builder with(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ResponseDTO build() {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.result = this.result;
            responseDTO.hasError = this.hasError;
            responseDTO.errorMessage = this.errorMessage;
            return responseDTO;
        }
    }
}
