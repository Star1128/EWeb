package com.ethan.eweb.response;

public class ResponseResult {

    private boolean success;
    private int code;
    private String msg;
    private Object data;

    public ResponseResult(IResponseState state) {
        this.success = state.isSuccess();
        this.code = state.getCode();
        this.msg=state.getMsg();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
