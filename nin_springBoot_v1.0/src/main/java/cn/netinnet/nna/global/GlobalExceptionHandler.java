package cn.netinnet.nna.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
/**
 * FileName: GlobalExceptionHandler
 *
 * @version v1.0
 * @Description: 局部异常处理类 —— 用来捕获Controller层异常
 * @author: chen.wb
 * @date: 2017.8.12
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String DEFAULT_ERROR_PATH = "error/debug.jsp";// For Develop
    // private static final String DEFAULT_ERROR_PATH = "error/500.jsp";// For publish

    /**
     * 返回错误页面
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.info("Catch an exception", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", e.getMessage());
        mav.addObject("stackTrace", e.getStackTrace());
        mav.setViewName(DEFAULT_ERROR_PATH);
        return mav;
    }

    /**
     * 自定义异常类，返回json格式
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {MyException.class})
    public ResponseData<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ResponseData<String> info = new ResponseData<>();
        info.setStatus(false);
        info.setMessage(e.getMessage());
        info.setCode(500);
        return info;
    }

}

