package com.ethan.eweb.response;

public interface IResponseState {

    boolean isSuccess();

    int getCode();

    String getMsg();
}
