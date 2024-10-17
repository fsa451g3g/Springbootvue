package com.example.handler;

import com.example.exception.CustomException;
import com.example.exception.ExceptionType;
import com.example.result.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result bizExceptionHandler(AccessDeniedException e) {
        return Result.failure(ExceptionType.FORBIDDEN.getMessage())
                .setCode(ExceptionType.FORBIDDEN.getCode());
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result bizExceptionHandler(DuplicateKeyException e) {
        return Result.failure(ExceptionType.USER_NAME_DUPLICATE.getMessage())
                .setCode(ExceptionType.USER_NAME_DUPLICATE.getCode());
    }

    @ExceptionHandler(value = CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result bizExceptionHandler(CustomException e) {
        return Result.build().setCode(e.getCode())
                .setMessage(e.getMessage())
                .setData(e.getStackTrace());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandler(Exception e) {
        System.out.println(e);
        return Result.build().setCode(ExceptionType.INNER_ERROR.getCode())
                .setMessage(ExceptionType.INNER_ERROR.getMessage());
    }

//    @ExceptionHandler(value = BindException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public Result exceptionHandler(BindException e) {
//        return Result.build().setCode(e.hashCode())
//                .setMessage("上传数据不合法");
//    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Result> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<Result> list = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            list.add(Result.build().setCode(ExceptionType.BAD_REQUEST.getCode())
                    .setMessage(error.getDefaultMessage()));
        });
        return list;
    }

}
