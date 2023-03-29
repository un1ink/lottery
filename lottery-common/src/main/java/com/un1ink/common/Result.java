package com.un1ink.common;

import com.un1ink.common.constants.ResponseCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Result implements Serializable {
    private static final long serialVersionUID = -3826891916021780628L;
    private String code;
    private String info;

    public Result(String code, String info) {
        this.code = code;
        this.info = info;
    }



    public static Result buildResult(ResponseCode code, String info) {
        return new Result(code.getCode(), info);
    }

    public static Result buildResult(ResponseCode code) {
        return new Result(code.getCode(), code.getInfo());
    }

    public static Result buildSuccessResult() {
        return new Result(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo());

    }

    public static Result buildSuccessResult(String info) {
        return new Result(ResponseCode.UN_ERROR.getCode(), info);
    }

    public static Result buildErrorResult() {
        return new Result(ResponseCode.UN_ERROR.getCode(), ResponseCode.UN_ERROR.getInfo());
    }

    public static Result buildErrorResult(String info) {
        return new Result(ResponseCode.UN_ERROR.getCode(), info);
    }
}
