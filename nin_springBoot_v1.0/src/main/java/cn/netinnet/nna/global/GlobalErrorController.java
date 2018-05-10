package cn.netinnet.nna.global;

/**
 * FileName: ErrorController
 *
 * @version v1.0
 * @Description: 全局异常处理类
 * @author: chen.wb
 * @date: 2017.8.12
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GlobalErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";
    /**
     * Default Exception
     * @return
     */
    @PostMapping(value=ERROR_PATH)
    public ModelAndView error(){
        return new ModelAndView("error/404.jsp");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * Unauthorized Exception
     * @return
     */
    @PostMapping("/error/401")
    public ModelAndView error401(){
        return new ModelAndView("error/401.jsp");
    }

    /**
     * Not Found Exception
     * @return
     */
    @PostMapping("/error/404")
    public ModelAndView error404(){
        return new ModelAndView("error/404.jsp");
    }

    /**
     * Internal Server Error Exception
     * @return
     */
    @PostMapping("/error/500")
    public ModelAndView error500(){
        return new ModelAndView("error/500.jsp");
    }
}
