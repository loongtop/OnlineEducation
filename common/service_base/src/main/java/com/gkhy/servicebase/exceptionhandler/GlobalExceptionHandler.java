package com.gkhy.servicebase.exceptionhandler;

import com.gkhy.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody //to return data
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("Global exception handling performed..");
    }


    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("ArithmeticException exception handling performed..");
    }

    //custom exception
    @ExceptionHandler(AcademyException.class)
    @ResponseBody
    public R error(AcademyException e) {
        log.error(e.getMessage());
        e.printStackTrace();

        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
