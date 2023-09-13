package com.hk.framework.exception;

import com.hk.framework.bean.base.R;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * 全局异常处理
 * </p>
 *
 * @author Matt
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     */
    @ExceptionHandler(value = CustomizeException.class)
    public R<String> handleCustom(CustomizeException e) {
        log.error("自定义异常拦截-> e={}", e.getMessage());
        e.printStackTrace();
        return e.getResult();
    }

    /**
     * 参数校验异常拦截
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("参数校验异常拦截-> e={}", e.getMessage());
        e.printStackTrace();
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return R.fail(objectError.getDefaultMessage());
    }

    /**
     * 参数异常拦截 （方法上的）
     **/
    @ExceptionHandler(ConstraintViolationException.class)
    public R<String> constraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String message = constraintViolation.getMessage();
            builder.append(message);
        }
        log.error("接口-> " + request.getServletPath(), e.toString());
        e.printStackTrace();
        return R.fail(builder.toString());
    }
}