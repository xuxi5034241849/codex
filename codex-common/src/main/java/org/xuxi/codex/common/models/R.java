package org.xuxi.codex.common.models;

import org.xuxi.codex.common.exceptions.CodeDefined;
import org.xuxi.codex.common.exceptions.RException;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", CodeDefined.SUCCESS.getValue());
        put("msg", CodeDefined.SUCCESS.getDesc());
    }



    public static R error(String code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }


    public static R error() {
        return error(CodeDefined.ERROR.getValue(), CodeDefined.ERROR.getDesc());
    }

    public static R error(String msg) {
        return error(CodeDefined.ERROR.getValue(), msg);
    }

    public static R error(CodeDefined codeDefined) {
        return error(codeDefined.getValue(), codeDefined.getDesc());
    }

    public static R error(RException ex) {
        return error(ex.getCode(), ex.getMsg());
    }






    public static R ok() {
        return new R();
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }


    public static R valid(String msg){
        R r = new R();
        r.put("code", CodeDefined.VALID.getValue());
        r.put("msg", msg);
        return r;
    }


    public R data(Object value) {
        super.put("data", value);
        return this;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
