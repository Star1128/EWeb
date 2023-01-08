package com.ethan.eweb.response;

public enum CommonState implements IResponseState {

    SUCCESS(true, 10000, "操作成功"),
    MSG_CODE_SEND_SUCCESS(true, 10001, "验证码发送成功"),
    REGISTER_SUCCESS(true, 10002, "注册成功"),
    LOGIN_SUCCESS(true, 10003, "登录成功"),
    UPLOAD_SUCCESS(true, 10004, "上传成功"),
    FAIL(false, 11111, "操作失败"),
    LOGIN_FAILED(false, 11112, "账号或密码错误"),
    PHONE_NUMBER_ERROR(false, 11113, "手机号码错误"),
    MSG_CODE_SEND_FAILED(false, 11114, "验证码发送失败，请稍后重试"),
    MSG_CODE_SEND_TOO_MUCH(false, 11115, "操作太过频繁，请稍后重试"),
    REGISTER_FAILED(false, 11116, "注册失败，请稍后重试"),
    REGISTER_INFO_ERROR(false, 11117, "注册信息错误"),
    MSG_CODE_ERROR(false, 11118, "验证码错误"),
    PHONE_NUMBER_NULL(false, 11119, "手机号码为空"),
    USER_NOT_REGISTER(false, 11120, "该手机号码未注册"),
    USER_NAME_ERROR_OR_PASSWORD_ERROR(false, 11121, "用户名或密码错误"),
    ACCOUNT_NOT_LOGIN(false, 11122, "账号未登录");

    private final boolean success;
    private final int code;
    private final String msg;

    CommonState(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
