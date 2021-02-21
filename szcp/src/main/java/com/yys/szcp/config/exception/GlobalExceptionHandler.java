package com.yys.szcp.config.exception;

import com.alibaba.fastjson.JSON;
import com.yys.szcp.constant.ExceptionConstant;
import com.yys.szcp.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s ExceptionTyepCode: %s ExceptionTyep: %s reasonString: %s";
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String runtimeExceptionHandler(RuntimeException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.RUNTIME_EXCEPTIONCODE, "RuntimeException", "运行时异常");
    }

    /**
     * 空指针异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.NULLPOINTER_EXCEPTIONCODE, "NullPointerException", "空指针异常");
    }

    /**
     * 类型转换异常
     * @param e
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    public String classCastExceptionHandler(ClassCastException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.CLASSCAST_EXCEPTIONCODE, "ClassCastException", "类型转换异常");
    }

    /**
     * IO异常
     * @param e
     * @return
     */
    @ExceptionHandler(IOException.class)
    public String iOExceptionHandler(IOException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.IO_EXCEPTIONCODE, "IOException", "IO异常");
    }

    /**
     * 未知方法异常
     * @param e
     * @return
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public String noSuchMethodExceptionHandler(NoSuchMethodException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.NOSUCHMETHOD_EXCEPTIONCODE, "NoSuchMethodException", "未知方法异常");
    }

    /**
     * 数组越界异常
     * @param e
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.INDEXOUTOFBOUNDS_EXCEPTIONCODE, "IndexOutOfBoundsException", "数组越界异常");
    }

    /**
     * Http消息不可读异常
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public String requestNotReadable(HttpMessageNotReadableException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.HTTPMESSAGENOTREADABLE_EXCEPTIONCODE, "HttpMessageNotReadableException", "Http消息不可读异常");
    }

    /**
     * 类型不匹配异常
     * @param e
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})
    public String requestTypeMismatch(TypeMismatchException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.TYPEMISMATCH_EXCEPTIONCODE, "TypeMismatchException", "类型不匹配异常");
    }

    /**
     * 缺少ServletRequest参数异常
     * @param e
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public String requestMissingServletRequest(MissingServletRequestParameterException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.MISSINGSERVLETREQUESTPARAMETER_EXCEPTIONCODE, "MissingServletRequestParameterException", "缺少ServletRequest参数异常");
    }

    /**
     * HttpRequest方法不支持异常
     * @param e
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String requestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.HTTPREQUESTMETHODNOTSUPPORTED_EXCEPTIONCODE, "HttpRequestMethodNotSupportedException", "HttpRequest方法不支持异常");
    }

    /**
     * Http媒体类型不可接受异常
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public String requestMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.HTTPMEDIATYPENOTACCEPTABLE_EXCEPTIONCODE, "HttpMediaTypeNotAcceptableException", "Http媒体类型不可接受异常");
    }

    /**
     * 不支持转换异常
     * @param e
     * @return
     */
    @ExceptionHandler({ConversionNotSupportedException.class, })
    public String serverConversionNotSupported(ConversionNotSupportedException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.CONVERSIONNOTSUPPORTED_EXCEPTIONCODE, "ConversionNotSupportedException", "不支持转换异常");
    }

    /**
     * Http消息不可写异常
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotWritableException.class})
    public String request406(HttpMessageNotWritableException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.HTTPMESSAGENOTWRITABLE_EXCEPTIONCODE, "HttpMessageNotWritableException", "Http消息不可写异常");
    }

    /**
     * 栈溢出异常
     * @param e
     * @return
     */
    @ExceptionHandler({StackOverflowError.class})
    public String requestStackOverflow(StackOverflowError e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.STACKOVERFLOW_EXCEPTIONCODE, "StackOverflowError", "栈溢出异常");
    }

    //除数不能为0异常
    @ExceptionHandler({ArithmeticException.class})
    public String arithmeticException(ArithmeticException e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.ARITHMETIC_EXCEPTIONCODE, "ArithmeticException", "除数不能为0异常");
    }


    //其他异常
    @ExceptionHandler({Exception.class})
    public String exception(Exception e) {
        return resultFormat(ExceptionConstant.GLOBAL_EXCEPTIONCODE, e, ExceptionConstant.EXCEPTIONCODE, "Exception", "其他异常");
    }

    private <T extends Throwable> String resultFormat(Integer code, T e, Integer exceptionTyepCode, String exceptionTyep, String reasonString) {
        logger.error(exceptionTyep + ":" + reasonString + ":", e);
        logger.error(String.format(logExceptionFormat, code, e.getMessage(), exceptionTyepCode, exceptionTyep, reasonString));
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(code);
        resultUtil.setMsg(reasonString + ":" + e.getMessage());
        return JSON.toJSONString(resultUtil);
    }

}
