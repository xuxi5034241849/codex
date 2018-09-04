package org.xuxi.codex.common.exceptions;


public enum CodeDefined implements IMessageEnum {

    SUCCESS("0000", "成功"),
    ERROR("9999", "系统异常,请稍后再试"),
    ERROR_TOKEN("9900", "TOKEN错误"),
    URL_NOT_FOUND("2100", "URL地址错误"),
    ERROR_PARAMETER("2200", "参数错误"),
    ERROR_SYNTAX("2201", "请求语法错误");


    /***************************************************************************/

    private String code;

    private String msg;

    CodeDefined(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.msg;
    }

}


