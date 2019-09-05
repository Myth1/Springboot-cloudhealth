package com.shgbit.cloudhealth.exception.result;

public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    LOGINID_NOTEXIST_ERROR(1, "此用户不存在"),
    FILENULL_ERROR(2, "文件不能为空"),
    FILEMAX_ERROR(2, "文件不能超过1M"),
    LOGINID_TAKEMEDICINEREMIND_NOTEXIST_ERROR(3, "无该用户服药提醒记录"),
    TAKEMEDICINEREMIND_NOTEXIST_ERROR(3, "此服药提醒Id不存在"),
    TAKEMEDICINEREMIND_hasbeenFinished_ERROR(3, "服药提醒已经结束"),
    PARAMETER_IS_NULL(4, "参数为空"),
    SUCCESS(0, "成功"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
