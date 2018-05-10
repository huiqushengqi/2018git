package cn.netinnet.nna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: TestSpringSessionController
 *
 * @version ${version}
 * @Description: springSession控制层
 * @author: ${user}
 * @date: ${date} ${time}
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
@RestController
@RequestMapping(value = "")
public class TestSpringSessionController {
    // @RequestMapping(value = "/first", method = RequestMethod.GET)
    @GetMapping(value = "/first")
    public Map<String, Object> firstResp (HttpServletRequest request){
        Map<String, Object> map = new HashMap<String,Object>();
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    // @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    // 这里指定RequestMethod，如果不指定Swagger会把所有RequestMethod都输出，在实际应用中，具体指定请求类型也使接口更为严谨。
    @GetMapping(value = "/sessions")
    public Object sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        return map;
    }
}
