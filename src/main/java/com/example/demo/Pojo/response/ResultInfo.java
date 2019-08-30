package com.example.demo.Pojo.response;

public class ResultInfo {
    private int resultCode = 0;
    private String resultMessage = "success";
    private Object resultObj;

    private ResultInfo() {
    }

    public ResultInfo(int resultCode, String resultMessage, Object resultObj) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultObj = resultObj;
    }

    public ResultInfo(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public static ResultInfo success(){
        return new ResultInfo();
    }
    public static ResultInfo errorMessage(String resultMessage){
        return new ResultInfo(9999,resultMessage);

    }
    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }
}
