package com.gkhy.commonutils.result;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * return value
 * This class that returns the result uniformly for the whole project
 * @author leo
 * @date 2022-06-02
 */

@Getter
@Setter
@Component
public final class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = -2666368596113433194L;

    private Boolean success;
    private Integer code;
    private String message;

    public static Result success() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("Success");
        return r;
    }

    public static Result fail() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("Failure");
        return r;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        super.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        super.putAll(map);
        return this;
    }
}
