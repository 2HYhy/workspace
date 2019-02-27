package com.me.gacl.handler;

import com.me.gacl.domain.R;
import com.me.gacl.exception.JsonException;
import com.me.gacl.exception.PageException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author CH-yfy
 * @date 2018/6/25
 * 自定义异常统一处理
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
    public static final String DEFAULT_ERROR_VIEW= "error";

    @org.springframework.web.bind.annotation.ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public R jsonErrorHandler(JsonException exception) {
        log.error("JsonException:code={},message={}",exception.getCode(),exception.getMessage());
        return R.error(exception);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = PageException.class)
    public ModelAndView pageErrorHandler(PageException exception) {
        log.error("PageException:code={},message={}",exception.getCode(),exception.getMessage());
        ModelAndView view = new ModelAndView();
        view.addObject("message", exception.getMessage());
        view.setViewName(DEFAULT_ERROR_VIEW);
        return view;
    }

}
