package com.witsoftware.challenge.dto;

public class ResponseDTO {

    private double result;

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public static class Builder {
        private double result;

        public Builder com(double result) {
            this.result = result;
            return this;
        }

        public ResponseDTO build() {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.result = this.result;
            return responseDTO;
        }
    }
}
