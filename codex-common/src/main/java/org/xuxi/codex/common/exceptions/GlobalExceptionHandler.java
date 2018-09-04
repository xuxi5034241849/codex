package org.xuxi.codex.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.xuxi.codex.common.utils.R;
import org.xuxi.codex.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 自定义异常处理
     *
     * @param req
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = RException.class)
    @ResponseBody
    public R baseExceptionHandler(HttpServletRequest req, RException ex) throws Exception {
        return R.error(ex);
    }

    /**
     * 404 异常处理
     *
     * @param req
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public R noHandlerExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        return R.error(CodeDefined.URL_NOT_FOUND);
    }

    /**
     * 未知异常处理
     *
     * @param req
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R exceptionHandler(HttpServletRequest req, Exception ex) throws Exception {

        logger.error("未知异常:", ex);
        return R.error(CodeDefined.ERROR);
    }

    /**
     * 参数验证异常处理
     *
     * @param req
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    public R parameterExceptionHandler(HttpServletRequest req, Exception ex) {
        List<Map<String, String>> resErrors = new ArrayList<>();
        List<ObjectError> errors = null;
        if (ex instanceof MethodArgumentNotValidException) {
            errors = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
        } else if (ex instanceof BindException) {
            errors = ((BindException) ex).getBindingResult().getAllErrors();
        }

        for (ObjectError error : errors) {
            Map<String, String> result = new HashMap<>();
            result.put("message", "未知的参数错误");

            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                String messageFormat = fieldError.getDefaultMessage();
                String errorMessage = formatValidMessage(messageFormat, fieldError);

                result.put("field", fieldError.getField());
                result.put("message", errorMessage);

                String value = String.valueOf(fieldError.getRejectedValue());
                if (!StringUtils.isNulltoString(value)) {
                    result.put("value", value);
                }
            } else {
                result.put("message", error.getDefaultMessage());
            }
            resErrors.add(result);
        }


        return R.error(CodeDefined.ERROR_PARAMETER).put("data", resErrors);
    }

    private String formatValidMessage(String format, FieldError error) {

        return format;
    }

    /**
     * 请求参数语法错误
     *
     * @param req
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public R httpMessageNotReadableExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
        logger.error("json语法错误异常:", ex);
        return R.error(CodeDefined.ERROR_SYNTAX);
    }


}
